package com.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.bean.UserDTO;
import com.user.dao.UserDAO;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDTO findId(String id) {
		return userDAO.findId(id);
	}
	
	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);
	}
	
	@Override
	public Map<String, Object> list() {
		List<UserDTO> list = userDAO.list();
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("list", list);
		return userMap;
	}
}
