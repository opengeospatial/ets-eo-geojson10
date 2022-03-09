package org.opengis.cite.eogeojson10;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.w3c.dom.Document;

/**
 * Includes various tests of capability 1.
 */
public class DataFixture {

    protected String testSubject;

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
    
    public JSONObject readJSONObjectFromFile(File filePath) throws IOException {

        FileInputStream is = new FileInputStream(filePath);
        try ( Scanner scanner = new Scanner(is,
                StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");

            return new JSONObject(scanner.hasNext() ? scanner.next() : "");
        }

    }    
}