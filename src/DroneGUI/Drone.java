package DroneGUI;

import javafx.scene.image.Image;

public class Drone {
    public int coX;
    public int coY;
    public Direction dir;
    private int droneID;
    private static int count = 0;
    Image droneImage = new Image(getClass().getResourceAsStream("circle.png"));

    Drone(int x, int y, Direction d){
        coX = x;
        coY = y;
        dir = d;
        droneID = count++;
    }

    /** The code above defines variables that are used later in the code,
    * coX and coY are the x and y coordinates for the drone where dir is
    * the direction that the drone is given.
     */

    public void tryToMove(DroneArena a){

        switch (dir){
            case North:
                if(a.canMoveHere(coX, coY + 20)) {
                    coY++;
                }else{
                    dir = Direction.getRandomDir();
                }
                break;
            case South:
                if (a.canMoveHere(coX, coY - 20)) {
                    coY--;
                }else{
                    dir = Direction.getRandomDir();
                }
                break;
            case East:
                if (a.canMoveHere(coX + 20, coY)) {
                    coX++;
                }else{
                    dir = Direction.getRandomDir();
                }
                break;
            case West:
                if (a.canMoveHere(coX - 20, coY)) {
                    coX--;
                }else{
                    dir = Direction.getRandomDir();
                }
                break;
        }
    }
    /** The tryToMove() function looks at using the switch case statement
     * to decide the direction the drones are given, the switch statement
     * looks at which direction the drones can initially move in, so the first
     * direction is north and the function uses an if else statement to see
     * whether the drone can move north or not, if yes the drone moves north for
     * all the positions available, this includes whether it is on the border or
     * if there is another drone here. If it can not move in the chosen direction then
     * it checks the next switch statement to see if it can move in the next direction.
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

    /**
     * The isHere function checks a set of coordinates to see if there is a drone at the coordinates.
     */


    public String toString(){
        return "Drone " + droneID + " is at: " + coX + ", " + coY + "\nWith direction: " + dir.toString() + ".";
    }

    /** This toString function takes the coordinates of the drones and concatenates them into
    * a string as part of as return statement, this then calls the toString function in DroneGUI.Direction
    * to print later as part of a different function
     */
}