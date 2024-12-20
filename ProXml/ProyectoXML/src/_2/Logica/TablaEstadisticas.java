package _2.Logica;

import _5.Backend.Repositorio.EntrenamientoDao;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileWriter;

public class TablaEstadisticas {
    private EntrenamientoDao entrenamientoDao;

    public TablaEstadisticas(EntrenamientoDao entrenamientoDao) {
        this.entrenamientoDao = entrenamientoDao;
    }

    public void generarTablas() {
        try {

            Document doc = parseDoc();
            Element root = doc.getDocumentElement();
            doc.getDocumentElement().normalize();

            int totalElementos = contarElementos(root);
            int totalAtributos = contarAtributos(root);
            int totalNodos = contarNodos(root);

            FileWriter csvWriter = new FileWriter("C:/xampp/htdocs/dashboard/datos_ajax/estadisticas/estadisticas.csv");
            csvWriter.append("Nombre,Valores\n");
            csvWriter.append("Total Elementos,").append(String.valueOf(totalElementos)).append("\n");
            csvWriter.append("Total Atributos,").append(String.valueOf(totalAtributos)).append("\n");
            csvWriter.append("Total Nodos,").append(String.valueOf(totalNodos)).append("\n");
            csvWriter.close();

            System.out.println("Archivo CSV generado correctamente: estadisticas.csv");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Document parseDoc() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse("xml/entrenamientos.xml");
    }

    // Métodos para contar elementos
    private int contarAtributos(Element elemento) {
        NodeList nodo = elemento.getChildNodes();
        int contador = 1;

        for (int i = 0; i < nodo.getLength(); i++) {
            Node node = nodo.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                contador += contarAtributos((Element) node);
            }
        }
        return contador;
    }

    // Métodos para contar atributos
    private int contarElementos(Element elemento) {
        NamedNodeMap atributos = elemento.getAttributes();
        int contador = atributos.getLength();

        NodeList nodos = elemento.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            Node node = nodos.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                contador += contarElementos((Element) node);
            }

        }
        return contador;
    }

    // Métodos para contar nodos
    private int contarNodos(Node nodo) {
        int contador = 1;

        NodeList nodos = nodo.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            if (nodos.item(i).getNodeType() != Node.TEXT_NODE || !nodos.item(i).getTextContent().trim().isEmpty()) {
                contador += contarNodos(nodos.item(i));
            }
        }
        return contador;
    }

}
