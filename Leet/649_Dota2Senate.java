/*
separate members(index) into 2 queues:
each time poll members and compare:
the one with smaller index can ban the bugger one => smaller back to queue
smaller one will not ban others until next round => add n to smaller index
*/
class Solution {
    public String predictPartyVictory(String senate) {
        Queue<Integer> qr = new LinkedList<>();
        Queue<Integer> qd = new LinkedList<>();
        char[] se = senate.toCharArray();
        int n = se.length;

        for (int i = 0; i < n; ++i) {
            if (se[i] == 'R') {
                qr.offer(i);
            } else {
                qd.offer(i);
            }
        }

        while (!qr.isEmpty() && !qd.isEmpty()) {
            int r = qr.poll();
            int d = qd.poll();
            if (r < d) {
                qr.add(r + n);
            } else {
                qd.add(d + n);
            }
        }

        if (qr.size() > qd.size()) {
            return "Radiant";
        } else {
            return "Dire";
        }

    }
}
