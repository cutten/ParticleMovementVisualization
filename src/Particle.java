import javafx.animation.TranslateTransition;

import java.awt.*;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Particle {
    GraphCanvas graphCanvas;
    private Circle partical;
    private ArrayList<PathLine> lineArr;
    private ArrayList<Node> nodeArr;
    private Node firstNode;
    private double pathlineWidth;
    private double pathlineHeight;
    private double animDuration;
    //TranslateTransition transition;

    Particle(GraphCanvas graphCanvas){
        this.graphCanvas = graphCanvas;
        this.animDuration = 0.75;


        lineArr = graphCanvas.getLineArr();
        nodeArr = graphCanvas.getNodeArr();
        firstNode = nodeArr.get(0);
        partical = new Circle(firstNode.getCenterX(),firstNode.getCenterY(),firstNode.getRadius()/1.25, Color.GREEN);
        for (int i = 0; i < lineArr.size(); i++) {
            if (lineArr.get(i).isHorizontal()) {
                this.pathlineWidth = lineArr.get(i).getLineWidth();
                break;
            }
        }
        for (int i = 0; i < lineArr.size(); i++) {
            if (!lineArr.get(i).isHorizontal()){
                this.pathlineHeight = lineArr.get(i).getLineHeight();
                break;
            }
        }

        graphCanvas.addParticle(this);
        graphCanvas.repaint();
    }

    TranslateTransition moveRight() throws InterruptedException {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animDuration));
        transition.setNode(partical);
        transition.setByX(pathlineWidth);
        return transition;
    }

    TranslateTransition moveLeft() throws InterruptedException {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animDuration));
        transition.setNode(partical);
        transition.setByX(-pathlineWidth);
        return transition;
    }

    TranslateTransition moveUp() throws InterruptedException {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animDuration));
        transition.setNode(partical);
        transition.setByY(-pathlineHeight);
        return transition;
    }

    TranslateTransition moveDown() throws InterruptedException {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animDuration));
        transition.setNode(partical);
        transition.setByY(pathlineHeight);
        return transition;
    }

    void paint(Pane pane){
        pane.getChildren().add(partical);
    }
}
