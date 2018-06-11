import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private static GraphCanvas graphCanvas;
    private static Parent panel1;
    private Scene primaryScene;
    private static Text errorLabel;
    private static Button okButton;
    private static TextField amountOfNodes;
    private static TextField amountOfParticles;
    private static TextField stepsAmount;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        // Инициализация окна #1
        createPrimaryStage(primaryStage);
    }

    // Метод - "Оболочка" для вызова метода генерации
    static void mainGenerate() {

        graphCanvas.playAll(false);
        graphCanvas.clearGraph();
        graphCanvas.clearParticle();
        CircleGenerator.generate(graphCanvas, Integer.parseInt(amountOfNodes.getText()));
        graphCanvas.reAddChildren();
    }

    // Метод - "Оболочка" для запуска анимации
    static void startAnimation() throws InterruptedException {
        mainGenerate();
        try {
            int k = (int) Math.round(((double) Integer.parseInt(amountOfNodes.getText()) * 2 - 1) / Integer.parseInt(amountOfParticles.getText()));
            if (k < 1)
                k = 1;
            int count = 0;
            for (int i = 1; i < Integer.parseInt(amountOfNodes.getText()) * 2 - 1; i += k) {
                Particle particle = new Particle(graphCanvas.getNodeArr(), i);
                graphCanvas.addParticle(particle);
                if (++count == Integer.parseInt(amountOfParticles.getText()))
                    break;
            }
            graphCanvas.reAddChildren();
            graphCanvas.playAll(true);

        } catch (IndexOutOfBoundsException e) {
            displayError("не на чем показывать анимацию :P");
        }
    }

    // Метод создания главного окна
    private void createPrimaryStage(Stage stage) throws IOException {
        panel1 = FXMLLoader.load(getClass().getResource("mainAppInfinity.fxml"));
        primaryScene = new Scene(panel1, 800, 530);
        graphCanvas = new GraphCanvas(panel1);
        errorLabel = (Text) panel1.lookup("#errorLabel");
        okButton = (Button) panel1.lookup("#okButton");
        amountOfNodes = (TextField) panel1.lookup(("#amount"));
        amountOfParticles = (TextField) panel1.lookup(("#amountParticles"));
        stepsAmount = (TextField) panel1.lookup("#stepsAmount");
        okButton.setVisible(false);
        amountOfNodes.setText("15");
        amountOfParticles.setText("1");
        stepsAmount.setText("1");
        stage.getIcons().add(new Image("file:iconv2.png"));
        stage.setTitle("Визуализация движения частиц по регулярным сетям");
        stage.setScene(primaryScene);
        stage.setResizable(false);
        stage.show();
    }

    static void displayError(String string) {
        okButton.setVisible(true);
        errorLabel.setText("Ошибка: " + string);
    }

    static void hideError() {
        okButton.setVisible(false);
        errorLabel.setText("");
    }
}
