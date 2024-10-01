
package javaxml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class Main {
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        String ruta = "/home/alumnadotarde/Escritorio/personas.xml";
        File fichero = new File(ruta);
        
        Document doc = xmlToDOM(fichero);
        Node root = doc.getFirstChild();
        
        listarNodos(root);
        
    }
    
    public static Document xmlToDOM(File fichero) throws ParserConfigurationException, SAXException, IOException{
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(fichero);
        
        return doc;
    }
    
    public static void DOMtoXML(Document doc) throws TransformerException{
        File archivoXML = new File("/home/alumnadotarde/Escritorio/personas2.xml");
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        
        StreamResult result = new StreamResult(archivoXML);
        DOMSource source = new DOMSource(doc);
                
        transformer.transform(source, result);
        
    }
    
    public static void listarNodos(Node node){
        
        
        switch(node.getNodeType()){
            case Node.ELEMENT_NODE:
                
                System.out.println("<"+node.getNodeName()+">");
                
                NodeList hijos = node.getChildNodes();

                for(int i = 0; i<hijos.getLength(); i++){
                    Node hijo = hijos.item(i);
                    listarNodos(hijo);
                }
                
                System.out.println("</"+node.getNodeName()+">");
            break;
                
            case Node.TEXT_NODE:
                Text t = (Text) node;
                System.out.println(t.getWholeText());
                break;
                
           
        }
        
        
    }
    
     

    
}
