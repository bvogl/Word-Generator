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

        TimeTakingHelper tth = new TimeTakingHelper();

        long timeStart = System.currentTimeMillis();


        for (String word : _WordList
                ) {

            calculate(word, _Alphabet);
        }

        //tth.writeToFileAndTakeTimes(_ResultStringList, timeStart, _Id);
    }

    private void calculate(String word, Alphabet alphabet) {

        if (word.length() >= _MaxLength) {

            //_ResultStringList.add(word);
            //System.out.print("\r" + word);

            return;
        }

        for (char letter : alphabet.Letters()) {

            _Word = word + letter;
            calculate(_Word, alphabet);
        }
    }
}

