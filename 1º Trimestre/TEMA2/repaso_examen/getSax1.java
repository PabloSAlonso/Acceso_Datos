package repaso_examen;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class getSax1 {
    public static void getSax(String entradaXML) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        ejemploSAX parserSax = new ejemploSAX();
        parser.parse(entradaXML, parserSax);
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        getSax("repaso_examen\\liga.xml");
    }
}