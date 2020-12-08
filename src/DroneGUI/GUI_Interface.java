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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.border.Border;
import java.util.Random;
import java.util.Scanner;

public class GUI_Interface extends Application {
    private int canvasSize = 512;
    Group group;
    public MyCanvas myCan;
    public Canvas canvas;
    DroneArena battleArena;
    private VBox infoPane;
    BorderPane bpane;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Drone Simulation");
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        bpane = new BorderPane();
        bpane.setTop(setMenu());
        group = new Group();
        canvas = new Canvas(canvasSize, canvasSize);
        myCan = new MyCanvas(canvas.getGraphicsContext2D(), canvasSize, canvasSize);
        battleArena = new DroneArena(myCan.getXCanvasSize(), myCan.getYCanvasSize());

        group.getChildren().add(canvas);
        drawStatus();
        bpane.setCenter(group);
        bpane.setRight(infoPane);
        bpane.setBottom(setButtons());

        primaryStage.setScene(new Scene(bpane, canvasSize * 2, canvasSize * 1.8));
        primaryStage.show();
    }


    HBox setButtons() {
        Random random = new Random();
        Button addButton = new Button("Add Drone");
        Button obstacleButton = new Button("Add Obstacle");
        Button stopButton = new Button("Stop Simulation");
        Button newArena = new Button("New Arena");


        addButton.setOnAction(actionEvent -> {
            battleArena.addDrone();
            updateStatus();
            battleArena.drawSystem(myCan);
        });

        obstacleButton.setOnAction(actionEvent ->{

        });

        newArena.setOnAction(actionEvent -> {
            newArena();
        });

        stopButton.setOnAction(actionEvent -> {
                System.exit(0);
        });

        return new HBox(addButton, obstacleButton, newArena, stopButton);
    }

    HBox newArena(){
        Button submit = new Button("Submit");
        Label labelXCo = new Label("Enter X value: ");
        TextField textFieldX = new TextField();
        Label labelYCo = new Label("Enter Y value: ");
        TextField textFieldY = new TextField();
        Label label = new Label();

        label.setText("The values you entered are: " + textFieldX.getText() + ", " + textFieldY);

        return new HBox(submit);
    }

    private void drawStatus() {
        infoPane = new VBox();
        Label drawLabel = new Label(battleArena.toString());
        infoPane.getChildren().add(drawLabel);
    }

    private void updateStatus(){
        infoPane.getChildren().clear();
        Label updateLabel = new Label(battleArena.toString());
        infoPane.getChildren().add(updateLabel);
    }

    MenuBar setMenu() {
        MenuBar mBar = new MenuBar();
        Menu mHelp = new Menu("Information");
        MenuItem help = new MenuItem("Help");
        MenuItem mAbout = new MenuItem("About");
        Menu mFile = new Menu("File");
        MenuItem exit = new MenuItem("Exit");

        help.setOnAction(actionEvent ->  {
                showMessage("Help", "Click the 'Add Drone' button to add a new drone, 'Add Obstacle' to add obstacles, 'New Arena' to create a new arena and 'Stop Simulation' to exit the program");
        });
        mHelp.getItems().addAll(help);

        mAbout.setOnAction(actionEvent ->  {
                showAbout();

        });
        mHelp.getItems().addAll(mAbout);

        exit.setOnAction(actionEvent ->  {
                System.exit(0);
        });

        mFile.getItems().addAll(exit);
        mBar.getMenus().addAll(mFile, mHelp);
        return mBar;
    }

    private void showAbout() {
        showMessage("About", "Drone Simulation, made by Charith Avancha Fragoso, 28004779");
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