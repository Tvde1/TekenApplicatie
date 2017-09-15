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
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;

public class DrawingApp extends Application implements DrawingListener {
    private Scene _scene;
    private Drawing _drawing;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 300, 275);
        _scene = scene;
        primaryStage.setScene(scene);
        primaryStage.show();
        fillShapeDropdown(root);

        _drawing = new Drawing("Drawing1", this);

        _drawing.addOval(Color.BLUE, new Point(30, 4), 30, 50, 10);
        _drawing.addOval(Color.BLUE, new Point(4, 5), 20, 10, 5);

        Point[] vertices = new Point[] {
                new Point(80, 50),
                new Point(40, 80),
                new Point(55, 100),
                new Point(80, 100)
        };

        _drawing.addPolygon(Color.GREEN, vertices, 40);
        _drawing.clear();
        _drawing.addPolygon(Color.BLUE, vertices, 40);

        // setMouseClickHandler(scene);
    }

    public static void main(String[] args) { launch(args); }

    private void fillShapeDropdown(Parent root) {
        ArrayList<String> items = new ArrayList<>();
        items.add("Image");
        items.add("Oval");
        items.add("Text");
        items.add("Polygon");

        ObservableList<String> list = FXCollections.observableArrayList(items);

        ChoiceBox cb = (ChoiceBox) root.lookup("#cbShape");

        if (cb != null) cb.setItems(list);
        else throw new Error("Combobox missing.");
    }

    private void setMouseClickHandler(Scene scene) {
        scene.setOnMousePressed(event -> {

            System.out.println(event.getX());

            JOptionPane.showMessageDialog(null, "Info", "InfoBox: " + "Titel", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void draw() {
        Pane pane = (Pane) _scene.getRoot().lookup("#pane");
        pane.getChildren().clear();

        for (DrawingItem item : _drawing.getItems()) {
            Object s = item.draw();
            if (s instanceof Node) {
                pane.getChildren().add((Node) s);
            } else {
                //pane.getChildren().add((Image) s);
            }
        }
    }

    @Override
    public void update() {
        draw();
    }
}