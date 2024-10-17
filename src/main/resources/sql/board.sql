CREATE TABLE board (
    seq_board INT AUTO_INCREMENT PRIMARY KEY,  -- 게시물 ID
    seq_member INT NOT NULL,                   -- 멤버 ID
    imageFileName VARCHAR(500) NOT NULL,       -- UUID로 저장된 이미지 파일 이름
    imageOriginalFileName VARCHAR(500) NOT NULL,  -- 원본 이미지 파일 이름
    name VARCHAR(100) NOT NULL,                -- 게시물 작성자
    imageSubject VARCHAR(100) NOT NULL,        -- 제목
    imageContent VARCHAR(4000),                -- 내용
    hit INT DEFAULT 0,                         -- 조회수
    logtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 게시물 작성 시간
    CONSTRAINT fk_board_member
    FOREIGN KEY(seq_member)
    REFERENCES member(seq_member)
    ON DELETE CASCADE
);
