public class UnionFindList {
    private int n;
    private int[] head, tail, root, next, size;

    public UnionFindList (int n){
        makeSet(n);
    }

    public void makeSet(int n){
        if(n<0){
            throw new IllegalArgumentException("n must be >=0");
        }
        this.n=n;
        head=new int[n];
        tail=new int[n];
        root=new int[n];
        next=new int[n];
        size=new int[n];

        for(int i=0;i<n;i++){
            head[i]=i;
            tail[i]=i;
            root[i]=i;
            next[i]= -1;
            size[i]=1;
        }
    }

    private void validateIndex(int x) {
        if (x < 0 || x >= n) {
            throw new IllegalArgumentException("index out of range: " + x);
        }
    }

    public int find(int x){
        validateIndex(x);
        return root[x];
    }

    public boolean sameSet(int x, int y) {
        validateIndex(x);
        validateIndex(y);
        return find(x) == find(y);
    }

    public int sizeOf(int x) {
        validateIndex(x);
        return size[find(x)];
    }

    public void union(int x,int y){
        validateIndex(x);
        validateIndex(y);

        int rx=find(x);
        int ry=find(y);

        if(rx==ry){
            return;
        }

        if(size[rx]<size[ry]){
            int temp = rx;
            rx=ry;
            ry=temp;
        }


        next[tail[rx]] = head[ry];

        int cur = head[ry];
        while (cur != -1) {
            root[cur] = rx;
            cur = next[cur];
        }

        tail[rx] = tail[ry];
        size[rx] += size[ry];

        head[ry] = -1;
        tail[ry] = -1;
        size[ry] = 0;
    }

    }






