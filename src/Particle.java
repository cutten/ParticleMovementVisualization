import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Particle {
    //Инициализация переменных
    private Circle partical; // Кружок-отображение частицы
    private ArrayList<Node> nodeArr; // Массив вершин
    private int startNode;
    private int nowNode;
    private boolean playAnim;
    private double animDuration; // Продолжительность одного движения
    private SequentialTransition particleMovement;


    Particle(ArrayList<Node> nodes, int startNode) {
        this.animDuration = ((double) new Random(startNode * 100).nextInt(2) + 1) / 10;
        nodeArr = nodes;
        playAnim = false;
        this.startNode = startNode;

        particleMovement = new SequentialTransition();

    }


    void playAnim(boolean play) {
        playAnim = play;

        if (playAnim) {
            particleMovement.stop();
            particleMovement.getChildren().clear();
            particleMovement = new SequentialTransition();

            nowNode = startNode;
            partical.setCenterX(nodeArr.get(startNode).getCenterX());
            partical.setCenterY(nodeArr.get(startNode).getCenterY());
            partical.setRadius(nodeArr.get(startNode).getRadius());

            nodeArr.get(startNode + 1).setCurrParticle(this);

            particleMovement.getChildren().add(moveToNextNode(nodeArr.get(startNode), nodeArr.get(startNode + 1)));
            particleMovement.play();

            particleMovement.setOnFinished(event -> {
                if (playAnim) {
                    if (++nowNode == nodeArr.size())
                        nowNode = 0;
                    particleMovement.getChildren().clear();
                    //System.out.println("I'm a " + startNode + ", now is " + nowNode + "!");
                    if (nowNode < nodeArr.size() - 1) {
                        if (nodeArr.get(nowNode + 1).getCurrParticle() == null) {
                            nodeArr.get(nowNode).setCurrParticle(null);
                            nodeArr.get(nowNode + 1).setCurrParticle(this);
                            if (nowNode == 0) {
                                nodeArr.get(nodeArr.size() / 2).setCurrParticle(null);
                            }
                            particleMovement.getChildren().add(moveToNextNode(nodeArr.get(nowNode), nodeArr.get(nowNode + 1)));
                        } else {
                            particleMovement.getChildren().add(moveToNextNode(nodeArr.get(nowNode), nodeArr.get(nowNode)));
                            //System.out.println("I'm a " + startNode + " and I waiting1 on " + nowNode);
                            nowNode--;
                        }
                    } else {
                        if (nodeArr.get(0).getCurrParticle() == null && nodeArr.get(nodeArr.size() / 2).getCurrParticle() == null/* || nodeArr.get(nodeArr.size() / 2 - 1).getCurrParticle() == null*/) {
                            nodeArr.get(nowNode).setCurrParticle(null);
                            nodeArr.get(0).setCurrParticle(this);
                            nodeArr.get(nodeArr.size() / 2).setCurrParticle(this);
                            particleMovement.getChildren().add(moveToNextNode(nodeArr.get(nowNode), nodeArr.get(0)));
                        } else {
                            particleMovement.getChildren().add(moveToNextNode(nodeArr.get(nowNode), nodeArr.get(nowNode)));
                            //System.out.println("I'm a " + startNode + " and I waiting2 on " + nowNode);
                            nowNode--;
                        }
                    }
                    particleMovement.play();
                }
            });
        } else {
            particleMovement.getChildren().clear();
            particleMovement.setOnFinished(null);
            particleMovement.stop();
        }
    }


    TranslateTransition moveToNextNode(Node originalNode, Node node) {

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animDuration));
        transition.setNode(partical);
        transition.setByX(node.getCenterX() - originalNode.getCenterX());
        transition.setByY(node.getCenterY() - originalNode.getCenterY());
        return transition;
    }

    void addChildren(Pane pane) {
        if (partical == null && !pane.getChildren().contains(partical)) {
            partical = new Circle(nodeArr.get(startNode).getCenterX(), nodeArr.get(startNode).getCenterY(), nodeArr.get(startNode).getRadius() / 1.25, Color.GREEN);
            pane.getChildren().add(partical);
        }
    }

    void removeChildren(Pane pane) {
        if (partical != null) {
            pane.getChildren().remove(partical);
        }
    }
}
