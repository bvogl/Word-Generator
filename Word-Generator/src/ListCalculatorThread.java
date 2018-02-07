import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ListCalculatorThread extends Thread {

    private int _MaxLength;
    private String _Word;
    private List<String> _WordList;
    private Alphabet _Alphabet;

    public ListCalculatorThread(int maxLength, Alphabet alphabet, List<String> wordList) {

        _MaxLength = maxLength;
        _WordList = wordList;
        _Alphabet = alphabet;
    }

    @Override
    public void run() {
        super.run();

        long timeStart = System.currentTimeMillis();

        for (String word : _WordList
                ) {

            calculate(word, _Alphabet);
        }

        long timeEnd = System.currentTimeMillis();
        long resultTime = timeEnd - timeStart;

        String outputString = "";
        outputString = outputString.concat("Thread: " + Thread.currentThread().getName());
        outputString = outputString.concat("\nThe Calculation took: " + resultTime / 1000 + "s (" + resultTime + "ms)");

        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/times.txt");

        writeTimeToFile(filePath, outputString);

    }

    private void calculate(String word, Alphabet alphabet) {

        if (word.length() >= _MaxLength) {
            System.out.println(word + " " + Thread.currentThread() + "\r");
            return;
        }

        for (char letter : alphabet.Letters()) {

            _Word = word + letter;
            calculate(_Word, alphabet);
        }
    }

    private static void writeTimeToFile(String fileName, String result) {

        BufferedWriter output = null;

        File file = new File(fileName);

        try {
            output = new BufferedWriter(new FileWriter(file, true));

            output.write("\n" + result);

            output.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

