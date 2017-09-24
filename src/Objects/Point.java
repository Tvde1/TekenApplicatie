package Objects;

import java.io.Serializable;

public class Point implements Serializable {
    private double _x;
    private double _y;

    double getX() { return _x; }
    void setX(double x) { _x = x; }

    double getY() { return _y; }
    void setY(double y) { _y = y; }

    public Point(double x, double y) {
        _x = x;
        _y = y;
    }

    @Override
    public String toString() {
        return "X: " + _x + " | Y: " + _y;
    }
}
