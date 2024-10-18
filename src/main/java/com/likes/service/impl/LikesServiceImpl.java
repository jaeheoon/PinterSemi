package com.likes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likes.dao.LikesDAO;
import com.likes.service.LikesService;

@Service
public class LikesServiceImpl implements LikesService{
	@Autowired
	LikesDAO likesDAO;

	@Override
	public void addLike(long seqBoard, long seqMember) {
		likesDAO.addLike(seqBoard, seqMember);		
	}

	@Override
	public void removeLike(long seqBoard, long seqMember) {
		likesDAO.removeLike(seqBoard, seqMember);
		
	}

	@Override
	public boolean isLikedByMember(long seqBoard, long seqMember) {		
		return likesDAO.isLikedByMember(seqBoard, seqMember);
	}

	@Override
	public int countLikesByBoard(long seqBoard) {		
		return likesDAO.countLikesByBoard(seqBoard);
	}

}
