import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
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

    public static void mostrarPelisConNDirectores(Document doc, int numDirectores) {
        Node filmoteca, peliculaNode, tituloDirector;
        NodeList pelicula, hijosPelicula, directores;
        NamedNodeMap atributos;
        filmoteca = doc.getFirstChild();
        pelicula = filmoteca.getChildNodes();
        int contadorDeDirectores = 0; ///

        for (int i = 0; i < pelicula.getLength(); i++) { // Si pelicula[i] tiene dos o mas directores no muestra
            peliculaNode = pelicula.item(i);
            if (peliculaNode.getNodeType() == Node.ELEMENT_NODE) {
                hijosPelicula = peliculaNode.getChildNodes();
                for (int j = 0; j < hijosPelicula.getLength(); j++) {
                    while (hijosPelicula.item(j).getNodeName().equals("director")) { // este qhile quitalo que es una mierda
                        contadorDeDirectores++;
                        if (contadorDeDirectores < numDirectores) {
                            tituloDirector = hijosPelicula.item(j);
                            if (tituloDirector.getNodeType() == Node.ELEMENT_NODE) {
                                System.out.println(
                                        tituloDirector.getNodeName() + " - " + tituloDirector.getTextContent());
                            }
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        String ruta = "peliculas.xml";
        Document doc = creaArbol(ruta);
        // mostrarTitulos(doc);
        // mostrarPeliculas(doc);
        mostrarPelisConNDirectores(doc, 2);

    }
}
// https://prod.liveshare.vsengsaas.visualstudio.com/join?25C8C09B654471D4E027496AC08B8159ADC9