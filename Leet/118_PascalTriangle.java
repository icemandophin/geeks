public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> row = new ArrayList<>();

        for (int i = 1; i <= numRows; ++i) {
            for (int j = row.size() - 1; j > 0; --j) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
            // last element in each row is always 1
            row.add(1);
            // insert cur row
            res.add(new ArrayList<Integer>(row));
        }

        return res;
    }
}
