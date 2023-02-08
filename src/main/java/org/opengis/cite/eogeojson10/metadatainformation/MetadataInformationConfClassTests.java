package org.opengis.cite.eogeojson10.metadatainformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;

import org.opengis.cite.eogeojson10.BaseJsonSchemaValidatorTest;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class MetadataInformationConfClassTests extends DataFixture{
    /**
     * Verifies conformance to /conf/metadata-information, Section 7.2
	 * @throws IOException Thrown if the file cannot be read
     */
    @Test(description = "Implements /conf/metadata-information, Section 7.2")
    public void validateMetadataInformation()  throws IOException{
        boolean valid = true;
        StringBuffer sb = new StringBuffer();

		BaseJsonSchemaValidatorTest tester = new BaseJsonSchemaValidatorTest();
		JsonNode jo = tester.getJsonNodeFromStringContent(tester.otherConvertInputStreamToString(new FileInputStream(new File(testSubject))));


		if(jo.has("properties")) {
       	 
       	 JsonNode propertiesJO = jo.get("properties");
	       	 if(propertiesJO.has("updated")) {
	       		//do nothing
	       	 }
	       	 else {
	       		sb.append("updated field is missing. \n");
	       		 valid = false;
	       	 }
        }
      	 else {
      		sb.append("updated field is missing. \n");
       		 valid = false;
       	 }
     	  
        Assert.assertTrue(valid,
                "Validation failed because "+sb.toString()+ " . ");
    }
}
