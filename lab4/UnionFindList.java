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
            next[i]= -1;;
            size[i]=1;
        }
    }


}

