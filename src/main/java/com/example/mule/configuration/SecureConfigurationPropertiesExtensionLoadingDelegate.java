package com.example.mule.configuration;

import static org.mule.metadata.api.model.MetadataFormat.JAVA;
import static org.mule.runtime.api.meta.Category.SELECT;
import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;
import org.mule.metadata.api.builder.BaseTypeBuilder;
import org.mule.runtime.api.meta.model.ModelProperty;
import org.mule.runtime.api.meta.model.declaration.fluent.ConfigurationDeclarer;
import org.mule.runtime.api.meta.model.declaration.fluent.ExtensionDeclarer;
import org.mule.runtime.api.meta.model.declaration.fluent.ParameterGroupDeclarer;
import org.mule.runtime.extension.api.loader.ExtensionLoadingContext;
import org.mule.runtime.extension.api.loader.ExtensionLoadingDelegate;
import org.mule.runtime.module.extension.internal.loader.java.property.ConfigurationFactoryModelProperty;

public class SecureConfigurationPropertiesExtensionLoadingDelegate implements ExtensionLoadingDelegate {
	public static final String EXTENSION_NAME = "Ramsey Config";
    public static final String CONFIG_ELEMENT = "config";
	
	@Override
	public void accept(ExtensionDeclarer extensionDeclarer, ExtensionLoadingContext context) {
		ConfigurationDeclarer configurationDeclarer = extensionDeclarer.named(EXTENSION_NAME)
		        .describedAs(String.format("Crafted %s Extension", EXTENSION_NAME))
		        .withCategory(SELECT)
		        .onVersion("1.0.0")
		        .fromVendor("Ramsey Solutions")
		        .withConfig(CONFIG_ELEMENT);

		    ParameterGroupDeclarer defaultParameterGroup = configurationDeclarer.onDefaultParameterGroup();
		    // TODO you can add/remove configuration parameter using the code below.
		    defaultParameterGroup
		        .withRequiredParameter("customParameter").ofType(BaseTypeBuilder.create(JAVA).stringType().build())
		        .withModelProperty(new ConfigurationFactoryModelProperty(new SecureConfigurationFactory()))
		        .withExpressionSupport(NOT_SUPPORTED)
		        .describedAs(" Connector to read Ramsey configuration properties");
		
	}

}
