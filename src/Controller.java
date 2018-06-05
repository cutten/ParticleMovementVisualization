import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

// Метод, контролирующий события в окне, все нажатия кнопок идут через него.


public class Controller {

    public void openMatrix(MouseEvent mouseEvent) {
        AdjMatrix.showMatrix();
    }

    public void generate(MouseEvent mouseEvent) {
        MainApp.mainGenerate();
    }

    public void startAnimation(MouseEvent mouseEvent) throws InterruptedException {
        MainApp.startAnimation();
    }

    public void hideError(MouseEvent mouseEvent) {
        MainApp.hideError();
    }

    public void paneClicked(MouseEvent mouseEvent) {
    }

    public void keyPressed(KeyEvent keyEvent) {
        GraphCanvas.setKonamiCode(keyEvent.getCode().ordinal());
    }

}
