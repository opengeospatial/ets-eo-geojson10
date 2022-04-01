package org.opengis.cite.eogeojson10.earthobservationcollection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
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
      
        String schemaToApply = "/org/opengis/cite/eogeojson10/jsonschema/eo-geojson-schema-standalone.json";    	
        InputStream inputStream = getClass()
                .getResourceAsStream(schemaToApply);
        JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));    	
    	StringBuffer errorMessage = new StringBuffer();
        Schema schema = SchemaLoader.load(rawSchema);     
        JSONObject jo  = null;
        boolean validCol = false;
        try {
        
        jo  = readJSONObjectFromFile(new File(collectionTestSubject));
        
        
        if(jo.has("type")) {
        	
        	if(jo.get("type").equals("FeatureCollection")) {
        		schema.validate(jo); // throws a ValidationException if this object is invalid
        		validCol = true;
        	}
        	else {
        		validCol = false;
        		errorMessage.append("Validation of feature collection did not have type property value that equals 'FeatureCollection'\n");
        	}
        }
    	else {
    		validCol = false;
    		errorMessage.append("Validation of feature collection did not have type property\n");
    	}
        
        }
        catch(Exception ex)
        {
        	errorMessage.append("Validation of Feature Collection failed because "+ex.getMessage()+"\n");
        
        	validCol = false;
        }
        
        if(validCol==false) {
        Assert.assertTrue(validCol,
                "Validation failed. "+errorMessage.toString());        
        }

    }  
}
