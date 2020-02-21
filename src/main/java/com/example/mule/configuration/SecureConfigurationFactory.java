package com.example.mule.configuration;

import org.mule.runtime.extension.api.runtime.config.ConfigurationFactory;

public class SecureConfigurationFactory implements ConfigurationFactory{

	@Override
	public Object newInstance() {
		return new SecureConfigurationPropertiesProviderFactory();
	}

	@Override
	public Class<?> getObjectType() {
		return SecureConfigurationPropertiesProviderFactory.class;
	}

}
