package org.opengis.cite.eogeojson10.acquisitionparameters;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class AquisitionParametersConfClassTests extends DataFixture{
    /**
     * Verifies conformance to /conf/acquisition-parameters, Section 7.6
     */
    @Test(description = "Implements /conf/acquisition-parameters, Section 7.6")
    public void validateAquisitionParameters()  throws IOException{
        boolean valid = true;
        StringBuffer sb = new StringBuffer();
   	 
   	 JSONObject jo  = readJSONObjectFromFile(new File(testSubject));  
        
        if(jo.has("properties")) {
       	 
       	 JSONObject propertiesJO = jo.getJSONObject("properties");
	       	 if(propertiesJO.has("acquisitionInformation")) {
	       		
		        //-------------------
	       		JSONArray acquisitionInformationJO = propertiesJO.getJSONArray("acquisitionInformation");
	       		for(int i=0; i < acquisitionInformationJO.length(); i++) {
		       		JSONObject acquisitionInformationJOItem = (JSONObject) acquisitionInformationJO.get(i);
		       		if(acquisitionInformationJOItem.has("acquisitionParameters")) {
		       		   //do nothing
		       		}
			       	else {
				       		sb.append("acquisitionParameters field is missing. \n");
				       		 valid = false;
				     }
		       		//-------------------   			
	       		}
	       		
	       	 }
	       	 else {
	       		sb.append("acquisitionInformation and acquisitionParameters fields are missing. \n");
	       		 valid = false;
	       	 }
        }
      	 else {
      		sb.append("acquisitionInformation and acquisitionParameters fields are missing. \n");
       		 valid = false;
       	 }
     	  
        Assert.assertTrue(valid,
                "Validation failed because "+sb.toString()+ " . ");
    }
}
