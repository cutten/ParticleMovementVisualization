import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Node {

    private double centerX, centerY;
    private double radius;
    private int number;
    private Node up, down, left, right;

    public Node(int number) {
        this.centerX = -1;
        this.centerY = -1;
        this.radius = -1;
        this.number = number;
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;
    }

    public Node(double x, double y, double radius, int number) {
        //Инициализация переменных
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
        //Инициализация переменных
        this.centerX = x;
        this.centerY = y;
        this.radius = radius;
        this.number = number;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
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


    //Отрисовка
    public void paint(Pane pane) {
        Circle circle = new Circle(centerX,centerY,radius, Color.BLACK);
        pane.getChildren().add(circle);
    }


}

