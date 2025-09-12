import java.util.Random;

public class Main {

    public static void main(String args[]) {
        Random random = new Random();
        SparseSet setA = createRandomSet(100000, 80000, 5);
        SparseSet setB = createRandomSet(100000, 80000, 5);

        System.out.print("\nSet A: ");
        printSet(setA);

        setA.insert(70456);
        setA.insert(69089);
        System.out.print("\nSet A after adding elements 70456 and 69089 to the set: ");
        printSet(setA);
        setA.delete(69089);
        System.out.print("\nSet A after removing element 69089: ");
        printSet(setA);


        System.out.print("\nSet B: ");
        printSet(setB);


        SparseSet union = setA.union(setB);
        System.out.print("\nUnion: ");
        printSet(union);

        SparseSet intersection = setA.intersection(setB);
        System.out.print("\nIntersection: ");
        printSet(intersection);

        SparseSet setDifference = setA.setDifference(setB);
        System.out.print("\nSetDifference: ");
        printSet(setDifference);

        SparseSet symDifference = setA.symDifference(setB);
        System.out.print("\nSymDifference: ");
        printSet(symDifference);

        boolean isSubset = setA.isSubset(setB);
        System.out.println("\nIs set A a subset of set B?" + isSubset);


    }

    private static SparseSet createRandomSet(int maxVal, int capacity, int n) {
        Random random = new Random();
        SparseSet set = new SparseSet(maxVal, capacity);
        for (int i = 0; i < n; i++) {
            int value = random.nextInt(maxVal + 1);
            set.insert(value);
        }
        return set;
    }

    private static void printSet(SparseSet s) {
        System.out.print("{ ");
        for (int i = 0; i < s.size(); i++) {
            System.out.print(s.getEl(i));
            if (i != s.size() - 1) System.out.print(", ");
        }
        System.out.print(" }");
    }
}

