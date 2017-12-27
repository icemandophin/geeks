// binary search x in Sorted Array a:
// when x not in a[] return -(idx + 1) where idx is insertion point
// that is index of 1st element that > x or a.length
int find = Collections.binarySearch(a, x);

// search with customized comparator comp
int find = Collections.binarySearch(a, x, comp);

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

// split array with "/"
String cut = str.split("/");

// sort customized array
Pair[] sorted = Collections.sort(pairs, new Comparator<Pair>(){
    public int compare(Pair a, Pair b) {
        return a.x - b.x;
    }
});
