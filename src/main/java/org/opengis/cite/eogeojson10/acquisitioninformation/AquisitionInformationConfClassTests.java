package org.opengis.cite.eogeojson10.acquisitioninformation;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class AquisitionInformationConfClassTests extends DataFixture{
    /**
     * Verifies conformance to /conf/acquisition-information, Section 7.6
     */
    @Test(description = "Implements /conf/acquisition-information, Section 7.6")
    public void validateAquisitionInformation() throws IOException{
        boolean valid = true;
        StringBuffer sb = new StringBuffer();
   	 
   	 JSONObject jo  = readJSONObjectFromFile(new File(testSubject));  
        
        if(jo.has("properties")) {
       	 
       	 JSONObject propertiesJO = jo.getJSONObject("properties");
	       	 if(propertiesJO.has("acquisitionInformation")) {
	       		//do nothing
	       	 }
	       	 else {
	       		sb.append("acquisitionInformation field is missing. \n");
	       		 valid = false;
	       	 }
        }
      	 else {
      		sb.append("acquisitionInformation field is missing. \n");
       		 valid = false;
       	 }
     	  
        Assert.assertTrue(valid,
                "Validation failed because "+sb.toString()+ " . ");
    }
}
