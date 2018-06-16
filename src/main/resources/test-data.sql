INSERT INTO intq_authority VALUES
  (0,  'User which have all privileges',        'ROLE_ADMIN'),
  (10, 'User which have moderating privileges', 'ROLE_MODERATOR'),
  (20, 'User which have read/write privileges', 'ROLE_USER'),
  (30, 'User which have only read privileges',  'ROLE_GUEST')
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
  (0,  0),
  (10, 1),
  (20, 2),
  (20, 3)
;
