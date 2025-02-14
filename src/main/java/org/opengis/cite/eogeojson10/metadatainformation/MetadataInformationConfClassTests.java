package org.opengis.cite.eogeojson10.metadatainformation;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.opengis.cite.eogeojson10.DataFixture;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>
 * MetadataInformationConfClassTests class.
 * </p>
 *
 */
public class MetadataInformationConfClassTests extends DataFixture {

	/**
	 * Verifies conformance to /conf/metadata-information, Section 7.2
	 * @throws java.io.IOException Thrown if the file cannot be read
	 */
	@Test(description = "Implements /conf/metadata-information, Section 7.2")
	public void validateMetadataInformation() throws IOException {
		boolean valid = true;
		StringBuffer sb = new StringBuffer();

		JSONObject jo = readJSONObjectFromFile(new File(testSubject));

		if (jo.has("properties")) {

			JSONObject propertiesJO = jo.getJSONObject("properties");
			if (propertiesJO.has("updated")) {
				// do nothing
			}
			else {
				sb.append("updated field is missing. \n");
				valid = false;
			}
		}
		else {
			sb.append("updated field is missing. \n");
			valid = false;
		}

		Assert.assertTrue(valid, "Validation failed because " + sb.toString() + " . ");
	}

}
