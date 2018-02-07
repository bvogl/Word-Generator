
public class SingleThreadCalculator {

    private static int _MaxLength;
    private static String _Word;

    /**
     * Standard Constructor
     *
     * @param maxLength maximum word length to be calculated
     */
    public SingleThreadCalculator(int maxLength) {

        _MaxLength = maxLength;
    }

    /**
     * Calculates all combinations and prints them to the Console
     *
     * @param word     Word under calculation
     * @param alphabet chars to be used for calculation
     */
    public void calculateInASingleThread(String word, Alphabet alphabet) {

        if (word.length() >= _MaxLength) {

            //System.out.print(word + "\r");
            System.out.println(word + Thread.currentThread() + "\r");

            return;
        }

        for (char letter : alphabet.Letters()) {

            _Word = word + letter;
            calculateInASingleThread(_Word, alphabet);
        }
    }
}
