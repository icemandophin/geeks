public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> curr = new ArrayList<Integer>();

        for (int i = 0; i <= rowIndex; i++) {
            for (int j = curr.size() - 1; j > 0; j--) {
                curr.set(j, curr.get(j) + curr.get(j - 1));
            }

            curr.add(1);
        }

        return curr;
    }
}
