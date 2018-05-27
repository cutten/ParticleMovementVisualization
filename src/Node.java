import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Node {

    private int centerX, centerY;
    private int radius;

    public Node(int x, int y, int radius) {
        this.centerX = x;
        this.centerY = y;
        this.radius = radius;
    }

    /*public boolean isConnected(Node p){
        return false;
}*/

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
    }


}

