import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class TheMatrixReload {
    private static boolean[][] boolArray;
    private int nodesCount;
    private static Stage stage;
    private Scene scene;
    private Group group;
    private ArrayList<ArrayList<LimitedTextField>> textFieldsArr;
    private ArrayList<Label> horizontalLabels;
    private ArrayList<Label> verticalLabels;


    TheMatrixReload(int nodesCount){
        this.group = new Group();
        this.nodesCount = nodesCount;
        this.textFieldsArr = new ArrayList<ArrayList<LimitedTextField>>();
        this.horizontalLabels = new ArrayList<Label>();
        this.verticalLabels = new ArrayList<Label>();
        boolArray = new boolean[nodesCount][nodesCount];
        for (int i = 0; i < nodesCount; i++) {
            Label verticalLabel = new Label("N" + i);
            verticalLabel.setLayoutY(30 + i*25);
            verticalLabel.setLayoutX(6);
            verticalLabels.add(verticalLabel);
            group.getChildren().add(verticalLabel);
            ArrayList<LimitedTextField> a = new ArrayList<LimitedTextField>();
            textFieldsArr.add(a);
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
                tf.setFont(new Font(12));
//                if (i==j){
//                    tf.setEditable(false);
//                    tf.setText("X");
//                }
                textFieldsArr.get(i).add(tf);
                group.getChildren().add(tf);
            }
        }


        scene = new Scene(group, nodesCount*25+50, nodesCount*25+50);
        stage = new Stage();
        stage.getIcons().add(new Image("file:iconv2.png"));
        stage.setTitle("Матрица смежности");
        stage.setResizable(false);
        stage.setScene(scene);
        
        
    }



    static void showMatrix(){
        stage.show();
    }


    public boolean[][] getBoolArray() {
        return boolArray;
    }

    boolean[][] fillBoolArray(){
        boolean flag = true;
        for (int i = 0; i < nodesCount; i++) {
            for (int j = 0; j < nodesCount; j++) {
                String s = textFieldsArr.get(i).get(j).getText();
                boolArray[i][j] = strToBool(s);
            }
        }

        return boolArray;
    }

    boolean strToBool(String string){
        if (string == "0")
            return false;
        else
        return true;
    }

    String boolToStr(boolean b){
        if (!b)
            return "0";
        else
        return "1";
    }

    void setPreset(boolean[][] preset){
        for (int i = 0; i < nodesCount; i++) {
            for (int j = 0; j < nodesCount; j++) {
                boolArray[i][j] = preset[i][j];

            }
        }



    }

    void refreshMatrix(){
        for (int i = 0; i < nodesCount; i++) {
            for (int j = 0; j < nodesCount; j++) {
                textFieldsArr.get(i).get(j).setText(boolToStr(boolArray[i][j]));
            }
        }
    }



}
