package org.opengis.cite.eogeojson10.earthobservationcollection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;

import org.opengis.cite.eogeojson10.BaseJsonSchemaValidatorTest;
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

        StringBuffer errorMessage = new StringBuffer();

        String schemaToApply = "/org/opengis/cite/eogeojson10/jsonschema/eo-geojson-schema-standalone.json";


        boolean valid = false;

        JsonSchema schema = null;
        BaseJsonSchemaValidatorTest tester = new BaseJsonSchemaValidatorTest();

        StringBuffer sb = new StringBuffer();

        InputStream inputStream = tester.getClass()
                .getResourceAsStream(schemaToApply);

        try {
            JsonNode schemaNode = tester.getJsonNodeFromStringContent(tester.otherConvertInputStreamToString(inputStream));
            schema = tester.getJsonSchemaFromJsonNodeAutomaticVersion(schemaNode);

            schema.initializeValidators();

            JsonNode node = tester.getJsonNodeFromStringContent(tester.otherConvertInputStreamToString(new FileInputStream(testSubject)));
            Set<ValidationMessage> errors = schema.validate(node);



            Iterator it = errors.iterator();
            while(it.hasNext())
            {
                valid = false;
                String errorText = " "+it.next();
                sb.append(errorText+".\n");
                errorMessage.append(errorText+".\n");

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        ///---



        //------Test the Feature Collection

        JsonNode jo  = null;
        boolean validCol = false;
        try {


            jo = tester.getJsonNodeFromStringContent(tester.otherConvertInputStreamToString(new FileInputStream(collectionTestSubject)));


            if(jo.has("type")) {

                if(jo.get("type").textValue().equals("FeatureCollection")) {
                    Set<ValidationMessage> errors = schema.validate(jo); // throws a ValidationException if this object is invalid
                    if(errors.size()>0) errorMessage.append("CoreTests validation error -> "+errors.toString()+".");
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

        if(validCol==false && valid==false) {

            Assert.assertTrue(validCol,
                    "Validation failed. "+errorMessage.toString());
        }

    }  
}
