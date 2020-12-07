package ru.napadovskiu;

import ru.napadovskiu.setings.Settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class WorkWithXMLFile {

    /**
     * Setting for properties.
     */
    private static Settings settings = new Settings();


    /**
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(WorkWithXMLFile.class);

    /**
     * Path for directory with xml file.
     */
    private Path xmlDirectory;

    /**
     * Constructor for class.
     */
    public WorkWithXMLFile() {
        ClassLoader loader = Settings.class.getClassLoader();
        InputStream io = loader.getResourceAsStream("app.properties");
        this.settings.load(io);
        this.xmlDirectory = Paths.get(getXMLDirectory());

    }

    /**
     * Method  return root directory for xml files.
     * @return root directory.
     */
    private String getXMLDirectory() {
        ClassLoader loader = Settings.class.getClassLoader();
        File fileProperties = new File(loader.getResource("app.properties").getFile());
        return fileProperties.getParent();
    }


    /**
     * Method convert xml file with XSL
     */
    public void convertXmlFile() {
        File secondXMLFile =  createXmlFile(this.settings.getValue("secondXML"));
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(this.xmlDirectory + this.settings.getValue("styleXSL")));
        try {
            Transformer transformer = factory.newTransformer(xslt);
            Source text = new StreamSource(new File(this.xmlDirectory + this.settings.getValue("firstXML")));
            transformer.transform(text, new StreamResult(secondXMLFile));
        } catch (TransformerConfigurationException e) {
            LOG.error(e.getMessage(), e);
        } catch (TransformerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method calculate amount all value in file.
     * @return amount
     */
    public int  calculateAmount() {
        int result = 0;
        XMLInputFactory factory = XMLInputFactory.newInstance();

        InputStream in = null;
        try {
            in = new FileInputStream(this.xmlDirectory + this.settings.getValue("secondXML"));
            XMLStreamReader streamReader = factory.createXMLStreamReader(in);

            while (streamReader.hasNext()) {
                if (streamReader.isStartElement()) {
                    if (streamReader.getLocalName().equals("entry")) {
                        if (streamReader.getAttributeValue(0) != null) {
                            String number = streamReader.getAttributeValue(0);
                            result = result + Integer.parseInt(number);
                        }
                    }
                }
                streamReader.next();
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

        return  result;
    }


    /**
     * Method create xml file.
     * @param fileName file name.
     * @return file.
     */
    public File createXmlFile(String fileName) {
        File file = null;
        ClassLoader loader = Settings.class.getClassLoader();
        File fileProperties = new File(loader.getResource("app.properties").getFile());
        try {
            Path path = Paths.get(fileProperties.getParent() + fileName);
            if (Files.deleteIfExists(path)) {
                System.out.println("deleted existing file.");
            }
            Path newFilePath = Files.createFile(path);
            file = newFilePath.toFile();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return file;
    }

}
