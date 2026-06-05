public class Box {

    private final int width;
    private final int height;
    private final int depth;

    public Box(int width, int height, int depth ) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public int width() {
        return width;
    } 

    public int height() {
        return height;
    } 

    public int depth() {
        return depth;
    } 

    public double getVolume() {
        double volume = (width * height * depth);
        return volume;
    }
}

