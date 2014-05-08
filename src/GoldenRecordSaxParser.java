import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * User: tom
 * Date: 5/2/14
 * Time: 10:21 AM.
 */
public class GoldenRecordSaxParser {
    public static void main (String[] args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            SAXParser saxParser = factory.newSAXParser();

            String fileName = args[0];
            File outputFile = new File(fileName + "-output.csv");
            File inputFile = new File(fileName);
            final BufferedWriter outputFileWriter = getOutputFileWriter(outputFile);

            outputFileWriter.write(HEADERS);
            saxParser.parse(inputFile, new GoldenRecordSaxHandler(outputFileWriter));
            outputFileWriter.close();
        }
    }

    static BufferedWriter getOutputFileWriter(File outputFile) throws Exception {
        return new BufferedWriter(new FileWriter(outputFile));
    }

    public static final String HEADERS =
            "productCode," +
            "affWaistRise," +
            "coProduct," +
            "colorCode," +
            "colorDescription," +
            "curveId," +
            "deliveryEndDate," +
            "deliveryStartDate," +
            "fabricSubcategory," +
            "fabricType," +
            "ffcCode," +
            "ffcName," +
            "fiberContent," +
            "fitDescription," +
            "gender," +
            "keyOutfitIndicator," +
            "productCategory," +
            "productLine," +
            "productLineDescription," +
            "productionCenter," +
            "profitCenterCode," +
            "productColorFinishGroup," +
            "productDescription," +
            "productFabricFamily," +
            "productFabricStretch," +
            "productFrontFlyOpening," +
            "productGroup," +
            "productGroupDescription," +
            "productLegOpening," +
            "productLifecycleGroup," +
            "productNeckline," +
            "productSeason," +
            "productSleeveLength," +
            "productSubCategory," +
            "productWaistRise," +
            "productYear," +
            "styleCode," +
            "styleName," +
            "styleNumber," +
            "taxCategoryUS," +
            "ean," +
            "height," +
            "length," +
            "sizeCode," +
            "sizeDescription," +
            "sku," +
            "upc," +
            "waist," +
            "weight," +
            "width";
}
