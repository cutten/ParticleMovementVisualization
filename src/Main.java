import javax.swing.*;
import java.awt.*;

public class Main {
    // TODO: Everything.
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final Dimension d = new Dimension(WIDTH,HEIGHT);

    public static void main(String[] args) {
            JFrame frame = new JFrame(""); // Инициализация главного окна
            frame.add(new Graph()); // Добавляем фрпэйм с графом
            frame.setPreferredSize(d); //Размер окна
            frame.setMinimumSize(d); //
            frame.setMaximumSize(d); //
            frame.setResizable(false); // Можно ли изменять размер
            frame.setLocationRelativeTo(null); // Если null то окошко открывается по центру экрана
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Операция при закрытии окна по умолчанию
            frame.setVisible(true);
    }
}
