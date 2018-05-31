import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class PathLine {


    private double x, y; // Координаты
    private double lineWidth, lineHeight; // Ширина, высота
    private Node node1; // Вершина 1
    private Node node2; // Вершина 2
    private int var; // вариант соединения(пока не используется)
    private boolean isHorizontal; // ориентация линии
    Rectangle line;
    private final Color COLOR = new Color(0, 0, 0); // Черный

    public PathLine(Node node1, Node node2, int var) {
        // Определение координат
        x = node1.getCenterX() < node2.getCenterX() ? node1.getCenterX() : node2.getCenterX();
        y = node1.getCenterY() < node2.getCenterY() ? node1.getCenterY() : node2.getCenterY();
        //Определение ориентации
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

    // Добавление в панель графического компонента.
    void addChildren(Pane pane) {
        if (line == null || !pane.getChildren().contains(line)) {
            line = new Rectangle(x, y, lineWidth, lineHeight);
            line.setFill(javafx.scene.paint.Color.BLACK);
            pane.getChildren().add(line);
        }
    }

    public void removeChildren(Pane pane) {
        if (line != null) {
            pane.getChildren().remove(line);
        }
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }
}
