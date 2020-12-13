package apps;
import org.apache.commons.lang3.time.StopWatch;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.NClob;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;
import structure.Node;
public class A9 {
    //In Nano Seconds;
    static boolean sss = false;
    static StopWatch gg = new StopWatch();
    static long time;
    static int outputNum = -1;
    static long clock;
    private static int hillClimbing(Node[][] matrix, int k) {
        StopWatch temp1 = new StopWatch();
        temp1.start();
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
                if (newCount < oldCount) {
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
            if (outputNum != 22) c--;
            if(temp1.getTime(TimeUnit.MILLISECONDS)> 300){
                return 0;
            }
        }
        return 1;
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

       Node[][] matrix; // = randomGen.generateMatrix(5);
/*
        System.out.println();
        int k = 1;
        long newEndTime = System.nanoTime();
        long endtim = System.nanoTime() + 1000000;

*/
        gg.start();
        while(1==1) {
            matrix = randomGen.generateMatrix(5);
            int x = hillClimbing(matrix, 1);
            if(gg.getTime() == 1000) break;
            if(x == 1) break;
        }
        printResult(matrix);
        System.out.println(outputNum);
        System.out.println(time);
    }
}


