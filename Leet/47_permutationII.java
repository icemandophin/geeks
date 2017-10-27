public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), result);

        return result;
    }

    private void dfs(int[] nums, boolean[] visited, List<Integer> curr, List<List<Integer>> result) {
        if (curr.size() == nums.length) {
            result.add(new ArrayList<Integer>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i] && (i == 0 || nums[i] != nums[i - 1] || visited[i - 1])) {
                visited[i] = true;
                curr.add(nums[i]);
                dfs(nums, visited, curr, result);
                visited[i] = false;
                curr.remove(curr.size() - 1);
            }
        }
    }
}
