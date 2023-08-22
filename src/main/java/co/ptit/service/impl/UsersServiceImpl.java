package co.ptit.service.impl;

import co.ptit.domain.dto.request.LoginRequestDto;
import co.ptit.domain.dto.request.RegisterRequestDto;
import co.ptit.domain.dto.response.UsersResponseDto;
import co.ptit.domain.entity.UserInfo;
import co.ptit.domain.entity.Users;
import co.ptit.domain.json.ResponseJson;
import co.ptit.domain.json.UserJson;
import co.ptit.exception.ValidateCommonException;
import co.ptit.repo.UserInfoRepository;
import co.ptit.repo.UsersRepository;
import co.ptit.service.ExcelService;
import co.ptit.service.UsersService;
import co.ptit.utils.*;
import com.google.common.io.Files;
import com.google.gson.Gson;
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
import java.time.LocalTime;
import java.util.*;

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
    private String exportUsersFile;

    @Value("${templates.excel.file-name.export-json}")
    private String exportJson;

    @Value("${templates.excel.file-name.import-users}")
    private String importUsersFile;

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
        usersRepository.findByUserNameAndStatus(userName, STATUS.ACTIVE.value())
                .ifPresent(u -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("users.exists", "userName", userName));
                });
        userInfoRepository.findByPhoneNumberAndStatus(phoneNumber, STATUS.ACTIVE.value())
                .ifPresent(u -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("users.exists", "phoneNumber", phoneNumber));
                });
        userInfoRepository.findByEmailAndStatus(email, STATUS.ACTIVE.value())
                .ifPresent(u -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("users.exists", "email", email));
                });

        //create Users info
        UserInfo userInfo = UserInfo.builder()
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .email(email)
                .address(address)
                .status(STATUS.ACTIVE.value())
                .createDatetime(LocalDateTime.now())
                .build();
        userInfo = userInfoRepository.save(userInfo);

        //create Users
        Users user = Users.builder()
                .userName(userName)
                .password(BCrypt.hashpw(password, BCrypt.gensalt()))
                .role(ROLE.USER.value())
                .userInfoId(userInfo.getUserInfoId())
                .status(STATUS.ACTIVE.value())
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

        Users user = usersRepository.findByUserNameAndStatus(userName, STATUS.ACTIVE.value())
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
        Users users = usersRepository.findByUserIdAndStatus(userId, STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("users.not.exists", "usedId", userId));
                });
        UserInfo userInfo = userInfoRepository.findByUserInfoIdAndStatus(users.getUserInfoId(), STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new ValidateCommonException(
                            MsgUtil.getMessage("users.info.not.exists", "usedInfoId", users.getUserInfoId()));
                });

        String url = FileUtil.upload(data, AVATAR_PATH);
        userInfo.setAvatarUrl(url);
        userInfo.setUpdateDatetime(LocalDateTime.now());
        userInfoRepository.save(userInfo);
        return Boolean.TRUE;
    }

    @Override
    public void export(HttpServletResponse response) {
        List<Users> usersList = usersRepository.findAllByStatus(STATUS.ACTIVE.value());
        if (CollectionUtils.isEmpty(usersList)) {
            return;
        }

        List<UsersResponseDto> result = new ArrayList<>();
        usersList.forEach(u -> {
            UserInfo userInfo = userInfoRepository
                    .findByUserInfoIdAndStatus(u.getUserInfoId(), STATUS.ACTIVE.value()).orElse(null);

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
            InputStream inputStream = excelService.loadExcelTemplateFromResource(exportUsersFile);
            if (Objects.isNull(inputStream)) {
                return;
            }

            String resultFile = String.format(USERS_EXPORT_FILE_NAME, DateUtil.formatStringLongTimestamp(new Date())) + StringPool.XLSX;
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, FileUtil.ATTACHMENT_FILE + resultFile);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            OutputStream os = response.getOutputStream();
            Context context = new Context();
            context.putVar("items", result);
            context.putVar("currentTime", DateUtil.formatStringLongDate(new Date()));

            JxlsHelper.getInstance().processTemplate(inputStream, os, context);
            response.flushBuffer();
        } catch (Exception e) {
            log.error("Users export error: {0}", e);
        }
    }

    @Override
    public void downloadTemplate(HttpServletResponse response) {
        try {
            InputStream inputStream = this.excelService.loadExcelTemplateFromResource(importUsersFile);
            if (Objects.isNull(inputStream)) {
                return;
            }
            String resultFile = USERS_IMPORT_TEMP_FILE_NAME + StringPool.XLSX;
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, FileUtil.ATTACHMENT_FILE + resultFile);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            OutputStream os = response.getOutputStream();
            os.write(inputStream.readAllBytes());
            response.flushBuffer();
        } catch (Exception e) {
            log.error("Users download temp import error: {0}", e);
        }
    }

    @Override
    public Boolean importUsers(MultipartFile file) {
        //validate
        String fileExtension = Files.getFileExtension(FileUtil.sanitizeFilename(file.getOriginalFilename()));
        if (!List.of(FileUtil.XLS, FileUtil.XLSX).contains(fileExtension)) {
            throw new ValidateCommonException(MsgUtil.getMessage("invalid.file.format"));
        }

        //read data from file
        Set<String> userNameList = usersRepository.findAllUserName();
        Set<String> phoneNumberList = userInfoRepository.findAllPhoneNumber();
        Set<String> emailList = userInfoRepository.findAllEmail();
        List<UsersResponseDto> usersDtoList = excelService.readUsersDataFromExcelFile(file, phoneNumberList, emailList, userNameList);
        if (CollectionUtils.isEmpty(usersDtoList)) {
            throw new IllegalArgumentException(MsgUtil.getMessage("import.users.fail"));
        }

        // insert data to database
        usersDtoList.forEach(u -> {
            UserInfo userInfo = userInfoRepository.save(UserInfo.builder()
                    .fullName(u.getFullName())
                    .phoneNumber(u.getPhoneNumber())
                    .email(u.getEmail())
                    .address(u.getAddress())
                    .status(STATUS.ACTIVE.value())
                    .createDatetime(LocalDateTime.of(u.getCreateDate(), LocalTime.now()))
                    .build());

            usersRepository.save(Users.builder()
                    .userName(u.getUserName())
                    .password(BCrypt.hashpw(DEFAULT_PASSWORD, BCrypt.gensalt()))
                    .role(ROLE.USER.value())
                    .userInfoId(userInfo.getUserInfoId())
                    .status(STATUS.ACTIVE.value())
                    .createDatetime(LocalDateTime.of(u.getCreateDate(), LocalTime.now()))
                    .build());
        });

        return Boolean.TRUE;
    }

    @Override
    public void loadFileJson(MultipartFile file, HttpServletResponse response) {
        try {
            ResponseJson data = new Gson().fromJson(new String(file.getBytes()), ResponseJson.class);
            var items = data.getData().getPayload().getData();
            items.sort(Comparator.comparingInt(UserJson::getUserId));
            InputStream inputStream = excelService.loadExcelTemplateFromResource(exportJson);

            String fileName = DateUtil.formatStringLongTimestamp(new Date()) + StringPool.XLSX;
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, FileUtil.ATTACHMENT_FILE + fileName);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            OutputStream os = response.getOutputStream();
            Context context = new Context();
            context.putVar("items", items);
            context.putVar("currentTime", DateUtil.formatStringLongDate(new Date()));

            JxlsHelper.getInstance().processTemplate(inputStream, os, context);
            response.flushBuffer();
        } catch (Exception e) {
            log.error("Load file failed: {}", e.getMessage());
        }
    }
}
