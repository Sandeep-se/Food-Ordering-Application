package com.project.repository;

import java.sql.ResultSet;

public interface DatabaseRepository {
	public boolean executeUpdate(String query,Object[] values) ;
	public boolean executeUpdate(String query);
	public ResultSet executeQuery(String query,Object[] values);
}
