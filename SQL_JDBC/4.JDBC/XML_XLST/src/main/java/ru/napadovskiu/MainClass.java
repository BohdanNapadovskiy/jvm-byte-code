package ru.napadovskiu;

/**
 * 
 */
public class MainClass {


    /**
     * Main method for calculate amount all values in file.
     * @param args
     */
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        WorkWithSQL workWithSQL = new WorkWithSQL(1000000);
        workWithSQL.createTable();

        WorkWithXMLFile workWithXMLFile = new WorkWithXMLFile();
        workWithXMLFile.convertXmlFile();

        int summ = workWithXMLFile.calculateAmount();

        long end = System.currentTimeMillis();
        long result = (end - start);

        System.out.println(summ);

        System.out.println((float) (result / 1000)/60);


    }
}

