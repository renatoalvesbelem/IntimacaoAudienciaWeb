package com.util;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
public class XMLToObject {
    private final String objectXML;
    private final File file;
    private Object objectXMLFilled = null;

    public XMLToObject(Object className, String pathXml) {
        objectXML = className.getClass().getCanonicalName();
        file = new File(pathXml);
    }

    public Object instanceObjectParsed() {
        JAXBContext jaxbContext;
        Unmarshaller jaxbUnmatchable;
        try {

            jaxbContext = JAXBContext.newInstance(Class.forName(objectXML));
            jaxbUnmatchable = jaxbContext.createUnmarshaller();
            objectXMLFilled = jaxbUnmatchable.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objectXMLFilled;
    }
}
