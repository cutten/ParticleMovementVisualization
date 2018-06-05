import java.util.Arrays;
import java.util.HashSet;

public class GraphGenerator {

    /*
     * Метод для генерации графа по матрице.
     * Элементы графа рассчитываются и передаются в canvas.
     */
    public static void generate(boolean[][] matrix, GraphCanvas canvas) {
        //Запускаю обработку графа в отдельный поток, чтобы не зависало окно.
        System.out.println("Построение графа по матрице:");
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        //Thread thread = new Thread(Thread.currentThread().getThreadGroup(), new Generator(canvas, matrix));
        //thread.start();
        Generator generator = new Generator(canvas, matrix);
        generator.run();
    }

    /*
     * Класс для генерирования графа в потоке.
     * Инициализируется матрицей и ссылкой на canvas.
     */
    private static class Generator implements Runnable {

        private GraphCanvas canvas;
        private boolean[][] matrix;
        private static boolean isRunning; //Контроль за обработкой, предотвращает одновременный запуск нескольких генераций.

        public Generator(GraphCanvas canvas, boolean[][] matrix) {
            this.canvas = canvas;
            this.matrix = matrix;
        }


        public void run() {
            if (isRunning) //Реализация контроля единовременной обработки. Если обработка уже идёт, поток закроется.
                return;
            isRunning = true;

            canvas.clearGraph(); //Сброс текущего графа.

            Node[] nodes = new Node[matrix.length];


            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new Node(i);
            }

            HashSet<Integer> missingNodes = new HashSet<>();

