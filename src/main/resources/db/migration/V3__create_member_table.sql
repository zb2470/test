CREATE TABLE member (
  id         BIGINT PRIMARY KEY    AUTO_INCREMENT,
  user_id   VARCHAR(32)  NOT NULL UNIQUE,
  role_id   VARCHAR(32)  NOT NULL UNIQUE,
  created_at TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3)
);