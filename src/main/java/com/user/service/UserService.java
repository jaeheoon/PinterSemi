package com.user.service;

import java.util.Map;

import com.user.bean.UserDTO;

public interface UserService {
	public UserDTO findId(String id);
	public void write(UserDTO userDTO);
	public Map<String, Object> list();
}
