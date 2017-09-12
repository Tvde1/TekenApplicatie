import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Main extends Application {
    private Parent _root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
        _root = root;
        fillShapeDropdown(root);
        setMouseClickHandler(scene);
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
}