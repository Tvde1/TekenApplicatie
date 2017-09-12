package Objects;

public class Oval extends DrawingItem {
    private Point _anchor;
    private double _width;
    private double _height;
    private double _weight;

    Oval(Color color, Point anchor, double width, double height, double weight) {
        super(color);
        _anchor = anchor;
        _width = width;
        _height = height;
        _weight = weight;
    }

    public void setWidth(double width) {
        saveState();
        _width = width;
    }

    public void setHeight(double height) {
        saveState();
        _height = height;
    }

    public double getWeight() { return _weight; }

    public void setWeight(double weight) {
        saveState();
        _weight = weight;
    }

    public void setAnchor(Point anchor) {
        saveState();
        _anchor = anchor;
    }

    @Override
    public Point getAnchor() {
        return _anchor;
    }

    @Override
    public double getWidth() {
        return _width;
    }

    @Override
    public double getHeight() {
        return _height;
    }

    @Override
    public void saveState() {
        setPreviousState(new Oval(getColor(), getAnchor(), getWidth(), getHeight(), getWeight()));
    }

    @Override
    public String toString() {
        return "Oval        - Anchor: (" + _anchor.toString() + ") | Weight: " + _weight + " | " + super.toString();
    }
}
