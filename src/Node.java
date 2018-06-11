import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Node {

    private double centerX, centerY;
    private double radius;
    private int number;
    private Node up, down, left, right;
    private Circle circle;
    private Text text;
    private Particle currParticle;

    public Node(int number) {
        this.centerX = -1;
        this.centerY = -1;
        this.radius = -1;
        this.number = number;
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;
        currParticle = null;
    }

    public Node(double x, double y, double radius) {
        //Инициализация переменных
        this.centerX = x;
        this.centerY = y;
        this.radius = radius;
        currParticle = null;
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

    public void setNumber(int number) {
        this.number = number;
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


    public Particle getCurrParticle() {
        return currParticle;
    }

    public void setCurrParticle(Particle currParticle) {
        this.currParticle = currParticle;
    }

    //Отрисовка
    public void addChildren(Pane pane) {
        if (circle == null || !pane.getChildren().contains(circle)) {
            circle = new Circle(centerX, centerY, radius, Color.BLACK);
            circle.setFill(null);
            circle.setStroke(Color.BLACK);
            if (number != 0)
                text = new Text(centerX - radius / 2, centerY + radius / 3, "" + number);
            else
                text = new Text(centerX - radius / 2, centerY + radius / 3, "");

            pane.getChildren().add(circle);
            pane.getChildren().add(text);
        }
    }

    public void removeChildren(Pane pane) {
        if (circle != null) {
            pane.getChildren().remove(circle);
            pane.getChildren().remove(text);
        }
    }


}

