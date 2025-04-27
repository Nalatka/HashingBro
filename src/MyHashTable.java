public class MyHashTable<K,V> {
    public class Node<K,V> {
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
    Node<K,V>[] chainArray;
     int size;
     int M=11;
    public MyHashTable() {
        chainArray = new Node[M];
    }
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new Node[M];
    }
    private int hash(K key) {
        int hash = key.hashCode()%M;
        return (hash<0)?(hash+M):(hash);
    }
    public void put(K key, V value) {
        int index = hash(key);
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

        checkResize();
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
    public V getValue(K key) {
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
    private void resize() {
        int newM = M * 2; // Увеличиваем размер в 2 раза
        Node<K, V>[] newChainArray = new Node[newM];
        for (Node<K, V> node : chainArray) {
            while (node != null) {
                K key = node.getKey();
                V value = node.getValue();
                int newIndex = Math.abs(key.hashCode()) % newM; // Новый индекс
                Node<K, V> newNode = new Node<>(key, value);
                newNode.setNext(newChainArray[newIndex]);
                newChainArray[newIndex] = newNode;
                node = node.getNext();
            }
        }
        chainArray = newChainArray;
        M = newM;
    }
    public void checkResize() {
        if ((size * 1.0) / M > 0.75) {
            resize();
        }
    }
}
