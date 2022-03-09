package org.opengis.cite.eogeojson10.earthobservationcollection;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class EOCollectionConfClassTests extends DataFixture{
	   /**
     * Verifies conformance to /conf/earthobservation-collection, Section 7.8
		* @throws IOException Thrown if the file cannot be read
     */
    @Test(description = "Implements /conf/earthobservation-collection, Section 7.8")
    public void validateEarthObservationMetadataCollection() throws IOException{
        JSONObject jo  = readJSONObjectFromFile(new File(testSubject));
        
        boolean valid = true;
        
        if(jo.has("type")) {
        	
        	if(jo.get("type").equals("FeatureCollection")) {
        		//do nothing since valid is true
        	}
        	else {
        		valid = false;
        	}
        }
    	else {
    		valid = false;
    	}
        
        
        Assert.assertTrue(valid,
                "Validation failed because there is no type field equals to FeatureCollection. ");

    }  
}
