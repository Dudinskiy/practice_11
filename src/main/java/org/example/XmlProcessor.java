package org.example;

import org.xml.sax.ErrorHandler;
import org.example.model.Group;
import org.example.model.Student;
import org.example.model.Subject;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XmlProcessor {
    private static final String attribute_firstname = "firstname";
    private static final String attribute_lastname = "lastname";
    private static final String attribute_groupnumber = "groupnumber";
    private static final String attribute_title = "title";
    private static final String attribute_mark = "mark";
    private static final String tag_subject = "subject";
    private static final String tag_average = "subject";


    public static Group convertFromXml(File source, boolean validating)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(validating);

        DocumentBuilder builder = factory.newDocumentBuilder();

        ErrorHandler handler = new MyErrorHandler();
        builder.setErrorHandler(handler);

        Document document = builder.parse(source);

        Group group = new Group();
        List<Student> studentList = new ArrayList<>();

        Node groupNode = document.getLastChild();
        NodeList groupChildNodes = groupNode.getChildNodes();

        for (int i = 0; i < groupChildNodes.getLength(); i++) {
            if (groupChildNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                NamedNodeMap studentAttr = groupChildNodes.item(i).getAttributes();
                String firstName = studentAttr.getNamedItem(attribute_firstname).getNodeValue();
                String lastName = studentAttr.getNamedItem(attribute_lastname).getNodeValue();
                String groupNumber = studentAttr.getNamedItem(attribute_groupnumber).getNodeValue();

                Student student = new Student(firstName, lastName, groupNumber);
                Set<Subject> subjects = new HashSet<>();
                double averageFromXml = 0;
                studentList.add(student);

                NodeList studentChildNodes = groupChildNodes.item(i).getChildNodes();

                for (int j = 0; j < studentChildNodes.getLength(); j++) {
                    if (studentChildNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        if (studentChildNodes.item(j).getNodeName().equals(tag_subject)) {
                            NamedNodeMap subjectAttr = studentChildNodes.item(j).getAttributes();
                            String title = subjectAttr.getNamedItem(attribute_title).getNodeValue();
                            int subjectMark = Integer.parseInt(subjectAttr.getNamedItem(attribute_mark).getNodeValue());

                            Subject subject = new Subject(title, subjectMark);
                            subjects.add(subject);

                            student.setSubjects(subjects);

                        } else if (studentChildNodes.item(j).getNodeName().equals(tag_average)) {
                            String averageStr = studentChildNodes.item(j).getTextContent();
                            averageFromXml = Double.parseDouble(averageStr);
                        }
                    }
                }
                student.setAverage(averageFromXml);
            }
        }
        group.setStudents(studentList);

        return group;
    }

    public static void convertToXml(Group group, File result) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Group.class);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(group, result);
        marshaller.marshal(group, System.out);
    }
}

