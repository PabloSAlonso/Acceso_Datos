import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class prueba {
    public static void main(String[] args) {
        String ruta = "archivo.xml";
        Document doc = creaArbol(ruta);
        recorrerArbol(doc, "Dune");
    }

    public static void recorrerArbol(Document doc, String titulo) {
        NodeList titulos = doc.getElementsByTagName("Título");
        NodeList aux;
        Node tit;
        Element padre;
        for (int i = 0; i < titulos.getLength(); i++) {
            tit = titulos.item(i);
            if (tit.getTextContent().equals(titulo)) {
                padre = (Element) tit.getParentNode();
                aux = padre.getElementsByTagName("Director");
                System.out.println(aux.item(0).getFirstChild().getNodeValue());
            }
        }
    }

    public static Document creaArbol(String ruta) {
        Document doc = null;
        try {
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            factoria.setIgnoringComments(true);
            DocumentBuilder builder = factoria.newDocumentBuilder();
            doc = builder.parse(ruta);
        } catch (Exception e) {
            System.out.println("Error generando el árbol DOM: " + e.getMessage());
        }
        return doc;
    }
}
