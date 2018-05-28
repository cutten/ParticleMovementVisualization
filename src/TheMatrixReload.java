import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TheMatrixReload {
    private boolean[][] boolArray;
    private int nodesCount;
    private static Stage stage;
    private Scene scene;
    private Group group;
    private ArrayList<LimitedTextField> textFieldsArr;
    private ArrayList<Label> horizontalLabels;
    private ArrayList<Label> verticalLabels;


    TheMatrixReload(int nodesCount){
        this.group = new Group();
        this.nodesCount = nodesCount;
        this.textFieldsArr = new ArrayList<LimitedTextField>();
        this.horizontalLabels = new ArrayList<Label>();
        this.verticalLabels = new ArrayList<Label>();
        boolArray = new boolean[nodesCount][nodesCount];
        for (int i = 0; i < nodesCount; i++) {
            Label verticalLabel = new Label("N" + i);
            verticalLabel.setLayoutY(30 + i*25);
            verticalLabel.setLayoutX(6);
            verticalLabels.add(verticalLabel);
            group.getChildren().add(verticalLabel);
            for (int j = 0; j < nodesCount; j++) {
                if (i==0){
                    Label horizontalLabel = new Label("N" + j);
                    horizontalLabel.setLayoutX(30 + j*25);
                    horizontalLabel.setLayoutY(6);
                    horizontalLabels.add(horizontalLabel);
                    group.getChildren().add(horizontalLabel);
                }
                LimitedTextField tf = new LimitedTextField();
                tf.setPrefSize(25,25);
                tf.setLayoutX(25 + j*25);
                tf.setLayoutY(25 + i*25);
//                if (i==j){
//                    tf.setEditable(false);
//                    tf.setText("X");
//                }
                textFieldsArr.add(tf);
                group.getChildren().add(tf);
            }
        }


        scene = new Scene(group, nodesCount*25+50, nodesCount*25+50);
        stage = new Stage();
        stage.getIcons().add(new Image("file:iconv2.png"));
        stage.setTitle("Матрица смежности");
        stage.setScene(scene);
        
        
    }

    static void showMatrix(){
        stage.show();
    }


    public boolean[][] getBoolArray() {
        return boolArray;
    }

    boolean[][] fillBoolArray(){
        for (int i = 0; i < nodesCount; i++) {
            for (int j = 0; j < nodesCount; j++) {
                boolArray[i][j] = strToBool(textFieldsArr.get(i+j).getText());
            }
        }
        return boolArray;
    }

    boolean strToBool(String string){
        if (string == "0")
            return false;
        return true;
    }

    String boolToStr(boolean b){
        if (!b)
            return "0";
        return "1";
    }

    void setPreset(boolean[][] preset){
        for (int i = 0; i < nodesCount; i++) {
            for (int j = 0; j < nodesCount; j++) {
                boolArray[i][j] = preset[i][j];
                refreshMatrix();
            }
        }
    }

    void refreshMatrix(){
        for (int i = 0; i < nodesCount; i++) {
            for (int j = 0; j < nodesCount; j++) {
                textFieldsArr.get(i+j).setText(boolToStr(boolArray[i][j]));
            }
        }
    }
}
