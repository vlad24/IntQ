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
        public static final String LINK_USER_AUTHORITY = "intq_m2m_user__authority";

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
        public static final String USER_CREDS_ID = "user_credentials_id";
        public static final String USER_CREDS_LOGIN = "user_credentials_login";
        public static final String USER_CREDS_PASS = "user_credentials_pass_hash";
        public static final String USESSION_ID = "user_session_id";
        public static final String USESSION_SHIFT = "user_session_shift";
        public static final String USESSION_IDENTIIY_COOKIE = "user_session_identity_cookie";
        public static final String USER_ID = "user_id";
        public static final String USER_FIRST_NAME = "user_first_name";
        public static final String USER_LAST_NAME = "user_last_name";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_AGE = "user_age";
        public static final String USER_ACTIVENESS = "user_activeness";
        public static final String USER_IS_BLOCKED = "user_is_blocked";
        public static final String USER_IS_DELETED = "user_is_deleted";
        public static final String AUTHORITY_ID = "authority_id";
        public static final String AUTHORITY_NAME = "authority_name";
        public static final String AUTHORITY_COMMENT = "authority_comment";
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


    public class Profile {
        public static final String TEST = "TEST";
        public static final String PRODUCTION = "PRODUCTION";
    }
}
