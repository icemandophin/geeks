/*
classic backtrack model:
permutation => for each result element only appear once
=> add visited[] to mark visit status
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
       List<List<Integer>> res = new ArrayList<>();
       List<Integer> cur = new ArrayList<>();
       boolean[] visited = new boolean[nums.length];
       backtrack(res, cur, visited, nums);
       return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> cur, boolean[] visited, int [] nums) {
       if(cur.size() == nums.length) {
          res.add(new ArrayList<>(cur));
       } else {
          for(int i = 0; i < nums.length; ++i) {
             if(!visited[i]) {
                 visited[i] = true;
                 cur.add(nums[i]);
                 backtrack(res, cur, visited, nums);
                 cur.remove(cur.size() - 1);
                 visited[i] = false;
             }
             // element already exists, skip
          }
       }
    }
}
