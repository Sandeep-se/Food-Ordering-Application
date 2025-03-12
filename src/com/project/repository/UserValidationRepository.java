package com.project.repository;

public interface UserValidationRepository extends EmailValidationRepository{
	public boolean checkPhoneNumberExists(Object values[]) ;
}
