package com.bedrosians.bedlogic.service.security;

import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;

public interface KeymarkUcUserAuthentication {

	public boolean authenticate(KeymarkUcUser user, String password, boolean isPasswordBasedAuth) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException;
	
}
