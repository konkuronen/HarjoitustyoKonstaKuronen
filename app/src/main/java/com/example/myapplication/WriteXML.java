package com.example.myapplication;

import android.content.Context;



import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.OutputStreamWriter;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class WriteXML {

    public static final String filePath = "";

    public WriteXML() {}

    public void write(Context context) {
        BookingManager br = BookingManager.getInstance();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();

            Element root = doc.createElement("BookingManager");
            doc.appendChild(root);

            for (int i = 0; i < br.getHallList().size(); i++) {
                Hall hall = br.getHallList().get(i);
                Element eHall = doc.createElement(hall.getName().replace(" ", ""));
                root.appendChild(eHall);
                for (int a = 0 ; a < hall.getSize() ; a++) {
                    Booking booking = hall.getBooking(a);
                    Element eBooking = doc.createElement("booking");
                    eHall.appendChild(eBooking);

                    Attr attr = doc.createAttribute("Time");
                    attr.setValue(booking.getDay() + "-" + booking.getstart() + "-" + booking.getend());
                    eBooking.setAttributeNode(attr);

                    Element name = doc.createElement("Name");
                    name.appendChild(doc.createTextNode(booking.getBookerName().replace(" ", "")));
                    eBooking.appendChild(name);

                    Element phoneNumber = doc.createElement("Phonenumber");
                    phoneNumber.appendChild(doc.createTextNode(booking.getBookerPhone()));
                    eBooking.appendChild(phoneNumber);

                    Element reason = doc.createElement("Reason");
                    reason.appendChild(doc.createTextNode(booking.getReason()));
                    eBooking.appendChild(reason);
                }
            }

            FileOutputStream fos = context.openFileOutput("testi.xml", context.MODE_PRIVATE);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(fos);
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
