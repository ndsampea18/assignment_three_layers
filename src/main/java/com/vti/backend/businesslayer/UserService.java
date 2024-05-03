package com.vti.backend.businesslayer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.vti.backend.datalayer.IUserRepository;
import com.vti.backend.datalayer.UserRepository;
import com.vti.entity.Users;

public class UserService implements IUserService {
	private final IUserRepository userRepository;

	public UserService() {
		userRepository = new UserRepository();
	}

	@Override
	public boolean registerUser(String username, String password, String email, String phone) throws SQLException {
		return userRepository.insertUser(username, password, email, phone);
	}

	@Override
	public boolean login(String username, String password) throws SQLException {
		return userRepository.checkExists(username, password);
	}

	@Override
	public List<Users> allUsers() throws SQLException {
		return userRepository.findAll();
	}

	@Override
	public Optional<Users> userDetail(int userId) {
		return userRepository.findById(userId);
	}
}
