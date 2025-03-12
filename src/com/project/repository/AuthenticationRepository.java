package com.project.repository;

import java.sql.ResultSet;

public interface AuthenticationRepository{
	public ResultSet login(Object[] values);
	
}
