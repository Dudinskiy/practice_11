package org.example;

import org.example.model.Group;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        File source = new File(args[0]);
        File result = new File(args[1]);
        Group group;

        try {
            group = XmlProcessor.convertFromXml(source, true);
            XmlProcessor.convertToXml(group, result);
        } catch (ParserConfigurationException | IOException
                | SAXException | JAXBException e) {
            e.printStackTrace();
        }
    }
}

