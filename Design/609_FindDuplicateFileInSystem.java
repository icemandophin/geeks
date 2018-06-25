/*
base approach:
build hashmap of each file's content and its paths(dir + file name)
*/
class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();

        for (String path : paths) {
            String[] s = path.split(" ");

            for (int i = 1; i < s.length; ++i) {
                // separate file name from content
                String[] str = s[i].split("\\(");
                // get file content / remove ")"
                String key = str[1].substring(0, str[1].length() - 1);
                List<String> val = map.getOrDefault(key, new ArrayList<>());
                // create path = dir + filename
                val.add(s[0] + "/" + str[0]);
                map.put(key, val);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (String key : map.keySet()) {
            List<String> cur = map.get(key);
            // add duplicate file paths
            if (cur.size() > 1) {
                res.add(cur);
            }
        }

        return res;
    }
}