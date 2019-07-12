-- Этот файл сам Spring Boot будет запускать при старте приложения

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    id                      INTEGER PRIMARY KEY AUTO_INCREMENT,        -- SQL Dialects
    username                VARCHAR(255) UNIQUE NOT NULL,
    password                VARCHAR(255)        NOT NULL,
    enabled                 BOOLEAN             NOT NULL DEFAULT TRUE, -- включен ли аккаунт
    account_non_expired     BOOLEAN             NOT NULL DEFAULT TRUE, -- время действия аккаунта не истекло
    account_non_locked      BOOLEAN             NOT NULL DEFAULT TRUE, -- аккаунт не заблокировать
    credentials_non_expired BOOLEAN             NOT NULL DEFAULT TRUE  -- не истекло ли время действия пароля
);

DROP TABLE IF EXISTS authorities;
CREATE TABLE IF NOT EXISTS authorities
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_id   INTEGER      NOT NULL REFERENCES users,
    authority VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS notes;
CREATE TABLE IF NOT EXISTS notes
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    author_id INTEGER      NOT NULL REFERENCES users,
    name      VARCHAR(255) NOT NULL,
    content   TEXT         NOT NULL,
    likes     INTEGER      NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS medias;
CREATE TABLE IF NOT EXISTS medias
(
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_id INTEGER      NOT NULL REFERENCES users,
    name    VARCHAR(255) NOT NULL,
    path    VARCHAR(255) NOT NULL,                -- если может и не быть, то убираете NOT NULL
    type    VARCHAR(255) NOT NULL DEFAULT 'IMAGE' -- 'AUDIO', 'VIDEO"
);

DROP TABLE IF EXISTS comments;
CREATE TABLE IF NOT EXISTS comments
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_id  INTEGER NOT NULL REFERENCES users,
    media_id INTEGER NOT NULL REFERENCES medias,
    content  TEXT    NOT NULL
);

CREATE TABLE competitions (
                       id INTEGER PRIMARY KEY AUTO_INCREMENT,
                       name TEXT,
                       type VARCHAR(255) NOT NULL,
                       media TEXT -- путь к файлику
);