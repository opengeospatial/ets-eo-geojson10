package org.opengis.cite.eogeojson10.links;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>
 * LinksConfClassTests class.
 * </p>
 *
 */
public class LinksConfClassTests extends DataFixture {

	/**
	 * Verifies conformance to /conf/links, Section 7.1.2
	 * @throws java.io.IOException Thrown if the file cannot be read
	 */
	@Test(description = "Implements /conf/links, Section 7.1.2")
	public void validateLinks() throws IOException {
		boolean valid = true;
		StringBuffer sb = new StringBuffer();

		JSONObject jo = readJSONObjectFromFile(new File(testSubject));

		if (jo.has("properties")) {

			JSONObject propertiesJO = jo.getJSONObject("properties");
			if (propertiesJO.has("links")) {
				// do nothing
			}
			else {
				sb.append("links field is missing. \n");
				valid = false;
			}
		}
		else {
			sb.append("links field is missing. \n");
			valid = false;
		}

		Assert.assertTrue(valid, "Validation failed because " + sb.toString() + " . ");

	}

}
