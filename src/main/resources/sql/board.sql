-- 게시물 생성 쿼리문
CREATE TABLE board (
    seq_board INT AUTO_INCREMENT PRIMARY KEY,  -- 게시물 ID
    seq_member INT NOT NULL, -- 멤버 ID
    image VARCHAR(500) NOT NULL, -- 이미지
    name VARCHAR(100) NOT NULL, -- 게시물 작성자
    imageSubject VARCHAR(100) NOT NULL,  -- 제목
    imageContent VARCHAR(4000),  -- 내용
    hit INT DEFAULT 0, -- 조회수
    logtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 게시물 작성 시간   
    CONSTRAINT fk_board_member
    FOREIGN KEY(seq_member)
    REFERENCES member(seq_member)
    ON DELETE CASCADE
);