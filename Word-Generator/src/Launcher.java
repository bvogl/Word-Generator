import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;


public class Launcher {

    private static LinkedList<String> _Dict;

    private static Alphabet _Alphabet;
    private static String _Word;
    private static int _MaxLength;

    private static ArrayList<String> _WordsList;


    public static void main(String[] args) {

        _Dict = new LinkedList<String>();
        _Word = "";
        _Alphabet = new Alphabet();
        _WordsList = new ArrayList<String>();


        if (args.length < 2 || args[0].equals("--help")) {
            System.out.println("Overload with up to 2 arguments. \nThe first determines the amount of digits to be calculated" +
                    "\nThe second determens the alphabet that will be used:" +
                    "\n     dig     => all digits from 0 to 9" +
                    "\n     low     => all lower letters" +
                    "\n     up      => all capital letters" +
                    "\n     letter  => all letters" +
                    "\n     or      => letters and digits" +
                    "\n     all     => all elements of the alphabet");
        } else {
            _MaxLength = Integer.parseInt(args[0]);
        }


        if (args.length >= 2) {

            if (args[1].contains("dig")) {
                _Alphabet.SetAlphabetToDigits();
            } else if (args[1].contains("low")) {
                _Alphabet.SetAlphabetToLowerLetters();
            } else if (args[1].contains("up")) {
                _Alphabet.SetAlphabetToUpperLetters();
            } else if (args[1].contains("letter")) {
                _Alphabet.SetAlphabetToAllLetters();
            } else if (args[1].contains("or")) {
                _Alphabet.SetAlphabetToLetterOrDigit();
            } else if (args[1].contains("all")) {
                _Alphabet.SetAlphabetToAll();
            }

            int threadsCount = 0;

            if (args.length > 3) {
                threadsCount = Integer.parseInt(args[3]);
            }

            MultiThreadCalculator multiCalc = new MultiThreadCalculator(threadsCount, _Word, _Alphabet);

            if (args[2].equals("single")) {
                SingleThread();
            } else if (args[2].equals("list")) {
                multiCalc.doWorkWithSeperateLists(_MaxLength, _Alphabet);
            } else if (args[2].equals("int")) {
                multiCalc.doWorkWithThreadsafeInteger(_MaxLength, _Alphabet);
            }
        }
    }

    private static void SingleThread() {

        // set up the environment
        SingleThreadCalculator calculator = new SingleThreadCalculator(_MaxLength);

        String resultFilePath = new File("").getAbsolutePath();
        resultFilePath = resultFilePath.concat("/resultMain.txt");

        String timeFilePath = new File("").getAbsolutePath();
        timeFilePath = timeFilePath.concat("/times.txt");

        FileWriterHelper fwh = new FileWriterHelper();

        // start time taking
        long timeStart = System.currentTimeMillis();

        // start calculation
        calculator.calculateInASingleThread(_Word, _Alphabet);

        // end time taking for calculation
        long timeEnd = System.currentTimeMillis();
        long resultTime = timeEnd - timeStart;

        String text = "\n" + Thread.currentThread().toString() +
                "\nSingle Thread (no writing): \t" + resultTime + "ms";
        System.out.println(text);

        fwh.writeTimeToFile(timeFilePath, text, true);

        fwh.writeResultToFile(resultFilePath, calculator.ResultStringList, false);

        // new time for writing to file
        timeEnd = System.currentTimeMillis();
        long totalResultTime = timeEnd - timeStart;

        text = "Single Thread (with writing): \t" + totalResultTime + "ms";
        System.out.println(text);

        fwh.writeTimeToFile(timeFilePath, text, true);

        text = "Writing Time: \t\t\t\t\t" + (totalResultTime - resultTime) + "ms" +
                "\n========================================";

        System.out.println(text);
        fwh.writeTimeToFile(timeFilePath, text, true);

        fwh = null;
        calculator = null;
    }
}
