package day24pmTestSimplify.model;

public class Person {
    private int id;
    private String name;
    private int age;
    private double salary;
    private String born;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", born='" + born + '\'' +
                '}';
    }

    public Person(int id, String name, int age, double salary, String born) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.born = born;
    }

    public Person() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }
}
