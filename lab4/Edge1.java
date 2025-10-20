public final class Edge1 implements Comparable<Edge1>{
    private final int u;
    private final int v;
    private final long w;
    public Edge1(int u,int v,long w){
        this.u=u;
        this.v=v;
        this.w=w;
    }
    public int getU(){
        return u;
    }
    public int getV(){
        return v;
    }
    public long getW() {
        return w;
    }

    @Override
    public int compareTo(Edge1 other) {
        return  Long.compare(this.w, other.w);
    }

    public String toString() {
        return "(" + u + ", " + v + ", w=" + w + ")";
    }
}
