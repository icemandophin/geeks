/*
Imagine you are given a real file system, how will you search files? DFS or BFS ?
In general, BFS will use more memory then DFS. However BFS can take advantage of
the locality of files in inside directories, and therefore will probably be faster

If the file content is very large (GB level), how will you modify your solution?
In a real life solution we will not hash the entire file content, since it’s not
practical. Instead we will first map all the files according to size. Files with
different sizes are guaranteed to be different. We will than hash a small part of
the files with equal sizes (using MD5 for example). Only if the md5 is the same,
we will compare the files byte by byte

If you can only read the file by 1kb each time, how will you modify your solution?
This won’t change the solution. We can create the hash from the 1kb chunks, and
then read the entire file if a full byte by byte comparison is required.

What is the time complexity of your modified solution? What is the most time
consuming part and memory consuming part of it? How to optimize?
Time complexity is O(n^2 * k) since in worse case we might need to compare every
file to all others. k is the file size.
MD5 hashing cost most time/space. Can optimize with:
1. only hash first n  bytes of file
2. multiple layer compare (1) compare name (2) compare hashing (3) byte-to-byte

How to make sure the duplicated files you find are not false positive?
We will use several filters to compare: File size, Hash and byte by byte comparisons.
avoid collision: SHA256 SHA512
*/

class File {
    public boolean isFile();
    public boolean isDirectory();
    public String getAbsolutePath();
    public String content();
    public File[] listFiles();
}
//Find files in a folder
 public Map<String, String> FindFiles(File root)
    {
        // map path to its content
        Map<String, String > map = new HashMap<>();
        // BFS from root and find all files
        Queue<File> queue = new LinkedList<>();
        queue.offer(root);
        // record visited path
        List<String> isVisited = new ArrayList<>();
        while(queue.size()>0)
        {
            File file = queue.poll();
            if(file.isFile())
            {
                map.put(file.getAbsolutePath(), file.content());
            }
            if(file.isDirectory())
            {
                File[] files = file.listFiles();
                for (File childFile : files)
                {
                    if(!isVisited.contains(file.getAbsolutePath()))
                    {
                        queue.offer(childFile);
                        isVisited.add(file.getAbsolutePath());
                    }
                }
            }
        }
        return map;
    }
//File with small size
public List<List<String>> findDuplicate(String[] paths) {
        Map<String, HashSet<String>> mp = new HashMap<>();
        for (String path:paths)
        {
            String[] files = path.split(" ");
            if(files.length<1) continue;
            String rootPath = files[0];
            for(int i =1; i<files.length;i++)
            {
                int index = files[i].indexOf('(');
                String fileName = rootPath+'/'+files[i].substring(0, index);
                String fileContent = files[i].substring(index+1, files[i].length()-1);

                mp.putIfAbsent(fileContent, new HashSet<>());
                mp.get(fileContent).add(fileName);
            }
        }
        List<List<String>> output = new ArrayList<>();
        for(String key: mp.keySet())
        {
            if(mp.get(key).size()>0)
            {
                output.add(new ArrayList<>(mp.get(key)));
            }
        }
        return output;
    }
  //File with large size
  public List<List<String>> findLargeDuplicate(String[] paths) {
        Map<Integer, HashSet<String>> mp = new HashMap<>();
        for (String path:paths)
        {
            String[] files = path.split(" ");
            if(files.length<1) continue;
            String rootPath = files[0];
            for(int i =1; i<files.length;i++)
            {
                int index = files[i].indexOf('(');
                String fileName = rootPath+'/'+files[i].substring(0, index);
                int fileSize=  files[i].length()-1;

                mp.putIfAbsent(fileSize, new HashSet<>());
                mp.get(fileSize).add(fileName);
            }
        }
        List<List<String>> output = new ArrayList<>();
        //create MD5 hashing map
        //MD5Hash<bytes[], HashSet<String>> MD5Hash = new HashMap<>()
        for(Integer key: mp.keySet())
        {
            int len = mp.get(key).size();
            if(len>1)
            {
                for(int i =0; i<len; i++)
                {

                    //MD5Hash.putIfAbsent(Hashed(FindContent(mp.get(key)[i]), mp.get(key)[i]));
                }
            }
        }

        return output;
    }
