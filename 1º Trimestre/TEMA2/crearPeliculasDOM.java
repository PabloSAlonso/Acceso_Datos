import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

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

    public static void diferentesGeneros(Document doc) {
        Node filmotecaNode, peliculaNode;
        NodeList pelicula;
        ArrayList<String> generos = new ArrayList<>();
        NamedNodeMap atributos;
        filmotecaNode = doc.getFirstChild();
        pelicula = filmotecaNode.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) {
            if (pelicula.item(i).getNodeType() == Node.ELEMENT_NODE) {
                peliculaNode = (Element) pelicula.item(i);
                if (peliculaNode.hasAttributes()) {
                    atributos = peliculaNode.getAttributes();
                    for (int j = 0; j < atributos.getLength(); j++) {
                        if (atributos.item(j).getNodeName() == "genero") {
                            if (!generos.contains(atributos.item(j).getTextContent())) {
                                generos.add(atributos.item(j).getTextContent());
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < generos.size(); i++) {
            System.out.println(generos.get(i));
        }
        System.out.printf("Nº DE GENEROS DIFRENTES %d", generos.size());
    }

    // Dado o título dunha película engádalle, se non existe, un atributo pasado
    // como parámetro.
    // Ø Dado o título dunha película e un atributo, se existe elimíneo.

    public static void añadirAtributo(Document doc, String tituloPelicula, String atributo) {
        NodeList peliculas = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < peliculas.getLength(); i++) {
            Element pelicula = (Element) peliculas.item(i);
            String titulo = pelicula.getElementsByTagName("titulo").item(0).getTextContent();
            if (titulo.equalsIgnoreCase(tituloPelicula) && !pelicula.hasAttribute(atributo)) {
                pelicula.setAttribute("version", atributo);
                System.out.println("Atributo añadido");
            }
        }
    }

    public static void eliminarAtributo(Document doc, String tituloPelicula, String atributo) {
        NodeList peliculas = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < peliculas.getLength(); i++) {
            Element pelicula = (Element) peliculas.item(i);
            String titulo = pelicula.getElementsByTagName("titulo").item(0).getTextContent();
            if (titulo.equalsIgnoreCase(tituloPelicula) && pelicula.hasAttribute(atributo)) {
                pelicula.removeAttribute(atributo);
                System.out.println(atributo + "eliminado");
            }
        }
    }

    public static void grabarDOM(Document document, String ficheroSalida)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, FileNotFoundException {
        DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
        DOMImplementationLS ls = (DOMImplementationLS) registry.getDOMImplementation("XML 3.0 LS 3.0");
        // Se crea un destino vacio
        LSOutput output = ls.createLSOutput();
        output.setEncoding("UTF-8");
        // Se establece el flujo de salida
        output.setByteStream(new FileOutputStream(ficheroSalida));
        // output.setByteStream(System.out);
        // Permite escribir un documento DOM en XML
        LSSerializer serializer = ls.createLSSerializer();
        // Se establecen las propiedades del serializador
        serializer.setNewLine("\r\n");
        ;
        serializer.getDomConfig().setParameter("format-pretty-print", true);
        // Se escribe el documento ya sea en un fichero o en una cadena de texto
        serializer.write(document, output);
        // String xmlCad=serializer.writeToString(document);
    }

    // 8. Engade a seguinte película "Depredador" dirixida en 1987 por John Tiernan
    // dentro do xénero acción. Esta en versión orixinal. Almacena esta árbore nun
    // ficheiro XML.
    public static void añadirPelicula(Document doc, String nombre, String directorNombre, String directorApellido,
            String año, String tipo,
            String idioma) {
        Element nodoPelicula = doc.createElement("pelicula");
        nodoPelicula.setAttribute("genero", tipo);
        nodoPelicula.setAttribute("año", año);
        nodoPelicula.setAttribute("idioma", idioma);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Element nodoTitulo = doc.createElement("titulo");
        nodoTitulo.appendChild(doc.createTextNode(nombre));
        nodoPelicula.appendChild(nodoTitulo);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Element nodoDirector = doc.createElement("director");
        Node nomTexto = nodoDirector.appendChild(doc.createElement("nombre"));
        nomTexto.appendChild(doc.createTextNode(directorNombre));
        Node apeTexto = nodoDirector.appendChild(doc.createElement("apellido"));
        apeTexto.appendChild(doc.createTextNode(directorApellido));
        nodoPelicula.appendChild(nodoDirector);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Node raiz = doc.getFirstChild();
        raiz.appendChild(nodoPelicula);
        raiz.appendChild(doc.createTextNode("\n"));
    }

    // 9. Larry Wachowski cambiou o seu nome por Lana. Modifica a árbore e
    // almacénao.
    public static void wachowski(Document doc, String nomInicial, String nomFinal, String apellido) {
        NodeList directores = doc.getElementsByTagName("director");
        for (int i = 0; i < directores.getLength(); i++) {
            Node director = directores.item(i);
            if (director.getFirstChild().getNodeType() != Node.ELEMENT_NODE) {
                Node nom = director.getFirstChild().getNextSibling();
                NodeList ape = doc.getElementsByTagName("apellido");
                for (int j = 0; j < ape.getLength(); j++) {
                    if (ape.item(j).getTextContent().equals(apellido) && nom.getTextContent().equals(nomInicial)) {
                        nom.setTextContent(nomFinal);
                    }
                }
            }
        }
    }

    // 10. Decatámonos que Alfredo Landa axudo a David Lynch a dirixir Dune. Engádeo
    // como director e almacena esta modificación en nova árbore.

    public static void añadirAlfredo(Document doc, String nomDirector, String añadirDirector, String tituloPeli) {
        NodeList peliculas = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < peliculas.getLength(); i++) {
            Node titulo = peliculas.item(i).getFirstChild().getNextSibling();
            if(titulo.getFirstChild().getNodeType() == Node.ELEMENT_NODE && titulo.getTextContent().equals(tituloPeli)){
                System.out.println(titulo.getTextContent());
                Element pelicula = (Element) titulo.getParentNode();
                Element nuevoDirector = doc.createElement(añadirDirector);
                pelicula.appendChild(nuevoDirector);
                pelicula.appendChild(doc.createTextNode(""));
                Element nomDir = doc.createElement("nombre");
                nomDir.appendChild(doc.createTextNode(añadirDirector));
            }
        }

    }

    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        String ruta = "peliculas.xml";
        Document doc = creaArbol(ruta);
        // mostrarTitulos(doc);
        // mostrarPeliculas(doc);
        // contarDirectores(doc, 1);
        // añadirAtributo(doc, "El Señor de los Anillos", "nom Atributo");
        // añadirPelicula(doc, "Depredador", "Jhon", "Tiernan", "1987", "acción", "vo");
        // wachowski(doc, "Larry", "Lana", "Wachowski");
        añadirAlfredo(doc, "David", "Alfredo");
        grabarDOM(doc, ruta);

    }
    // https://prod.liveshare.vsengsaas.visualstudio.com/join?532D038200169E890BD9FA0C2FE98658E4CC
}
