package com.mmdis.dis.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExampleServiceImpl implements ExampleService {

	@Override
	@Transactional(readOnly = true)
	@PreAuthorize("hasAuthority('AUTHORITY_READ') and hasAuthority('AUTHORITY_SAVE')")
	public void doSomething() {
		
	}

}
