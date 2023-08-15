package co.ptit.domain.json;

import lombok.*;

/**
 * project: library_springboot
 * author:  HieuDM
 * date:    8/15/2023
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJson {

    String fullName;
    String userName;
    String keycloakUserId;
    Integer userId;
    Integer userInfoId;
    Integer partyId;
    String phoneNumber;
    String email;
    String userCode;
    String birthDay;
    Integer gender;
    String taxCode;
    String identityNo;
    String identityTypeName;
    String issueDate;
    String expireDate;
    String issuePlace;
    Integer status;
    Integer bankAccountId;
    String bankAccountNo;
    String bankAccountName;
    Integer rmInfoId;
    String rmFullName;
    String rmPhoneNumber;
    String documentNumber;
    String createDatetime;
    String createUser;
    String signDatetime;
    String startDatetime;
    String endDatetime;


}
