package com.dao;

import java.util.List;

import com.models.User;

public interface UserDAO {
	
	//CREATE
	public boolean createUser(User u);
	
	//READ
	public User selectUserById(int userId);
	public List<User> selectAllUsers();

	
	//UPDATE
	public int updateUser();
	
	//DELETE
	public boolean deleteUser(int userId);

}
