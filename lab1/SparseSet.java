public  class SparseSet {
    private int maxVal,capacity,n;
    int[] dense,sparse;

    public  SparseSet  (int maxVal, int capacity) {
        if (maxVal < 65536 || capacity < 65536 ){
            throw new IllegalArgumentException("incorrect input of values");
        }
        this.maxVal=maxVal;
        this.capacity=capacity;
        dense = new int[capacity];
        sparse = new int[maxVal+1];
        n=0;
    }

    public int search(int x){
        if (x > maxVal){
            return -1;
        }
        if (sparse[x] < n && dense[sparse[x]] == x) {
            return sparse[x];
        }return -1;
    }

    public void insert(int x){
        if(x>maxVal || n >= capacity){
            return;
        }
        if(search(x)==-1){
            dense[n]=x;
            sparse[x]=n;
            n++;
        }
    }

    public void delete(int x) {
        if (search(x) == -1) {
            return;
        }
        int temp = dense[n - 1];
        dense[sparse[x]] = temp;
        sparse[temp] = sparse[x];
        n--;
    }

    public void clear(){
        n=0;
    }

    public SparseSet union(SparseSet s1){
        int uCapacity = Math.max(capacity, s1.capacity);
        int uMaxVal = Math.max(maxVal,s1.maxVal);
        SparseSet uResult = new SparseSet(uMaxVal,uCapacity);

        for(int i=0;i<n;i++){
            uResult.insert(dense[i]);
        }
        for (int i=0;i<s1.n;i++){
            uResult.insert(s1.dense[i]);
        }
        return uResult;
    }

    public SparseSet intersection(SparseSet s2){
        int iCapacity = Math.max(capacity, s2.capacity);
        int iMaxVal = Math.max(maxVal,s2.maxVal);
        SparseSet iResult = new SparseSet(iMaxVal,iCapacity);

        if (n < s2.n) {
            for (int i = 0; i < n; i++) {
                if (s2.search(dense[i]) != -1) {
                    iResult.insert(dense[i]);
                }


            }
        }
        else{
            for (int i = 0; i < s2.n; i++){
                if (search(s2.dense[i]) != -1) {
                    iResult.insert(s2.dense[i]);
                }
            }
        }
        return iResult;


    }

    public SparseSet setDifference(SparseSet s3){
        int dCapacity = Math.max(capacity, s3.capacity);
        int dMaxVal=Math.max(maxVal, s3.maxVal);
        SparseSet dResult = new SparseSet(dMaxVal, dCapacity);

        for (int i = 0; i < n; i++) {
            int el=dense[i];
            if(s3.search(el)==-1){
                dResult.insert(dense[i]);
            }
        }
        return dResult;
    }

    public SparseSet symDifference(SparseSet s4){
        int symCapacity = Math.max(capacity, s4.capacity);
        int symMaxVal=Math.max(maxVal,s4.maxVal);
        SparseSet symResult = new SparseSet(symMaxVal,symCapacity);

        for (int i = 0;i<n;i++) {
            int el = dense[i];
            if (s4.search(el) == -1) {
                symResult.insert(el);
            }
        }
        for (int i = 0;i<s4.n;i++){
            int el =s4.dense[i];
            if (search(el)==-1){
                symResult.insert(el);
            }
        }
        return symResult;


    }

    public  boolean  isSubset(SparseSet s5){
        if(n==0){
            return true;
        }
        if(n>s5.n){
            return false;
        }
        for(int i=0;i<n;i++){
            int el=dense[i];
            if(s5.search(el)==-1){
                return false;
            }
        }
        return true;
    }

    public int size() {
        return n;
    }

    public int getEl(int index) {
        if (index < 0 || index >= n) throw new IndexOutOfBoundsException();
        return dense[index];
    }

}


