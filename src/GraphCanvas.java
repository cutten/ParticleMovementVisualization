import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GraphCanvas {
    private Pane pane; // Полотно, на котором отрисовывается граф
    private ArrayList<Node> nodeArr; // Массив вершин
    private ArrayList<Particle> particleArr; // Массив частиц

    //Конструктор
    public GraphCanvas(Parent panel) {
        //Инициализация переменных
        pane = (Pane) panel.lookup("#graphPane");
        nodeArr = new ArrayList<>();
        particleArr = new ArrayList<>();
    }

    //Отрисовка графа
    public void reAddChildren() {
//        }
        for (int i = 0; i < nodeArr.size(); i++) {
            nodeArr.get(i).addChildren(pane);



        }

        for (Particle particle:particleArr) {
            particle.addChildren(pane);
        }

    }

    void playAll(boolean play) {
        for (Particle particle : particleArr) {
            particle.playAnim(play);
        }
    }

    // Удаление графа
    void clearGraph() {

        for (Node node : nodeArr) {
            node.removeChildren(pane);
        }
        nodeArr = new ArrayList<>();
    }

    void clearParticle() {
        for (Particle particle : particleArr) {
            particle.removeChildren(pane);
        }
        particleArr = new ArrayList<>();
    }

    //Добавление вершины графа в массив
    void addNode(Node node) {
        nodeArr.add(node);
    }

    void addParticle(Particle particle) {
        particleArr.add(particle);
    }


    //Геттеры и сеттеры

    public ArrayList<Node> getNodeArr() {
        return nodeArr;
    }


    double getWidth() {
        return pane.getWidth();
    }

    double getHeight() {
        return pane.getHeight();
    }

    public ArrayList<Particle> getParticleArr() {
        return particleArr;
    }
}


