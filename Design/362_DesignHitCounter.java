/*
Design a hit counter which counts the number of hits received in the past 5 minutes.
Each function accepts a timestamp parameter (in seconds granularity) and you may
assume that calls are being made to the system in chronological order
(ie, the timestamp is monotonically increasing). You may assume that the earliest
timestamp starts at 1.
It is possible that several hits arrive roughly at the same time.
*/

/*
Producer - Consumer model => message queue
5min is condition to dequeue dated message
hit: enqueue hit as timestamp
count: dequeue all msg that older than 5min from head, then return queue size
*/
public class HitCounter {
    private Queue<Integer> q;

    public HitCounter() {
        q = new LinkedList<>();
    }

    public void hit(int timestamp) {
        q.offer(timestamp);
    }
    // pull "expired" hit when getting hit counts
    public int getHits(int timestamp) {
        while (!q.empty() && timestamp - queue.peek() >= 300) {
            q.pull();
        }
        return q.size();
    }
}

/*
large hit in short time optimization:
5min = 300s => rolling array/bucket time[300] and hit[300]
hit: check incoming time[timestamp % 300] == timestamp
Y: multiple hits coming in together, increase hit[timestamp % 300]
N: last hit at this spot came in 5 min ago, reset time[] and hit[]
count: summ all hit[i] whose time[i] < current timestamp
*/
public class HitCounter {
    private int[] time;
    private int[] hit;

    public HitCounter() {
        time = new int[300];
        hit = new int[300];
    }

    public void hit(int timestamp) {
        int i = timestamp % 300;
        if (time[i] != timestamp) {
            // it has been more than 300s since last hit of this time bucket
            // need reset outdated time/hit
            time[i] = timestamp;
            hit[i] = 1;
        }
        else {
            // new hit arrive at the same time unit
            // just add hit count
            hit[i]++;
        }
    }

    public int getHits(int timestamp) {
        int res = 0;
        for (int i = 0; i < 300; ++i) {
            // find all hit buckets that has time stamp within 300s
            if (timestamp - time[i] < 300) {
                res += hit[i];
            }
        }
        return res;
    }
}
