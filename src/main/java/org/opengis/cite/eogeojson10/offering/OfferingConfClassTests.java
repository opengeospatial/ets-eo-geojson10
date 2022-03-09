package org.opengis.cite.eogeojson10.offering;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class OfferingConfClassTests extends DataFixture{
    /**
     * Verifies conformance to /conf/offering, Section 7.1.4
	 * @throws IOException Thrown if the file cannot be read
     */
    @Test(description = "Implements /conf/offering, Section 7.1.4")
    public void validateOffering() throws IOException{
        boolean valid = true;
        StringBuffer sb = new StringBuffer();
   	 
   	 JSONObject jo  = readJSONObjectFromFile(new File(testSubject));  
        
        if(jo.has("properties")) {
       	 
       	 JSONObject propertiesJO = jo.getJSONObject("properties");
	       	 if(propertiesJO.has("offerings")) {
	       		//do nothing
	       	 }
	       	 else {
	       		sb.append("offerings field is missing. \n");
	       		 valid = false;
	       	 }
        }
      	 else {
      		sb.append("offerings field is missing. \n");
       		 valid = false;
       	 }
     	  
        Assert.assertTrue(valid,
                "Validation failed because "+sb.toString()+ " . ");

    }
}
