import java.util.PriorityQueue;

public class TenWayMergeSort implements Sorter {
    private static final int K = 10;
    private static final int CUTOFF = 20;

    @Override
    public String nameAlg() {
        return "10-way MergeSort";
    }

    @Override
    public void sort(int[] a, Metrics m) {
        m.reset();
        int n = a.length;

        long start = System.nanoTime();

        int[][] blocks = splitIntoBlocks(a, n, K);
        m.extraMemory += (long) K * Integer.BYTES * (n / K);

        for (int[] block : blocks) {
            insertionSort(block, 0, block.length - 1, m);
        }

        int[] res = kWayMerge(blocks, m);

        System.arraycopy(res, 0, a, 0, n);
        m.cop += n;
        m.time = System.nanoTime() - start;
    }

    private int[][] splitIntoBlocks(int[] a, int n, int K) {
        int blockSize = (n + K - 1) / K;

        int[][] blocks = new int[K][];
        for (int i = 0; i < K; i++) {
            int start = i * blockSize;
            int end = Math.min((i + 1) * blockSize, n);

            int size = Math.max(0, end - start);
            int[] block = new int[size];

            for (int j = 0; j < size; j++) {
                block[j] = a[start + j];
            }

            blocks[i] = block;
        }
        return blocks;
    }

    private void insertionSort(int[] a, int left, int right, Metrics m) {
        for (int i = left + 1; i <= right; i++) {
            int key = a[i];
            m.cop++;

            int j = i - 1;
            while (j >= left) {
                m.comparison++;
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    m.cop++;
                    j--;
                } else break;
            }
            a[j + 1] = key;
            m.cop++;
        }
    }

    private static class Node {
        int value;
        int block;
        int index;

        Node(int value, int block, int index) {
            this.value = value;
            this.block = block;
            this.index = index;
        }
    }

    private int[] kWayMerge(int[][] blocks, Metrics m) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (x, y) -> {
                    m.comparison++;
                    return Integer.compare(x.value, y.value);
                });

        int totalLength = 0;

        for (int b = 0; b < blocks.length; b++) {
            if (blocks[b].length > 0) {
                pq.add(new Node(blocks[b][0], b, 0));
                totalLength += blocks[b].length;
            }
        }

        int[] result = new int[totalLength];

        int idx = 0;
        while (!pq.isEmpty()) {

            Node smallest = pq.poll();
            result[idx++] = smallest.value;
            m.cop++;

            int block = smallest.block;
            int nextIndex = smallest.index + 1;

            if (nextIndex < blocks[block].length) {
                pq.add(new Node(blocks[block][nextIndex], block, nextIndex));
            }
        }

        return result;
    }
}

