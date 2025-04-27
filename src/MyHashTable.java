public class MyHashTable<K,V> {
    private class Node<K,V> {
        private K key;
        private V value;
        private Node<K,V> next;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
        public Node<K,V> getNext() {
            return next;
        }
        public void setNext(Node<K,V> next) {
            this.next = next;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    private Node<K,V>[] chainArray;
    private int size;
    private int M=11;
    public MyHashTable() {
        chainArray = new Node[M];
    }
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new Node[M];
    }
    private int hash(K key) {
        return key.hashCode() % M;
    }
    public void put(K key, V value) {
        int index = key.hashCode() % M;
        Node<K,V> node = chainArray[index];
        if (node == null) {
            chainArray[index] = new Node<>(key, value);
            size++;
        } else {
            Node<K,V> cur = node;
            while (cur.getNext() != null) {
                if (cur.getKey().equals(key)) {
                    cur.value = value;
                    return;
                }
                if(cur.getNext()==null) {
                    break;
                }
                cur = cur.getNext();
            }
            cur.setNext(new Node<>(key, value));
            size++;
        }

    }
    public V get(K key) {
        int index = hash(key);
        Node<K,V> node = chainArray[index];
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
            node = node.getNext();
        }
        return null;
    }
    public V remove(K key) {
        int index = hash(key);
        Node<K,V> node = chainArray[index];
        Node<K,V> pre = null;
        while (node != null) {
            if (node.getKey().equals(key)) {
                if (pre == null) {
                    chainArray[index] = node.getNext();
                    return node.getValue();
                } else {
                    pre.setNext(node.getNext());
                }
                size--;
                return node.getValue();
            }
            pre = node;
            node = node.getNext();
        }
        return null;
    }
    public int getSize() {
        return size;
    }
    public boolean contains(K key) {
        return get(key) != null;
    }
    public K getKey(K key) {
        int index = hash(key);
        Node<K,V> node = chainArray[index];
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node.getKey();
            }
            node = node.getNext();
        }
        return null;
    }

}
