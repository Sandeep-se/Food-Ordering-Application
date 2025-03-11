package com.project.repository;

import java.sql.ResultSet;

public interface AutheticationRepository extends UserValidationRepository{
	public ResultSet login(Object[] values);
	
}
