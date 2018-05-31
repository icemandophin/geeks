public class Solution {
    public IList<int> TopKFrequent(int[] nums, int k) {
                                           IList<int> output = new List<int>();
            Dictionary<int, int> dict = new Dictionary<int, int>();
            IList<int>[] bucket = new List<int>[nums.Length + 1];
            foreach(var num in nums)
            {
                if(dict.ContainsKey(num))
                {
                    dict[num]++;
                }
                else
                {
                    dict.Add(num, 1);
                }
            }
            foreach(var pair in dict)
            {
                if (bucket[pair.Value] == null)
                    bucket[pair.Value] = new List<int>();
                bucket[pair.Value].Add(pair.Key);
            }
            for(int i = bucket.Length - 1; i>=0; i--)
            {
                if(bucket[i] != null && output.Count < k)
                {
                    foreach (var value in bucket[i])
                        output.Add(value);
                }
            }
            return output;
    }
}
public List<Integer> topKFrequent(int[] nums, int k) {
         Map<Integer, Integer> map = new HashMap<>();
        for(int i =0; i<nums.length;i++)
        {
           map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>((a,b)->(b.getValue() - a.getValue()));
        for (Map.Entry<Integer, Integer> entry: map.entrySet()
             ) {
            priorityQueue.add(entry);
        }
        List<Integer> list = new ArrayList<>();
        while(list.size()<k)
        {
            list.add(priorityQueue.poll().getKey());
        }
        return list;
    }
