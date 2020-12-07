package DroneGUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.border.Border;

public class GUI_Interface extends Application {
    private int canvasSize = 512;
    Group group;
    public MyCanvas myCan;
    DroneArena battleArena;
    private VBox infoPane;
    BorderPane bpane;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Drone Simulation");
        bpane = new BorderPane();
        bpane.setTop(setMenu());
        group = new Group();
        //myCan = new MyCanvas(myCan.gc, myCan.xCanvasSize, myCan.yCanvasSize);
        Canvas canvas = new Canvas(canvasSize, canvasSize);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        group.getChildren().add(canvas);
        myCan = new MyCanvas(canvas.getGraphicsContext2D(), canvasSize, canvasSize);
        battleArena = new DroneArena(myCan.xCanvasSize, myCan.yCanvasSize);
        //battleArena.addDrone();
//        setMouseEvents();
//        bpane.setCenter(root);
//        infoPane = new VBox();
////        drawStatus();
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
        MenuBar mBar = new MenuBar();
        Menu mHelp = new Menu();
        MenuItem help = new MenuItem("Help");
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showMessage("Help", "Watch the drones move");
            }
        });
        mHelp.getItems().addAll(help);

        MenuItem mAbout = new MenuItem("About");
        mAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showAbout();
            }
        });
        mHelp.getItems().addAll(mAbout);

        Menu mFile = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        mFile.getItems().addAll(exit);
        mBar.getMenus().addAll(mFile, mHelp);

        return mBar;
    }

    private void showAbout() {
        showMessage("About", "Pane Demonstrator");
    }

    private void showMessage(String Title, String Content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.setHeaderText(null);
        alert.setContentText(Content);
        alert.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}