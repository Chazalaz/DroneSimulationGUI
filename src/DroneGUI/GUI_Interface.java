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

import javax.swing.*;
import javax.swing.border.Border;
import java.util.Random;
import java.util.Scanner;

public class GUI_Interface extends Application {
    int canvasSizeX = 400;
    int canvasSizeY = 400;
    Group group;
    public MyCanvas myCan;
    public Canvas canvas;
    DroneArena battleArena;
    private VBox infoPane;
    BorderPane bpane;
    JFrame xCo, yCo;
    String x, y;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Drone Simulation");

        bpane = new BorderPane();
        group = new Group();


        canvas = new Canvas(canvasSizeX, canvasSizeY);
        myCan = new MyCanvas(canvas.getGraphicsContext2D(), canvasSizeX, canvasSizeY);
        battleArena = new DroneArena(canvasSizeX, canvasSizeY);
        myCan.setFillColour(canvasSizeX, canvasSizeY);
        group.getChildren().add(canvas);
        drawStatus();

        bpane.setTop(setMenu());
        bpane.setCenter(group);
        bpane.setRight(infoPane);
        bpane.setBottom(setButtons());

        primaryStage.setScene(new Scene(bpane, canvasSizeX * 2.5, canvasSizeY * 2.2));
        primaryStage.show();
    }


    HBox setButtons() {
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
            battleArena.addObstacle();
            updateStatus();
            battleArena.drawObs(myCan);
        });

        newArena.setOnAction(actionEvent -> {
            myCan.clearCanvas();
            enterCoords();
        });

        stopButton.setOnAction(actionEvent -> {
                System.exit(0);
        });

        return new HBox(addButton, obstacleButton, newArena, stopButton);
    }

    public void enterCoords(){
        xCo = new JFrame();
        yCo = new JFrame();

        x = JOptionPane.showInputDialog(xCo, "Enter X value");
        y = JOptionPane.showInputDialog(yCo, "Enter Y value");
        canvasSizeX = Integer.parseInt(x);
        canvasSizeY = Integer.parseInt(y);
        canvas = new Canvas(canvasSizeX, canvasSizeY);
        myCan = new MyCanvas(canvas.getGraphicsContext2D(), canvasSizeX, canvasSizeY);
        battleArena = new DroneArena(canvasSizeX, canvasSizeY);
        myCan.setFillColour(canvasSizeX, canvasSizeY);
        group.getChildren().add(canvas);
        drawStatus();
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
        Menu mFile = new Menu("File");
        MenuItem help = new MenuItem("Help");
        MenuItem about = new MenuItem("About");
        MenuItem exit = new MenuItem("Exit");

        help.setOnAction(actionEvent ->  {
                showMessage("Help", "Click the 'Add Drone' button to add a new drone, 'Add Obstacle' to add obstacles, 'New Arena' to create a new arena and 'Stop Simulation' to exit the program");
        });
        mHelp.getItems().addAll(help);

        about.setOnAction(actionEvent ->  {
                showAbout();
        });
        mHelp.getItems().addAll(about);

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