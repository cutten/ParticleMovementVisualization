import javafx.scene.input.MouseEvent;

// Метод, контролирующий события в окне, все нажатия кнопок идут через него.


public class Controller {

    public void generate(MouseEvent mouseEvent) {
        MainApp.mainGenerate();
    }

    public void startAnimation(MouseEvent mouseEvent) throws InterruptedException {
        MainApp.startAnimation();
    }

    public void hideError(MouseEvent mouseEvent) {
        MainApp.hideError();
    }

}
