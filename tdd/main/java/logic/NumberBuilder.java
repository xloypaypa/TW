package logic;

import java.util.Arrays;
import java.util.Random;

public class NumberBuilder {

    private Random random;

    public NumberBuilder(Random random) {
        this.random = random;
    }

    public String buildNumber() {
        Node[] nodes = buildNodeArray();
        String result = "";
        for (int i = 0; i < 4; i++) {
            result += nodes[i].value;
        }
        return result;
    }

    private Node[] buildNodeArray() {
        Node[] nodes = new Node[10];
        for (int i = 0; i < 10; i++) {
            nodes[i] = new Node(i, random);
        }
        Arrays.sort(nodes);
        return nodes;
    }

    private static class Node implements Comparable<Node> {
        private int value;
        private int key;

        private Node(int value, Random random) {
            this.value = value;
            this.key = random.nextInt();
        }

        @Override
        public int compareTo(Node o) {
            return this.key - o.key;
        }
    }
}
