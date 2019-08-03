package pl.java.bag_problem;

import java.util.*;

public class BagProblemBruteForce {
    private static int elementCount;
    private static Element[] elements;
    private static Bag bag;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadz pojemność plecaka:");
        int bagCapacity = scanner.nextInt();
        bag = new Bag(bagCapacity);

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

        Set<Element> mySet = new HashSet<>(Arrays.asList(elements));
        Bag bestBag = new Bag(bagCapacity);

        for(Set<Element> combination : powerSet(mySet)) {
            Bag bag = new Bag(bagCapacity);
            for (Element element: combination) {
                try {
                    bag.put(element);
                } catch (Exception e) {
                    continue;
                }

                if (bag.getCurrentBagPrice() > bestBag.getCurrentBagPrice()) {
                    bestBag = bag;
                }
            }
        }

        System.out.println(bestBag);
    }


    public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

    private static boolean isMoreProfitable(Element first, Element second){
        return first.price / first.weight < second.price / second.weight;
    }
}
