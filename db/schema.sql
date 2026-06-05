-- 과제용 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS fruit_survey
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE fruit_survey;

-- 과일 항목과 투표 수를 저장하는 테이블
CREATE TABLE IF NOT EXISTS fruit_option (
    fruit_id INT PRIMARY KEY AUTO_INCREMENT,
    fruit_name VARCHAR(30) NOT NULL,
    vote_count INT NOT NULL DEFAULT 0
);

-- 초기 데이터는 모든 과일의 득표율이 25%가 되도록 동일한 표 수로 넣는다.
INSERT INTO fruit_option (fruit_name, vote_count)
VALUES ('Apple', 25),
       ('Grape', 25),
       ('Strawberry', 25),
       ('Melon', 25);
