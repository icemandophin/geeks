class Solution {
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        // define all possible value defined by 1/2/3 digits
        // one covers 1-20
        String[] one = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
        // two covers 10-90
        String[] two = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
        // three covers 1k/1m/1b
        String[] three = { "", "Thousand", "Million", "Billion" };

        String res = "";
        int i = 0;
        // convert abc,def,ghi to string, ghi first
        while (num > 0) {
            // convert 3 digits in a group
            if (num % 1000 != 0) {
                res = numToWord(num % 1000, one, two).trim() + " " + three[i] + " " + res;
            }
            // right shift 3 digits
            num /= 1000;
            i++;
        }

        return res.trim();
    }
    // convert 3 digit num XYZ to string
    private String numToWord(int num, String[] one, String[] two) {
        if (num < 20) {
            // 0-19
            return one[num];
        } else if (num < 100) {
            // 20-99
            return two[num / 10] + " " + numToWord(num % 10, one, two);
        } else {
            // 100-999
            return one[num / 100] + " Hundred " + numToWord(num % 100, one, two);
        }
    }
}
