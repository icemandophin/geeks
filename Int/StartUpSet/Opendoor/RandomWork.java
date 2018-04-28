/*
Your previous Plain Text content is preserved below:

# Write a function:
#
#   random_walk(
#     trans_probs, # a list of transition probabilities between each state in a directed graph (see example below)
#     start_state, # the state to start the walk from
#     num_steps,   # the number of steps in the random walk to simulate
#   )
#
# that returns a map from states to the number of times that state was visited

# Example:

trans_probs = [
  ("a", "a", 0.9),  # a -> a with prob 0.9
  ("a", "b", 0.05), # a -> b with prob 0.05
  ("a", "c", 0.05), # a -> c with prob 0.05
  ("b", "a", 0.10), # etc.
  ("b", "b", 0.85),
  ("b", "c", 0.05),
  ("c", "a", 0.50),
  ("c", "b", 0.25),
  ("c", "c", 0.25),
]

# All possible outputs for (a, 0)
random_walk(trans_probs, "a", 0) == {"a":1}        # 100% of the time

# All possible outputs for (a, 1)
random_walk(trans_probs, "a", 1) == {"a":2}        # 90% of the time
by ine 41, know if we're in a, next step -> a 90% of time. So, we take 1 step, end in a 90% of time,
then exits since input is just 1 step. so, we're in a twice, as so return {"a": 2}

random_walk(trans_probs, "a", 1) == {"a":1, "b":1} # 5% of the time
by line 42, know if we're in a, next step -> b 5% of time. So, we take 1 step, end in b 5% of time,
then exits since input is just 1 step. so, we're in a twice, as so return {"a": 1, "b": 1}

random_walk(trans_probs, "a", 1) == {"a":1, "c":1} # 5% of the time

# All possible outputs for (a, 2)
random_walk(trans_probs, "a", 2) == {"a":3}        # 81% of the time (90% a->a * 90% a->a)
random_walk(trans_probs, "a", 2) == {"a":2, "b":1} # 5% of the time (4.5% a->a->b + 0.5% a->b->a)
# etc.                                             # etc.
 */
package Solution;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Solution {
    public static void main(String[] args) {
        Map<String, Double> trans_probs = new HashMap<>();
        trans_probs.put("aa", 0.9);
        trans_probs.put("ab", 0.05);
        trans_probs.put("ac", 0.05);
        trans_probs.put("ba", 0.10);
        trans_probs.put("bb", 0.85);
        trans_probs.put("bc", 0.05);
        trans_probs.put("ca", 0.5);
        trans_probs.put("cb", 0.25);
        trans_probs.put("cc", 0.25);

        for (int i = 0; i < 3000; i++) {
            Map<String, Integer> res = random_walk(trans_probs, "a", 2);
            System.out.println(i);
            for (Map.Entry<String, Integer> entry : res.entrySet()) {

                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    public static Map<String, Integer> random_walk(Map<String, Double> map, String start, int num) {
        Map<String, Integer> res = new HashMap<>();

        if (num == 0) {
            res.put(start, 1);

            return res;
        }

        String cur = start;
        for (int i = 0; i < num; ++i) {
            cur = nextStep(map, cur);
            res.put(cur, res.getOrDefault(cur, 0) + 1);
        }

        return res;
    }

    private static String nextStep(Map<String, Double> map, String start) {
        String res = null;

        Map<String, Double> trans = new HashMap<>();
        for (String key : map.keySet()) {
            if (key.substring(0, 1).equals(start)) {
                trans.put(key.substring(1, 2), map.get(key));
            }
        }

        Random rand = new Random();
        Double val = rand.nextDouble();

        for (Map.Entry<String, Double> entry : trans.entrySet()) {
            val -= entry.getValue();
            if (val.compareTo(0.0) <= 0) {
                res = entry.getKey();
                break;
            }
        }

        if (res == null) {
            System.out.println("val: " + val);
        }

        return res;
    }
}
