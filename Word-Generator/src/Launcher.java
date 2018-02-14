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


            // Calculation in a single Thread
            long timeStart = System.currentTimeMillis();

            SingleThreadCalculator calculator = new SingleThreadCalculator(_MaxLength);
            calculator.calculateInASingleThread(_Word, _Alphabet);

            long timeEnd = System.currentTimeMillis();
            long resultTime = timeEnd - timeStart;
            System.out.println("\nThe Calculation took: " + resultTime / 1000 + "s (" + resultTime + "ms)");

            String resultFilePath = new File("").getAbsolutePath();
            resultFilePath = resultFilePath.concat("/resultMain.txt");

            FileWriterHelper fwh = new FileWriterHelper();

            fwh.writeResultToFile(resultFilePath, calculator.ResultStringList, false);

            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/times.txt");

            fwh.writeTimeToFile(filePath, "\nThe Calculation took the main Thread: " + resultTime / 1000 + "s (" + resultTime + "ms)", true);


            // Mehrere Threads
            MultiThreadCalculator multiCalc = new MultiThreadCalculator(1, _Word, _Alphabet);
            //multiCalc.doWorkWithThreadsafeInteger(_MaxLength, _Alphabet);

            multiCalc.doWorkWithSeperateLists(_MaxLength, _Alphabet);

        }
    }



}
