package Objects;

import java.util.Arrays;
import java.util.Collections;

public class Polygon extends DrawingItem {
    private Point[] _vertices;
    private double _weight;

    Polygon(Color color, Point[] vertices, double weight) {
        super(color);
        _vertices = vertices;
        _weight = weight;
    }

    public Point[] getVertices() { return _vertices; }
    public void setVertices(Point[] vertices) { saveState();
        saveState();
        _vertices = vertices;
    }

    public double getWeight() { return _weight; }
    public void setWeight(double weight) {
        saveState();
        _weight = weight;
    }

    @Override
    public Point getAnchor() {
        if (getVertices().length == 0) return null;

        double x = getVertices()[0].getX();
        double y = getVertices()[0].getY();

        for (Point vertice : getVertices()) {
            if (vertice.getX() < x) x = vertice.getX();
            if (vertice.getY() < y) y = vertice.getY();
        }

        return new Point(x, y);
    }

    @Override
    public double getWidth() {
        if (getVertices().length == 0) throw new Error("No vertices means no width.");

        double right, left;
        right = left = getVertices()[0].getX();

        for (Point vertice : getVertices()) {
            if (vertice.getX() < left) left = vertice.getX();
            if (vertice.getX() > right) right = vertice.getX();
        }

        return right-left;
    }

    @Override
    public double getHeight() {
        if (getVertices().length == 0) throw new Error("No vertices means no height.");

        double top, bottom;
        top = bottom = getVertices()[0].getX();

        for (Point vertice : getVertices()) {
            if (vertice.getY() < top) top = vertice.getY();
            if (vertice.getX() > bottom) bottom = vertice.getY();
        }

        return bottom-top;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Point point : getVertices()) {
            sb.append("Point: (").append(point).append(") | ");
        }

        String verticesString = sb.toString().substring(0, sb.toString().length()-3);

        return "Polygon     - Vertices: (" + verticesString + ") | Weight: " + getWeight() + " | " + super.toString();
    }

    @Override
    public void saveState() {
        setPreviousState(new Polygon(getColor(), getVertices(), getWeight()));
    }
}
