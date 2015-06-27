package com.icetea.manager.pagodiario.service;

import javax.transaction.Transactional;

@Transactional
public abstract class BasicServiceImpl implements BasicService {

	public BasicServiceImpl() {
		super();
	}
}
