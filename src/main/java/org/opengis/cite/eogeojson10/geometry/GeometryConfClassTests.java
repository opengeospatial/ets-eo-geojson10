package org.opengis.cite.eogeojson10.geometry;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>
 * GeometryConfClassTests class.
 * </p>
 *
 */
public class GeometryConfClassTests extends DataFixture {

	/**
	 * Verifies conformance to /conf/geometry, Section 7.4
	 * @throws java.io.IOException Thrown if the file cannot be read
	 */
	@Test(description = "Implements /conf/geometry, Section 7.4")
	public void validateGeometry() throws IOException {
		JSONObject jo = readJSONObjectFromFile(new File(testSubject));
		String[] mandatoryFields = { "geometry" };

		boolean valid = true;
		StringBuffer sb = new StringBuffer();

		for (String prop : mandatoryFields) {
			if (jo.has(prop) != true) {
				sb.append(prop + " field is missing. \n");
				valid = false;
			}
		}

		Assert.assertTrue(valid, "Validation failed because " + sb.toString() + " . ");
	}

}
