
package apps;
import structure.Node;
import java.util.*;
public class A3 {
    static int n;
    static int m;
    static boolean[][] visited;
    static int[][] result;

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
                int step = bfs(matrix, i, j);
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



    public static int[][] print() {
        return result;
    }



    public static int bfs(Node[][] matrix, int goali, int goalj) {
        n = matrix.length;
        m = matrix[0].length;
        //初始化 visited
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = false;
            }
        }

        Queue<Node> queue = new LinkedList<>();
        int step = 0;
        Node start = new Node(0, 0, matrix[0][0].val, 0, 0, 0);
        queue.add(start);
        visited[0][0] = true;
        int[] deltI = {0, 1, 0, -1};
        int[] deltJ = {1, 0, -1, 0};

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                assert node != null;
                if (node.i == goali && node.j == goalj) {
                    return step;
                }
                int value = node.val;
                for (int direction = 0; direction < 4; direction++) {
                    int newI = node.i + deltI[direction] * value;
                    int newJ = node.j + deltJ[direction] * value;
                    if (isValid(newI, newJ)) {
                        Node next = matrix[newI][newJ];
                        queue.add(next);
                        visited[next.i][next.j] = true;
                    }
                }
            }
            step++;
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
 /*       Node node00 = new Node(0, 0, 3);
        Node node01 = new Node(0, 1, 3);
        Node node02 = new Node(0, 2, 2);
        Node node03 = new Node(0, 3, 4);
        Node node04 = new Node(0, 4, 3);
        Node node10 = new Node(1, 0, 2);
        Node node11 = new Node(1, 1, 2);
        Node node12 = new Node(1, 2, 2);
        Node node13 = new Node(1, 3, 1);
        Node node14 = new Node(1, 4, 1);
        Node node20 = new Node(2, 0, 4);
        Node node21 = new Node(2, 1, 3);
        Node node22 = new Node(2, 2, 1);
        Node node23 = new Node(2, 3, 3);
        Node node24 = new Node(2, 4, 4);
        Node node30 = new Node(3, 0, 2);
        Node node31 = new Node(3, 1, 3);
        Node node32 = new Node(3, 2, 1);
        Node node33 = new Node(3, 3, 1);
        Node node34 = new Node(3, 4, 3);
        Node node40 = new Node(4, 0, 1);
        Node node41 = new Node(4, 1, 1);
        Node node42 = new Node(4, 2, 3);
        Node node43 = new Node(4, 3, 2);
        Node node44 = new Node(4, 4, 0);
        Node[][] matrix = {{node00, node01, node02, node03, node04},
                {node10, node11, node12, node13, node14},
                {node20, node21, node22, node23, node24},
                {node30, node31, node32, node33, node34},
                {node40, node41, node42, node43, node44}
        };
  */
        int result = A3.findStep(matrix);
        System.out.println(result);
        int[][] print = A3.print();
        for (int[] ints : print) {
            for (int j = 0; j < print[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}
