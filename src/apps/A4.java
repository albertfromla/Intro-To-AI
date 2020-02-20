package apps;
import java.util.*;
import structure.Node;
public class A4 {
    //In Nano Seconds;
    static long time;
    static int outputNum = -1;
    private static void hillClimbing(Node[][] matrix, int k) {
        //get old matrix output;
        int n = matrix.length;
        int m = matrix[0].length;

        for (int c = 0; c < k; c++) {
            //find location of new node;
            long oldStartTime=System.nanoTime();
            int oldCount = A3.bfs(matrix, n - 1, m - 1);
            long oldEndTime=System.nanoTime();
            long Od = oldEndTime - oldStartTime;
            int i = getRandom(0, n - 1);
            int j = getRandom(0, m - 1);
            while ((i == n - 1 && j == m - 1)) {
                i = getRandom(0, n - 1);
                j = getRandom(0, m - 1);
            }
            //get max value for new node;
            int maxI = Math.max((n - i - 1),(i));
            int maxJ = Math.max((m - j - 1), (j));
            int maxNum = Math.max(maxI, maxJ);
            int newValue = getRandom(1, maxNum);
            //create new node, find old node;
            Node newNode = new Node(i, j, newValue, 0, 0, 0);
            Node oldNode = matrix[i][j];
            matrix[i][j] = newNode;
            //get new matrix output;
            long newStartTime = System.nanoTime();
            int newCount = A3.bfs(matrix, n - 1, m - 1);
            long newEndTime=System.nanoTime();
            long Nd = newEndTime - newStartTime;
            //determine whether use new matrix or old matrix;
            if (newCount != -1 && oldCount != -1) {
                if (newCount > oldCount) {
                    matrix[i][j] = oldNode;
                    time = Od;
                }
                outputNum = newCount;
                time = Nd;
            } else if (newCount == -1) {
                matrix[i][j] = oldNode;
            }
            outputNum = newCount;
            time = Nd;
        }
    }

    private static int getRandom(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    public static void printResult(Node[][] matrix) {
        int col = matrix[0].length;
        int j;
        for (Node[] nodes : matrix) {
            for (j = 0; j < col; j++) {
                System.out.print(nodes[j].val + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
/*        Node node00 = new Node(0, 0, 3, 0);
        Node node01 = new Node(0, 1, 3, 0);
        Node node02 = new Node(0, 2, 2, 0);
        Node node03 = new Node(0, 3, 4, 0);
        Node node04 = new Node(0, 4, 3, 0);
        Node node10 = new Node(1, 0, 2, 0);
        Node node11 = new Node(1, 1, 2, 0);
        Node node12 = new Node(1, 2, 2, 0);
        Node node13 = new Node(1, 3, 1, 0);
        Node node14 = new Node(1, 4, 1, 0);
        Node node20 = new Node(2, 0, 4, 0);
        Node node21 = new Node(2, 1, 3, 0);
        Node node22 = new Node(2, 2, 1, 0);
        Node node23 = new Node(2, 3, 3, 0);
        Node node24 = new Node(2, 4, 4, 0);
        Node node30 = new Node(3, 0, 2, 0);
        Node node31 = new Node(3, 1, 3, 0);
        Node node32 = new Node(3, 2, 1, 0);
        Node node33 = new Node(3, 3, 1, 0);
        Node node34 = new Node(3, 4, 3, 0);
        Node node40 = new Node(4, 0, 1, 0);
        Node node41 = new Node(4, 1, 1, 0);
        Node node42 = new Node(4, 2, 3, 0);
        Node node43 = new Node(4, 3, 2, 0);
        Node node44 = new Node(4, 4, 0, 0);
        Node[][] matrix = {{node00, node01, node02, node03, node04},
                {node10, node11, node12, node13, node14},
                {node20, node21, node22, node23, node24},
                {node30, node31, node32, node33, node34},
                {node40, node41, node42, node43, node44}
        };

 */
        Node[][] matrix = randomGen.generateMatrix(5);
        System.out.println();
        int k = 3000;
        hillClimbing(matrix, k);
        printResult(matrix);
        System.out.println(outputNum);
        System.out.println(time);
    }
}