            boolean correctMode = false;
            for (int i = 0; i < matrix.length; i++) {

                if (!correctMode && missingNodes.contains(i))
                    continue;
                if (correctMode && !missingNodes.contains(i))
                    continue;

                boolean correctMode2 = false;
                for (int j = 0; j < matrix.length; j++) {
                    if (j == i)
                        continue;
                    if (!correctMode2 && missingNodes.contains(j))
                        continue;
                    if (correctMode2 && !missingNodes.contains(j))
                        continue;
                    if (matrix[i][j] || matrix[j][i]) {
                        if (nodes[i].getRight() == null && nodes[i].getDown() == null && nodes[i].getLeft() == null && nodes[i].getUp() == null) {
                            nodes[i].setRight(nodes[j]);
                            nodes[j].setLeft(nodes[i]);
                            if (!missingNodes.isEmpty()) {
                                j = 0;
                                correctMode2 = true;
                            }
                        } else {
                            Node right = nodes[i].getRight();
                            Node left = nodes[i].getLeft();
                            Node up = nodes[i].getUp();
                            Node down = nodes[i].getDown();
                            if ((right != null && right == nodes[j]) || (left != null && left == nodes[j]) || (up != null && up == nodes[j]) || (down != null && down == nodes[j]))
                                continue;
                            if (right != null && down == null) {
                                boolean flag = false;
                                for (int k = 0; k < matrix.length; k++) {

                                    if (k == i || k == right.getNumber() || k == j)
                                        continue;
                                    if ((matrix[right.getNumber()][k] || matrix[k][right.getNumber()]) && (matrix[k][j] || matrix[j][k])) {
                                        if (nodes[k] == right.getUp()) {
                                            nodes[i].setUp(nodes[j]);
                                            nodes[j].setDown(nodes[i]);
                                            nodes[j].setRight(nodes[k]);
                                            nodes[k].setLeft(nodes[j]);
                                        } else {
                                            nodes[i].setDown(nodes[j]);
                                            nodes[right.getNumber()].setDown(nodes[k]);
                                            nodes[j].setUp(nodes[i]);
                                            nodes[k].setUp(nodes[right.getNumber()]);
                                            nodes[j].setRight(nodes[k]);
                                            nodes[k].setLeft(nodes[j]);
                                        }
                                        flag = true;
                                        break;
                                    }

                                }
                                if (!flag) {

                                    if (left == null && up == null) {
                                        missingNodes.add(j);
                                        continue;
                                    }
                                    if (left == null) {
                                        nodes[i].setLeft(nodes[j]);
                                        nodes[j].setRight(nodes[i]);
                                    } else {
                                        nodes[i].setUp(nodes[j]);
                                        nodes[j].setDown(nodes[i]);
                                    }
                                    flag = true;
                                }
                                if (correctMode2 && flag) {
                                    missingNodes.remove(j);
                                    i = 0;
                                    break;
                                }
                                continue;
                            }

                            if (right != null && up == null) {
                                boolean flag = false;
                                for (int k = 0; k < matrix.length; k++) {

                                    if (k == i || k == right.getNumber() || k == j)
                                        continue;
                                    if ((matrix[right.getNumber()][k] || matrix[k][right.getNumber()]) && (matrix[k][j] || matrix[j][k])) {
                                        nodes[i].setUp(nodes[j]);
                                        nodes[right.getNumber()].setUp(nodes[k]);
                                        nodes[j].setDown(nodes[i]);
                                        nodes[k].setDown(nodes[right.getNumber()]);
                                        nodes[j].setRight(nodes[k]);
                                        nodes[k].setLeft(nodes[j]);
                                        flag = true;
                                        break;
                                    }

                                }
                                if (!flag) {
                                    if (left == null && up == null) {
                                        missingNodes.add(j);
                                        continue;
                                    }
                                    if (left == null) {
                                        nodes[i].setLeft(nodes[j]);
                                        nodes[j].setRight(nodes[i]);
                                    } else {
                                        nodes[i].setUp(nodes[j]);
                                        nodes[j].setDown(nodes[i]);
                                    }
                                    flag = true;
                                }
                                if (correctMode2 && flag) {
                                    missingNodes.remove(j);
                                    i = 0;
                                    break;
                                }
                                continue;
                            }

                            if (right != null) {
                                nodes[i].setLeft(nodes[j]);
                                nodes[j].setRight(nodes[i]);
                                if (correctMode2) {
                                    missingNodes.remove(j);
                                    i = 0;
                                    break;
                                }
                                continue;
                            }

                            if (down != null) {
                                boolean flag = false;
                                for (int k = 0; k < matrix.length; k++) {

                                    if (k == i || k == down.getNumber() || k == j)
                                        continue;
                                    if ((matrix[down.getNumber()][k] || matrix[k][down.getNumber()]) && (matrix[k][j] || matrix[j][k])) {
                                        nodes[i].setRight(nodes[j]);
                                        nodes[down.getNumber()].setRight(nodes[k]);
                                        nodes[j].setLeft(nodes[i]);
                                        nodes[k].setLeft(nodes[down.getNumber()]);
                                        nodes[j].setDown(nodes[k]);
                                        nodes[k].setUp(nodes[j]);
                                        flag = true;
                                        break;
                                    }
                                }
                                if (!flag) {
                                    nodes[i].setUp(nodes[j]);
                                    nodes[j].setDown(nodes[i]);
                                    flag = true;
                                }
                                if (correctMode2 && flag) {
                                    missingNodes.remove(j);
                                    i = 0;
                                    break;
                                }
                                continue;
                            }

                            if (up != null && left != null) {
                                boolean flag = false;
                                for (int k = 0; k < matrix.length; k++) {

                                    if (k == i || k == up.getNumber() || k == j)
                                        continue;
                                    if ((matrix[up.getNumber()][k] || matrix[k][up.getNumber()]) && (matrix[k][j] || matrix[j][k])) {
                                        nodes[i].setRight(nodes[j]);
                                        nodes[up.getNumber()].setRight(nodes[k]);
                                        nodes[j].setLeft(nodes[i]);
                                        nodes[k].setLeft(nodes[up.getNumber()]);
                                        nodes[j].setUp(nodes[k]);
                                        nodes[k].setDown(nodes[j]);
                                        flag = true;
                                        break;
                                    }
                                    if ((matrix[left.getNumber()][k] || matrix[k][left.getNumber()]) && (matrix[k][j] || matrix[j][k])) {
                                        nodes[i].setDown(nodes[j]);
                                        nodes[left.getNumber()].setDown(nodes[k]);
                                        nodes[j].setUp(nodes[i]);
                                        nodes[k].setUp(nodes[left.getNumber()]);
                                        nodes[j].setLeft(nodes[k]);
                                        nodes[k].setRight(nodes[j]);
                                        flag = true;
                                        break;
                                    }

                                }
                                if (!flag) {
                                    nodes[i].setRight(nodes[j]);
                                    nodes[j].setLeft(nodes[i]);
                                    flag = true;
                                }
                                if (correctMode2 && flag) {
                                    missingNodes.remove(j);
                                    i = 0;
                                    break;
                                }
                            }

                        }
                    }
                    if (j == matrix.length - 1 && correctMode && !correctMode2 && !missingNodes.isEmpty()) {
                        correctMode2 = true;
                        j = 0;
                    }
                }
                if (i == matrix.length - 1 && !correctMode && !missingNodes.isEmpty()) {
                    correctMode = true;
                    i = 0;
                }
            }

