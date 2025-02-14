package org.opengis.cite.eogeojson10.core;

import java.io.File;
import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Includes various tests of capability 1.
 */
public class CoreTests extends DataFixture {

	/**
	 * Verifies conformance to /conf/core, Sections 7.1 and Section 7.8
	 */
	@Test(description = "Implements /conf/core, Sections 7.1 and Section 7.8")
	public void validateEOMetadataGeoJSONValidPerSchema() {

		StringBuffer errorMessage = new StringBuffer();

		String schemaToApply = "/org/opengis/cite/eogeojson10/jsonschema/eo-geojson-schema-standalone.json";

		boolean valid = false;
		InputStream inputStream = getClass().getResourceAsStream(schemaToApply);

		Schema schema = null;
		// ------Test the Feature

		try {
			JSONObject rawSchema = new JSONObject(convertInputStreamToString(inputStream));
			schema = SchemaLoader.load(rawSchema);

			schema.validate(readJSONObjectFromFile(new File(testSubject))); // throws a
																			// ValidationException
																			// if this
																			// object is
																			// invalid

			valid = true;
		}
		catch (Exception ex) {
			errorMessage.append("Validation of single feature document failed because " + ex.getMessage() + "\n");

			valid = false;
		}

		if (valid == false) {
			Assert.assertTrue(valid, "Validation failed. " + errorMessage.toString() + " . ");
		}

		// ------Test the Feature Collection

		JSONObject jo = null;
		boolean validCol = false;
		try {

			jo = readJSONObjectFromFile(new File(collectionTestSubject));

			if (jo.has("type")) {

				if (jo.get("type").equals("FeatureCollection")) {
					schema.validate(jo); // throws a ValidationException if this object is
											// invalid
					validCol = true;
				}
				else {
					validCol = false;
					errorMessage.append(
							"Validation of feature collection did not have type property value that equals 'FeatureCollection'\n");
				}
			}
			else {
				validCol = false;
				errorMessage.append("Validation of feature collection did not have type property\n");
			}

		}
		catch (Exception ex) {
			errorMessage.append("Validation of Feature Collection failed because " + ex.getMessage() + "\n");

			validCol = false;
		}

		if (validCol == false) {
			Assert.assertTrue(validCol, "Validation failed. " + errorMessage.toString());
		}

	}

}
