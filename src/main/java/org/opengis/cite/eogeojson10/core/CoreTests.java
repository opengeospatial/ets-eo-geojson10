package org.opengis.cite.eogeojson10.core;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.opengis.cite.eogeojson10.DataFixture;
import org.opengis.cite.eogeojson10.ErrorMessage;
import org.opengis.cite.eogeojson10.ErrorMessageKeys;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.Method.GET;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Includes various tests of capability 1.
 */
public class CoreTests extends DataFixture {


    /**
     * Verifies conformance to /conf/core, Sections 7.1 and Section 7.8
     */
    @Test(description = "Implements /conf/core, Sections 7.1 and Section 7.8")
    public void validateEOMetadataGeoJSONValidPerSchema(){
        
    	String errorMessage = null;

        String schemaToApply = "/org/opengis/cite/eogeojson10/jsonschema/eo-geojson-schema-standalone.json";


        boolean valid = false;
        InputStream inputStream = getClass()
                .getResourceAsStream(schemaToApply);
        JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
        Schema schema = SchemaLoader.load(rawSchema);
        
        
        try {
        	
        schema.validate(readJSONObjectFromFile(new File(testSubject))); // throws a ValidationException if this object is invalid
        System.out.println("CHK 1");
        valid = true;
        }
        catch(Exception ex) {
        	errorMessage = ex.getMessage();
        	System.out.println("CHK 2 "+errorMessage);
        	valid = false;
        }

        Assert.assertTrue(valid,
                "Validation failed because "+errorMessage+ " . ");

    }
    

    
   

 
}
