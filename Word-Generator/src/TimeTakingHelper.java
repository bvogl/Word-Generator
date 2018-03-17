import java.io.File;
import java.util.ArrayList;

public class TimeTakingHelper {

    public void writeToFileAndTakeTimes(ArrayList<String> resultStringList, long timeStart, int id) {

        // Set up environment
        String resultFilePath = new File("").getAbsolutePath();
        resultFilePath = resultFilePath.concat("/result" + id + ".txt");

        String timesFilePath = new File("").getAbsolutePath();
        timesFilePath = timesFilePath.concat("/times.txt");

        FileWriterHelper fwh = new FileWriterHelper();

        // take time without writing
        long timeCalculationEnd = System.currentTimeMillis();
        long resultTimeCalculationEnd = timeCalculationEnd - timeStart;

        // write results to file
        fwh.writeResultToFile(resultFilePath, resultStringList, false);

        // take time with Writing
        long timeTotalEnd = System.currentTimeMillis();
        long resultTimeTotalEnd = timeTotalEnd - timeStart;

        String outputString = "";

        outputString = outputString.concat(Thread.currentThread().getName() + "(no writing): \t\t" + resultTimeCalculationEnd + "ms\n");

        outputString = outputString.concat(Thread.currentThread().getName() + "(included writing): \t" + resultTimeTotalEnd + "ms");

        outputString = outputString.concat("\nWriting time: \t\t\t" + (resultTimeTotalEnd - resultTimeCalculationEnd) + "ms" +
                "\n========================================");

        fwh.writeTimeToFile(timesFilePath, outputString, true);

        System.out.println(outputString);

        fwh = null;
    }

    public void takeTimeWithoutWritingToFile(long timeStart) {

        String timesFilePath = new File("").getAbsolutePath();
        timesFilePath = timesFilePath.concat("/times.txt");

        FileWriterHelper fwh = new FileWriterHelper();

        // take time without writing
        long timeCalculationEnd = System.currentTimeMillis();
        long resultTimeCalculationEnd = timeCalculationEnd - timeStart;

        String outputString = "";

        outputString = outputString.concat(Thread.currentThread().getName() + "(no writing): \t\t\t" + resultTimeCalculationEnd + "ms\n");

        fwh.writeTimeToFile(timesFilePath, outputString, true);

        System.out.println(outputString);
    }
}
