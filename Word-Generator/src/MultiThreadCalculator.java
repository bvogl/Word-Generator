import java.io.File;
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

        int max = 2;

        calculateWordsList(word, alphabet, max);
    }

    public void doWorkWithSeperateLists(int maxLength, Alphabet alphabet) {

        FileWriterHelper fwh = new FileWriterHelper();
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/times.txt");

        String text = "\nNew Round using " + _ThreadCount + " Threads and seperate Lists";
        fwh.writeTimeToFile(filePath, text, true);
        System.out.println(text);

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
                    //ex.printStackTrace();
                } finally {
                    //lists.get(i).add(restList.get(i));
                    newLIst.add(lst);
                }
            }

            ArrayList<Thread> threadList = new ArrayList<>();

            int count = 0;

            long timeStart = System.currentTimeMillis();

            for (List<String> list : newLIst
                    ) {

                ListCalculatorThread t = new ListCalculatorThread(maxLength, alphabet, list, count);
                t.start();
                System.out.println("Thread: " + count + " started");
                count++;
                threadList.add(t);
            }

            System.out.println("========================================");

            // getting total Waiting time
            getTotalWaitingTime(threadList, timeStart);
        }
    }

    public void doWorkWithThreadsafeInteger(int maxLength, Alphabet alphabet) {

        FileWriterHelper fwh = new FileWriterHelper();
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/times.txt");

        String text = "\nNew Round using " + _ThreadCount + " Threads and threadsafe integer";
        fwh.writeTimeToFile(filePath, text, true);
        System.out.println(text);

        _Counter = new AtomicInteger();
        // Initiate and Start Threads

        ArrayList<Thread> threadList = new ArrayList<>();

        long timeStart = System.currentTimeMillis();

        for (int i = 0; i < _ThreadCount; i++) {

            CalculatorThread t = new CalculatorThread(maxLength, alphabet, _WordsList, _Counter, i);
            t.start();
            threadList.add(t);
        }

        getTotalWaitingTime(threadList, timeStart);
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

    private void getTotalWaitingTime(ArrayList<Thread> threadList, long timeStart) {

        FileWriterHelper fwh = new FileWriterHelper();

        try {

            for (int i = 0; i < threadList.size(); i++) {
                threadList.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long timeFinished = System.currentTimeMillis();

        String text = "\nTotal time: \t\t\t" + (timeFinished - timeStart) + "ms";

        String timesFilePath = new File("").getAbsolutePath();
        timesFilePath = timesFilePath.concat("/times.txt");

        System.out.println(text);
        fwh.writeTimeToFile(timesFilePath, text, true);
    }
}
