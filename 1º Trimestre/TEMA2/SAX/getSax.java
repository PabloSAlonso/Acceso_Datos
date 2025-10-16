import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class getSax {
    public static void getSax(String entradaXML) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Ejemplo1 parserSax = new Ejemplo1();
        parser.parse(entradaXML, parserSax);
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        getSax("DOM\\archivo.xml");
    }
}