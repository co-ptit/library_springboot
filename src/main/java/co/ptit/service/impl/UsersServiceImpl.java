package co.ptit.service.impl;

import co.ptit.domain.dto.request.LoginRequestDto;
import co.ptit.domain.dto.request.RegisterRequestDto;
import co.ptit.domain.entity.UserInfo;
import co.ptit.domain.entity.Users;
import co.ptit.exception.ValidateCommonException;
import co.ptit.repo.UserInfoRepository;
import co.ptit.repo.UsersRepository;
import co.ptit.service.UsersService;
import co.ptit.utils.Constant;
import co.ptit.utils.FileUtil;
import co.ptit.utils.InputValidateUtil;
import co.ptit.utils.MsgUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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
}
