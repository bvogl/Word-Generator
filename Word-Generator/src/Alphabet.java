import java.util.ArrayList;


public class Alphabet {

    private char[] _Letters1;
    private ArrayList<Character> _Letters;

    public Alphabet() {

        _Letters1 = new char[94];
        _Letters = new ArrayList<Character>();

        int counter = 33;

        for (int i = 0; i < 94; i++) {

            _Letters1[i] = (char) counter;
            _Letters.add((char) counter);
            counter++;
        }
    }

    public void SetAlphabetToAll() {
        _Letters.clear();

        for (char letter : _Letters1) {
            _Letters.add(letter);
        }
    }


    public void SetAlphabetToLowerLetters() {
        _Letters.clear();

        for (char letter : _Letters1) {

            if (Character.isLetter(letter) && Character.isLowerCase(letter)) {
                _Letters.add(letter);
            }
        }
    }

    public void SetAlphabetToUpperLetters() {
        _Letters.clear();

        for (char letter : _Letters1) {
            if (Character.isLetter(letter) && Character.isUpperCase(letter)) {
                _Letters.add(letter);
            }
        }
    }

    public void SetAlphabetToAllLetters() {
        _Letters.clear();

        for (char letter : _Letters1) {

            if (Character.isLetter(letter)) {
                _Letters.add(letter);
            }
        }
    }

    public void SetAlphabetToLetterOrDigit() {
        _Letters.clear();

        for (char letter : _Letters1) {

            if (Character.isLetterOrDigit(letter)) {
                _Letters.add(letter);
            }
        }
    }

    public void SetAlphabetToDigits() {
        _Letters.clear();

        for (char letter : _Letters1) {

            if (Character.isDigit(letter)) {
                _Letters.add(letter);
            }
        }
    }

    public ArrayList<Character> Letters() {

        return _Letters;
    }
}
