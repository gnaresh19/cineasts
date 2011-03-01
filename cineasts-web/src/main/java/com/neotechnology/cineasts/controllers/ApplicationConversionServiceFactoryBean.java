package com.neotechnology.cineasts.controllers;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

public class ApplicationConversionServiceFactoryBean implements
		ConversionService {

	@Override
	public boolean canConvert(Class<?> arg0, Class<?> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canConvert(TypeDescriptor arg0, TypeDescriptor arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T convert(Object arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object convert(Object arg0, TypeDescriptor arg1, TypeDescriptor arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
