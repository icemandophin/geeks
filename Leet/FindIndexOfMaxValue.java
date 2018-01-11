public class Solution {
    public static void main(String[] args) {
        int[] A = {1,2,3,4,5,5,5,2};
        int res = findRandomIndexOfMaxValue(A);
        System.out.println("res: " + res);
    }
    // apply template of reservior sampling
    public static int findRandomIndexOfMaxValue(int[] a) {
        Random rand = new Random();
        int[] reservior = new int[1];
        int maxValue = Integer.MIN_VALUE;
        int numOfMaxValue = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] == maxValue) {
            	// found new known max
            	// random update index
                ++numOfMaxValue;
                // generate random num in [0, k-1] for k max values
                int randNum = rand.nextInt(numOfMaxValue);
                if(randNum == 0) {
                    reservior[0] = i;
                }
            }
            // update max value found so far
            if(a[i] > maxValue) {
                maxValue = a[i];
                // reset number and index of max value
                numOfMaxValue = 1;
                reservior[0] = i;
            }
        }

        return reservior[0];
    }
}