            //Расчёт шага и первой позиции. Неоптимален. Хотите лучше, делайте.
            double step = matrix.length * 2 <= 90 ? 100 - matrix.length * 2 : 10;
            double firstX = canvas.getWidth() / 2;// - (matrix.length * step) / 8;
            double firstY = canvas.getHeight() / 2;// - (matrix.length * step) / 8;

            canvas.setNodeRadius(step / 10);
            canvas.setPathStep(step);

            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i].getUp() != null || nodes[i].getDown() != null || nodes[i].getLeft() != null || nodes[i].getRight() != null) {
                    nodes[i].setCenterX(firstX);
                    nodes[i].setCenterY(firstY);
                    nodes[i].setRadius(step / 10);
                    break;
                }
            }


            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i].getCenterX() != -1) {
                    Node right = nodes[i].getRight();
                    Node left = nodes[i].getLeft();
                    Node up = nodes[i].getUp();
                    Node down = nodes[i].getDown();

                    if (right != null && right.getCenterX() == -1) {
                        right.setCenterX(nodes[i].getCenterX() + step);
                        right.setCenterY(nodes[i].getCenterY());
                        right.setRadius(step / 10);
                    }
                    if (left != null && left.getCenterX() == -1) {
                        left.setCenterX(nodes[i].getCenterX() - step);
                        left.setCenterY(nodes[i].getCenterY());
                        left.setRadius(step / 10);
                    }
                    if (up != null && up.getCenterX() == -1) {
                        up.setCenterX(nodes[i].getCenterX());
                        up.setCenterY(nodes[i].getCenterY() - step);
                        up.setRadius(step / 10);
                    }
                    if (down != null && down.getCenterX() == -1) {
                        down.setCenterX(nodes[i].getCenterX());
                        down.setCenterY(nodes[i].getCenterY() + step);
                        down.setRadius(step / 10);
                    }


                }
            }

            for (Node node : nodes) {
                canvas.addNode(node);
            }

            for (int i = 0; i < nodes.length; i++) {
                for (int j = i + 1; j < nodes.length; j++) {
                    PathLine pathLine;
                    if (matrix[nodes[i].getNumber()][nodes[j].getNumber()] && matrix[nodes[j].getNumber()][nodes[i].getNumber()]) {
                        pathLine = new PathLine(nodes[i], nodes[j], 3);
                    } else if (matrix[nodes[i].getNumber()][nodes[j].getNumber()]) {
                        pathLine = new PathLine(nodes[i], nodes[j], 1);
                    } else if (matrix[nodes[j].getNumber()][nodes[i].getNumber()]) {
                        pathLine = new PathLine(nodes[i], nodes[j], 2);
                    } else
                        continue;
                    canvas.addPath(pathLine);
                }
            }

            canvas.reAddChildren();
            isRunning = false;
        }
    }

}
