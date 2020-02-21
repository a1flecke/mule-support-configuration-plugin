package com.example.mule.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.xml.namespace.QName;

import org.mule.runtime.api.component.location.ComponentLocation;
import org.mule.runtime.api.component.location.Location;
import org.mule.runtime.api.event.Event;
import org.mule.runtime.api.meta.model.ExtensionModel;
import org.mule.runtime.api.meta.model.config.ConfigurationModel;
import org.mule.runtime.config.api.dsl.model.ResourceProvider;
import org.mule.runtime.config.api.dsl.model.properties.ConfigurationProperty;
import org.mule.runtime.config.api.dsl.model.properties.DefaultConfigurationPropertiesProvider;
import org.mule.runtime.extension.api.runtime.config.ConfigurationInstance;
import org.mule.runtime.extension.api.runtime.config.ConfigurationProvider;



public class SecureConfigurationPropertiesProvider extends DefaultConfigurationPropertiesProvider{
	private static final org.apache.logging.log4j.Logger logger= org.apache.logging.log4j.LogManager.getLogger(SecureConfigurationPropertiesProvider.class);
	private final static String SECURE_PREFIX = "secure::";
	public static final String PARAMETER_STORE_VALUE_PREFIX = "secure.";
	private final Object ramseySecurePropertiesProcessor;
	private final Map<String, String> ramseyConfig = new HashMap<String, String>();
	
	public SecureConfigurationPropertiesProvider(String fileLocation, ResourceProvider resourceProvider) {
		super(fileLocation, resourceProvider);
		ramseySecurePropertiesProcessor = new Object();  //replace with our actual secure processor
	}

	@Override
	public Optional<ConfigurationProperty> getConfigurationProperty(String configurationAttributeKey) {
		// TODO Auto-generated method stub
		logger.info("Looking up property {}", configurationAttributeKey);
		if(configurationAttributeKey.startsWith(SECURE_PREFIX)) {
			final String effectiveKey = configurationAttributeKey.substring(SECURE_PREFIX.length());
			logger.info("Looking up property {} with effective Key {}", configurationAttributeKey, effectiveKey);
			ConfigurationProperty originalConfigurationProperty = configurationAttributes.get(effectiveKey);
			logger.info("Found value {} with effective Key {}", originalConfigurationProperty, effectiveKey);
			if (originalConfigurationProperty == null) {
		        return Optional.empty();
		      }
			
	      final String resolvedValue = ramseyConfig.computeIfAbsent(effectiveKey, key-> {
	    	  //lookup secure value in our parameter store
	    	  return "FOUND VALUE";
	      });
	      
	      logger.info("resolved value {} for effective key {} and original key of {}", resolvedValue, effectiveKey, configurationAttributeKey);
	      
	      return Optional.of(new ConfigurationProperty() {
			
	    	  @Override
	          public Object getSource() {
	            return originalConfigurationProperty.getSource();
	          }
			
			@Override
	        public Object getRawValue() {
	          return resolvedValue;
	        }
			
			@Override
	        public String getKey() {
	          return originalConfigurationProperty.getKey();
	        }
		});
		}else {
			return Optional.empty();
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return super.getDescription();
	}

}
