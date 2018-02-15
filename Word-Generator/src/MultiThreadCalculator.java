import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadCalculator {

    private static ArrayList<String> _WordsList;
    private static String _Word;
    private static int _ThreadCount;
    public AtomicInteger _Counter;

    public MultiThreadCalculator(int threadCount, String word, Alphabet alphabet) {

        _WordsList = new ArrayList<String>();
        _ThreadCount = threadCount;

        // Get Wordlist with initial Letters
        int max = 0;

        if (alphabet.Letters().size() < _ThreadCount) {
            max = 2;
        } else {
            max = 1;
        }

        max = 2;
        calculateWordsList(word, alphabet, max);
    }

    public void doWorkWithSeperateLists(int maxLength, Alphabet alphabet) {

        int partialListSize = _WordsList.size() / _ThreadCount;
        int tmp = 0;
        int counter = partialListSize;

        ArrayList<List<String>> lists = new ArrayList<List<String>>();

        for (int i = 0; i < _ThreadCount; i++) {

            List<String> partialList = new ArrayList<String>();

            partialList = _WordsList.subList(tmp, counter);
            lists.add(partialList);

            tmp = counter;

            counter = counter + partialListSize;
        }

        List<String> restList = new ArrayList<String>();


        try {
            //lists.add(_WordsList.subList(tmp, _WordsList.size()));
            restList = _WordsList.subList(tmp, _WordsList.size());

        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        } finally {

            ArrayList<List<String>> newLIst = new ArrayList<List<String>>();

                for (int i = 0; i < lists.size(); i++) {

                    ArrayList lst = new ArrayList(lists.get(i));

                    try {

                        lst.add(restList.get(i));
                    } catch (IndexOutOfBoundsException ex) {
                        ex.printStackTrace();
                    } finally {
                        //lists.get(i).add(restList.get(i));
                        newLIst.add(lst);
                    }
                }

                int count = 0;
                for (List<String> list : newLIst
                        ) {

                    ListCalculatorThread t = new ListCalculatorThread(maxLength, alphabet, list, count);
                    t.start();
                    count++;
                }
            }

    }

    public void doWorkWithThreadsafeInteger(int maxLength, Alphabet alphabet) {

        _Counter = new AtomicInteger();
        // Initiate and Start Threads

        for (int i = 0; i < _ThreadCount; i++) {

            CalculatorThread t = new CalculatorThread(maxLength, alphabet, _WordsList, _Counter);
            t.start();
        }
    }

    private void calculateWordsList(String word, Alphabet alphabet, int maxLength) {

        if (word.length() >= maxLength) {
            _WordsList.add(word);

            return;
        }

        for (char letter : alphabet.Letters()) {

            _Word = word + letter;
            calculateWordsList(_Word, alphabet, maxLength);
        }
    }
}
