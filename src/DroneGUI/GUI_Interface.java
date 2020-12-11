package DroneGUI;

import javafx.animation.AnimationTimer;
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

/**
 * Pre-defined variables
 */

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
    boolean animation = false;


    /**
     * Start function to create the different parts of the window, border pane to set different things at the border
     * such as the top menu bar or the bottom set of buttons. Create a new canvas and drone arena and set colours
     * to easily differentiate the different sections.
     * @param primaryStage used to set the title, create a new scene and display the window.
     */
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Drone Simulation");

        bpane = new BorderPane();
        group = new Group();


        canvas = new Canvas(canvasSizeX, canvasSizeY);                                  //creates a new canvas
        myCan = new MyCanvas(canvas.getGraphicsContext2D(), canvasSizeX, canvasSizeY);  //creates a new MyCanvas
        battleArena = new DroneArena(canvasSizeX, canvasSizeY);                         //creates a new DroneArena
        myCan.setFillColour(canvasSizeX, canvasSizeY);                                  //Sets the colour for the window to show the differences between the canvas and dronearena
        group.getChildren().add(canvas);
        drawStatus();                                                                   //draws the information

        bpane.setTop(setMenu());               //sets the top menu
        bpane.setCenter(group);                //sets the drone arena
        bpane.setRight(infoPane);              //sets the information pane on the right handside
        bpane.setBottom(setButtons());          //sets the buttons at the bottom of the window

        primaryStage.setScene(new Scene(bpane, canvasSizeX * 2.5, canvasSizeY * 2.2));      //creates a new scene to be shown
        primaryStage.show();
    }

    /**
     * setButtons created a number of buttons that can be used within the simulation such as the new arena button
     * to create a completely new arena based on the arena sizes that the user manually inputed.
     * @return this returns all the buttons that were defined on the window which is then called within the start method
     */

    HBox setButtons() {
        Button addButton = new Button("Add Drone");
        Button obstacleButton = new Button("Add Obstacle");
        Button stopButton = new Button("Stop Simulation");
        Button newArena = new Button("New Arena");
        Button start = new Button("Start Animation");
        Button stop = new Button ("Stop Animation");

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

        start.setOnAction(actionEvent -> {
            animation = true;

            new AnimationTimer(){                                                   //animation timer that will run as long as the given parameter is true or unless the user clicks stop below.
                public void handle(long currentNanoTime){
                    if(animation){
                        battleArena.moveAllDrones();
                        myCan.setFillColour(canvasSizeX, canvasSizeY);
                        displaySystem();
                    }
                }
            }.start();

        });

        stop.setOnAction(actionEvent -> {
            animation = false;
        });

        return new HBox(addButton, obstacleButton, newArena, start, stop, stopButton);
    }

    /**
     * this method creates an input dialog to allow the user to choose the arena size manually, it then parses the string
     * type into an int and pushes it to the respective variables. It creates the new canvas and arena and displays the result.
     */

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
        updateStatus();

    }

    /**
     * Displays the information from the drones and the obstacles
     */

    private void drawStatus() {
        infoPane = new VBox();
        Label drawLabel = new Label(battleArena.toString());
        infoPane.getChildren().add(drawLabel);
    }

    /**
     * Updates the information from the drones and the obstacles
     */

    private void updateStatus(){
        infoPane.getChildren().clear();
        Label updateLabel = new Label(battleArena.toString());
        infoPane.getChildren().add(updateLabel);
    }

    /**
     * Used in the start button to help with the animations, clears the canvas so that the drones do not leave a trail
     * and re-draws their new position.
     */
    public void displaySystem(){
        myCan.clearCanvas();
        battleArena.drawSystem(myCan);
        updateStatus();
    }

    /**
     * Sets the menubar at the top of the window and creates the drop down menus and their submenus to be accessed
     * creates the messages/dialogs for each event.
     * @return
     */

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

    /**
     * Creates a message to be printed out.
     */

    private void showAbout() {
        showMessage("About", "Drone Simulation, made by Charith Avancha Fragoso, 28004779");
    }

    /**
     * Creates an alert within the sub menu that pops up as a window
     * @param Title
     * @param Content
     */

    private void showMessage(String Title, String Content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Title);                                  //sets the title of the pop up window
        alert.setHeaderText(null);
        alert.setContentText(Content);                          //takes in the content that is given in the showAbout method
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}