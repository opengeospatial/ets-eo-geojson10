package org.opengis.cite.eogeojson10.acquisitioninformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;

import org.opengis.cite.eogeojson10.BaseJsonSchemaValidatorTest;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class AquisitionInformationConfClassTests extends DataFixture{
    /**
     * Verifies conformance to /conf/acquisition-information, Section 7.6
	 * @throws IOException Thrown if the file cannot be read
     */
    @Test(description = "Implements /conf/acquisition-information, Section 7.6")
    public void validateAquisitionInformation() throws IOException{
        boolean valid = true;
        StringBuffer sb = new StringBuffer();

		BaseJsonSchemaValidatorTest tester = new BaseJsonSchemaValidatorTest();
		JsonNode jo = tester.getJsonNodeFromStringContent(tester.otherConvertInputStreamToString(new FileInputStream(new File(testSubject))));
   	 

        if(jo.has("properties")) {
       	 
       	 JsonNode propertiesJO = jo.get("properties");
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
