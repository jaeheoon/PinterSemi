package com.likes.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikesDAO {
    // 좋아요 생성
    void addLike(@Param("seqBoard") long seqBoard, @Param("seqMember") long seqMember);

    // 좋아요 삭제
    void removeLike(@Param("seqBoard") long seqBoard, @Param("seqMember") long seqMember);

    // 특정 게시물에 대한 좋아요 여부 확인 
    boolean isLikedByMember(@Param("seqBoard") long seqBoard, @Param("seqMember") long seqMember);

    // 특정 게시물의 좋아요 수 조회
    int countLikesByBoard(long seqBoard);
}
