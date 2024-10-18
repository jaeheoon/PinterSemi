package com.likes.bean;

import lombok.Data;

@Data
public class LikesDTO {
    private int likeId;           // 좋아요 고유 ID
    private int seqBoard;         // 게시물 ID
    private int seqMember;        // 멤버 ID (좋아요 누른 사용자)
    private String likedAt;  
    @Override
    public String toString() {
        return "LikesDTO{" +
                "likeId=" + likeId +
                ", seqBoard=" + seqBoard +
                ", seqMember=" + seqMember +
                ", likedAt='" + likedAt + '\'' +
                '}';
    }
}