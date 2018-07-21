/*
ref: http://www.cnblogs.com/grandyang/p/8440087.html
*/
class Solution {
    public List<String> ipToCIDR(String ip, int n) {
        // get start/end ip addr
        long start = ipToLong(ip);
        long end = start + n - 1;
        List<String> res = new ArrayList<>();
        // try to fill ips into cur ip range (start + mask)
        while(start <= end) {
            // get first 1 from right, in long expression
            // cur start can make range that hold at most 2^(32 - mask) ips
            long firstOne = start & (-start);
            // get mask length
            int mask = 32 - (int)(Math.log(firstOne) / Math.log(2));
            // get length of cur range [start, end]
            double cover = Math.floor(Math.log(end - start + 1) / Math.log(2));
            int stable = 32 - (int)(cover);
            // update mask to larger length / include less ips for cur range
            mask = Math.max(mask, stable);
            // add to result
            String cur = longToIp(start);
            res.add(cur + "/" + mask);
            // 2^(32 - mask) is number of ips covered by cur range
            start += Math.pow(2, (32 - mask));
        }

        return res;
    }
    // covert per byte
    private long ipToLong(String ip) {
        long[] res = new long[4];
        String[] s = ip.split("\\.");
        for (int i = 0; i < 4; ++i) {
            res[i] = Long.valueOf(s[i]);
        }

        return (res[0] << 24) + (res[1] << 16) + (res[2] << 8) + res[3];
    }
    // binary format view: covert per byte
    private String longToIp(long num) {
        StringBuilder res = new StringBuilder();
        res.append(String.valueOf(num >>> 24));
        res.append(".");
        res.append(String.valueOf((num & 0xFFFFFF) >>> 16));
        res.append(".");
        res.append(String.valueOf((num & 0xFFFF) >>> 8));
        res.append(".");
        res.append(String.valueOf(num & 0xFF));

        return res.toString();
    }
}