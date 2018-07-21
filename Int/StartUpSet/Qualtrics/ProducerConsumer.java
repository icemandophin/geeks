import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Sample message 1:
// {“producer”:”A”,”payload”:”8b346834f”}

// Sample message 2:
// {“producer”:”Delta”,”payload”:”574d7e3f6”}

// Sample message 3:
// {“producer”:”A”,”payload”:”47654e”}

// Sample message 4:
// {“producer”:”Charlie”,“payload”:”858deea”}

public interface Message {
    String getProducer();
}

public interface QDQueue {
    Integer getNumMsgs();
    void add(Message msg);
}

public class QueueDistributor {
    private List<QDQueue> allQueues;
    private int n;
    private Map<String, Integer> map;
    Random rand;

    public QueueDistributor(List<QDQueue> initializedQueues) {
        allQueues = initializedQueues;
        n = allQueues.size();
        map = new HashMap<String, Integer>();
        rand = new Random();
    }

    public void handleMessage(Message msg) throws Exception {
        // TODO
        if (msg == null) {
            throw new Exception("invalid input");
        }

        if (msg.getProducer() == null) {
            throw new Exception("invalid input");
        }

        String producter = msg.getProducer();

        if (map.containsKey(producer)) {
            int idx = map.get(producer);
            QDQueue cur = allQueues.get(idx);
            if (cur.size() < thres) {
                allQueues.get(idx).add(msg);
            } else {
                int j = 0;
                int min = allQueues.get(0);
                for (int k = 1; k < allQueues.size(); k++) {
                    if (allQueues.get(k).size() < min && k != idx) {
                        j = k;
                    }
                }
                allQueues.get(j).add(msg);
            }
        } else {
            int i = rand.nextInt(n);

            map.add(producer, i);
            allQueues.get(i).add(msg);
        }
    }
}


