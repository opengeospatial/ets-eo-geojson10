package org.opengis.cite.eogeojson10.productinformation;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class ProductInformationConfClassTests extends DataFixture{
    /**
     * Verifies conformance to /conf/product-information, Section 7.7
	 * @throws IOException Thrown if the file cannot be read
     */
    @Test(description = "Implements /conf/product-information, Section 7.7")
    public void validateProductInformation() throws IOException{
        boolean valid = true;
        StringBuffer sb = new StringBuffer();
   	 
   	 JSONObject jo  = readJSONObjectFromFile(new File(testSubject));  
        
        if(jo.has("properties")) {
       	 
       	 JSONObject propertiesJO = jo.getJSONObject("properties");
	       	 if(propertiesJO.has("productInformation")) {
	       		//do nothing
	       	 }
	       	 else {
	       		sb.append("productInformation field is missing. \n");
	       		 valid = false;
	       	 }
        }
      	 else {
      		sb.append("productInformation field is missing. \n");
       		 valid = false;
       	 }
     	  
        Assert.assertTrue(valid,
                "Validation failed because "+sb.toString()+ " . ");
    }
}
