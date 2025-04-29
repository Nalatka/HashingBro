import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer,Student> hashTable = new MyHashTable<>();
        Random random = new Random();
        for(int i=0;i<100000;i++) {
            hashTable.put(random.nextInt(100000),new Student("name"+i,random.nextInt(100)));
        }
        for(int i=0;i< hashTable.M;i++) {
            int count = 0;
            MyHashTable<Integer, Student>.Node<Integer, Student> head = hashTable.chainArray[i];
            while(head!=null) {
                count++;
                head = head.getNext();
            }
            System.out.println(count);
        }
        System.out.println(hashTable.getSize());
    }
}