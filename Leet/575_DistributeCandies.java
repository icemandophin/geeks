/*
if n candies are unique, sister can get n/2 unique types
=> expand: as long as there are n/2 unique types, we can assign to sister
if less than n/2 => sister gets all unique we have => hashset
*/
class Solution {
    public int distributeCandies(int[] candies) {
        int n = candies.length;
        Set<Integer> unique = new HashSet<>();

        for (int one : candies) {
            unique.add(one);
        }

        return Math.min(n / 2, unique.size());
    }
}
