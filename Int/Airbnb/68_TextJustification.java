/*
keep recording total len of words and min space for cur line
if exceed limit X => print cur line
space_cnt / X and space_cnt % X to get space needed to insert
Notice: for last line all extra space goes to tail
*/
class Solution {
    public List<String> fullJustify(String[] words, int x) {
        List<String> res = new ArrayList<>();
        // record # of space in cur line
        int cnt = 1;
        // record total word len in cur line
        int len = words[0].length();
        // index of cur word
        int i = 1;

        while (i < words.length) {
            int wl = words[i].length();

            if (len + wl + cnt > x) {
                // exceed line limit when adding w[i]
                // currently # of space equals to # of words
                // => write words[i - cnt : i) into res
                res.add(writeLine(words, x, i, cnt, len));
                // reset space and word len count
                // and start new line with words[i] as top word
                cnt = 1;
                len = wl;
            } else {
                // add more words
                cnt++;
                len += wl;
            }

            i++;
        }
        // handle last line: add space at the end
        res.add(writeLine(words, x, i, cnt, len));

        return res;
    }
    // arrange word/space mixture with end word index, space count and expected word length
    private String writeLine(String[] words, int x, int i, int cnt, int len) {
        StringBuilder res = new StringBuilder();
        int space = 1;
        int extra = 0;
        // space allocation
        if (i != words.length && cnt != 1) {
            // get # of spaces between 2 words
            space = (x - len) / (cnt - 1);
            // get # of additional spaces to fill in first k slots
            extra = (x - len) % (cnt - 1);
        }
        // build cur line
        for (int j = i - cnt; j < i; ++j) {
            // check if start of line
            // if not, insert space in 2 layers
            if (res.length() > 0) {
                // insert spaces
                addSpace(res, space);
                // add additional spaces
                if (extra > 0) {
                    addSpace(res, 1);
                    extra--;
                }
            }
            // add word
            res.append(words[j]);
        }
        // for last line: fill remaining of line with space
        if (res.length() < x) {
            addSpace(res, x - res.length());
        }

        return res.toString();
    }
    // add n spaces to cur string builder
    private void addSpace(StringBuilder res, int n) {
        for (int i = 0; i < n; ++i) {
            res.append(" ");
        }
    }
}
