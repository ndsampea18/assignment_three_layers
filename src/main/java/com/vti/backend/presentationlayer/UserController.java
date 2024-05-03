package com.vti.backend.presentationlayer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.vti.backend.businesslayer.IUserService;
import com.vti.backend.businesslayer.UserService;
import com.vti.entity.Users;

public class UserController {
	private IUserService usService;

	public UserController() {
		usService = new UserService();
	}

	public boolean registerUser(String username, String password, String email, String phone) throws SQLException {
		return usService.registerUser(username, password, email, phone);
	}

	public boolean login(String username, String password) throws SQLException {
		return usService.login(username, password);
	}

	public List<Users> getAllUsers() throws SQLException {
        return usService.allUsers();
    }

	public Optional<Users> getUserDetail(int id) {
		return usService.userDetail(id);
	}
}
