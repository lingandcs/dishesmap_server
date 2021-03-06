package wd.goodFood.utils;

import java.util.logging.Logger;
import java.util.logging.Level;
import org.apache.commons.configuration.PropertiesConfiguration;

@Deprecated
public class Config {

	protected Logger logger = Logger.getLogger(this.getClass().getPackage().getName());	
	protected PropertiesConfiguration props= new PropertiesConfiguration();

	public Config(String propertyFile) throws Exception {
		props.setDelimiterParsingDisabled(true);
		props.load(propertyFile);
	}	

	protected String readString(String key, String defaultValue, boolean required)
	throws Exception
	{
		String store = props.getString(key);
		if (store==null) {
			if (required) {
				logger.log(Level.SEVERE,"Could not read " + key);
				throw new Exception("Could not read " + key);
			}
			store = defaultValue;
		}
		return store==null ? null : store.trim();
	}

	protected boolean readBoolean(String key, boolean defaultValue, boolean required)
	throws Exception
	{
		return readString(key, defaultValue ? "true" : "false", required).equalsIgnoreCase("true");
	}

	protected int readInt(String key, int defaultValue, boolean required)
	throws Exception
	{
		int store;
		String buf = props.getString(key);
		if (buf==null) {
			if (required) {
				logger.log(Level.SEVERE,"Could not read " + key);
				throw new Exception("Could not read " + key);
			}
			return defaultValue;
		}

		// parse buf
		try {
			store = Integer.parseInt(buf);
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE,"Could not convert " + key + " value " + buf + " to integer");
			throw new Exception("Could not convert " + key + " value " + buf + " to integer");
		}			
		return store;
	}	

	protected float readFloat(String key, float defaultValue, boolean required)
	throws Exception
	{
		float store;
		String buf = props.getString(key);
		if (buf==null) {
			if (required) {
				logger.log(Level.SEVERE,"Could not read " + key);
				throw new Exception("Could not read " + key);
			}
			return defaultValue;
		}

		// parse buf
		try {
			store = Float.parseFloat(buf);
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE,"Could not convert " + key + " value " + buf + " to float");
			throw new Exception("Could not convert " + key + " value " + buf + " to float");
		}			
		return store;
	}	
}