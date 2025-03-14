package com.project.repository;

import java.sql.ResultSet;

public interface UserRepository {
	public String register(Object values[]);
	public String updateUser(Object values[]);
	public ResultSet viewUserProfile(Object values[]);
}
