import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ListCalculatorThread extends Thread {

    private int _MaxLength;
    private String _Word;
    private List<String> _WordList;
    private Alphabet _Alphabet;
    private String _ResultString;
    private ArrayList<String> _ResultStringList;
    private int _Id;

    public ListCalculatorThread(int maxLength, Alphabet alphabet, List<String> wordList, int id) {

        _MaxLength = maxLength;
        _WordList = wordList;
        _Alphabet = alphabet;
        _ResultString = "";
        _Id = id;
        _ResultStringList = new ArrayList<String>();
    }

    @Override
    public void run() {
        super.run();

        long timeStart = System.currentTimeMillis();

        for (String word : _WordList
                ) {

            calculate(word, _Alphabet);
        }

        // In Zeitmessung wird einbezogen:
        // Das Schreiben der Werte in die Datei
        String resultFilePath = new File("").getAbsolutePath();
        resultFilePath = resultFilePath.concat("/result" + _Id + ".txt");

        FileWriterHelper fwh = new FileWriterHelper();
        fwh.writeResultToFile(resultFilePath, _ResultStringList, false);

        long timeEnd = System.currentTimeMillis();
        long resultTime = timeEnd - timeStart;

        // Nicht in die Zeitmessung einbezogen wird:
        // Das Schreiben der Zeit auf der Konsole,
        // Das Schreiben der Zeit in die Datei
        String outputString = "";
        outputString = outputString.concat("Thread: " + Thread.currentThread().getName());
        outputString = outputString.concat("\nThe Calculation took: " + resultTime / 1000 + "s (" + resultTime + "ms)");

        String timesFilePath = new File("").getAbsolutePath();
        timesFilePath = timesFilePath.concat("/times.txt");

        System.out.println(outputString);

        fwh.writeTimeToFile(timesFilePath, outputString, true);
    }

    private void calculate(String word, Alphabet alphabet) {

        if (word.length() >= _MaxLength) {
            //System.out.println(word + " " + Thread.currentThread() + "\r");

            //_ResultString = _ResultString.concat(word + "\n");

            _ResultStringList.add(word);

            return;
        }

        for (char letter : alphabet.Letters()) {

            _Word = word + letter;
            calculate(_Word, alphabet);
        }
    }

    private static void writeTimeToFile(String fileName, String result, boolean append) {

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
}

