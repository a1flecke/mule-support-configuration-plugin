package com.example.mule.configuration;

import org.mule.runtime.api.component.ComponentIdentifier;
import org.mule.runtime.config.api.dsl.model.ConfigurationParameters;
import org.mule.runtime.config.api.dsl.model.ResourceProvider;
import org.mule.runtime.config.api.dsl.model.properties.ConfigurationPropertiesProvider;
import org.mule.runtime.config.api.dsl.model.properties.ConfigurationPropertiesProviderFactory;

public class SecureConfigurationPropertiesProviderFactory implements ConfigurationPropertiesProviderFactory {
	public static final ComponentIdentifier RAMSEY_CONFIG_IDENTIFIER = ComponentIdentifier.builder().namespace("ramsey-config").name("config").build();
	private static final org.apache.logging.log4j.Logger logger= org.apache.logging.log4j.LogManager.getLogger(SecureConfigurationPropertiesProviderFactory.class);
	
	
	
	public SecureConfigurationPropertiesProviderFactory() {
		logger.info("Creating SecureConfigurationPropertiesProviderFactory");
	}
	
	@Override
	public ComponentIdentifier getSupportedComponentIdentifier() {
		return RAMSEY_CONFIG_IDENTIFIER;
	}

	@Override
	public ConfigurationPropertiesProvider createProvider(ConfigurationParameters parameters,
			ResourceProvider externalResourceProvider) {
		logger.info("Creating ConfigurationPropertiesProvider");
		final String fileLocation = parameters.getStringParameter("file");
		return new SecureConfigurationPropertiesProvider(fileLocation, externalResourceProvider);
	}

}
