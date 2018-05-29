import java.util.ArrayList;

public class Animator {
    GraphCanvas graphCanvas;
    private ArrayList<PathLine> lineArr;
    private ArrayList<Node> nodeArr;
    Animator(GraphCanvas graphCanvas){
        this.graphCanvas = graphCanvas;
        lineArr = graphCanvas.getLineArr();
        nodeArr = graphCanvas.getNodeArr();
    }
}
