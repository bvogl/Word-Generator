import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class CalculatorThread extends Thread {

    private int _MaxLength;
    private String _Word;
    private ArrayList<String> _WordList;
    private Alphabet _Alphabet;
    private AtomicInteger _Counter;
    private ArrayList<String> _ResultStringList;
    private int _Id;

    public CalculatorThread(int maxLength, Alphabet alphabet, ArrayList<String> wordList, AtomicInteger counter, int id) {

        _MaxLength = maxLength;
        _WordList = wordList;
        _Alphabet = alphabet;
        _Counter = counter;
        _ResultStringList = new ArrayList<String>();
        _Id = id;
    }

    @Override
    public void run() {
        super.run();

        long timeStart = System.currentTimeMillis();

        String word = "";

        while (_WordList.size() > _Counter.get()) {

            word = _WordList.get(_Counter.getAndIncrement());

            calculate(word, _Alphabet);
        }

        TimeTakingHelper.takeTime(_ResultStringList, timeStart, _Id);
    }

    private void calculate(String word, Alphabet alphabet) {

        if (word.length() >= _MaxLength) {
            _ResultStringList.add(word);

            return;
        }

        for (char letter : alphabet.Letters()) {

            _Word = word + letter;
            calculate(_Word, alphabet);
        }
    }
}


