import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoletinSAX4 extends DefaultHandler {
    // int cont = 0;
    ArrayList <String> generos = new ArrayList<>() ; 
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("EJERCICIO 16");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.getLocalName(i).equals("genero")) {
                if (!generos.contains(attributes.getValue(i))) {
                    generos.add(attributes.getValue(i));
                }
            }
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }
    
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.printf("Nº de generos: %d",generos.size());
    }
}