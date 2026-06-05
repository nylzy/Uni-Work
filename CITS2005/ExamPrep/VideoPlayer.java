interface VideoFrame {
    void draw();
}

public class VideoPlayer implements VideoFrame {

    private VideoFrame[] frames;
    private int currentIndex

    public VideoPlayer(VideoFrame[] frames) {
        this.frames = frames;
        this.currentIndex = 0;
    }

    public void drawNextFrame() {
        frames[currentIndex].draw();
        currentIndex = (currentIndex + 1) % frames.length;
    }

    public void reset() {
        currentIndex = 0;
    }
}

// VideoFrame is a good abstraction as it does not know how draw should be implemented, it just knows that it should me.