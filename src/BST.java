import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BST<K extends Comparable<K>,V> {
    private Node root;
    private int size;
    private class Node{
        private K key;
        private V value;
        private Node left;
        private Node right;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    public void put(K key, V value) {
        if(root==null) {
            root = new Node(key,value);
            size++;
            return;
        }
        Node cur = root;
        while (cur!=null) {
            if(cur.key.compareTo(key)<0) {
                if(cur.left==null) {
                    cur.left = new Node(key,value);
                    return;
                }
            }else if(cur.key.compareTo(key)>0) {
                if(cur.right==null) {
                    cur.right = new Node(key,value);
                    return;
                }
            }
            else{
                cur.value = value;
                return;
            }
        }
        if(key.compareTo(cur.key)<0) {
            cur.left = new Node(key,value);
        }
        else{
            cur.right = new Node(key,value);
        }
        size++;
    }
    public V get(K key) {
        Node cur = root;
        while (cur!=null) {
            if(cur.key.compareTo(key)<0) {
                cur = cur.left;
            }else if(cur.key.compareTo(key)>0) {
                cur = cur.right;
            }else{
                return cur.value;
            }
        }
        return null;
    }
    public void delete(K key) {
        Node parent = null;
        Node cur = root;
        while (cur!=null) {
            if(cur.key.compareTo(key)<0) {
                parent = cur;
                cur = cur.left;
            }else if(cur.key.compareTo(key)>0) {
                parent = cur;
                cur = cur.right;
            }else{
                cur = cur.left;
            }
        }
        if(cur==null) {
            return;
        }
        size--;
        if(cur.left==null) {
            if(parent==null) {
                root = cur.right;
            }else if(parent.left==cur) {
                parent.left = cur.right;
            }else{
                parent.right = cur.right;
            }
        }else if(cur.right==null) {
            if(parent==null) {
                root = cur.left;
            }else if(parent.left==cur) {
                parent.left = cur.left;
            }
        }
        else{
            Node minNode = cur.right;
            Node minParent = cur;
            while (minNode.left!=null) {
                minParent = minNode;
                minNode = minNode.left;
            }
            cur.key = minNode.key;
            cur.value = minNode.value;
        }
    }
    public int getSize() {
        return size;
    }
    public Iterable<K> iterator() {
       List<K> keys = new ArrayList<>();
       inOrder(root,keys);
       return keys;
    }
    private void inOrder(Node cur,List<K> keys) {
        if(cur==null) {
            return;
        }
        inOrder(cur.left,keys);
        keys.add(cur.key);
        inOrder(cur.right,keys);
    }
}
