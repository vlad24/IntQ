INSERT INTO intq_authority VALUES
  (0,  'User which is considered to be an admin',     'ROLE_ADMIN_ACCESS'),
  (1,  'User which can ban users',                    'ROLE_ADMIN_ACCOUNT_BAN'),
  (250, 'User which is considered to be a moderator', 'ROLE_MODERATOR_ACCESS'),
  (251, 'User which is considered to be a moderator', 'ROLE_MODERATOR_QUESTION_ACCEPT'),
  (252, 'User which is considered to be a moderator', 'ROLE_MODERATOR_QUESTION_REJECT'),
  (252, 'User which is considered to be a moderator', 'ROLE_MODERATOR_QUESTION_MODIFY'),
  (500, 'User which have read/write privileges',      'ROLE_USER_ACCESS'),
  (501, 'User which have a right to rate questions',  'ROLE_USER_RATE'),
  (750, 'User which have only read privileges',       'ROLE_GUEST')
;

INSERT INTO intq_user_credentials VALUES
  (0, 'admin',     'admin'),
  (1, 'moderator', 'moderator'),
  (2, 'user',      'user'),
  (3, 'guest',     'guest')
;

INSERT INTO intq_user VALUES
  (0, 0, 20, 'appricots@gmail.com',  'Appricots',  FALSE, FALSE, 'Administration', 0, NULL),
  (1, 0, 20, 'moderator1@gmail.com', 'Appricots',  FALSE, FALSE, 'Moderator_1',    1, NULL),
  (2, 0, 20, 'intq_john1@gmail.com', 'John',       FALSE, FALSE, 'Smith',          2, NULL),
  (3, 0, 20, 'intq_polina@gmail.com','Polina',     FALSE, FALSE, 'White',          3, NULL)
;


INSERT INTO intq_m2m_user__authority VALUES
  (0,   0),
  (250, 1),
  (500, 2),
  (750, 3)
;

INSERT INTO intq_difficulty VALUES
  (0, 'Easy'),
  (1, 'Middle'),
  (2, 'Hard')
;


INSERT INTO intq_language VALUES
  (0, 'English'),
  (1, 'Russian')
;


INSERT INTO intq_category VALUES
  (0, 'Java'),
  (1, 'Python'),
  (2, 'Testing'),
  (3, 'SQL')
;

INSERT INTO intq_question VALUES
  (0, 'Answer1', '', 0, 0, 'What is Q1?', 'ACCEPTED', 0, 0),
  (1, 'Answer2', '', 0, 0, 'What is Q2?', 'ACCEPTED', 1, 0),
  (2, 'Answer3', '', 0, 0, 'What is Q3?', 'ACCEPTED', 2, 0),
  (3, 'Answer4', '', 0, 0, 'What is Q4?', 'ACCEPTED', 0, 0),
  (4, 'Answer5', '', 0, 0, 'What is Q5?', 'ACCEPTED', 1, 0),
  (5, 'Answer6', '', 0, 0, 'What is Q6?', 'ACCEPTED', 1, 0)
;

INSERT INTO intq_m2m_question__category VALUES
  (0, 0),
  (0, 2),
  (1, 1),
  (1, 3),
  (2, 2),
  (3, 3),
  (3, 1),
  (4, 1),
  (4, 3),
  (5, 1)
;

-- select
--   Q.question_id,
--   Q.question_content,
--   string_agg(CC.category_alias, ',')
-- from
--   intq_question Q
--   join intq_m2m_question__category QC on Q.question_id = QC.question_id
--   join intq_category CC on CC.category_id= QC.category_id
-- group by
--   Q.question_id,
--   Q.question_content
-- order by
--   Q.question_id