package com.vti.backend.datalayer;

import com.vti.entity.Users;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUserRepository {
//	Method insert a new user
	public boolean insertUser(String username, String password, String email, String phone) throws SQLException;

//	Method check username and password
	public boolean checkExists(String username, String password) throws SQLException;

	public List<Users> findAll() throws SQLException;

	public Optional<Users> findById(int userId);
}
