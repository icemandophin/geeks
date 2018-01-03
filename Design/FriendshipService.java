public class FriendshipService {

    private Map<Integer, Set<Integer>> followers, followings;

    public FriendshipService() {
        // initialize your data structure here.
        this.followers = new HashMap<Integer, Set<Integer>>();
        this.followings = new HashMap<Integer, Set<Integer>>();
    }

    // @param user_id an integer
    // return all followers and sort by user_id
    public List<Integer> getFollowers(int user_id) {
        // Write your code here
        if (!followers.containsKey(user_id))
            return new ArrayList<Integer>();

        return new ArrayList<Integer>(followers.get(user_id));
    }

    // @param user_id an integer
    // return all followings and sort by user_id
    public List<Integer>  getFollowings(int user_id) {
        // Write your code here
        if (!followings.containsKey(user_id))
            return new ArrayList<Integer>();

        return new ArrayList<Integer>(followings.get(user_id));
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        // Write your code here
        if (!followers.containsKey(from_user_id))
            followers.put(from_user_id, new TreeSet<Integer>());

        followers.get(from_user_id).add(to_user_id);

        if (!followings.containsKey(to_user_id))
            followings.put(to_user_id, new TreeSet<Integer>());

        followings.get(to_user_id).add(from_user_id);
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        // Write your code here
        if (followers.containsKey(from_user_id))
            followers.get(from_user_id).remove(to_user_id);

        if (followings.containsKey(to_user_id))
            followings.get(to_user_id).remove(from_user_id);
    }
}
