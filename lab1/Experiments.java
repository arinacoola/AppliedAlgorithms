import java.util.Random;
import java.util.HashSet;
public class Experiments{
    public static void main(String[] args){
        int[] sizes={10000, 20000, 40000, 60000};
        int capacity=70000;
        int maxVal=70000;
        int exper=1000;

        Random rnd = new Random();
        for (int size:sizes){
            SparseSet set = generateRandomSet(maxVal, capacity, size);
            int[] elInSet=new int[exper];
            for(int i=0;i<exper;i++){
                elInSet[i] = set.getEl(rnd.nextInt(set.size()));
            }
            long startTime = System.nanoTime();
            for (int i=0;i<exper;i++){
                set.search(elInSet[i]);
            }
            long endTime = System.nanoTime();
            long executionTime= endTime - startTime;
            double totalTime = executionTime/(double)exper;

            int[] elNotInSet = new int [exper];
            for(int i=0;i<exper;i++){
                int v;
                do {
                    v=rnd.nextInt(maxVal+1);
                }
                while (set.search(v)!=-1) ;
                elNotInSet[i]=v;
            }
            long startTime1 = System.nanoTime();
            for(int i=0;i<exper;i++){
                set.search(elNotInSet[i]);
            }
            long endTime1=System.nanoTime();
            long executionTime1= endTime1 - startTime1;
            double totalTime1 = executionTime1/(double)exper;

            SparseSet newSet = generateRandomSet(maxVal, capacity, size);
            long startUnion = System.nanoTime();
            for (int i = 0;i<exper;i++){
                set.union(newSet);
            }
            long endUnion = System.nanoTime();
            long executionUnion = endUnion - startUnion;
            double totalUnion = executionUnion/(double)exper;

            System.out.println("Size of the set: " + size + "\nTime to search for an element that is in a set: "+ totalTime + "\nTime to search for an element that is not in the set: "+ totalTime1 +"\nTime for uniom: "+ totalUnion);
        }


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
