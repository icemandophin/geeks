/*
S: Scenario
Long URL to short URL and reversed.

N: Need (Assume the system is not massive if you are not sure)
QPS (queries per second)
Daily User: 100M
Daily usage per person: (Write) long2short 0.1, (Read) short2long 1
Daily request: Write 10M, Read 100M
QPS: Since a day is 86400s approximately 100K.
Write 100, Read 1K

Peak QPS: Write 200, Read 2K
(Thousand level can be handled by a single SSD MySQL Machine)

Storage
10M new mappings (long URL to short URL) per day
assume each mapping takes 100B in average
1GB every day. 1 TB hard drive could stand for 3 years.
Storage is not the problem for this kind of system.

A: API
Only one service: URLService

Core (Business Logic) Layer:
Class: URLService
Interface:
URLService.encode(String long_url)
URLService.decode(String short_url)
Web Layer:
REST API:
GET: /{short_url}, return a http redirect response(301)
POST: goo.gl method - google shorten URL
Request Body: {url=longUrl} e.g. {"longUrl": "http://www.google.com/"}
Return OK(200), short_url is included in the data

Database Schema
One table (id, long_url). id is the primary key, ordered by long_url
The basic system architecture:
Browser <-> Web <-> Core <-> DB

O: optimize
How to improve the response speed?
IMPROVE THE RESPONSE SPEED BETWEEN WEB SERVER AND DATABASE
Use Memcached to improve response speed.
When getting long_url, search in the cache first, then database.
We could put 90% read request on the cache.

IMPROVE THE RESPONSE SPEED BETWEEN WEB SERVER AND USER'S BROWSER
Different locations use different web server and cache server.
All the areas share a DB used to match the users to the closest web server
(through DNS) when they have a miss on the cache.
*/

/*
base62:
Take short_url as a 62 base notation. 6 bits could represent 62^6=57 billion.
Each short_url represent a decimal digit.
It could be the auto_increment/Sequential id in SQL database.
*/
public class URLService {
    HashMap<String, Integer> ltos;
    HashMap<Integer, String> stol;
    static int COUNTER;
    String elements;
    URLService() {
        ltos = new HashMap<String, Integer>();
        stol = new HashMap<Integer, String>();
        COUNTER = 1;
        elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }
    public String longToShort(String url) {
        String shorturl = base10ToBase62(COUNTER);
        ltos.put(url, COUNTER);
        stol.put(COUNTER, url);
        COUNTER++;
        return "http://tiny.url/" + shorturl;
    }
    public String shortToLong(String url) {
        url = url.substring("http://tiny.url/".length());
        int n = base62ToBase10(url);
        return stol.get(n);
    }

    public int base62ToBase10(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            n = n * 62 + convert(s.charAt(i));
        }
        return n;

    }
    public int convert(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }
        return -1;
    }
    public String base10ToBase62(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.insert(0, elements.charAt(n % 62));
            n /= 62;
        }
        while (sb.length() != 6) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }
}

/*
Recommended: Random generate
generate 6-byte string with 62 elements
check if it already exist
No Sequential ID - harder to decode
*/
public class TinyUrl {
    private Map<String, String> long2Short;
    private Map<String, String> short2Long;

    public TinyUrl() {
        long2Short = new HashMap<String, String>();
        short2Long = new HashMap<String, String>();
    }

    /**
     * @param url a long url
     * @return a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        if (long2Short.containsKey(url)) {
            return long2Short.get(url);
        }

        while (true) {
            String shortURL = generateShortURL();
            if (!short2Long.containsKey(shortURL)) {
                short2Long.put(shortURL, url);
                long2Short.put(url, shortURL);
                return shortURL;
            }
        }
    }

    /**
     * @param url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String url) {
        if (!short2Long.containsKey(url)) {
            return null;
        }

        return short2Long.get(url);
    }

    private String generateShortURL() {
        String allowedChars =
            "0123456789" +
            "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random rand = new Random();
        String shortURL = "http://tiny.url/";
        for (int i = 0; i < 6; i++) {
            int index = rand.nextInt(62);
            shortURL += allowedChars.charAt(index);
        }

        return shortURL;
    }
}
