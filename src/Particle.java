import javafx.animation.TranslateTransition;

import java.awt.*;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Particle {
    GraphCanvas graphCanvas;
    private Circle partical;
    private ArrayList<PathLine> lineArr;
    private ArrayList<Node> nodeArr;
    private Node firstNode;
    private double pathlineWidth;
    private double pathlineHeight;
    TranslateTransition transition;

    Particle(GraphCanvas graphCanvas){
        this.graphCanvas = graphCanvas;
        this.transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1));

        lineArr = graphCanvas.getLineArr();
        nodeArr = graphCanvas.getNodeArr();
        firstNode = nodeArr.get(0);
        partical = new Circle(firstNode.getCenterX(),firstNode.getCenterY(),firstNode.getRadius()/2, Color.GREEN);
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

    void moveRight(){
        transition.setNode(partical);
        transition.setByX(pathlineWidth);
        transition.play();
    }

    void moveLeft(){
        transition.setNode(partical);
        transition.setByX(-pathlineWidth);
        transition.play();
    }

    void moveUp(){
        transition.setNode(partical);
        transition.setByY(pathlineHeight);
        transition.play();
    }

    void moveDown(){
        transition.setNode(partical);
        transition.setByY(-pathlineHeight);
        transition.play();
    }

//    void paint(GraphicsContext graphicsContext){
//        graphCanvas.getMainCanvas().
//    }
}
