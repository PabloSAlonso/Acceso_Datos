import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class getSaxBoletin {
    public static void getSax(String entradaXML) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        BoletinSAX parserSax = new BoletinSAX();
        parser.parse(entradaXML, parserSax);
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        getSax("SAX\\peliculas.xml");
    }
}