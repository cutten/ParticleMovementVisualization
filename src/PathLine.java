import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class PathLine {


    private double x, y;
    private double lineWidth, lineHeight;
    private Node node1;
    private Node node2;
    private int var;
    private boolean isHorizontal;
    private final Color COLOR = new Color(0, 0, 0); // Черный

    public PathLine(Node node1, Node node2, int var) {

        x = node1.getCenterX() < node2.getCenterX() ? node1.getCenterX() : node2.getCenterX();
        y = node1.getCenterY() < node2.getCenterY() ? node1.getCenterY() : node2.getCenterY();
        isHorizontal = node1.getCenterX() != node2.getCenterX();
        if (isHorizontal) {
            lineWidth = Math.abs(node2.getCenterX() - node1.getCenterX());
            lineHeight = node1.getRadius() / 4;
            y -= node1.getRadius() / 8;
        } else {
            lineWidth = node1.getRadius() / 4;
            lineHeight = Math.abs(node2.getCenterY() - node1.getCenterY());
            x -= node1.getRadius() / 8;
        }
        this.node1 = node1;
        this.node2 = node2;
        this.var = var;
    }

    void paint(Pane pane) {
        Rectangle line = new Rectangle(x,y,lineWidth,lineHeight);
        line.setFill(javafx.scene.paint.Color.BLACK);
        pane.getChildren().add(line);
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public double getLineHeight() {
        return lineHeight;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }
}
