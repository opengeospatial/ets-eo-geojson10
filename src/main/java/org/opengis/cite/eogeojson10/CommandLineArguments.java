package org.opengis.cite.eogeojson10;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;

/**
 * Declares supported command line arguments that are parsed using the JCommander library.
 * All arguments are optional. The default values are as follows:
 * <ul>
 * <li>XML properties file: ${user.home}/test-run-props.xml</li>
 * <li>outputDir: ${user.home}</li>
 * <li>deleteSubjectOnFinish: false</li>
 * </ul>
 *
 * <p>
 * <strong>Synopsis</strong>
 * </p>
 *
 * <pre>
 * ets-${ets-code}-${version}-aio.jar [-o|--outputDir $TMPDIR] [-d|--deleteSubjectOnFinish] [test-run-props.xml]
 * </pre>
 */
public class CommandLineArguments {

	@Parameter(description = "Properties file")
	private List<String> xmlProps;

	@Parameter(names = { "-o", "--outputDir" }, description = "Output directory")
	private String outputDir;

	@Parameter(names = { "-d", "--deleteSubjectOnFinish" },
			description = "Delete file containing representation of test subject when finished")
	private boolean deleteSubjectOnFinish = false;

	/**
	 * <p>
	 * Constructor for CommandLineArguments.
	 * </p>
	 */
	public CommandLineArguments() {
		this.xmlProps = new ArrayList<>();
	}

	/**
	 * <p>
	 * getPropertiesFile.
	 * </p>
	 * @return a {@link java.io.File} object
	 */
	public File getPropertiesFile() {
		File fileRef;
		if (xmlProps.isEmpty()) {
			fileRef = new File(System.getProperty("user.home"), "test-run-props.xml");
		}
		else {
			String propsFile = xmlProps.get(0);
			fileRef = (propsFile.startsWith("file:")) ? new File(URI.create(propsFile)) : new File(propsFile);
		}
		return fileRef;
	}

	/**
	 * <p>
	 * Getter for the field <code>outputDir</code>.
	 * </p>
	 * @return a {@link java.lang.String} object
	 */
	public String getOutputDir() {
		return (null != outputDir) ? outputDir : System.getProperty("user.home");
	}

	/**
	 * <p>
	 * doDeleteSubjectOnFinish.
	 * </p>
	 * @return a boolean
	 */
	public boolean doDeleteSubjectOnFinish() {
		return deleteSubjectOnFinish;
	}

}
