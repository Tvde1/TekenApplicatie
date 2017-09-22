package Objects;

import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PaintedText extends DrawingItem {
    private Point _anchor;
    private double _width;
    private double _height;
    private String _content;
    private String _fontName;

    public PaintedText(Color color,Point anchor, double width, double height, String content, String fontName) {
        super(color);
        _anchor = anchor;
        _width = width;
        _height = height;
        _content = content;
        _fontName = fontName;
    }

    public void setWidth(double width) {
        saveState();
        _width = width;
    }

    public void setHeight(double height) {
        saveState();
        _height = height;
    }

    public void setAnchor(Point anchor) {
        saveState();
        _anchor = anchor;
    }

    public String getContent() { return _content; }
    public void setContent(String content) { _content = content; }

    public String getFontName() { return _fontName; }
    public void setFontName(String fontName) {
        saveState();
        _fontName = fontName;
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
        return "PaintedText - Anchor: (" + _anchor.toString() + ") | Text: " + _content + " | FontName: " + getFontName() + " | " + super.toString();
    }

    @Override
    public void saveState() {
        setPreviousState(new PaintedText(getColor(), getAnchor(), getWidth(), getHeight(), getContent(), getFontName()));
    }

    @Override
    public Shape draw() {
        Text text = new Text(getAnchor().getX(), getAnchor().getY(), getContent());
        text.setFont(new Font(getFontName(), getWidth() / getContent().length()));
        return text;
    }
}
