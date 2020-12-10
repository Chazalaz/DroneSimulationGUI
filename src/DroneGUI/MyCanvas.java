package DroneGUI;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.TextAlignment;

/**
 * @author Charith Avancha Fragoso
 */


public class MyCanvas{
    int xCanvasSize = 512;				// constants for relevant sizes, default values set
    int yCanvasSize = 512;
    GraphicsContext gc;


    public MyCanvas(GraphicsContext g, int xcs, int ycs) {
        gc = g;
        xCanvasSize = xcs;
        yCanvasSize = ycs;
    }

    public int getXCanvasSize() {
        return xCanvasSize;
    }
    public int getYCanvasSize() {
        return yCanvasSize;
    }
    /**
     * clear the canvas
     */
    public void clearCanvas() {
        gc.clearRect(0,  0,  xCanvasSize,  yCanvasSize);		// clear canvas
    }
    /**
     * drawImage ... draws object defined by given image at position and size
     * @param i		image
     * @param x		xposition	in range 0..1
     * @param y
     * @param sz	size
     */
    public void drawImage (Image i, double x, double y, double sz) {
        // to draw centred at x,y, give top left position and x,y size
        // sizes/position in range 0.. canvassize
        gc.drawImage(i, x - sz/2, y - sz/2, sz, sz);
    }

    public void setFillColour (int xCanvasSize, int yCanvasSize) {
        gc.setFill(Color.GHOSTWHITE);
        gc.fillRect(0, 0, xCanvasSize, yCanvasSize);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, xCanvasSize, yCanvasSize);
    }
}