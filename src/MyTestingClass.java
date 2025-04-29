import java.util.Random;
public class MyTestingClass {
    private int id;
    private String name;
    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        for(char c : name.toCharArray()) {
            hash = 97 * hash + c;
        }
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        MyTestingClass other = (MyTestingClass) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "MyTestingClass{" + "id=" + id + ", name=" + name + '}';
    }
}
