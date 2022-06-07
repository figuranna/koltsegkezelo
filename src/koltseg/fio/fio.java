package koltseg.fio;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import koltseg.business.BiztosEnum;
import koltseg.business.GetterFunctionName;
import koltseg.business.biztosKoltseg;
import koltseg.business.os.osszKoltseg;
import koltseg.business.pluszKoltseg;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class fio <T>{
    
    public static ArrayList<pluszKoltseg> plusKolteg = fio.beolvaspluszKoltseg();
    public static ArrayList<biztosKoltseg> biztosKoltseg = fio.beolvasbiztosKoltseg();
    
    public static ArrayList<pluszKoltseg> beolvaspluszKoltseg(){
        ArrayList<pluszKoltseg> plus = new ArrayList<>();

        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(osszKoltseg.pfajlnev);
            Element rootElement = doc.getDocumentElement();

            NodeList childNodesList = rootElement.getChildNodes();
            Node node;

            for(int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childNodesOfRuhaTag = node.getChildNodes();

                    String plusz = "", nev = "", ho = "";
                    for(int j = 0; j < childNodesOfRuhaTag.getLength(); j++) {
                        if(childNodesOfRuhaTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch(childNodesOfRuhaTag.item(j).getNodeName()) {
                                case "plusz": plusz = childNodesOfRuhaTag.item(j).getTextContent(); break;
                                case "nev": nev = childNodesOfRuhaTag.item(j).getTextContent(); break;
                                case "ho": ho = childNodesOfRuhaTag.item(j).getTextContent(); break;
                            }

                        }
                    }
                    pluszKoltseg p = new pluszKoltseg(Integer.parseInt(plusz), nev, Integer.parseInt(ho));
                    plus.add(p);

                    }
                }

        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return plus;
    }
    
    public static ArrayList<biztosKoltseg> beolvasbiztosKoltseg(){
        ArrayList<biztosKoltseg> bizt = new ArrayList<>();

        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(osszKoltseg.bfajlnev);
            Element rootElement = doc.getDocumentElement();

            NodeList childNodesList = rootElement.getChildNodes();
            Node node;

            for(int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childNodesOfRuhaTag = node.getChildNodes();

                    String haviKiadas = "", nev = "", ho = "";
                    for(int j = 0; j < childNodesOfRuhaTag.getLength(); j++) {
                        if(childNodesOfRuhaTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch(childNodesOfRuhaTag.item(j).getNodeName()) {
                                case "haviKiadas": haviKiadas = childNodesOfRuhaTag.item(j).getTextContent(); break;
                                case "nev": nev = childNodesOfRuhaTag.item(j).getTextContent(); break;
                                case "ho": ho = childNodesOfRuhaTag.item(j).getTextContent(); break;
                            }

                        }
                    }
                    biztosKoltseg b = new biztosKoltseg(BiztosEnum.valueOf(haviKiadas), nev, Integer.parseInt(ho));
                    bizt.add(b);

                    }
                }

        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return bizt;
    }
    
    public void mentes(T entity, String fajlnev){
        Class clazz = entity.getClass();
        Class superclazz = clazz.getSuperclass();
        try{
            File f = new File(fajlnev);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xml = db.parse(f);
            Element iroeszkoz = xml.createElement("koltseg");
            Field[] tuls = clazz.getDeclaredFields();
            System.out.println(Arrays.toString(tuls));
            for(Field tul : tuls){
                if(tul.getAnnotation(GetterFunctionName.class) != null){
                    String gfn = tul.getAnnotation(GetterFunctionName.class).name();
                    //előállítani a getter metódust:
                    Method gm = clazz.getMethod(gfn);
                    //Meghívni az entityre a method-ot:
                    String ertek = gm.invoke(entity).toString();
                    String valtozoNev = tul.getName();
                    Element elem = xml.createElement(valtozoNev);
                    elem.setTextContent(ertek);
                    iroeszkoz.appendChild(elem);
                }
            }
            Field[] tuls2 = superclazz.getDeclaredFields();
            for(Field tul2 : tuls2){
                if(tul2.getAnnotation(GetterFunctionName.class) != null){
                    String gfn = tul2.getAnnotation(GetterFunctionName.class).name();
                    //előállítani a getter metódust:
                    Method gm = clazz.getMethod(gfn);
                    //Meghívni az entityre a method-ot:
                    String ertek = gm.invoke(entity).toString();
                    String valtozoNev = tul2.getName();
                    Element elem = xml.createElement(valtozoNev);
                    elem.setTextContent(ertek);
                    iroeszkoz.appendChild(elem);
                }
            }
            iroeszkoz.setAttribute("class", clazz.getSimpleName());
            xml.getFirstChild().appendChild(iroeszkoz);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            
            DOMSource s = new DOMSource(xml);
            StreamResult r = new StreamResult(f);
            t.transform(s, r);
        }
        catch(Exception ex){
            System.out.println("Hiba: " + ex.toString());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
        
    }
    
    public static Integer beolvasas(String fajlnev) {
        Integer a = -1;

         try {
             Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fajlnev);
             Element rootElement = document.getDocumentElement();

             return Integer.parseInt(rootElement.getTextContent());

         }
         catch (ParserConfigurationException e) {
             e.printStackTrace();
         }
         catch (Exception e) {
             e.printStackTrace();
         }

        return a;
     }
    
    public static void ementes(Integer a,String fajlnev) {
         try {
             Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fajlnev);
             document.setXmlStandalone(true);
             Element rootElement = document.getDocumentElement();
             rootElement.setTextContent(a.toString());

             Transformer transformer = TransformerFactory.newInstance().newTransformer();

             Result output = new StreamResult(new File(fajlnev));
             Source input = new DOMSource(document);

             transformer.transform(input, output);

         } catch (ParserConfigurationException e) {
             e.printStackTrace();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}
