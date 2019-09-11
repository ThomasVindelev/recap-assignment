import java.util.concurrent.atomic.AtomicInteger;

public class Exam {

    private int id;
    private String name;

    public Exam(String name, AtomicInteger id) {
        this.id = id.incrementAndGet();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "ID: " + id + "  Navn: " + name;
    }
}