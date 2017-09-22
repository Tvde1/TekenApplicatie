package Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ImageTest {

    private Image _image;
    private File _file;
    private Point _anchor;
    private double _width = 400;
    private double _height = 500;

    @Before
    public void setup() {
        _file = new File("abc");
        _anchor = new Point(10, 20);
        _image = new Image(Color.GREEN, _file, _anchor, _width, _height);
    }

    @Test
    public void getAnchor() throws Exception {
        Assert.assertSame(_image.getAnchor(), _anchor);
    }

    @Test
    public void getWidth() throws Exception {
        Assert.assertSame();
    }

    @Test
    public void getHeight() throws Exception {
    }

}