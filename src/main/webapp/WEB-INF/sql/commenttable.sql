-- 댓글 생성 쿼리문
CREATE TABLE CommentTable (
    seq_comment INT AUTO_INCREMENT PRIMARY KEY,  -- 댓글 ID
    seq_board INT NOT NULL,  -- 연관된 게시물 ID (외래키)
    name VARCHAR(50) NOT NULL,  -- 댓글 작성자
    commentContent VARCHAR(1000) NOT NULL,  -- 댓글 내용
    logtime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 댓글 작성 시간
    CONSTRAINT fk_comment_board
        FOREIGN KEY (seq_board)
        REFERENCES board(seq_board)
        ON DELETE CASCADE  -- 게시물 삭제 시 댓글도 삭제
);