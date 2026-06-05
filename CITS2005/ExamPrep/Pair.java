class Pair<A, B> {
    public A first;
    public B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public Pair<B, A> swap(){
        return new Pair<>(this.second, this.first);
    }
}