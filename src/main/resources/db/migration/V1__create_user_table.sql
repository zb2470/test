CREATE TABLE user (
  id         BIGINT PRIMARY KEY    AUTO_INCREMENT,
  username   VARCHAR(32)  NOT NULL UNIQUE,
  created_at TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3)
);