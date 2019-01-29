public class Claim {
    private int ID;
    private int inchesFromLeft;
    private int inchesFromTop;
    private int width;
    private int height;

    public Claim(int ID, int inchesFromLeft, int inchesFromTop, int width, int height) {
        this.ID = ID;
        this.inchesFromLeft = inchesFromLeft;
        this.inchesFromTop = inchesFromTop;
        this.width = width;
        this.height = height;
    }

    public int getID() {
        return ID;
    }

    public int getInchesFromLeft() {
        return inchesFromLeft;
    }

    public int getInchesFromTop() {
        return inchesFromTop;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
