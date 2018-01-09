/*
consider string length can be different
remember to update carry for next bit add
loop condition consider last carry bit
*/
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        // consider num1, num2 has diff length
        // and MSB add can generate new carry
        while (i >= 0 || j >= 0|| carry > 0) {
            int sum = 0;
            if (i >= 0) {
                sum += num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += num2.charAt(j) - '0';
                j--;
            }
            sum += carry;
            // update carry
            carry = sum / 10;
            res.append(sum % 10);
        }

        // reverse and output
        return res.reverse().toString();
    }
}
