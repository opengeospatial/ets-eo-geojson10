package org.opengis.cite.eogeojson10;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.w3c.dom.Document;

/**
 * Includes various tests of capability 1.
 */
public class DataFixture {

    protected String testSubject;
    protected String collectionTestSubject;   
    protected final int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * Obtains the test subject from the ISuite context. 
     * 
     * @param testContext
     *            The test (group) context.
     */
    @BeforeClass
    public void obtainTestSubject(ITestContext testContext) {
        Object obj = testContext.getSuite().getAttribute(SuiteAttribute.TEST_SUBJ_FILE.getName());
                
        if (null != obj) {
            this.testSubject = obj.toString();
        }
        
        //-----
        Object collectionObj = testContext.getSuite().getAttribute(SuiteAttribute.COL_TEST_SUBJ_FILE.getName());
        
        if (null != collectionObj) {
            this.collectionTestSubject = collectionObj.toString();
        }        
    }

    /**
     * Sets the test subject. This method is intended to facilitate unit
     * testing.
     *
     * @param testSubject
     *            A Document node representing the test subject or metadata
     *            about it.
     */
    public void setTestSubject(String testSubject) {
        this.testSubject = testSubject;
    }
    

    
    // from https://mkyong.com/java/how-to-convert-inputstream-to-string-in-java/
    public String convertInputStreamToString(InputStream is) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }



        return result.toString("UTF-8");


    }    
}