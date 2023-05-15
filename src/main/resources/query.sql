-- TABLE TEST
create sequence SEQ_TEST
    cache 10
/

create table TEST
(
    ID   NUMBER(5) generated as identity
        constraint TEST_PK
            primary key,
    CODE VARCHAR2(50),
    NAME VARCHAR2(100),
    STATUS          NUMBER(2),
    CREATE_USER     VARCHAR2(50),
    CREATE_DATETIME DATE,
    UPDATE_USER     VARCHAR2(50),
    UPDATE_DATETIME DATE
)
/

create unique index TEST_CODE_UINDEX
    on TEST (CODE)
/


-- TABLE USERS
create sequence SEQ_USER_ID
    cache 10
/

create table USERS
(
    USER_ID         NUMBER(5) generated as identity
        constraint USERS_PK
            primary key,
    USER_NAME       VARCHAR2(50),
    PASSWORD        VARCHAR2(100),
    ROLE            VARCHAR2(10),
    USER_INFO_ID    NUMBER(5),
    STATUS          NUMBER(2),
    CREATE_USER     VARCHAR2(50),
    CREATE_DATETIME DATE,
    UPDATE_USER     VARCHAR2(50),
    UPDATE_DATETIME DATE
)
/

create unique index USERS_USER_INFO_ID_UINDEX
    on USERS (USER_INFO_ID)
/

create unique index USERS_USER_NAME_UINDEX
    on USERS (USER_NAME)
/



-- TABLE USER_INFO
create sequence SEQ_USER_INFO_ID
    cache 10
/

create table USER_INFO
(
    USER_INFO_ID    NUMBER(5) generated as identity
        constraint USER_INFO_PK
            primary key,
    FULL_NAME       VARCHAR2(50),
    PHONE_NUMBER    VARCHAR2(15),
    EMAIL           VARCHAR2(50),
    ADDRESS         VARCHAR2(100),
    AVATAR_URL      VARCHAR2(200),
    STATUS          NUMBER(2),
    CREATE_USER     VARCHAR2(50),
    CREATE_DATETIME DATE,
    UPDATE_USER     VARCHAR2(50),
    UPDATE_DATETIME DATE
)
/

create unique index USER_INFO_EMAIL_UINDEX
    on USER_INFO (EMAIL)
/

create unique index USER_INFO_PHONE_NUMBER_UINDEX
    on USER_INFO (PHONE_NUMBER)
/



-- TABLE BOOK
create sequence SEQ_BOOK_ID
    cache 10
/

create table BOOK
(
    BOOK_ID         NUMBER(5) generated as identity
        constraint BOOK_PK
            primary key,
    TITLE           VARCHAR2(50),
    AUTHOR          VARCHAR2(50),
    CATEGORY_ID     NUMBER(5),
    RELEASE_DATE    DATE,
    PRICE           NUMBER(15),
    PAGE_NUMBER     NUMBER(10),
    QUANTITY_SOlD   NUMBER(10),
    DESCRIPTION     VARCHAR2(500),
    COVER_IMAGE_URL VARCHAR2(200),
    STATUS          NUMBER(2),
    CREATE_USER     VARCHAR2(50),
    CREATE_DATETIME DATE,
    UPDATE_USER     VARCHAR2(50),
    UPDATE_DATETIME DATE
)
/



-- TABLE CATEGORY
create sequence SEQ_CATEGORY_ID
    cache 10
/

create table CATEGORY
(
    CATEGORY_ID     NUMBER(5) generated as identity
        constraint CATEGORY_PK
            primary key,
    CODE            VARCHAR2(50),
    NAME            VARCHAR2(50),
    DESCRIPTION     VARCHAR2(500),
    STATUS          NUMBER(2),
    CREATE_USER     VARCHAR2(50),
    CREATE_DATETIME DATE,
    UPDATE_USER     VARCHAR2(50),
    UPDATE_DATETIME DATE
)
/

create unique index CATEGORY_CODE_UINDEX
    on CATEGORY (CODE)
/



-- TABLE COMMENT
create sequence SEQ_COMMENT_ID
    cache 10
/

create table "COMMENT"
(
    COMMENT_ID      NUMBER(5) generated as identity
        constraint COMMENT_PK
            primary key,
    BOOK_ID         NUMBER(5),
    USER_ID         NUMBER(5),
    CONTENT         VARCHAR2(1000),
    RATING          NUMBER(2),
    STATUS          NUMBER(2),
    CREATE_USER     VARCHAR2(50),
    CREATE_DATETIME DATE,
    UPDATE_USER     VARCHAR2(50),
    UPDATE_DATETIME DATE
)
/



-- TABLE BILL
create sequence SEQ_BILL_ID
    cache 10
/

create table BILL
(
    BILL_ID               NUMBER(5) generated as identity
        constraint BILL_PK
            primary key,
    BOOK_ID               NUMBER(5),
    USER_ID               NUMBER(5),
    QUANTITY              NUMBER(10),
    TOTAL_PAYMENT         NUMBER,
    DELIVERY_ADDRESS      VARCHAR2(100),
    delivery_phone_number VARCHAR2(15),
    STATUS          NUMBER(2),
    CREATE_USER     VARCHAR2(50),
    CREATE_DATETIME DATE,
    UPDATE_USER     VARCHAR2(50),
    UPDATE_DATETIME DATE
)
/