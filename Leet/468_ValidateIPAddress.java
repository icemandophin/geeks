/*
sub-methods to check:
1. IPv4 / IPv6 format
2. split ip into tokens for further check
*/
class Solution {
    public String validIPAddress(String ip) {
        if (checkIPv4(ip)) {
            return "IPv4";
        } else if (checkIPv6(ip)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }

    private boolean checkIPv4(String ip) {
        // check size range 7 - 15
        if (ip.length() < 7 || ip.length() > 15) {
            return false;
        }
        // check head / tail valid
        if (ip.charAt(0) == '.' || ip.charAt(ip.length() - 1) == '.') {
            return false;
        }
        // check each of 4 tokens
        String[] tokens = ip.split("\\.");
        if (tokens.length != 4) {
            return false;
        }

        for (String token : tokens) {
            if (!checkIPv4Token(token)) {
                return false;
            }
        }

        return true;
    }

    private boolean checkIPv4Token(String token) {
        // check length 1 - 3
        if (token.length() < 1 || token.length() > 3) {
            return false;
        }
        // check token start with 0
        if (token.charAt(0) == '0' && token.length() > 1) {
            return false;
        }
        // check val is not negative
        if (token.charAt(0) == '-') {
            return false;
        }

        try{
            int val = Integer.parseInt(token);
            // check value scope 0 - 255
            if (val > 255) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private boolean checkIPv6(String ip) {
        // check length scope
        if (ip.length() < 15 || ip.length() > 39) {
            return false;
        }
        // check head / tail
        if (ip.charAt(0) == ':' || ip.charAt(ip.length() - 1) == ':') {
            return false;
        }
        // check each token
        String[] tokens = ip.split(":");
        if (tokens.length != 8) {
            return false;
        }

        for (String token : tokens) {
            if (!checkIPv6Token(token)) {
                return false;
            }
        }

        return true;
    }

    private boolean checkIPv6Token(String token) {
        // check length
        if (token.length() < 1 || token.length() > 4) {
            return false;
        }
        // check token value
        char[] val = token.toCharArray();

        for (char ch : val) {
            // check digit scope 0 - 9, A - F
            if (!Character.isDigit(ch) && !(ch >= 'a' && ch <= 'f') && !(ch >= 'A' && ch <= 'F')) {
                return false;
            }
        }

        return true;
    }
}
