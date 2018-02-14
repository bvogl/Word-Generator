import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by cs15bv1 on 14.02.2018.
 */
public class FileWriterHelper {

    public static void writeTimeToFile(String fileName, String result, boolean append) {

        BufferedWriter output = null;

        File file = new File(fileName);

        try {
            output = new BufferedWriter(new FileWriter(file, append));

            output.write("\n" + result);

            output.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void writeResultToFile(String fileName, ArrayList<String> resultList, boolean append) {

        BufferedWriter output = null;

        File file = new File(fileName);

        try {
            output = new BufferedWriter(new FileWriter(file, append));

            for (String result: resultList
                    ) {

                output.write(result);
                output.newLine();
            }

            output.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
