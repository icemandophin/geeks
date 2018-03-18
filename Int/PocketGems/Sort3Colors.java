class PocketColor {
int data
char c;
PocketColor() {
}
PocketColor(int data, char c) {
this.data = data;
this.c = c;
}
}

public class Solution {
public void sort(PocketColor[] colors) {[br] if(colors.length == 0 || colors == null) return;
int j = 0;
while(colors[j].c == 'r') j++;
for(int i = j + 1; i < colors.length; i++) {
if(colors.c == 'r') {
exch(colors, i, j);
while(colors[j].c == 'r') j++;
}
}
while(colors[j].c == 'g') j++;
for(int i = j + 1; i < colors.length && i > j; i++) {
if(colors.c == 'g') {
exch(colors, i, j);
while(colors[j].c == 'g') j++;
}
}
}
private void exch(PocketColor[] colors, int i, int j) {[br] PocketColor temp = new PocketColor();
temp = colors;
colors = colors[j];
colors[j] = temp;
}
}
楼主搞出这个之后，他让我写test case，结果居然不是要test case，而是要我写出main function，晕
public static void main(String[] args) {[br] PocketColor p1 = new PocketColor(3， 'g');
PocketColor p2 = new PocketColor(1， 'r');
PocketColor p3 = new PocketColor(0， 'b');
PocketColor p4 = new PocketColor(2， 'r');
PocketColor p5 = new PocketColor(5， 'g');
PocketColor p6 = new PocketColor(4， 'r');
PocketColor[] colors = new PocketColor[]{p1, p2, p3, p4, p5, p6};
Solution s = new Solution();
s.sort(colors);
System.out.print(colors[0].c + " ");
System.out.print(colors[1].c + " ");
System.out.print(colors[2].c + " ");
System.out.print(colors[3].c + " ");
System.out.print(colors[4].c + " ");
System.out.print(colors[5].c + " ");
}
