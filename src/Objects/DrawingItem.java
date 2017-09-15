package Objects;

public abstract class DrawingItem implements Comparable<DrawingItem> {

    private Color _color;
    private DrawingItem _previousState;

    public abstract Point getAnchor();
    public abstract double getWidth();
    public abstract double getHeight();
    public abstract void saveState();
    public abstract Object draw();

    Color getColor() { return _color; }
    public void setColor(Color color) {
        saveState();
        _color = color;
    }

    DrawingItem(Color color) {
        _color = color;
    }

    public DrawingItem getPreviousState() { return _previousState; }
    void setPreviousState(DrawingItem previousState) { _previousState = previousState; }

    @Override
    public String toString() {
        return "Width: " + getWidth() + " | Height: " + getHeight() + " | Color: " + _color;
    }

    @Override
    public int compareTo(DrawingItem o) {
        return -((Double)(o.getAnchor().getX() - getAnchor().getX() + o.getAnchor().getY() - getAnchor().getY())).intValue();
    }
}
