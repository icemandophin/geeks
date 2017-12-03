// char to int:
int num = Character.getNumericValue(ch);

// string to int:
int num = Integer.valueOf(str);

// string to char array:
char[] ch = str.toCharArray();

// check alphanumeric:
boolean check = Character.isLetterOrDigit(ch);

// convert case
char low = Character.toLowerCase(ch);

// sort customized array
Pair[] sorted = Collections.sort(pairs, new Comparator<Pair>(){
    public int compare(Pair a, Pair b) {
        return a.x - b.x;
    }
});
