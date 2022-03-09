package org.opengis.cite.eogeojson10.earthobservation;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class EarthObservationConfClassTests extends DataFixture{
    /**
     * Verifies conformance to /conf/earthobservation, Section 7.1
     * @throws IOException Thrown if the file cannot be read
     */
    @Test(description = "Implements /conf/earthobservation, Section 7.1")
    public void validateEarthObservationMetadata() throws IOException{
        JSONObject jo  = readJSONObjectFromFile(new File(testSubject));        
        String[] mandatoryFields = {"type","id","geometry","properties"};
        
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
