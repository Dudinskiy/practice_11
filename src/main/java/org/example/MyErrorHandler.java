package org.example;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class MyErrorHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException e) {
        System.err.println("Warning (line " + e.getLineNumber() + ") : " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) {
        System.err.println("Error (line " + e.getLineNumber() + ") : " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) {
        System.err.println("FatalError (line " + e.getLineNumber() + ") : " + e.getMessage());
    }
}

