DROP TABLE follow;


INSERT INTO follow(user_id1, user_id2)
VALUES (5, 6);
INSERT INTO follow(user_id1, user_id2)
VALUES (6, 7);
INSERT INTO follow(user_id1, user_id2)
VALUES (5, 7);
INSERT INTO follow(user_id1, user_id2)
VALUES (7, 2);
INSERT INTO follow(user_id1, user_id2)
VALUES (1, 2);
INSERT INTO follow(user_id1, user_id2)
VALUES (3, 4);
INSERT INTO follow(user_id1, user_id2)
VALUES (3, 1);
INSERT INTO follow(user_id1, user_id2)
VALUES (7, 1);
INSERT INTO follow(user_id1, user_id2)
VALUES (6, 1);
INSERT INTO follow(user_id1, user_id2)
VALUES (7, 1);


# DELETE FROM user WHERE id = 1;

#koito sledvat 2
SELECT u.username
FROM follow f
         JOIN user u ON u.id = f.user_id1
WHERE user_id2 = 2;

SELECT u.username
FROM user u
WHERE id = 2;

#koito 2 sledva
SELECT u.username
FROM follow f
         JOIN user u ON u.id = f.user_id2
WHERE user_id1 = 2;


SELECT post.*
FROM post
         JOIN user u ON u.id = post.user_id
WHERE user_id = 7;

SELECT u.image_url
FROM post p
         JOIN user u ON u.id = p.user_id
WHERE p.id = 10;

INSERT INTO comment(user_id, post_id, comment_id, content)
VALUES (1, 2, NULL, 'text');

ins

DROP TABLE user;
DROP TABLE follow;
DROP TABLE post;

DROP TABLE comment;
DROP TABLE like_comment;
DROP TABLE like_post;
DROP TABLE report_comment;
DROP TABLE report_post;
DROP TABLE report_user;
DROP TABLE activity_log;
DROP TABLE tags;
DROP TABLE post_tags;
DROP TABLE categories;
DROP TABLE post_categories;

DELETE
FROM follow
WHERE user_id1 = 1
  AND user_id2 = 7;


# INSERT INTO report(user_id, post_id, comment_id, content)
# VALUES (?, ?, ?, ?);
#
# SELECT *
# FROM report
# WHERE comment_id = ?;
#
# INSERT INTO likes(user_id, post_id, comment_id)
# VALUES (?, ?, ?);
#
# SELECT *
# FROM likes
# WHERE comment_id = ?

INSERT INTO tags(name)
VALUES ('funny');
INSERT INTO tags(name)
VALUES ('quote');
INSERT INTO tags(name)
VALUES ('meme');
INSERT INTO tags(name)
VALUES ('woman');
INSERT INTO tags(name)
VALUES ('man');
INSERT INTO tags(name)
VALUES ('child');
INSERT INTO tags(name)
VALUES ('usa');
INSERT INTO tags(name)
VALUES ('bulgaria');
INSERT INTO tags(name)
VALUES ('sad');
INSERT INTO tags(name)
VALUES ('happy');

INSERT INTO categories
VALUES ('politics');
INSERT INTO categories(name)
VALUES ('pandemic');
INSERT INTO categories(name)
VALUES ('economics');
INSERT INTO categories(name)
VALUES ('trading');
INSERT INTO categories
VALUES ('adult');
INSERT INTO categories
VALUES ('gaming');

SELECT *
FROM post_tags
WHERE post_id = 1;

SELECT COUNT(*) AS likes
FROM like_post
WHERE liked = 2;

INSERT INTO post_categories
VALUES (1, 1);