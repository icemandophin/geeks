//Find files in a folder
 public Map<String, String> FindFiles(File root)
    {
        Map<String, String > map = new HashMap<>();
        Queue<File> queue = new LinkedList<>();
        queue.offer(root);
        List<String> isVisited = new ArrayList<>();
        while(queue.size()>0)
        {
            File file = queue.poll();
            if(file.isFile())
            {

                map.put(file.getAbsolutePath(), file.toString());

            }
            if(file.isDirectory())
            {
                File[] files = file.listFiles();
                for (File childFile:files)
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
