package co.ptit.utils;

import org.springframework.http.HttpStatus;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

public interface Constant {

    enum STATUS {
        ACTIVE(1), INACTIVE(0);

        final int status;

        public int value() {
            return status;
        }

        STATUS(int status) {
            this.status = status;
        }
    }

    enum ROLE {
        USER("USER"), ADMIN("ADMIN");

        final String role;

        public String value() {
            return role;
        }

        ROLE(String role) {
            this.role = role;
        }
    }

    interface Sequence {
        String SEQ_USERS = "SEQ_USER_ID";
        String SEQ_USER_INFO = "SEQ_USER_INFO_ID";
        String SEQ_BOOK = "SEQ_BOOK_ID";
        String SEQ_CATEGORY = "SEQ_CATEGORY_ID";
        String SEQ_COMMENT = "SEQ_COMMENT_ID";
        String SEQ_BILL = "SEQ_BILL_ID";
        String SEQ_TEST = "SEQ_TEST";
    }

    interface Regex {
        String PHONE_NUMBER = "((\\+84|0)(3|5|7|8|9))+([0-9]{8})";
        String EMAIL = "^[a-zA-Z0-9-_]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    }

    Integer HTTP_ERROR_STATUS = HttpStatus.INTERNAL_SERVER_ERROR.value();
    String ROOT_PATH = "home/";
    String AVATAR_PATH = "avatar/";
    String BOOK_PATH = "book/";

}
