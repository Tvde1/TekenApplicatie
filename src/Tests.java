import Objects.Color;
import Objects.DrawingItem;
import Objects.Point;
import org.junit.jupiter.api.Test;

import java.io.File;

class Tests {
    @Test
    void test() {
        Drawing drawing = new Drawing("Tekening");
        System.out.println("Nieuwe tekening: " + drawing.getName());
        drawing.addPaintedText(Color.RED, new Point(5, 5), 20, 10, "Test", "Comic Sans MS");
        drawing.addOval(Color.BLUE, new Point(10, 10), 10, 8, 2);
        drawing.addImage(Color.WHITE, new File("file.txt"), new Point(11, 8), 20, 20);

        Point[] vertices = new Point[5];
        vertices[0] = new Point(2, 2);
        vertices[1] = new Point(2, 6);
        vertices[2] = new Point(4, 8);
        vertices[3] = new Point(4, 6);
        vertices[4] = new Point(2, 8);

        drawing.addPolygon(Color.GREEN, vertices, 4);

        for (DrawingItem item : drawing.getItems()) {
            System.out.println(item.toString());
        }
    }
}
