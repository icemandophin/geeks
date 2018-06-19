/*
DFS + backtrack:
start from base layer, DFS and generate all possible upper layers
recursively generate upper until reach top (base.length() == 1) or fail to map
*/
class Solution {
    public boolean pyramidTransition(String base, List<String> allowed) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : allowed) {
            String key = s.substring(0, 2);
            String val = s.substring(2);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(val);
        }

        return dfs(base, map);
    }
    // recursively build upper layer from base until top 1
    private boolean dfs(String base, Map<String, List<String>> map) {
        // check if arrive top layer
        if (base.length() == 1) {
            return true;
        }
        // ensure there exist mapping for base layer
        for (int i = 0; i < base.length() - 1; ++i) {
            String pair = base.substring(i, i + 2);
            if (!map.containsKey(pair)) {
                return false;
            }
        }
        // get all possible upper layers from cur base
        List<String> next = new ArrayList<>();
        buildUpper(base, map, new StringBuilder(), next, 0);
        // DFS next layer
        for (String s : next) {
            if (dfs(s, map)) {
                return true;
            }
        }

        // no valid mapping - backtrack
        return false;
    }
    // generate all possible upper layers from base.substring(idx)
    private void buildUpper(String base, Map<String, List<String>> map, StringBuilder sb, List<String> next, int idx) {
        if (idx == base.length() - 1) {
            // add cur mapping to res
            next.add(sb.toString());

            return;
        }
        // map one pair from idx
        List<String> val = map.get(base.substring(idx, idx + 2));
        // dfs for next pair
        for (String s : val) {
            sb.append(s);
            buildUpper(base, map, sb, next, idx + 1);
            //backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
