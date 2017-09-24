import Objects.Color;
import Objects.DrawingItem;
import Objects.Point;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

public class DrawingApp extends Application implements Observer {
    private Scene _scene;
    private Drawing _drawing;
    private PersistencyMediator _persistencyMediator = new SerialisationMediator();

    /**
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Drawing loadedDrawing = _persistencyMediator.load("drawing1");

        _drawing = loadedDrawing;


        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 800, 500);
        _scene = scene;
        primaryStage.setScene(scene);
        primaryStage.show();
        fillShapeDropdown();
        fillColorDropdown();
        addButtonEvents();

        if (loadedDrawing == null) {
            _drawing = new Drawing("Drawing1");
        }

        _drawing.addObserver(this);


//        _drawing.addOval(Color.BLUE, new Point(30, 4), 30, 50, 10);
//        _drawing.addOval(Color.BLUE, new Point(4, 5), 20, 10, 5);
//
//        Point[] vertices = new Point[] {
//                new Point(80, 50),
//                new Point(40, 80),
//                new Point(55, 100),
//                new Point(80, 100)
//        };
//
//        _drawing.addPolygon(Color.GREEN, vertices, 40);
//        //_drawing.clear();
//        //_drawing.addPolygon(Color.BLUE, vertices, 40);
//
//        _drawing.addPaintedText(Color.RED, new Point(400, 200), 400, 400, "Test", "Comic Sans MS");
//
//        _drawing.addImage(Color.BLUE, new File("D:\\temp\\old-man-with-a-phone.jpg"), new Point(100, 100), 200, 150);
        // setMouseClickHandler(scene);

        update(null, null);
    }

    private void addButtonEvents() {
        Button btnAdd = (Button) _scene.getRoot().lookup("#btnAdd");
        btnAdd.setOnMouseClicked(this::btnAdd_Click);

        Button btnRemove = (Button) _scene.getRoot().lookup("#btnRemove");
        btnRemove.setOnMouseClicked(this::btnRemove_Click);
    }

    public static void main(String[] args) { launch(args); }

    private void showItems() {
        ArrayList<DrawingItem> items = _drawing.getItems();
        javafx.scene.control.ListView lv = (javafx.scene.control.ListView) _scene.getRoot().lookup("#lvItems");
        lv.getItems().setAll(items);
    }

    private void fillShapeDropdown() {
        ArrayList<String> items = new ArrayList<>();
        items.add("Image");
        items.add("Oval");
        items.add("Text");
        items.add("Polygon");

        ObservableList<String> list = FXCollections.observableArrayList(items);

        ChoiceBox cb = (ChoiceBox) _scene.getRoot().lookup("#cbShape");

        if (cb != null) cb.setItems(list);
        else throw new Error("Combobox missing.");
    }

    private void fillColorDropdown() {
        ChoiceBox cb = (ChoiceBox) _scene.getRoot().lookup("#cbColor");
        cb.getItems().setAll(Color.values());
    }

    private void draw() {
        Pane pane = (Pane) _scene.getRoot().lookup("#pane");
        pane.getChildren().clear();

        for (DrawingItem item : _drawing.getItems())
            pane.getChildren().add(item.draw());
    }

    @Override
    public void update(Observable o, Object arg) {
        draw();
        showItems();
        _persistencyMediator.save(_drawing);
    }

    private void btnRemove_Click(MouseEvent mouseEvent) {
        Parent r = _scene.getRoot();
        Node l =  r.lookup("#lvItems");
        ListView lv = (ListView) l;
        DrawingItem item = (DrawingItem) lv.getSelectionModel().getSelectedItem();
        _drawing.remove(item);
    }

    private void btnAdd_Click(MouseEvent mouseEvent) {
        ChoiceBox cbShape = (ChoiceBox) _scene.getRoot().lookup("#cbShape");
        ChoiceBox cbColor = (ChoiceBox) _scene.getRoot().lookup("#cbColor");
        double x = Double.parseDouble(((TextField)_scene.getRoot().lookup("#tbX")).getText());
        double y = Double.parseDouble(((TextField)_scene.getRoot().lookup("#tbY")).getText());
        double width = Double.parseDouble(((TextField)_scene.getRoot().lookup("#tbWidth")).getText());
        double height = Double.parseDouble(((TextField)_scene.getRoot().lookup("#tbHeight")).getText());
        double weight = ((Slider) _scene.getRoot().lookup("#slWeight")).getValue();

        TextField tbFile = (TextField) _scene.getRoot().lookup("#tbFile");
        File file = new File(tbFile.getText());

        Color c = (Color) cbColor.getSelectionModel().getSelectedItem();
        Point anchor = new Point(x, y);

        String text = ((TextField) _scene.getRoot().lookup("#tbText")).getText();
        String font = ((TextField) _scene.getRoot().lookup("#tbFont")).getText();

        switch ((String) cbShape.getSelectionModel().getSelectedItem()) {
            case "Image": {
                _drawing.addImage(c, file, anchor,width, height);
                break;
            }
            case "Oval": {
                _drawing.addOval(c, anchor, width, height, weight);
                break;
            }
            case "Text": {
                _drawing.addPaintedText(c, anchor, width, height, text, font);
                break;
            }
            case "Polygon": {
                break;
            }
        }
    }
}