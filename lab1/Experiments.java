import java.util.Random;
import java.util.HashSet;
public class Experiments{
    public static void main(String[] args){
        int[] sizes={10000, 20000, 40000, 60000};
        int capacity=70000;
        int maxVal=70000;
        int exper=1000;

        double[] timeIn = new double[sizes.length];
        double[] timeNotIn = new double[sizes.length];
        double[] timeUnion = new double[sizes.length];

        Random rnd = new Random();
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            SparseSet set = generateRandomSet(maxVal, capacity, size);
            int[] elInSet = new int[exper];
            for (int j = 0; j < exper; j++) {
                elInSet[j] = set.getEl(rnd.nextInt(set.size()));
            }
            long startTime = System.nanoTime();
            for (int j = 0; j < exper; j++) {
                set.search(elInSet[j]);
            }
            long endTime = System.nanoTime();
            timeIn[i] = ((endTime - startTime) / (double) exper) / 1000.0;

            int[] elNotInSet = new int[exper];
            for (int j = 0; j < exper; j++) {
                int v;
                do {
                    v = rnd.nextInt(maxVal + 1);
                } while (set.search(v) != -1);
                elNotInSet[j] = v;
            }
            long startTime1 = System.nanoTime();
            for (int j = 0; j < exper; j++) {
                set.search(elNotInSet[j]);
            }
            long endTime1 = System.nanoTime();
            timeNotIn[i] = ((endTime1 - startTime1) / (double) exper) / 1000.0;

            SparseSet newSet = generateRandomSet(maxVal, capacity, size);
            long startUnion = System.nanoTime();
            for (int j = 0;j<exper;j++){
                set.union(newSet);
            }
            long endUnion = System.nanoTime();
            long executionUnion = endUnion - startUnion;
            timeUnion[i] = ((endUnion - startUnion) / (double) exper) / 1000.0;

            System.out.println("Size of the set: " + size + "\nTime to search for an element that is in a set: "+ timeIn[i] + "\nTime to search for an element that is not in the set: "+ timeNotIn[i]  +"\nTime for uniom: "+ timeUnion[i]);

        }
        new ResultsChart(sizes, timeIn, timeNotIn, timeUnion);
    }

    private static SparseSet generateRandomSet(int maxVal, int capacity, int size) {
        SparseSet set=new SparseSet(maxVal,capacity);
        Random random = new Random();
        HashSet<Integer> used =new HashSet<>();
        while(set.size() < size) {
            int value=random.nextInt(maxVal+1);
            if(!used.contains(value)){
                set.insert(value);
                used.add(value);
            }

        }
    return set;

    }
}
