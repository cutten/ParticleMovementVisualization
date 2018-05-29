import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Particle {
    //Инициализация переменных
    GraphCanvas graphCanvas; // Полотно
    private Circle partical; // Кружок-отображение частицы
    private ArrayList<PathLine> lineArr; // Массив линий
    private ArrayList<Node> nodeArr; // Массив вершин
    private Node firstNode; // Начальная вершина
    private double pathlineLength; // Длинна соденияющих линий
    private double animDuration; // Продолжительность одного движения

    Particle(GraphCanvas graphCanvas){
        this.graphCanvas = graphCanvas;
        this.animDuration = 0.75;
        lineArr = graphCanvas.getLineArr();
        nodeArr = graphCanvas.getNodeArr();
        firstNode = nodeArr.get(0);
        pathlineLength = lineArr.get(0).getLength();
        graphCanvas.addParticle(this);
        graphCanvas.reAddChildren();
    }

    // Движение вправо
    TranslateTransition moveRight() throws InterruptedException {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animDuration));
        transition.setNode(partical);
        transition.setByX(pathlineLength);
        return transition;
    }

    // Движение влево
    TranslateTransition moveLeft() throws InterruptedException {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animDuration));
        transition.setNode(partical);
        transition.setByX(-pathlineLength);
        return transition;
    }

    // Движение вверк
    TranslateTransition moveUp() throws InterruptedException {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animDuration));
        transition.setNode(partical);
        transition.setByY(-pathlineLength);
        return transition;
    }

    // Движение вниз
    TranslateTransition moveDown() throws InterruptedException {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animDuration));
        transition.setNode(partical);
        transition.setByY(pathlineLength);
        return transition;
    }

    void addChildren(Pane pane) {
        if (partical == null && !pane.getChildren().contains(partical)) {
            partical = new Circle(firstNode.getCenterX(), firstNode.getCenterY(), firstNode.getRadius() / 1.25, Color.GREEN);
            pane.getChildren().add(partical);
        }
    }

    void removeChildren(Pane pane) {
        if (partical != null) {
            pane.getChildren().remove(partical);
        }
    }
}
