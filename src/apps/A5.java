package apps;
import structure.Node;
import java.util.*;

public class A5 {
    static int n;
    static int m;
    static boolean[][] visited;
    static int[] DeltaI = {0, 1, 0, -1};
    static int[] DeltaJ = {1, 0, -1, 0};
    static int[][] result;
    private static Comparator<Node> NodeComparator = (n1, n2) -> n1.len - n2.len;

    public static int findStep(Node[][] matrix) {
        if (matrix == null || matrix[0].length == 0) {
            return -1;
        }
        if (matrix.length == 1 && matrix[0].length == 1) {
            return 0;
        }
        n = matrix.length;
        m = matrix[0].length;
        result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int step = SFA(matrix, i, j);
                result[i][j] = step;
            }
        }
        if (result[n - 1][m - 1] != -1) {
            return result[n - 1][m - 1];
        } else {
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (result[i][j] == -1) {
                        count--;
                    }
                }

            }
            return count;
        }
    }

    public static int SFA(Node[][] matrix, int goali, int goalj) {
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = false;
            }
        }

        PriorityQueue<Node> queue = new PriorityQueue<Node>(NodeComparator);
        queue.add(matrix[0][0]);
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited[node.i][node.j] = true;
            if (node.i == goali && node.j == goalj) {
                return node.len;
            }
            int len = node.len;
            int value = node.val;
            for (int Dir = 0; Dir < 4; Dir++) {
                int newI = node.i + DeltaI[Dir] * value;
                int newJ = node.j + DeltaJ[Dir] * value;
                if (isValid(newI, newJ)) {
                    Node next = matrix[newI][newJ];
                    if (next.len != 0) {
                        next.len = Math.min(next.len, len + 1);
                    } else {
                        next.len = len + 1;
                    }
                    queue.add(next);
                }
            }
        }
        return -1;
    }
    public static boolean isValid(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return false;
        }
        return !visited[i][j];
    }

    public static void main(String[] args) {
        Node[][] matrix = randomGen.generateMatrix(5);
        int step = findStep(matrix);
        System.out.println(step);
        for (int[] ints : result) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}
