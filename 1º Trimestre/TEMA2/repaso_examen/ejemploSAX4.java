package repaso_examen;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ejemploSAX4 extends DefaultHandler {
    boolean esGol = false;
    boolean esNombre = false;
    int maxGoles = 0;
    String goles = "";
    String equipoGoleador = "";
    String equipo = "";

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
        goles = new String(ch, start, length);
        equipo = new String(ch, start, length);
        if (esNombre){
            equipoGoleador = equipo;
            esNombre = false;
        }
        if (esGol) {
            if (Integer.parseInt(goles) > maxGoles) {
                maxGoles = Integer.parseInt(goles);
            }
            esGol = false;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.endDocument();
        System.out.println(maxGoles);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // TODO Auto-generated method stub
        super.endElement(uri, localName, qName);
    }

    @Override
    public void startDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.startDocument();

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        super.startElement(uri, localName, qName, attributes);
        if (qName == "name") {
            esNombre = true;
        }

        if (qName == "goals_scored") {
            esGol = true;
        }

    }
}
