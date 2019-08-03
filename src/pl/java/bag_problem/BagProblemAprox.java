package pl.java.bag_problem;

import java.util.Scanner;

public class BagProblemAprox {
    private static int elementCount;
    private static Element[] elements;
    private static Bag bag;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadz pojemność plecaka:");
        bag = new Bag(scanner.nextInt());

        System.out.println("Wprowadz ilość elementów:");
        elementCount = scanner.nextInt();
        elements = new Element[elementCount];

        for (int i = 0; i < elementCount; i++) {
            System.out.printf("Wprowadz wartość i wagę %d elementu:", i + 1);
            elements[i] = new Element(
                    scanner.nextInt(),
                    scanner.nextInt()
            );
        }

        while (true) {
            try {
                bag.put(elements[findMostProfitable(bag)]);
            } catch (Exception e) {
                break;
            }
        }

        System.out.println(bag);
    }

    private static int findMostProfitable(Bag bag) {
        int topPrice = 0;

        for (int i = 0; i < elementCount; i++) {
            if (bag.isContains(elements[i])) {
                continue;
            }
            topPrice = i;
            break;
        }

        for (int i = 0; i < elementCount; i++) {
            if (bag.isContains(elements[i])) {
                continue;
            }
            if (isMoreProfitable(elements[topPrice], elements[i])) {
                topPrice = i;
            }
        }

        return topPrice;
    }

    private static boolean isMoreProfitable(Element first, Element second){
        return first.price / first.weight < second.price / second.weight;
    }
}
