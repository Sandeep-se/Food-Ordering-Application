package com.project.repository;

public interface UserValidationRepository {
	public boolean checkUserEmailExists(Object values[]);
	public boolean checkUserPhoneNumberExists(Object values[]) ;
}
