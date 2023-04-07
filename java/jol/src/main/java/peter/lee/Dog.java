package peter.lee;

public class Dog extends Pet {
    private boolean isVaccination;

    public Dog(String name, int age, float weight, boolean isVaccination) {
        super(name, age, weight);
        this.isVaccination = isVaccination;
    }

}
