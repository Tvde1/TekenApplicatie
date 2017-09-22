import Objects.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

public class Drawing extends Observable {
    private ArrayList<DrawingItem> _items = new ArrayList<>();
    private String _name;

    Drawing(String name) {
        _name = name;
    }

    String getName() { return _name; }
    public void setName(String name) { _name = name; }

    ArrayList<DrawingItem> getItems() {
        Collections.sort(_items);
        return _items;
    }

    void addOval(Color color, Point anchor, double width, double height, double weight) {
        Oval oval = new Oval(color, anchor, width, height, weight);
        _items.add(oval);
        setChanged();
        notifyObservers();
    }

    void addPaintedText(Color color, Point anchor, double width, double height, String content, String fontName) {
        PaintedText paintedText = new PaintedText(color, anchor, width, height, content, fontName);
        _items.add(paintedText);
        setChanged();
        notifyObservers();
    }

    void addPolygon(Color color, Point[] vertices, double weight) {
        Polygon polygon = new Polygon(color, vertices, weight);
        _items.add(polygon);
        setChanged();
        notifyObservers();
    }

    void addImage(Color color, File file, Point anchor, double width, double height) {
        Image image = new Image(color, file, anchor, width, height);
        _items.add(image);
        setChanged();
        notifyObservers();
    }

    void clear() {
        _items.clear();
        setChanged();
        notifyObservers();
    }

    void remove(DrawingItem item) {
        _items.remove(item);
        setChanged();
        notifyObservers();
    }
}
