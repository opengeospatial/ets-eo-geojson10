package org.opengis.cite.eogeojson10.dataidentification;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>
 * DataIdentificationConfClassTests class.
 * </p>
 *
 */
public class DataIdentificationConfClassTests extends DataFixture {

	/**
	 * Verifies conformance to /conf/data-identification, Section 7.3
	 * @throws java.io.IOException Thrown if the file cannot be read
	 */
	@Test(description = "Implements /conf/data-identification, Section 7.3")
	public void validateDataIdentification() throws IOException {
		boolean valid = true;
		StringBuffer sb = new StringBuffer();
		String[] mandatoryFields = { "title", "identifier", "date" };

		JSONObject jo = readJSONObjectFromFile(new File(testSubject));

		if (jo.has("properties")) {

			JSONObject propertiesJO = jo.getJSONObject("properties");
			for (String prop : mandatoryFields) {
				if (propertiesJO.has(prop) != true) {
					sb.append(prop + " field is missing. \n");
					valid = false;
				}
			}
		}

		Assert.assertTrue(valid, "Validation failed because " + sb.toString() + " . ");
	}

}
