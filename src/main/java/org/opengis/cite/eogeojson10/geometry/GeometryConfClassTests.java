package org.opengis.cite.eogeojson10.geometry;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;

import org.opengis.cite.eogeojson10.BaseJsonSchemaValidatorTest;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class GeometryConfClassTests extends DataFixture{
    /**
     * Verifies conformance to /conf/geometry, Section 7.4
     * @throws IOException Thrown if the file cannot be read
     */
    @Test(description = "Implements /conf/geometry, Section 7.4")
    public void validateGeometry() throws IOException{
        BaseJsonSchemaValidatorTest tester = new BaseJsonSchemaValidatorTest();
        JsonNode jo = tester.getJsonNodeFromStringContent(tester.otherConvertInputStreamToString(new FileInputStream(new File(testSubject))));

        String[] mandatoryFields = {"geometry"};
        
        boolean valid = true;
        StringBuffer sb = new StringBuffer();
        
        for(String prop:mandatoryFields)
        {
        	if(jo.has(prop)!=true) {
        		sb.append(prop+" field is missing. \n");
        		valid = false;
        	}
        }
        
        Assert.assertTrue(valid,
                "Validation failed because "+sb.toString()+ " . ");
    }
}
