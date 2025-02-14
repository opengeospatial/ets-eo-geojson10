package org.opengis.cite.eogeojson10;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

/**
 * Includes various tests of capability 1.
 */
public class DataFixture {

	/**
	 * testSubject
	 */
	protected String testSubject;

	/**
	 * collectionTestSubject
	 */
	protected String collectionTestSubject;

	/**
	 * DEFAULT_BUFFER_SIZE = 8192
	 */
	protected final int DEFAULT_BUFFER_SIZE = 8192;

	/**
	 * Obtains the test subject from the ISuite context.
	 * @param testContext The test (group) context.
	 */
	@BeforeClass
	public void obtainTestSubject(ITestContext testContext) {
		Object obj = testContext.getSuite().getAttribute(SuiteAttribute.TEST_SUBJ_FILE.getName());

		if (null != obj) {
			this.testSubject = obj.toString();
		}

		// -----
		Object collectionObj = testContext.getSuite().getAttribute(SuiteAttribute.COL_TEST_SUBJ_FILE.getName());

		if (null != collectionObj) {
			this.collectionTestSubject = collectionObj.toString();
		}
	}

	/**
	 * Sets the test subject. This method is intended to facilitate unit testing.
	 * @param testSubject A Document node representing the test subject or metadata about
	 * it.
	 */
	public void setTestSubject(String testSubject) {
		this.testSubject = testSubject;
	}

	/**
	 * <p>
	 * readJSONObjectFromFile.
	 * </p>
	 * @param filePath a {@link java.io.File} object
	 * @return a {@link org.json.JSONObject} object
	 * @throws java.io.IOException if any.
	 */
	public JSONObject readJSONObjectFromFile(File filePath) throws IOException {

		FileInputStream is = new FileInputStream(filePath);
		try (Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.toString())) {
			scanner.useDelimiter("\\A");

			return new JSONObject(scanner.hasNext() ? scanner.next() : "");
		}

	}

	// from https://mkyong.com/java/how-to-convert-inputstream-to-string-in-java/
	/**
	 * <p>
	 * convertInputStreamToString.
	 * </p>
	 * @param is a {@link java.io.InputStream} object
	 * @return a {@link java.lang.String} object
	 * @throws java.io.IOException if any.
	 */
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
