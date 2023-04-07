package peter.lee;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

public final class PetShop {
    public static void main(String[] args) {
        // General VM details
        System.out.println(VM.current().details());
        final String catName = "Tommmmmmmmm";
        final String dogName = "Flashhhhhhhhhhhhhhh";
        final Cat cat = new Cat(catName, 1, 2.5f);
        final Dog dog = new Dog(dogName, 7, 2.5f, true);

        // String layout
        System.out.println("String layout: ");
        System.out.println(ClassLayout.parseClass(String.class).toPrintable());

        // Class layout
        System.out.println("The Class layout: ");
        System.out.println(ClassLayout.parseClass(Cat.class).toPrintable());
        System.out.println(ClassLayout.parseClass(Dog.class).toPrintable());

        // Deep size
        System.out.println("The deep size: ");
        System.out.println(ClassLayout.parseInstance(catName.toCharArray()).toPrintable());
        System.out.println(ClassLayout.parseInstance(dogName.toCharArray()).toPrintable());
        System.out.println(GraphLayout.parseInstance(cat).toFootprint());
        System.out.println(GraphLayout.parseInstance(dog).toFootprint());

        // ToString
        System.out.println("Object toString: ");
        System.out.println(cat);
        System.out.println(dog);

        // Hashcode
        System.out.println("Hashcode: ");
        System.out.println(cat.hashCode());
        System.out.println(dog.hashCode());

        // Address on memory
        System.out.println("Address on memory: ");
        System.out.println(VM.current().addressOf(cat));
        System.out.println(VM.current().addressOf(dog));
    }
}
