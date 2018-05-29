import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GraphCanvas {
    private Pane pane; // Полотно, на котором отрисовывается граф
    private ArrayList<PathLine> lineArr; // Массив соединяющих линий
    private ArrayList<Node> nodeArr; // Массив вершин
    private ArrayList<Particle> particleArr; // Массив частиц

    //Конструктор
    public GraphCanvas(Parent panel) {
        //Инициализация переменных
        pane = (Pane) panel.lookup("#graphPane");
        lineArr = new ArrayList<>();
        nodeArr = new ArrayList<>();
        particleArr = new ArrayList<>();
    }

    //Отрисовка графа
    public void reAddChildren() {

        for (PathLine pathline : lineArr) {
            pathline.addChildren(pane);
        }
        for (Node node : nodeArr) {
            node.addChildren(pane);
        }

        for (Particle particle:particleArr) {
            particle.addChildren(pane);
        }

    }

    // Удаление графа
    void reinit() {
        lineArr = new ArrayList<>();
        nodeArr = new ArrayList<>();
    }

    //Добавление вершины графа в массив
    void addNode(Node node) {
        nodeArr.add(node);
    }

    //Добавление соединяющие линии в массив
    void addPath(PathLine pathline) {
        lineArr.add(pathline);
    }

    //Добавление частицы в массив
    void addParticle(Particle particle){particleArr.add(particle);}

    //Геттеры и сеттеры
    //
    //
    //
    public ArrayList<PathLine> getLineArr() {
        return lineArr;
    }

    public ArrayList<Node> getNodeArr() {
        return nodeArr;
    }

    double getWidth() {
        return pane.getWidth();
    }

    double getHeight() {
        return pane.getHeight();
    }


}


