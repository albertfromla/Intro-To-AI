package structure;

public class Node {
        public int i;
        public int j;
        public int val;
        public int len;
        public int heuri;
        public int total;
        public Node(int i, int j, int val, int len, int heri, int total) {
            this.i = i;
            this.j = j;
            this.val = val;
            this.len = len;
            this.heuri = heuri;
            this.total = total;
        }
        public int getLen(){
            return len;
        }
}
