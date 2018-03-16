import java.io.File;
import java.util.ArrayList;

public class TimeTakingHelper {

    public static void takeTime(ArrayList<String> resultStringList, long timeStart, int id) {

        String resultFilePath = new File("").getAbsolutePath();
        resultFilePath = resultFilePath.concat("/result" + id + ".txt");

        FileWriterHelper fwh = new FileWriterHelper();

        long timeCalculationEnd = System.currentTimeMillis();
        long resultTimeCalculationEnd = timeCalculationEnd - timeStart;

        fwh.writeResultToFile(resultFilePath, resultStringList, false);

        long timeTotalEnd = System.currentTimeMillis();
        long resultTimeTotalEnd = timeTotalEnd - timeStart;

        String outputString = "";

        outputString = outputString.concat(Thread.currentThread().getName() + "(no writing): \t\t\t" + resultTimeCalculationEnd + "ms\n");

        outputString = outputString.concat(Thread.currentThread().getName() + "(included writing): \t" + resultTimeTotalEnd + "ms");

        String timesFilePath = new File("").getAbsolutePath();
        timesFilePath = timesFilePath.concat("/times.txt");

        outputString = outputString.concat("\nWriting time: \t\t\t\t\t" + (resultTimeTotalEnd - resultTimeCalculationEnd) + "ms" +
                "\n========================================");

        fwh.writeTimeToFile(timesFilePath, outputString, true);

        System.out.println(outputString);

        fwh = null;
    }
}
