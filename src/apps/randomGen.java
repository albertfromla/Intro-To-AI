package apps;
import java.util.*;
import apps.randomize;
import structure.Node;
import java.io.*;


public class randomGen {
    public static Node[][] generateMatrix(int n) {
        Node[][] a = new Node[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                /*do {
                    a[i][j] = new Node(i,j,randomize.getRandom(1, n-1));
                } while(a[i][j].val > n-j-1 || a[i][j].val > n-i-1);*/
                int maxI = Math.max((n - i - 1), (i));
                int maxJ = Math.max((n - j - 1), (j));
                int maxNum = Math.max(maxI, maxJ);
                int temp1 = randomize.getRandom(1, maxNum);
                a[i][j] = new Node(i, j, temp1, 0, 2, Integer.MAX_VALUE);
                if (i == n - 1 && j == n - 1) a[i][j].val = 0;
                System.out.print(a[i][j].val + " ");
            }
            System.out.println("");
        }
        //Node[][] matrix
        return a;
    }
}