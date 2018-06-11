package com.appricots.intq;

public class NameOf {

	public static final String NOTHING           = "nothing";
	public static final Integer MAX_POSSIBLE     = Integer.MAX_VALUE;
	public static final String COOKIE_4_IDENTITY = "maria";

	public class Table {
        public static final String AUTHORITY = "intq_authority";
        public static final String CATEGORY = "intq_category";
        public static final String LANGUAGE = "intq_language";
        public static final String USER = "intq_user";
        public static final String CREDENTIALS = "intq_user_credentials";
        public static final String QUESTION = "intq_question";
        public static final String DIFFICULTY = "intq_difficulty";
        public static final String USER_SESSION = "intq_user_session";
        public static final String LINK_QUESTION_CATEGORY = "intq_m2m_question__category";
        public static final String LINK_USER_AUTHORITY = "intq_o2m_user__authority";
    }

    public class Column {
        public static final String LANGUAGE_ID = "language_id";
        public static final String LANGUAGE_ALIAS = "language_alias";
        public static final String DIFFICULTY_ID = "difficulty_id";
        public static final String DIFFICULTY_ALIAS = "difficulty_alias";
        public static final String CATEGORY_ID = "category_id";
        public static final String CATEGORY_ALIAS = "category_alias";
        public static final String QUESTION_ID = "question_id";
        public static final String QUESTION_CONTENT = "question_content";
        public static final String QUESTION_ANSWER = "answer_Content";
        public static final String QUESTION_PLUS = "plus_count";
        public static final String QUESTION_MINUS = "minus_count";
        public static final String QUESTION_ATTACH_URL = "attachment_url";
        public static final String QUESTION_STATUS = "question_status";
        public static final String USER_CREDS_ID = "ucID";
        public static final String USER_CREDS_LOGIN = "login";
        public static final String USER_CREDS_PASS = "passHash";
        public static final String USESSION_ID = "usID";
        public static final String USESSION_SHIFT = "shift";
        public static final String USESSION_IDENTIIY_COOKIE = "identCookie";
        public static final String USER_ID = "uid";
        public static final String USER_USERNAME = "uUsername";
        public static final String USER_FIRST_NAME = "uFirstName";
        public static final String USER_LAST_NAME = "uLastName";
        public static final String USER_EMAIL = "uEmail";
        public static final String USER_AGE = "uAge";
        public static final String USER_ACTIVENESS = "uActiveness";
        public static final String USER_AUTHORITIES = "uAuthority";
        public static final String AUTHORITY_ID = "uAuthorityId";
        public static final String AUTHORITY_NAME = "uAuthorityName";
        public static final String AUTHORITY_COMMENT = "uAuthorityComment";
    }

    public static class ModelAttributeKey {
        public static final String ERROR_MSG = "error_msg";
        public static final String SUCCESS_MSG = "success_msg";
        public static final String USERNAME = "username";
        public static final String CATEGORIES = "categories";
        public static final String LANGUAGES = "languages";
        public static final String DIFFICULTIES = "difficulties";
        public static final String QUESTION_SELECTOR = "questionSelector";
        public static final String NO_MORE_QUESTIONS_FLAG = "no_more_questions";
    }


}
