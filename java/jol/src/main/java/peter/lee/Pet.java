package peter.lee;

public abstract class Pet {
    protected String name;
    protected int age;
    protected float weight;
    protected Pet(String name, int age, float weight){
        this.name = name;
        this.age = age;
        this.weight = weight;
    }
}
