package com.project.repository;

import java.sql.ResultSet;

public interface UserRepository {
	public boolean register(Object values[]);
	public boolean updateUser(Object values[]);
}
