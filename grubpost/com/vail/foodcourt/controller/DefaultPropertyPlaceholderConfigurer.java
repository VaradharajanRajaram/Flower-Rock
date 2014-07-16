package com.vail.foodcourt.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
 
public class DefaultPropertyPlaceholderConfigurer
        extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
{
  private Map<String, String> startingProperties = new HashMap<String, String>();
 
  public void setStartingProperties(Map<String, String> startingProperties)
  {
     this.startingProperties = startingProperties;
  }
 
  public DefaultPropertyPlaceholderConfigurer()
  {
      try
      {
          loadDefaultProperties();
      }
      catch (IOException e)
      {
          logger.warn("failed to load default properties", e);
      }
  }
   private void loadDefaultProperties()
          throws IOException
  {
      final Properties defaultProperties = new Properties();
       for(Map.Entry<String,String> entry : startingProperties.entrySet())
      {
          defaultProperties.put(entry.getKey(), entry.getValue());
      }
       this.setPropertiesArray(new Properties[]{defaultProperties});
  }
}

