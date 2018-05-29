import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GraphCanvas {
    private Pane pane;
    private ArrayList<PathLine> lineArr;
    private ArrayList<Node> nodeArr;
    private ArrayList<Particle> particleArr;

    public GraphCanvas(Parent panel) {
        pane = (Pane) panel.lookup("#graphPane");
        lineArr = new ArrayList<>();
        nodeArr = new ArrayList<>();
        particleArr = new ArrayList<>();
    }

    public void repaint() {

        for (PathLine pathline : lineArr) {
            pathline.paint(pane);
        }
        for (Node node : nodeArr) {
            node.paint(pane);
        }

        for (Particle particle:particleArr) {
            particle.paint(pane);
        }

    }

    void addNode(Node node) {
        nodeArr.add(node);
    }

    void addPath(PathLine pathline) {
        lineArr.add(pathline);
    }

    void addParticle(Particle particle){particleArr.add(particle);}

    void reinit() {
        lineArr = new ArrayList<>();
        nodeArr = new ArrayList<>();
    }

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


