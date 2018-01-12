/*
id => employee => importance
build map<id, employee> for quick refer
*/

/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        // build hashmap
        for (Employee e : employees) {
            map.put(e.id, e);
        }

        return dfs(map, id);
    }
    // find sum of importance in the tree (root.id = id)
    private int dfs(Map<Integer, Employee> map, int id) {
        Employee e = map.get(id);
        int sum = e.importance;

        for (int i : e.subordinates) {
            sum += dfs(map, i);
        }

        return sum;
    }
}
