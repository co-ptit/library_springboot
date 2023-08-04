package co.ptit.service.impl;

import co.ptit.domain.dto.request.LoginRequestDto;
import co.ptit.domain.dto.request.RegisterRequestDto;
import co.ptit.domain.dto.response.UsersResponseDto;
import co.ptit.domain.entity.UserInfo;
import co.ptit.domain.entity.Users;
import co.ptit.exception.ValidateCommonException;
import co.ptit.repo.UserInfoRepository;
import co.ptit.repo.UsersRepository;
import co.ptit.service.ExcelService;
import co.ptit.service.UsersService;
import co.ptit.utils.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static co.ptit.utils.Constant.*;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserInfoRepository userInfoRepository;
    private final ExcelService excelService;

    @Value("${templates.excel.file-name.export-users}")
    private String exportSmartBankFile;

    @Override
    public boolean register(RegisterRequestDto request) {
        String userName = request.getUserName();
        String password = request.getPassword();
        String fullName = request.getFullName();
        String phoneNumber = request.getPhoneNumber();
        String email = request.getEmail();
        String address = request.getAddress();

        //validate input
        InputValidateUtil.validateNotNull("userName", userName);
        InputValidateUtil.validateNotNull("password", password);
        InputValidateUtil.validateNotNull("fullName", fullName);
        InputValidateUtil.validatePhoneNumber(phoneNumber);
        InputValidateUtil.validateEmail(email);
        InputValidateUtil.validateNotNull("address", address);

        //check exists
        usersRepository.findByUserNameAndStatus(userName, Constant.STATUS.ACTIVE.value())
                .ifPresent(u -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("users.exists", "userName", userName));
                });
        userInfoRepository.findByPhoneNumberAndStatus(phoneNumber, Constant.STATUS.ACTIVE.value())
                .ifPresent(u -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("users.exists", "phoneNumber", phoneNumber));
                });
        userInfoRepository.findByEmailAndStatus(email, Constant.STATUS.ACTIVE.value())
                .ifPresent(u -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("users.exists", "email", email));
                });

        //create Users info
        UserInfo userInfo = UserInfo.builder()
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .email(email)
                .address(address)
                .status(Constant.STATUS.ACTIVE.value())
                .createDatetime(LocalDateTime.now())
                .build();
        userInfo = userInfoRepository.save(userInfo);

        //create Users
        Users user = Users.builder()
                .userName(userName)
                .password(BCrypt.hashpw(password, BCrypt.gensalt()))
                .role(Constant.ROLE.USER.value())
                .userInfoId(userInfo.getUserInfoId())
                .status(Constant.STATUS.ACTIVE.value())
                .createDatetime(LocalDateTime.now())
                .build();
        usersRepository.save(user);
        return Boolean.TRUE;
    }

    @Override
    public Object login(LoginRequestDto request) {
        String userName = request.getUserName();
        String password = request.getPassword();

        //validate input
        InputValidateUtil.validateNotNull("userName", userName);
        InputValidateUtil.validateNotNull("password", password);

        Users user = usersRepository.findByUserNameAndStatus(userName, Constant.STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(MsgUtil.getMessage("login.error"));
                });

        if (BCrypt.checkpw(password, user.getPassword())) {
            return Boolean.TRUE;
        } else {
            throw new IllegalArgumentException(MsgUtil.getMessage("login.error"));
        }
    }

    @Override
    public boolean updateAvatar(MultipartFile data, Long userId) {
        //validate input
        if (userId <= 0) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "userId"));
        }

        //check exists
        Users users = usersRepository.findByUserIdAndStatus(userId, Constant.STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("users.not.exists", "usedId", userId));
                });
        UserInfo userInfo = userInfoRepository.findByUserInfoIdAndStatus(users.getUserInfoId(), Constant.STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new ValidateCommonException(
                            MsgUtil.getMessage("users.info.not.exists", "usedInfoId", users.getUserInfoId()));
                });

        String url = FileUtil.upload(data, Constant.AVATAR_PATH);
        userInfo.setAvatarUrl(url);
        userInfo.setUpdateDatetime(LocalDateTime.now());
        userInfoRepository.save(userInfo);
        return Boolean.TRUE;
    }

    @Override
    public void export(HttpServletResponse response) {
        List<Users> usersList = usersRepository.findAllByStatus(Constant.STATUS.ACTIVE.value());
        if (CollectionUtils.isEmpty(usersList)) {
            return;
        }

        List<UsersResponseDto>  result= new ArrayList<>();
        usersList.forEach(u -> {
            UserInfo userInfo = userInfoRepository
                    .findByUserInfoIdAndStatus(u.getUserInfoId(), Constant.STATUS.ACTIVE.value()).orElse(null);

            UsersResponseDto usersDto = new UsersResponseDto();
            BeanUtils.copyProperties(u, usersDto);
            try {
                BeanUtils.copyProperties(userInfo, usersDto);
            } catch (Exception e) {
                log.error("User error not user info: userId = {}", u.getUserId());
            }

            result.add(usersDto);
        });

        try {
            InputStream inputStream = excelService.loadExcelTemplateFromResource(exportSmartBankFile);
            if (Objects.isNull(inputStream)) {
                return;
            }

            String resultFile = String.format(USERS_EXPORT_FILE_NAME, DateUtil.formatStringLongTimestamp(new Date())) + StringPool.XLSX;
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT_FILE + resultFile);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            OutputStream os = response.getOutputStream();
            Context context = new Context();
            context.putVar("items", result);
            context.putVar("currentTime", DateUtil.formatStringLongDate(new Date()));

            JxlsHelper.getInstance().processTemplate(inputStream, os, context);
            response.flushBuffer();
        } catch (Exception e) {
            log.error("SmartBank export error: {0}", e);
        }
    }

    @Override
    public void downloadTemplate(HttpServletResponse response) {

    }

    @Override
    public void importUsers(MultipartFile file) {

    }
}
