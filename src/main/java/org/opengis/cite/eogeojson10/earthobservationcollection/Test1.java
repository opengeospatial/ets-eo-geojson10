package org.opengis.cite.eogeojson10.earthobservationcollection;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import org.opengis.cite.eogeojson10.BaseJsonSchemaValidatorTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;

public class Test1 {
    public static void main(String[] args) {
        StringBuffer errorMessage = new StringBuffer();

        String schemaToApply = "/org/opengis/cite/eogeojson10/jsonschema/eo-geojson-schema-standalone.json";
  String inputfile = "/Users/gobehobona/Documents/GitHub/ets-eo-geojson10/src/test/resources/example-1-seasat.json";

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

            JsonNode node = tester.getJsonNodeFromStringContent(tester.otherConvertInputStreamToString(new FileInputStream(new File(inputfile))));
            Set<ValidationMessage> errors = schema.validate(node);


            Iterator it = errors.iterator();
            while (it.hasNext()) {
                sb.append(" " + it.next() + ".\n");

            }
            System.out.println(sb.toString());
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }
}
