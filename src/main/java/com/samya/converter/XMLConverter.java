/**
 * 
 */
package com.samya.converter;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

/**
 * @author Samya Ghosh 
 *
 */
public class XMLConverter {
	
	
	BufferedReader in;
    StreamResult out;
    TransformerHandler th;

    public static void main(String args[]) {
        new XMLConverter().begin();
    }

    public void begin() {
        try {
            in = new BufferedReader(new FileReader("User.txt"));
            out = new StreamResult("User.xml");
            openXml();
            String str;
            while ((str = in.readLine()) != null) {
                process(str);
            }
            in.close();
            closeXml();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openXml() throws ParserConfigurationException, TransformerConfigurationException, SAXException {

        SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        th = tf.newTransformerHandler();

        // pretty XML output
        Transformer serializer = th.getTransformer();
        serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");

        th.setResult(out);
        th.startDocument();
        th.startElement(null, null, "element", null);
    }

    public void process(String s) throws SAXException {
        th.startElement(null, null, "option", null);
        th.characters(s.toCharArray(), 0, s.length());
        th.endElement(null, null, "option");
    }

    public void closeXml() throws SAXException {
        th.endElement(null, null, "element");
        th.endDocument();
        
        System.out.println("done");
        System.out.println("To See outputxml refresh the project");
    }
    
    

}
