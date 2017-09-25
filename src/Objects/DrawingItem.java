package Objects;

import javafx.scene.shape.Shape;

import java.io.Serializable;

public abstract class DrawingItem implements Comparable<DrawingItem>, Serializable {

    private Color _color;
    private DrawingItem _previousState;

    public abstract Point getAnchor();
    public abstract double getWidth();
    public abstract double getHeight();
    public abstract void saveState();
    public abstract Shape draw();

    public boolean overlaps(DrawingItem item) {
        Point middleThis = new Point(getAnchor().getX() + getWidth() / 2, getAnchor().getY() + getHeight() / 2);
        Point middleItem = new Point(item.getAnchor().getX() + item.getWidth() / 2, item.getAnchor().getY() + item.getHeight() / 2);

        boolean overlapsX = Math.abs(middleThis.getX() - middleItem.getX()) - getWidth() - item.getWidth() <= 0;
        boolean overlapsY = Math.abs(middleThis.getY() - middleItem.getY()) - getHeight() - item.getHeight() <= 0;

        return overlapsX && overlapsY;
    }

    public boolean insideBoundingBox(Point point) {
        boolean betweenX = point.getX() > getAnchor().getX() && point.getX() < getAnchor().getX() + getWidth();
        boolean betweenY = point.getY() > getAnchor().getY() && point.getY() < getAnchor().getY() + getHeight();
    }

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
