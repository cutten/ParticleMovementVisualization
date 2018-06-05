import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Animatrix {

    static void startAnimation(GraphCanvas graphCanvas){
        Circle c;
        Circle cminusone;
        for (int i = 0; i < graphCanvas.getNodeArr().size(); i++) {
            c = graphCanvas.getNodeArr().get(i).getCircle();
            if (i==0)
                c.setFill(Color.GREEN);
            cminusone = graphCanvas.getNodeArr().get(i-1).getCircle();
            c.setFill(Color.GREEN);
            cminusone.setFill(null);
            graphCanvas.getPane().getChildren().addAll(c,cminusone);

        }
    }
}
