package com.bedrosians.bedlogic.service.security;

import com.bedrosians.bedlogic.domain.product.enums.DBOperation;
import com.bedrosians.bedlogic.domain.user.KeymarkUcUser;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;
import com.bedrosians.bedlogic.exception.BedDAOException;
import com.bedrosians.bedlogic.exception.BedResUnAuthorizedException;

public interface KeymarkUcUserAuthorization {

	public boolean authorize(KeymarkUcUser user, String domain, DBOperation dBOperation) throws BedDAOBadParamException, BedDAOException, BedResUnAuthorizedException;
	
}
