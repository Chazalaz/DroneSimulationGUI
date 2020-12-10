package DroneGUI;

import javafx.scene.image.Image;

public class Obstacle extends Drone{
    Image obstacleImage = new Image(getClass().getResourceAsStream("square.png"));

    Obstacle(int x, int y, Direction d){
        super(x, y, d);
    }

    /** The code above defines variables that are used later in the code,
     * coX and coY are the x and y coordinates for the drone where dir is
     * the direction that the drone is given.
     */

    public void displayObstacle(MyCanvas mc) {
        mc.drawImage(obstacleImage, coX, coY, 20);
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

    /** The two codes above are setters and getters for the coordinates of the drone and the
     * direction that the drone has been given, these are used in later functions and methods
     */

    public boolean isHere(int X, int Y){
        return coX == X && coY == Y;
    }

}
