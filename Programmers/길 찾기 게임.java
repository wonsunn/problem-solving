import java.util.*;

class Solution {
    
    static int[] pre, post;
    static int idx = 0;
    
    public int[][] solution(int[][] nodeinfo) {
        pre = new int[nodeinfo.length];
        post = new int[nodeinfo.length];
        
        Node[] arr = new Node[nodeinfo.length];
        for (int i = 0; i < arr.length; i++) 
            arr[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        Arrays.sort(arr);
        
        Tree tree = new Tree(arr[0]);
        for (int i = 1; i < arr.length; i++)
            tree.insert(arr[i]);
        
        preorder(tree.root);
        idx = 0;
        postorder(tree.root);
        
        return new int[][]{pre, post};
    }
    
    static void preorder(Node node) {
        if (node == null)
            return;
        
        pre[idx++] = node.val;
        preorder(node.left);
        preorder(node.right);
    }
    
    static void postorder(Node node) {
        if (node == null)
            return;
        
        postorder(node.left);
        postorder(node.right);
        post[idx++] = node.val;
    }
}

class Tree {
    Node root;
    Tree (Node root) {
        this.root = root;
    }
    
    public void insert(Node node) {
        Node thisNode = root;
        
        while (true) {
            if (node.x < thisNode.x) {
                if (thisNode.left != null)
                    thisNode = thisNode.left;
                else {
                    thisNode.left = node;
                    break;
                }
            }
            else {
                if (thisNode.right != null)
                    thisNode = thisNode.right;
                else {
                    thisNode.right = node;
                    break;
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int x, y, val;
    Node left, right;
    
    Node (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    
    public int compareTo(Node other) {
        if (this.y == other.y) return this.x - other.x;
        else return other.y - this.y;
    }
}