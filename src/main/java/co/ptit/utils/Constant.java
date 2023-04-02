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

    interface Sequence{
        String SEQ_USERS = "SEQ_USER_ID";
        String SEQ_USER_INFO = "SEQ_USER_INFO_ID";
        String SEQ_BOOK = "SEQ_BOOK_ID";
        String SEQ_CATEGORY = "SEQ_CATEGORY_ID";
        String SEQ_COMMENT = "SEQ_COMMENT_ID";
        String SEQ_BILL = "SEQ_BILL_ID";
        String SEQ_TEST = "SEQ_TEST";
    }

    Integer HTTP_ERROR_STATUS = HttpStatus.INTERNAL_SERVER_ERROR.value();
}
