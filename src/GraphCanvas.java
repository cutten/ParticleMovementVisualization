import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class GraphCanvas {
    private Canvas mainCanvas;
    private ArrayList<PathLine> lineArr;
    private ArrayList<Node> nodeArr;

    public GraphCanvas(Parent panel) {
        mainCanvas = (Canvas) panel.lookup("#mainCanvas");
        lineArr = new ArrayList<>();
        nodeArr = new ArrayList<>();
    }

    public void repaint() {

        for (PathLine pathline : lineArr) {
            pathline.paint(mainCanvas.getGraphicsContext2D());
        }
        for (Node node : nodeArr) {
            node.paint(mainCanvas.getGraphicsContext2D());
        }
    }

    void addNode(Node node) {
        nodeArr.add(node);
    }

    void addPath(PathLine pathline) {
        lineArr.add(pathline);
    }

    void reinit() {
        lineArr = new ArrayList<>();
        nodeArr = new ArrayList<>();
    }

    double getWidth() {
        return mainCanvas.getWidth();
    }

    double getHeight() {
        return mainCanvas.getHeight();
    }

}


