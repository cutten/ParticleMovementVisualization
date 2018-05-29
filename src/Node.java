import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Node {

    private double centerX, centerY;
    private double radius;
    private int number;
    private Node up, down, left, right;

    public Node(double x, double y, double radius, int number) {
        this.centerX = x;
        this.centerY = y;
        this.radius = radius;
        this.number = number;
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;
    }

    public Node(double x, double y, double radius, int number, Node up, Node down, Node left, Node right) {
        this.centerX = x;
        this.centerY = y;
        this.radius = radius;
        this.number = number;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    /*public boolean isConnected(Node p){
        return false;
}*/

    public double getCenterX() {
        return centerX;
    }


    public double getCenterY() {
        return centerY;
    }


    public double getRadius() {
        return radius;
    }

    public int getNumber() {
        return number;
    }

    public Node getUp() {
        return up;
    }

    public void setUp(Node up) {
        this.up = up;
    }

    public Node getDown() {
        return down;
    }

    public void setDown(Node down) {
        this.down = down;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void paint(Pane pane) {
        Circle circle = new Circle(centerX,centerY,radius, Color.BLACK);
        pane.getChildren().add(circle);
    }


}

