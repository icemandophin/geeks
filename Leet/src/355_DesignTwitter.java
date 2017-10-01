/*
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
 public class Twitter {
     // Twitter system time counter
     private int timeStamp;
     // map to find if user exist
     private Map<Integer, User> userMap;
     // define tweet/msg class
     private class Tweet{
         // tweet msg id
         public int id;
         // post-time
         public int time;
         // ptr to next (older) tweet - link list mode
         // save a lot of time when getting News feed
         public Tweet next;
         // constructor
         public Tweet(int id) {
             this.id = id;
             time = timeStamp++;
             next = null;
         }
     }
     // define user class
     public class User {
         // user
         public int id;
         // set general interface as set (easy to update implementation)
         public Set<Integer> followed;
         public Tweet tweetHead;
         // construct user
         public User(int id) {
             this.id = id;
             // implement set with HasSet
             followed = new HashSet<>();
             // first follow itself
             follow(id);
             tweetHead = null;
         }
         // user should be able to follow/unfollow others
         // takes O(1) to add/remove - reason we want hash map
         public void follow(int id) {
             followed.add(id);
         }
         public void unfollow(int id) {
             followed.remove(id);
         }
         // user need to post new tweet
         public void post(int id) {
             Tweet cur = new Tweet(id);
             // point to previous head/latest tweet
             cur.next = tweetHead;
             // add cur tweet to head of list
             tweetHead = cur;
         }
     }
     // construct Twitter
     public Twitter() {
         timeStamp = 0;
         userMap = new HashMap<Integer, User>();
     }

     /** Compose a new tweet. */
     public void postTweet(int userId, int tweetId) {
         // check if it is existing user
         if (!userMap.containsKey(userId)) {
             User cur = new User(userId);
             userMap.put(userId, cur);
         }
         // post for this user
         userMap.get(userId).post(tweetId);
     }

     /*
     Retrieve the 10 most recent tweet ids in the user's news feed.
     Each item in the news feed must be posted by users who the user followed or by the user herself.
     Tweets must be ordered from most recent to least recent.
     */
     // pull mode:
     // 1. get all users that followered => get all tweets of them
     // 2. build max heap and insert all tweetHeads
     // 3. each time poll the root of heap, add its next tweet into heap
     // 4. repeat 3 for 10 times - get 10 most recent
     public List<Integer> getNewsFeed(int userId) {
         List<Integer> res = new LinkedList<>();
         // return if current user does not exist
         if(!userMap.containsKey(userId)) {
             return res;
         }
         Set<Integer> users = userMap.get(userId).followed;
         // implement max heap with PQ
         PriorityQueue<Tweet> pq = new PriorityQueue<Tweet>(users.size(), (a,b)->(b.time-a.time));
         // add al tweetHead
         for (int u : users) {
             Tweet t = userMap.get(u).tweetHead;
             // check and ensure t is valid: null root will break code flow
             if (t != null) {
                 pq.add(t);
             }
         }
         int i = 0;
         while (!pq.isEmpty() && i < 10) {
             Tweet t = pq.poll();
             res.add(t.id);
             i++;
             if (t.next != null) {
                 pq.add(t.next);
             }
         }
         return res;
 }

     /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
     public void follow(int followerId, int followeeId) {
         // check both of ids exist
         if(!userMap.containsKey(followerId)) {
             User a = new User(followerId);
             // add new user to user map also
             userMap.put(followerId, a);
         }
         // similiar approach to followee
         if(!userMap.containsKey(followeeId)) {
             User b = new User(followeeId);
             // add new user to user map also
             userMap.put(followeeId, b);
         }
         // add a -> b relationship in user a
         userMap.get(followerId).follow(followeeId);
     }

     /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
     public void unfollow(int followerId, int followeeId) {
         // if one of ids does NOT exist, or they are the same - then no need to continue
         if (!userMap.containsKey(followerId) || !userMap.containsKey(followeeId)
             || followerId==followeeId) {
             return;
         }
         // make following relationship
         userMap.get(followerId).unfollow(followeeId);
     }
 }
 
