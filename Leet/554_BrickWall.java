/*
best break point is certain brick edge
for each row record index of brick edge and its count
=> index with max count is res
*/
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (List<Integer> row : wall) {
            int idx = 0;
            // move idx by adding distance
            for (int i = 0; i < row.size() - 1; ++i) {
                idx += row.get(i);
                int cnt = map.getOrDefault(idx, 0) + 1;
                map.put(idx, cnt);
                res = Math.max(res, cnt);
            }
        }

        return wall.size() - res;
    }
}
