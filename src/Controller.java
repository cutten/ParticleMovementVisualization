import javafx.scene.input.MouseEvent;

import java.awt.*;

public class Controller {

    public void openMatrix(MouseEvent mouseEvent) {
        TheMatrixReload.showMatrix();
    }

    public void generate(MouseEvent mouseEvent) {
        MainApp.mainGenerate();
    }

    public void startAnimation(MouseEvent mouseEvent) {
        MainApp.startAnimation();
    }
}
