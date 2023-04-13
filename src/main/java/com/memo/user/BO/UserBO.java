package com.memo.user.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.user.DAO.UserMapper;
import com.memo.user.model.User;

@Service
public class UserBO {
	@Autowired
	private UserMapper userMapper;
	
	public User getUserByLoginId(String loginId) {
		return userMapper.selectUserByLoginId(loginId); 
	}
	
	public int addUser(String loginId, String password, String name, String email) {
		return userMapper.insertUser(loginId, password, name, email);
	}
}
