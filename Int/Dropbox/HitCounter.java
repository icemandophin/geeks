//use queue
public class HitCounter {

    const int past5Min = 300;
        Queue<int> queue;
        /** Initialize your data structure here. */
        public HitCounter()
        {
            queue = new Queue<int>();
        }

        /** Record a hit.
            @param timestamp - The current timestamp (in seconds granularity). */
        public void Hit(int timestamp)
        {
            queue.Enqueue(timestamp);
        }

        /** Return the number of hits in the past 5 minutes.
            @param timestamp - The current timestamp (in seconds granularity). */
        public int GetHits(int timestamp)
        {
            while(queue.Count>0)
            {
                if(timestamp - queue.Peek()>=past5Min)
                {
                    queue.Dequeue();
                }
                else
                    break;
            }
            return queue.Count;
        }
}

//user queue and hashMap
public class DesignHitCounter {

    Deque<Integer> queue;
    HashMap<Integer, Integer> hashMap;
    int times = 300;
    /** Initialize your data structure here. */
    private static Logger LOGGER = Logger.getLogger("InfoLogging");
    public DesignHitCounter() {
        queue = new ArrayDeque<>();
        hashMap = new HashMap<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {

        if(queue.size()>0&&queue.getFirst() == timestamp)
        {
            hashMap.put(timestamp, hashMap.get(timestamp)+1);
        }
        else
        {
            queue.push(timestamp);
            hashMap.put(timestamp, 1);
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (queue.size()>0)
        {
            LOGGER.info(queue.getLast().toString());
            if(timestamp-queue.getLast()>=times)
            {
                int key = queue.pollLast();
                hashMap.remove(key);
            }
            else
                break;
        }
        int count = 0;
        for (Integer key:hashMap.keySet()
             ) {count+=hashMap.get(key);

        }
        return count;
    }
}
//use two array
public class HitCounter {

    int[] hits;
    int[] times;
    /** Initialize your data structure here. */
    public HitCounter() {
        hits = new int[300];
        times = new int[300];
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int value = timestamp%300;
        if(times[value]!=timestamp)
        {
            times[value] = timestamp;
            hits[value] =1;
        }
        else
        {
            hits[value]++;
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int count = 0;
        for(int i =0; i<300; i++)
        {
            if(timestamp-times[i]<300)
                count+=hits[i];
        }
        return count;
    }
}
