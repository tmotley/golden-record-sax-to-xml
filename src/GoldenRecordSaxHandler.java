import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedWriter;
import java.io.StringWriter;

/**
 * User: tom
 * Date: 5/8/14
 * Time: 4:07 PM.
 */
public class GoldenRecordSaxHandler extends DefaultHandler {
    BufferedWriter outputFileWriter = null;
    String pc9Atts = null;
    int pc9Counter = 0;
    int skuCounter = 0;

    public GoldenRecordSaxHandler(BufferedWriter outputFileWriter) {
        this.outputFileWriter = outputFileWriter;
    }

    static void writeElementAttributeData(BufferedWriter writer, Attributes attributes) throws Exception {
        for (int i = 0; i < attributes.getLength(); i++) {
            //writer.write(attributes.getQName(i) + ":" + attributes.getValue(i));
            writer.write(attributes.getValue(i));
            if (i < (attributes.getLength() -1 )) {
                writer.write(",");
            }
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("PC9Product")) {
            pc9Counter++;
            pc9Atts = null;

            //System.out.println(qName + " : " + attributes.getValue(0));
            StringWriter stringWriter = new StringWriter();
            BufferedWriter bufferedStringWriter = new BufferedWriter(stringWriter);

            try {
                writeElementAttributeData(bufferedStringWriter, attributes);
                bufferedStringWriter.close();
                pc9Atts = stringWriter.toString();
                System.out.println(pc9Counter + ". " + pc9Atts);
            } catch (Exception e) {
                e.printStackTrace();
            }
            skuCounter = 0;
        }
        if (qName.equalsIgnoreCase("SKU")) {
            skuCounter++;
            //System.out.println("   " + skuCounter +  ". " + attributes.getQName(5) + " - " + attributes.getValue(5));
            try {
                outputFileWriter.newLine();
                //outputFileWriter.write("START-PC9");
                outputFileWriter.write(pc9Atts);
                //outputFileWriter.write("END-PC9");
                outputFileWriter.write(",");
                writeElementAttributeData(outputFileWriter, attributes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
