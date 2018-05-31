import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GraphCanvas {
    private Pane pane; // Полотно, на котором отрисовывается граф
    private ArrayList<PathLine> lineArr; // Массив соединяющих линий
    private ArrayList<Node> nodeArr; // Массив вершин
    private ArrayList<Particle> particleArr; // Массив частиц

    //16 left 17 up 18 right 19 down 37 b 36 a

    //Konami
    private static boolean ok;
    private static int[] konamiCode = new int[]{17, 17, 19, 19, 16, 18, 16, 18, 37, 36};
    private static int now;

    private double nodeRadius;
    private double pathStep;

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
            if (ok) {
                double radius = node.getRadius() / 4;
                node.setRadius(radius);
            }
            node.addChildren(pane);
        }

        for (Particle particle : particleArr) {
            particle.addChildren(pane);
        }

        if (ok) {
            ok = false;
            now = 0;
        }
    }

    // Удаление графа
    void clearGraph() {
        for (PathLine pathLine : lineArr) {
            pathLine.removeChildren(pane);
        }
        for (Node node : nodeArr) {
            node.removeChildren(pane);
        }
        lineArr = new ArrayList<>();
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

    //Добавление соединяющие линии в массив
    void addPath(PathLine pathline) {
        lineArr.add(pathline);
    }

    //Добавление частицы в массив
    void addParticle(Particle particle) {
        particleArr.add(particle);
    }

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

    public void setNodeRadius(double nodeRadius) {
        this.nodeRadius = nodeRadius;
    }

    public void setPathStep(double pathStep) {
        this.pathStep = pathStep;
    }

    public double getNodeRadius() {
        return nodeRadius;
    }

    public double getPathStep() {
        return pathStep;
    }

    //Konami
    public static void setKonamiCode(int symbol) {
        if (!ok) {
            if (konamiCode[now++] == symbol) {
                if (now == konamiCode.length) {
                    ok = true;
                    System.out.println("Konami code set!");
                }
            } else
                now = 0;
        }
    }
}


