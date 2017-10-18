/*
point to end of inout, add to sum if a[i]!=0
reverse the string in the end because we use append
pahy attention to last carry bit
*/

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int add = 0;
        int sum;

        while((i >= 0) || (j >= 0))
        {
            sum = add;

            if(i >= 0)
            {
                sum += a.charAt(i) - '0';
                i--;
            }
            if(j >= 0)
            {
                sum += b.charAt(j) - '0';
                j--;
            }

            res.append(sum % 2);
            add = sum / 2;
        }

        if(add > 0)
        {
            res.append(add);
        }

        return res.reverse().toString();
    }
}
