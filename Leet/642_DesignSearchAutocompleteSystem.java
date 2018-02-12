class Pair {
    public int count;
    public String sentence;

    public Pair(int count, String sentence) {
        this.count = count;
        this.sentence = sentence;
    }
}

class TrieNode {
    private final int N = 27;
    private TrieNode[] links;
    private int count;

    public TrieNode() {
        links = new TrieNode[N];
    }

    public boolean containsKey(char ch) {
        return links[getIdx(ch)] != null;
    }

    public TrieNode get(char ch) {
        return links[getIdx(ch)];
    }

    public void put(char ch, TrieNode node) {
        links[getIdx(ch)] = node;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    private int getIdx(char ch) {
        return ch == ' ' ? N - 1 : ch - 'a';
    }
}

public class AutocompleteSystem {
    private TrieNode root;
    private TrieNode node;
    private String sentence;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        node = root;
        sentence = "";

        for (int i = 0; i < sentences.length; i++) {
            insert(root, sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();

        if (c == '#') {
            insert(root, sentence, 1);
            node = root;
            sentence = "";
        } else {
            sentence += c;

            if (node == null || !node.containsKey(c)) {
                node = null;
            } else {
                node = node.get(c);
                Queue<Pair> queue = new PriorityQueue<>((a, b) -> a.count != b.count ? Integer.compare(b.count, a.count) : String.compare(a.sentence, b.sentence));
                traverse(node, sentence, queue);

                for (int i = 0; i < 3 && !queue.isEmpty(); i++) {
                    res.add(queue.poll().sentence);
                }
            }
        }

        return res;
    }
    // insert word to Trie
    // last char node contains cnt of this word
    private void insert(TrieNode node, String sentence, int count) {
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);

            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }

            node = node.get(ch);
        }

        node.setCount(node.getCount() + count);
    }

    private void traverse(TrieNode node, String sentence, Queue<Pair> queue) {
        if (node.getCount() > 0) {
            queue.offer(new Pair(node.getCount(), sentence));
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (node.containsKey(c)) {
                traverse(node.get(c), sentence + c, queue);
            }
        }

        char space = ' ';

        if (node.containsKey(space)) {
            traverse(node.get(space), sentence + space, queue);
        }
    }
}
