package Objects;

import java.io.File;

public class Image extends DrawingItem {
    private File _file;
    private Point _anchor;
    private double _width;
    private double _height;

    public Image(Color color, File file, Point anchor, double width, double height) {
        super(color);
        _file = file;
        _anchor = anchor;
        _width = width;
        _height = height;
    }

    public void setWidth(double width) {
        saveState();
        _width = width;
    }

    public void setHeight(double height) {
        saveState();
        _height = height;
    }

    public File getFile() {
        return _file;
    }

    public void setFile(File file) {
        saveState();
        _file = file;
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
    public String toString() {
        return "Image       - Anchor: (" + _anchor.toString() + ") | File: " + _file.toString() + " | " + super.toString();
    }

    @Override
    public void saveState() {
        setPreviousState(new Image(getColor(), getFile(), getAnchor(), getWidth(), getHeight()));
    }
}
