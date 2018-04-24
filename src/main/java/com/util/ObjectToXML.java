package com.util;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class ObjectToXML {
    public void createXML(Object objectXML, String absolutePath) {
        try {
            File file = new File(absolutePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Class.forName(objectXML.getClass().getCanonicalName()));
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(objectXML, file);
            jaxbMarshaller.marshal(objectXML, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
