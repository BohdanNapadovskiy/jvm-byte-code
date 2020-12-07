package ru.napadovskiyb;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Package of CollectionPro finalTask.
 * Main class for read xml file.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 01.08.2017
 */
public class XmlParser {

    /**
     * Map for all order books.
     */
    private HashMap<String, OrderBook> orderBooks = new HashMap<>();


    /**
     * Method return file name for properties.
     * @return file name
     */
    public String getFileName() {
        String fileName = "";

        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();

        try (InputStream is = loader.getResourceAsStream("app.properties")) {
            settings.load(is);

        } catch (Exception ex) {
            ex.getStackTrace();
        }
        fileName = settings.getValue("home.path");
        return fileName;
    }


    /**
     * Method read xml file.
     * @throws FileNotFoundException exception.
     * @throws XMLStreamException exception.
     */
    public void readXMLFile() throws FileNotFoundException, XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream in = new FileInputStream(getFileName());
        XMLStreamReader streamReader = factory.createXMLStreamReader(in);

        while (streamReader.hasNext()) {
            if (streamReader.isStartElement()) {
                if (streamReader.getLocalName().equals("AddOrder")) {
                    addOrderToBook(streamReader);
                } else if (streamReader.getLocalName().equals("DeleteOrder")) {
                    deleteOrder(streamReader);
                }
            }
            streamReader.next();
        }

    }

    /**
     *Method read XML and add too map Order.
     * @param stream stream.
     */
    private void addOrderToBook(XMLStreamReader stream) {
        final int bookNum =  0;
        final int operationNum = 1;
        final int priceNum = 2;
        final int volumeNum = 3;
        final int idNum = 4;

        String bookNumber = stream.getAttributeValue(bookNum);
        String operation = stream.getAttributeValue(operationNum);
        Double price = Double.valueOf(stream.getAttributeValue(priceNum));
        Integer volume = Integer.valueOf(stream.getAttributeValue(volumeNum));
        Integer id = Integer.valueOf(stream.getAttributeValue(idNum));

        boolean typeOperation = false;
        OrderBook newOrderBook;
        if (operation.equals("BUY")) {
            typeOperation = true;
        } else {
            typeOperation = false;
        }

        Order newOrder = new Order(typeOperation, price, volume, id);
        if (!this.orderBooks.containsKey(bookNumber)) {
            newOrderBook = new OrderBook();
            this.orderBooks.put(bookNumber, newOrderBook);
        } else {
            newOrderBook = this.orderBooks.get(bookNumber);

        }
        newOrderBook.addOrder(newOrder);
    }

    /**
     * Method delete order from book.
     * @param stream stream.
     */
    private void deleteOrder(XMLStreamReader stream) {
        final int bookNum =  0;
        final int idNum = 1;

        String bookNumber = stream.getAttributeValue(bookNum);
        Integer id = Integer.valueOf(stream.getAttributeValue(idNum));
        this.orderBooks.get(bookNumber).deleteOrder(id);
    }

    /**
     *Method print all book.
     */
    private void printAllBook() {
        for (String bookNumber : orderBooks.keySet()) {
            System.out.println(bookNumber);
            orderBooks.get(bookNumber).offsettingOrders();
            orderBooks.get(bookNumber).printMap();
        }
    }

    /**
     * Main method.
     * @param args argument
     * @throws Exception exception.
     */
    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        XmlParser xmlParser = new XmlParser();
        xmlParser.readXMLFile();
        xmlParser.printAllBook();

        long end = System.currentTimeMillis();

        long result = (end - start);
        System.out.println(result);
    }

}
