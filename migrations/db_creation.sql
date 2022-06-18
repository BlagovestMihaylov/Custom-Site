CREATE TABLE IF NOT EXISTS user
(
    id                BIGINT UNSIGNED AUTO_INCREMENT
        PRIMARY KEY,
    username          VARCHAR(255)                                                                             NOT NULL,
    email             VARCHAR(255)                                                                             NOT NULL,
    password          VARCHAR(255)                                                                             NOT NULL,
    phone_number      VARCHAR(15)                                                                              NOT NULL,
    registration_date DATE         DEFAULT CURRENT_TIMESTAMP()                                                 NULL,
    image_url         VARCHAR(255) DEFAULT 'https://cdn.marica.bg/images/marica.bg/857/991-ratio-preslava.jpg' NOT NULL,
    CONSTRAINT email
        UNIQUE (email),
    CONSTRAINT phone_number
        UNIQUE (phone_number),
    CONSTRAINT username
        UNIQUE (username),
    CONSTRAINT email_validation
        CHECK (`email` LIKE '%___@%.%'),
    CONSTRAINT password
        CHECK (OCTET_LENGTH(`password`) > 5)
);

CREATE TABLE IF NOT EXISTS follow
(
    user_id1    BIGINT UNSIGNED NOT NULL,
    CONSTRAINT fk_user_id_fiend1 FOREIGN KEY (user_id1) REFERENCES user (id),
    user_id2    BIGINT UNSIGNED NOT NULL,
    CONSTRAINT fk_user_id_fiend2 FOREIGN KEY (user_id2) REFERENCES user (id),
    PRIMARY KEY (user_id1, user_id2),
    follow_date DATETIME DEFAULT CURRENT_TIMESTAMP()
);



CREATE TABLE IF NOT EXISTS post
(
    id        SERIAL PRIMARY KEY,
    title     VARCHAR(255),
    content   TEXT,
    user_id   BIGINT UNSIGNED NOT NULL,
    CONSTRAINT fk_user_id_post FOREIGN KEY (user_id) REFERENCES user (id),
    views     BIGINT   DEFAULT 0,
    votes     BIGINT   DEFAULT 0,
    post_date DATETIME DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE IF NOT EXISTS comment
(
    id           SERIAL PRIMARY KEY,
    user_id      BIGINT UNSIGNED NOT NULL,
    CONSTRAINT fk_user_id_comment FOREIGN KEY (user_id) REFERENCES user (id),
    post_id      BIGINT UNSIGNED DEFAULT NULL,
    CONSTRAINT fk_post_id_comment FOREIGN KEY (post_id) REFERENCES post (id),
    comment_id   BIGINT UNSIGNED DEFAULT NULL,
    CONSTRAINT fk_comment_id_comment FOREIGN KEY (comment_id) REFERENCES comment (id),
    content      TEXT,
    votes        BIGINT          DEFAULT 0,
    comment_date DATETIME        DEFAULT CURRENT_TIMESTAMP(),
    CONSTRAINT ck_comment_post_checker
        CHECK (IF(`post_id` IS NULL, 0, 1) + IF(`comment_id` IS NULL, 0, 1) = 1)
);

CREATE TABLE IF NOT EXISTS report_post
(
    id          SERIAL PRIMARY KEY,
    user_id     BIGINT UNSIGNED NOT NULL,
    CONSTRAINT fk_user_id_report_post FOREIGN KEY (user_id) REFERENCES user (id),
    reported    BIGINT UNSIGNED,
    CONSTRAINT fk_post_id_report FOREIGN KEY (reported) REFERENCES post (id),
    content     TEXT,
    report_date DATETIME DEFAULT CURRENT_TIMESTAMP()
);
#(`site`.`report_comment`, CONSTRAINT `fk_comment_id_report` FOREIGN KEY (`reported`) REFERENCES `comment` (`id`))
CREATE TABLE IF NOT EXISTS report_comment
(
    id          SERIAL PRIMARY KEY,
    user_id     BIGINT UNSIGNED NOT NULL,
    CONSTRAINT fk_user_id_report_comment FOREIGN KEY (user_id) REFERENCES user (id),
    reported    BIGINT UNSIGNED,
    CONSTRAINT fk_comment_id_report FOREIGN KEY (reported) REFERENCES comment (id),
    content     TEXT,
    report_date DATETIME DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE IF NOT EXISTS report_user
(
    id          SERIAL PRIMARY KEY,
    user_id     BIGINT UNSIGNED NOT NULL,
    CONSTRAINT fk_user_id_report FOREIGN KEY (user_id) REFERENCES user (id),
    reported    BIGINT UNSIGNED,
    CONSTRAINT fk_reported_user_id_report FOREIGN KEY (reported) REFERENCES post (id),
    content     TEXT,
    report_date DATETIME DEFAULT CURRENT_TIMESTAMP()
);



CREATE TABLE IF NOT EXISTS like_comment
(
    user_id   BIGINT UNSIGNED NOT NULL,
    CONSTRAINT fk_user_id_likes_comment FOREIGN KEY (user_id) REFERENCES user (id),
    liked     BIGINT UNSIGNED,
    PRIMARY KEY (liked, user_id),
    CONSTRAINT fk_comment_id_likes FOREIGN KEY (liked) REFERENCES comment (id),
    like_date DATETIME DEFAULT CURRENT_TIMESTAMP()
);
CREATE TABLE IF NOT EXISTS like_post
(
    user_id   BIGINT UNSIGNED NOT NULL,
    CONSTRAINT fk_user_id_likes_post FOREIGN KEY (user_id) REFERENCES user (id),
    liked     BIGINT UNSIGNED,
    PRIMARY KEY (liked, user_id),
    CONSTRAINT fk_post_id_likes FOREIGN KEY (liked) REFERENCES post (id),
    like_date DATETIME DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE IF NOT EXISTS activity_log
(
    id          SERIAL PRIMARY KEY,
    user_id     BIGINT UNSIGNED NOT NULL,
    CONSTRAINT fk_user_id_log FOREIGN KEY (user_id) REFERENCES user (id),
    type        ENUM ('LIKE', 'FOLLOW', 'UNFOLLOW', 'POST', 'COMMENT'),
    time        DATETIME DEFAULT CURRENT_TIMESTAMP(),
    description TEXT
);

CREATE TABLE IF NOT EXISTS tags
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE
);

CREATE TABLE IF NOT EXISTS post_tags
(
    tag_id BIGINT UNSIGNED,
    CONSTRAINT fk_tag_id_post_tag FOREIGN KEY (tag_id) REFERENCES tags (id),
    post_id BIGINT UNSIGNED,
    CONSTRAINT fk_post_id_post_tag FOREIGN KEY (post_id) REFERENCES post(id),
    PRIMARY KEY (tag_id, post_id)
);

CREATE table if not EXISTS categories(
     id   SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE
);

CREATE TABLE IF NOT EXISTS post_categories
(
    category_id BIGINT UNSIGNED,
    CONSTRAINT fk_tag_id_post_categories FOREIGN KEY (category_id) REFERENCES categories (id),
    post_id BIGINT UNSIGNED,
    CONSTRAINT fk_post_id_post_categories FOREIGN KEY (post_id) REFERENCES post(id),
    PRIMARY KEY (category_id, post_id)
);



