import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> hashTable = new MyHashTable<>();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int id = random.nextInt(100000);
            String name = "name" + random.nextInt(100000);
            MyTestingClass key = new MyTestingClass(id, name);
            Student value = new Student(name, id);
            hashTable.put(key, value);
        }
        for (int i = 0; i < hashTable.getSize(); i++) {
            int count = 0;
            MyHashTable<MyTestingClass, Student>.Node<MyTestingClass, Student> node = hashTable.chainArray[i];
            while (node != null) {
                count++;
                node = node.getNext();
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }
}