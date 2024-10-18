package com.likes.service;

public interface LikesService {
    // 좋아요 생성
    void addLike(long seqBoard, long seqMember);

    // 좋아요 삭제
    void removeLike(long seqBoard, long seqMember);

    // 특정 게시물에 대한 좋아요 여부 확인 
    boolean isLikedByMember(long seqBoard, long seqMember);

    // 특정 게시물의 좋아요 수 조회
    int countLikesByBoard(long seqBoard);
}