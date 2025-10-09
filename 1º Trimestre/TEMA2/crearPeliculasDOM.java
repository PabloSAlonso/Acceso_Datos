import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class crearPeliculasDOM {

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

    public static void mostrarTitulos(Document doc) {
        Node filmoteca, peliculaNode, tituloDirector;
        NodeList pelicula, hijosPelicula;
        filmoteca = doc.getFirstChild();
        pelicula = filmoteca.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            peliculaNode = pelicula.item(i);
            if (peliculaNode.getNodeType() == Node.ELEMENT_NODE) {
                hijosPelicula = peliculaNode.getChildNodes();
                for (int j = 0; j < hijosPelicula.getLength(); j++) {
                    tituloDirector = hijosPelicula.item(j);
                    if (tituloDirector.getNodeType() == Node.ELEMENT_NODE && tituloDirector.getNodeName() == "titulo") {
                        System.out.println(tituloDirector.getTextContent());
                    }
                }
            }
        }
    }

    public static void mostrarPeliculas(Document doc) {
        Node filmoteca, peliculaNode, tituloDirector;
        NodeList pelicula, hijosPelicula, directores;
        NamedNodeMap atributos;
        filmoteca = doc.getFirstChild();
        pelicula = filmoteca.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            peliculaNode = pelicula.item(i);
            if (peliculaNode.hasAttributes()) {
                atributos = peliculaNode.getAttributes();
                for (int j = 0; j < atributos.getLength(); j++) {
                    if (atributos.item(j).getNodeName() == "genero") {
                        System.out.println("Genero: " + atributos.item(j).getTextContent());
                    }
                }
            }
            if (peliculaNode.getNodeType() == Node.ELEMENT_NODE) {
                hijosPelicula = peliculaNode.getChildNodes();
                for (int j = 0; j < hijosPelicula.getLength(); j++) {
                    tituloDirector = hijosPelicula.item(j);
                    if (tituloDirector.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println(tituloDirector.getNodeName() + " - " + tituloDirector.getTextContent());
                    }
                }
            }
        }
    }

    public static void contarDirectores(Document doc, int n) {
        Node filmotecaNode;
        Element peliculaNode;
        NodeList pelicula, directores, titulos;
        filmotecaNode = doc.getFirstChild();
        pelicula = filmotecaNode.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            if (pelicula.item(i).getNodeType() == Node.ELEMENT_NODE) {
                peliculaNode = (Element) pelicula.item(i);
                directores = peliculaNode.getElementsByTagName("director");
                titulos = peliculaNode.getElementsByTagName("titulo");
                if (directores.getLength() > n) {
                    System.out.printf("%S: %s\n", titulos.item(0).getNodeName(), titulos.item(0).getTextContent());
                }
            }
        }
    }

    public static void main(String[] args) {
        String ruta = "peliculas.xml";
        Document doc = creaArbol(ruta);
        // mostrarTitulos(doc);
        // mostrarPeliculas(doc);
        contarDirectores(doc, 1);;

    }
}