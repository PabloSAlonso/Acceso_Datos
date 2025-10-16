import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejemplo1 extends DefaultHandler {
    String etiqueta;
    boolean flag = false;
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
        String cadena = new String(ch, start, length);
        if (flag){
            System.out.println(cadena);
            flag = false;
        }
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
        etiqueta = qName;
        if (etiqueta.equals("Título")){
            flag = true;
        }
        if (etiqueta.equals("Película")) {
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.println(attributes.getLocalName(i) + " - " + attributes.getValue(i));
            }
        }
    }

}
