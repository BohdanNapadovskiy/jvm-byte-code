package ru.napadovskiu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

 /**
 * Class AbuseWord for check abuse word in the string.
 * @author Napadovskiy Bohdan
 * @version 1.00
 * @since 15.11.2016
 */
public class AbuseWord {

     /**
     * Public method for check abuse word in the string.
     * @param in input stream for search
     * @param out output stream
     * @param abuse array abuse words
     * @throws IOException exception
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {

        int ch;

        int counter = 0;

        try (InputStreamReader inputStreamReader     = new InputStreamReader(in);
        OutputStreamWriter outputStreamWriter   = new OutputStreamWriter(out)) {
            StringBuilder stringBuilder = new StringBuilder();
            while ((ch = inputStreamReader.read()) != -1) {
            stringBuilder.append((char) ch);
            for (String abuseString : abuse) {
                if (stringBuilder.toString().equalsIgnoreCase(abuseString)) {
                    stringBuilder.delete(0, stringBuilder.length());
                } else if (abuseString.toUpperCase().startsWith(stringBuilder.toString().toUpperCase())) {
                    counter++;
                }
            }
            if (counter == 0) {
                outputStreamWriter.write(stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.length());
            }
            counter = 0;
            outputStreamWriter.flush();
        }
        inputStreamReader.close();
        outputStreamWriter.close();

        }
        catch (IOException error) {
            error.printStackTrace();
        }

    }
}
