package repaso_examen;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class getSax {
    public static void getSax(String entradaXML) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        ejemploSAX parserSax = new ejemploSAX();
        parser.parse(entradaXML, parserSax);
        System.out.println();

        ejemploSAX2 pSax2 = new ejemploSAX2();
        parser.parse(entradaXML, pSax2);
        System.out.println();

        ejemploSAX3 pSax3 = new ejemploSAX3(); //Cambiar cuando la cree
        parser.parse(entradaXML, pSax3);
        System.out.println();

        ejemploSAX4 pSax4 = new ejemploSAX4();
        parser.parse(entradaXML, pSax4);
        System.out.println();

        ejemploSAX5 pSax5 = new ejemploSAX5();
        parser.parse(entradaXML, pSax5);
        System.out.println();

        ejemploSAX6 pSax6 = new ejemploSAX6();
        parser.parse(entradaXML, pSax6);
        System.out.println();

        ejemploSAX7 pSax7 = new ejemploSAX7();
        parser.parse(entradaXML, pSax7);
        System.out.println();

    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        getSax("repaso_examen\\liga.xml");
    }
}