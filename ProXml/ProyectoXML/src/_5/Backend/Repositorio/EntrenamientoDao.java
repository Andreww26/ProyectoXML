package _5.Backend.Repositorio;
import _5.Backend.Modelo.Entrenamiento;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;


public class EntrenamientoDao {
    ArrayList<Entrenamiento> listaEntrenamientos = new ArrayList<Entrenamiento>();
    File file = new File("C:/xampp/htdocs/dashboard/datos_ajax/datos/entrenamientos.xml");

    //Agregar entrenamiento
    public void agregarEntrenamiento(Entrenamiento entrenamiento) {
        try {
            Document doc = parseDoc();
            Element root = doc.getDocumentElement();

            doc.getDocumentElement().normalize();
            limpiarEspacios(doc);


            //Crear nuevo elemento
            Element nuevoEntrenamiento = doc.createElement("entrenamiento");
            nuevoEntrenamiento.setAttribute("id", String.valueOf(entrenamiento.getId()));

            Element nombre = doc.createElement("nombre");
            nombre.setTextContent(entrenamiento.getNombre());

            Element duracion = doc.createElement("duracion");
            duracion.setTextContent(String.valueOf(entrenamiento.getDuracion()));

            Element nivel = doc.createElement("nivel");
            nivel.setTextContent(entrenamiento.getNivel());
            

            nuevoEntrenamiento.appendChild(nombre);
            nuevoEntrenamiento.appendChild(duracion);
            nuevoEntrenamiento.appendChild(nivel);

            root.appendChild(nuevoEntrenamiento);

            //Guardar lo cambios en el xml
            guardarCambios(doc);

            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Leer todos
    public ArrayList<Entrenamiento> leerTodos() {

        try {
            Document doc = parseDoc();
            Element root = doc.getDocumentElement();
            doc.getDocumentElement().normalize();
            limpiarEspacios(doc);

            NodeList listaEntrenos = doc.getElementsByTagName("entrenamiento");

            for (int i=0; i<listaEntrenos.getLength();i++){
                Node nodo = listaEntrenos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE){

                    Element elemento = (Element) nodo;
                    int id = Integer.parseInt(elemento.getAttribute("id"));
                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    int duracion = Integer.parseInt(elemento.getElementsByTagName("duracion").item(0).getTextContent());
                    String nivel = elemento.getElementsByTagName("nivel").item(0).getTextContent();
                    listaEntrenamientos.add(new Entrenamiento(id,nombre,duracion,nivel));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }return listaEntrenamientos;
    }

    //Actualizar
    public void actualizarEntrenamiento(int id, Entrenamiento nuevoEntrenamiento){
        try{
            Document doc = parseDoc();
            Element root = doc.getDocumentElement();
            doc.getDocumentElement().normalize();
            limpiarEspacios(doc);

            NodeList listaEntrenos = doc.getElementsByTagName("entrenamiento");
            for (int i = 0; i< listaEntrenos.getLength(); i++){
                Node nodo = listaEntrenos.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element elemento = (Element) nodo;

                    if (Integer.parseInt(elemento.getAttribute("id")) == id) {

                        elemento.getElementsByTagName("nombre").item(0).setTextContent(nuevoEntrenamiento.getNombre());
                        elemento.getElementsByTagName("duracion").item(0).setTextContent(String.valueOf(nuevoEntrenamiento.getDuracion()));
                        elemento.getElementsByTagName("nivel").item(0).setTextContent(nuevoEntrenamiento.getNivel());
                    }
                }
            }
            //Guardar lo cambios en el xml
            guardarCambios(doc);

            } catch (Exception e){
            e.printStackTrace();
        }
    }

    //Eliminar
    public void eliminarEntrenamiento(int id) {
        try {
            Document doc = parseDoc();
            doc.getDocumentElement().normalize();
            limpiarEspacios(doc);
            NodeList listaEntrenos = doc.getElementsByTagName("entrenamiento");
            int i=0;
            Node nodo =null;

            while (i<listaEntrenos.getLength()) {
                nodo = listaEntrenos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;

                    if (Integer.parseInt(elemento.getAttribute("id")) == id) {
                        elemento.getParentNode().removeChild(elemento);
                        break;
                    }
                } i++;
            }
            //Guardar lo cambios en el xml
            guardarCambios(doc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void guardarCambios(Document doc){
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DocumentBuilder obtenerDocumentBuilder() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder();
    }
    private Document parseDoc() throws Exception{
        DocumentBuilder builder = obtenerDocumentBuilder();
        return builder.parse(file);
    }
    private void eliminarEspaciosEnBlanco(Node nodo) {
        NodeList listaEntrenos = nodo.getChildNodes();
        Node node = null;
        for (int i = listaEntrenos.getLength() - 1; i >= 0; i--) {
            node = listaEntrenos.item(i);
            if (node.getNodeType() == Node.TEXT_NODE && node.getTextContent().trim().isEmpty()) {
                nodo.removeChild(node);
            } else if (node.getNodeType() == Node.ELEMENT_NODE) {
                eliminarEspaciosEnBlanco(node);
            }
        }
    }
    private void limpiarEspacios(Document documento) {
        documento.normalizeDocument();
        eliminarEspaciosEnBlanco(documento.getDocumentElement());
    }

    private boolean validarEntrenamiento(int id, String nombre, int duracion, String nivel) {
        if (id <= 0) {
            System.out.println("El ID debe ser mayor a 0.");
            return false;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return false;
        }
        if (duracion <= 0) {
            System.out.println("La duración debe ser mayor a 0.");
            return false;
        }
        if (nivel == null || nivel.trim().isEmpty()) {
            System.out.println("El nivel no puede estar vacío.");
            return false;
        }
        return true;
    }

}


