import java.util.ArrayList;

public class SingleThreadCalculator {

    private int _MaxLength;
    private String _Word;
    public ArrayList<String> ResultStringList;

    /**
     * Standard Constructor
     *
     * @param maxLength maximum word length to be calculated
     */
    public SingleThreadCalculator(int maxLength) {

        _MaxLength = maxLength;
        ResultStringList = new ArrayList<String>();
    }

    /**
     * Calculates all combinations and prints them to the Console
     *
     * @param word     Word under calculation
     * @param alphabet chars to be used for calculation
     */
    public void calculateInASingleThread(String word, Alphabet alphabet) {

        if (word.length() >= _MaxLength) {

            //ResultStringList.add(word);
            //System.out.print("\r" + word);

            return;
        }

        for (char letter : alphabet.Letters()) {

            _Word = word + letter;
            calculateInASingleThread(_Word, alphabet);
        }
    }
}
