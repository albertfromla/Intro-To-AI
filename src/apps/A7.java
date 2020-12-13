package apps;

import org.apache.commons.lang3.ObjectUtils;
import structure.Node;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.*;

public class A7 {
    private static Comparator<Node[][]> matrixCom = (n1, n2) -> getStep(n1) - getStep(n2);
    private static int getStep(Node[][] matrix) {
        int step = A3.findStep(matrix);
        return step;
    }
    public static Node[][] crossover(Node[][] matrix1, Node[][] matrix2) {
        int n = matrix1.length;
        int m = matrix1[0].length;
        Node[][] matrix = new Node[n][m];
        //k1 = upper m1 lower m2;
        //mk2 = lower m1 upper m2;
        //compare k1 and k2 which step is less;
        //return smallest of m1, m2 and ma;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix1[i][j].val == matrix2[i][j].val) {
                    matrix[i][j] = matrix1[i][j];
                } else {
                    int maxI = Math.max((n - i - 1),(i));
                    int maxJ = Math.max((m - j - 1), (j));
                    int maxNum = Math.max(maxI, maxJ);
                    int newValue = A4.getRandom(1, maxNum);
                    matrix[i][j] = new Node(i, j, newValue, 0, 2,0);
                }
            }
        }
        return matrix;
    }
    public static boolean selection(Node[][] matrix, int k) {
        int n = matrix.length;
        int step = A3.findStep(matrix);
        if (step < 0) {
            return false;
        }
        return step <= k;
    }

    //randomly select n blocks and change to random values
    public static Node[][] mutate(Node[][] matrix, int blocksToMutate) {
        Node[][] mutMatrix = matrix;
        int n = matrix.length;
        int m = matrix[0].length;
        int percent = blocksToMutate;
        for (int c = 0; c < percent; c++) {
            //find location of new node;
            int i = A4.getRandom(0, n - 1);
            int j = A4.getRandom(0, m - 1);
            while ((i == n - 1 && j == m - 1)) {
                i = A4.getRandom(0, n - 1);
                j = A4.getRandom(0, m - 1);
            }
            //get max value for new node;
            int maxI = Math.max((n - i - 1),(i));
            int maxJ = Math.max((m - j - 1), (j));
            int maxNum = Math.max(maxI, maxJ);
            int newValue = A4.getRandom(1, maxNum);
            //create new node, find old node;
            Node newNode = new Node(i, j, newValue, 0, 0, 0);
            mutMatrix[i][j] = newNode;

            //get new matrix output;
        }
        return matrix;
    }
    public static int getGen(int k, Node[][] matrix) {
        int n = matrix.length;
        List<Node[][]> list = new LinkedList<>();
        for(int i = 0;i < 10; i++) {
            list.add(randomGen.generateMatrix(n));
            System.out.println();
        }
        double optimalRatio;
        int gen = 0;
        while(k >= 2){

            int size = list.size();

            //crossover
            for (int j = 0; j + 1 < size / 3; j++) {
                for (int i = 1; i < 6; i++) {
                    Node[][] temp = crossover(list.get(j), list.get(i));
                    int count = 0;
                        while(A3.findStep(temp) < 2 && count < 20) {
                            temp = crossover(list.get(i), list.get(j));
                            count++;
                        }

                    list.add(temp);
                }
            }

            //mutate
            for (int i = 0; i < size / 2; i++) {
                Node[][] temp = mutate(list.get(i), 7);
                while(A3.findStep(temp) < 2) temp = mutate(list.get(i), 7);
                list.add(temp);
            }

            //sort list by k
            Collections.sort(list, matrixCom);
            //remove last half of puzzles list
            int newSize = list.size();
            for (int i = newSize - 1; i > newSize - size / 2; i--) {
                list.remove(i);
            }

            //eliminate puzzles after set size
            while (list.size() > 100) {
                int j = list.size() - 1;
                list.remove(j);
            }
            //
            if (list.size() == 0) {
                list.add(randomGen.generateMatrix(5));
                list.add(randomGen.generateMatrix(5));
                k = 5;
                gen = 0;
            }

            //eliminate puzzles above step k
            for (int i = 0; i < list.size(); i++) {
                if (!selection(list.get(i), k)) {
                    list.remove(i);
                }
            }

            int numOptimal = 0;
            for (int i = 0; i < list.size(); i++) {
                if(A3.findStep(list.get(i)) == 2) numOptimal++;
            }
            optimalRatio = ((double) numOptimal / (double) list.size());
            System.out.println("gen = " + gen  + " optimalRatio = "  + optimalRatio);

            if (k > 2) {
                k--;
            }
            gen++;
            if (optimalRatio >= 0.9) break;
        }
        return gen;
    }



    public static void main(String[] args) {

/*        int k = 10;
        while (k >= 2) {
            for (int i = 0; i < list.size(); i++) {
                if(!selection(list.get(i), k)) {
                    list.remove(list.get(i));
                }
            }

            if (k > 2) {
                k--;
            }
        }
 */
        System.out.println();
        int k = 2;
        int n = 11;
        int gg = getGen(k, null);
        System.out.println("Ratio achieved at gen " + gg);//(getGen(k, n) - 1));
    }
}
