CREATE TABLE likes (
    like_id INT AUTO_INCREMENT PRIMARY KEY,  -- 좋아요 고유 ID
    seq_board INT NOT NULL,                  -- 게시물 ID (외래키)
    seq_member INT NOT NULL,                 -- 멤버 ID (외래키, 누가 좋아요 눌렀는지)
    liked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 좋아요 누른 시간
    CONSTRAINT fk_likes_board
    FOREIGN KEY (seq_board)
    REFERENCES board(seq_board)
    ON DELETE CASCADE,                      -- 게시물이 삭제되면 좋아요 기록도 삭제

    CONSTRAINT fk_likes_member
    FOREIGN KEY (seq_member)
    REFERENCES member(seq_member)
    ON DELETE CASCADE                       -- 회원이 삭제되면 해당 회원의 좋아요 기록도 삭제
);