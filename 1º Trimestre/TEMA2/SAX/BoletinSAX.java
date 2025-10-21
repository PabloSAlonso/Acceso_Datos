import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BoletinSAX extends DefaultHandler {
    boolean flag = false;
    String titulo = "";

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
        titulo = new String(ch, start, length);
        System.out.print(titulo);
    }

    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.endDocument();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // TODO Auto-generated method stub
        super.endElement(uri, localName, qName);
        System.out.printf("</%s>", qName);

    }

    @Override
    public void startDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.startDocument();
        System.out.println("EJERCICIO 14");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        super.startElement(uri, localName, qName, attributes);
        System.out.printf("<%s>", qName);
    }
}
