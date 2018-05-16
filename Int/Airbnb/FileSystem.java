/**
 * create(path, value), set(path, value), get(path), watch(path, callback)
 *
 * Example
 * create("/a",1)
 * get("/a") //得到1
 * create("/a/b",2)
 * get("/a/b") //得到2
 * create("/c/d",1) //Error，因为它的上一级“/c”并不存在
 * get("/c") //Error,因为“/c”不存在
 *
 * follow up是写一个watch函数，比如watch("/a",new Runnable(){System.out.println("helloword");})后，
 * 每当create("/a/b"，1) 等在/a之下的目录不产生error的话，都会执行绑在“/a”上的callback函数
 *
 * 比如 watch("/a",System.out.println("yes"))
 * watch("/a/b",System.out.println("no"))
 * 当create("/a/b/c",1)时，两个callback函数都会被触发，会output yes 和no
 *
 * NOTE: 这里用的Runnable会一直运行不停，Runnable用得不熟表示不知道怎么停下来，就用System.exit(0)测试了
 */

import java.util.HashMap;
import java.util.Map;

public class FileSystem {
  Map<String, Integer> pathMap;
  Map<String, Runnable> callbackMap;

  public FileSystem() {
    this.pathMap = new HashMap<>();
    this.callbackMap = new HashMap<>();
    this.pathMap.put("", 0);
  }

  public boolean create(String path, int value) {
    if (pathMap.containsKey(path)) {
      return false;
    }

    int lastSlashIndex = path.lastIndexOf("/");
    if (!pathMap.containsKey(path.substring(0, lastSlashIndex))) {
      return false;
    }

    pathMap.put(path, value);
    return true;
  }

  public boolean set(String path, int value) {
    if (!pathMap.containsKey(path)) {
      return false;
    }

    pathMap.put(path, value);

    // Trigger callbacks
    String curPath = path;
    while (curPath.length() > 0) {
      if (callbackMap.containsKey(curPath)) {
        callbackMap.get(curPath).run();
      }
      int lastSlashIndex = path.lastIndexOf("/");
      curPath = curPath.substring(0, lastSlashIndex);
    }

    return true;
  }

  public Integer get(String path) {
    return pathMap.get(path);
  }

  public boolean watch(String path, Runnable callback) {
    if (!pathMap.containsKey(path)) {
      return false;
    }

    callbackMap.put(path, callback);
    return true;
  }
}

//class Main {
//  public static void main(String[] args) {
//    FileSystem fs = new FileSystem();
//    System.out.println(fs.get("/a")); // null
//    System.out.println(fs.set("/a", 2)); // false
//    System.out.println(fs.create("/a", 1)); // true
//    System.out.println(fs.get("/a")); // 1
//    System.out.println(fs.create("/a/b", 2)); // true
//    System.out.println(fs.create("/b/c", 3)); // false
//    System.out.println(fs.watch("/a/b", new Runnable() {
//      @Override
//      public void run() {
//        System.out.println("callback on /a/b");
//        System.exit(0);
//      }
//    }));
//    System.out.println(fs.watch("/a", new Runnable() {
//      @Override
//      public void run() {
//        System.out.println("callback on /a");
//        System.exit(0);
//      }
//    }));
//    System.out.println(fs.set("/a/b", 10)); // trigger 2 callbacks and true
//  }
//}
