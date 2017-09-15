import Objects.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Drawing {
    private ArrayList<DrawingItem> _items = new ArrayList<>();
    private String _name;

    DrawingListener _listener;

    Drawing(String name, DrawingListener listener) {
        _name = name;
        _listener = listener;
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
        _listener.update();
    }

    void addPaintedText(Color color, Point anchor, double width, double height, String content, String fontName) {
        PaintedText paintedText = new PaintedText(color, anchor, width, height, content, fontName);
        _items.add(paintedText);
        _listener.update();
    }

    void addPolygon(Color color, Point[] vertices, double weight) {
        Polygon polygon = new Polygon(color, vertices, weight);
        _items.add(polygon);
        _listener.update();
    }

    void addImage(Color color, File file, Point anchor, double width, double height) {
        Image image = new Image(color, file, anchor, width, height);
        _items.add(image);
        _listener.notify();
    }

    void clear() {
        _items.clear();
        _listener.update();
    }
}
