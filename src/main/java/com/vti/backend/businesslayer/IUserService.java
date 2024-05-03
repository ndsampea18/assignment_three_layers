package com.vti.backend.businesslayer;

import com.vti.entity.Users;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUserService {
//	Method register a account
	public boolean registerUser(String username, String password, String email, String phone) throws SQLException;

//	Method login
	public boolean login(String username, String password) throws SQLException;

	public List<Users> allUsers() throws SQLException;

	public Optional<Users> userDetail(int userId);
}
