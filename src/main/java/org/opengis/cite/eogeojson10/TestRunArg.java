package org.opengis.cite.eogeojson10;

/**
 * An enumerated type defining all recognized test run arguments.
 */
public enum TestRunArg {

    /**
     * An absolute URI that refers to a representation of the test subject or
     * metadata about it.
     */
    COL,    
    /**
     * An absolute URI that refers to a representation of the test subject or
     * metadata about it.
     */
    IUT;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
