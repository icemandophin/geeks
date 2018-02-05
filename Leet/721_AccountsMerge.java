/*
union - find approach:
map email to UID to distinguish accounts with same name
then union emails in the same list => point to one UID/account
*/
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // map email to account name
        Map<String, String> emailToName = new HashMap();
        // map email to UID represenrt unique account
        Map<String, Integer> emailToID = new HashMap();

        DSU dsu = new DSU();
        int uid = 0;

        for (List<String> account : accounts) {
            // get 1st element as account name
            String name = "";
            for (String email: account) {
                if (name == "") {
                    name = email;
                    continue;
                }
                emailToName.put(email, name);
                if (!emailToID.containsKey(email)) {
                    // map each email to one UID
                    emailToID.put(email, uid++);
                }
                // union each email to the same UID (1st email's UID in each list)
                dsu.union(emailToID.get(account.get(1)), emailToID.get(email));
            }
        }
        // for each UID, add emails pointing to it
        Map<Integer, List<String>> ans = new HashMap();
        for (String email : emailToName.keySet()) {
            int index = dsu.find(emailToID.get(email));
            ans.computeIfAbsent(index, x -> new ArrayList()).add(email);
        }
        for (List<String> component : ans.values()) {
            // sort emails
            Collections.sort(component);
            // add account name at top
            component.add(0, emailToName.get(component.get(0)));
        }

        return new ArrayList(ans.values());
    }
}

// union - find class
// each email
class DSU {
    int[] parent;
    public DSU() {
        parent = new int[10001];
        for (int i = 0; i <= 10000; ++i)
            parent[i] = i;
    }
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }
    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}
