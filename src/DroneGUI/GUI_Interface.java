package DroneGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.border.Border;

public class GUI_Interface extends Application {
    private int canvasSize = 512;
    Group root;
    private MyCanvas myCan;
    DroneArena battleArena;
    private VBox infoPane;
    BorderPane bpane;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Drone Simulation");
        BorderPane bpane = new BorderPane();
        bpane.setTop(setMenu());
        Group root = new Group();
        Canvas canvas = new Canvas(canvasSize, canvasSize);
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root.getChildren().add(canvas);
        myCan = new MyCanvas(canvas.getGraphicsContext2D(), canvasSize, canvasSize);
        battleArena = new DroneArena(myCan.xCanvasSize, myCan.yCanvasSize);
//        battleArena.addDrone();
//        setMouseEvents();
//        bpane.setCenter(root);
//        infoPane = new VBox();
//        drawStatus();
//        bpane.setRight(infoPane);
//        bpane.setBottom(setButtons());
        primaryStage.setScene(new Scene(root, canvasSize * 2, canvasSize * 1.8));
        primaryStage.show();
    }

    MenuBar setButtons() {
        return null;
    }

    private void drawStatus() {
    }

    private void setMouseEvents() {
    }

    MenuBar setMenu() {
        return null;
    }


    public static void main(String[] args) {
        launch(args);
    }
}