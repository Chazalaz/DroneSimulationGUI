package DroneGUI;

import javafx.scene.image.Image;

public class Drone {
    public int coX;
    public int coY;
    public Direction dir;
    Image droneImage = new Image(getClass().getResourceAsStream("circle.png"));

    Drone(int x, int y, Direction d){
        coX = x;
        coY = y;
        dir = d;
    }

    /** The code above defines variables that are used later in the code,
    * coX and coY are the x and y coordinates for the drone where dir is
    * the direction that the drone is given.
     */

    public void displayDrone(MyCanvas mc) {
        mc.drawImage(droneImage, coX, coY, 10);
    }
    /** This code defines a variable in the ConsoleCanvas file, it then defines and initialises
    * a char type variable which is a visual representation of the drones on the canvas and a function
    * that shows the canvas and arena taking the coordinates of the drones and
    * the arena border limits as parameters.
     */

    public int getX(){
        return coX;
    }

    public int getY(){
        return coY;
    }

    public Direction getDir(){
        return dir;
    }
    /** The three codes above are setters and getters for the coordinates of the drone and the
    * direction that the drone has been given, these are used in later functions and methods
     */

    public boolean isHere(int X, int Y){
        return coX == X && coY == Y;
    }


    public String toString(){
        return "Drone is at: " + coX + ", " + coY + "\nWith direction: " + dir.toString() + ".";
    }

    /** This toString function takes the coordinates of the drones and concatenates them into
    * a string as part of as return statement, this then calls the toString function in DroneGUI.Direction
    * to print later as part of a different function
     */
}