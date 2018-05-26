import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class Graph extends JPanel implements ActionListener {
    Timer t = new Timer(20, this); // Таймер, нужен для работы анимации
    boolean startAnimation = false;
    Graphics g;
    Graphics2D g2d; //Графика

    public Graph() {
        setLayout(null);
        repaint(); // Переотрисовка
        g2d = (Graphics2D) g;
        // Код для включения сглаживания графики
        RenderingHints rhints = g2d.getRenderingHints();
        boolean antialiasOn = rhints.containsValue(RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        antialiasOn = rhints.containsValue(RenderingHints.VALUE_ANTIALIAS_ON);
        t.start();
        //
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Отрисовка

    }


    @Override
    public void actionPerformed(ActionEvent e) { //
            repaint();
        }
    }


