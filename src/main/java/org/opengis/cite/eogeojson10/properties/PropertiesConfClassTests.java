package org.opengis.cite.eogeojson10.properties;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class PropertiesConfClassTests extends DataFixture{
	   /**
  * Verifies conformance to /conf/properties, Section 7.1.1
		* @throws IOException Thrown if the file cannot be read
  */
 @Test(description = "Implements /conf/properties, Section 7.1.1")
 public void validateProperties()  throws IOException{
     
     boolean valid = true;
     StringBuffer sb = new StringBuffer();
     String[] mandatoryFields = {"links","status","acquisitionInformation"};
	 
	 JSONObject jo  = readJSONObjectFromFile(new File(testSubject));  
     
     
     
     if(jo.has("properties")) {
    	 
    	 JSONObject propertiesJO = jo.getJSONObject("properties");
    	  for(String prop:mandatoryFields)
    	  {
    	     	if(propertiesJO.has(prop)!=true) {
    	     		sb.append(prop+" field is missing. \n");
    	     		valid = false;
    	     	}
    	  }
     }
     
     
     Assert.assertTrue(valid,
             "Validation failed because "+sb.toString()+ " . ");

 }  
}
