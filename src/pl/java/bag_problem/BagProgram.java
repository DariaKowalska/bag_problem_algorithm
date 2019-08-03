package pl.java.bag_problem;


import java.util.Scanner;

public class BagProgram {
    private static int elementCount;
    private static Element[] elements;
    private static Bag bag;
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadź pojemność plecaka:");
        bag = new Bag(scanner.nextInt());

        System.out.println("wprowadź ilość elementów:");
        elementCount = scanner.nextInt();
        elements = new Element[elementCount];

        for (int i = 0; i < elementCount; i++) {
            System.out.printf("Wprowadź wartość i wagę %d elementu:", i + 1);
            elements[i] = new Element(scanner.nextInt(), scanner.nextInt());
        }
        while (true) {
            try {
                bag.put(elements[findByPrice()]);
            } catch (Exception e) {
                break;
            }
        }

    }
    private static int findByPrice(){
        int topPrice=0;
        for(int i=0; i<elementCount; i++){
            if (bag.isContains(elements[i])){
                continue;
            }
            topPrice = i;
            break;}
        for(int i=0; i<elementCount; i++){
            if (bag.isContains(elements[i])){
                continue;
            }
            if (elements[topPrice].price < elements[i].price){
                topPrice = i;
            }
        }
        return topPrice;
    }
}
