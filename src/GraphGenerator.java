import java.util.HashSet;

public class GraphGenerator {
    // TODO: Генерация графа на основе матрицы смежности.

    /*
     * Метод для генерации графа по матрице.
     * Элементы графа рассчитываются и передаются в canvas.
     */
    public static void generate(boolean[][] matrix, GraphCanvas canvas) {
        //Запускаю обработку графа в отдельный поток, чтобы не зависало окно.
        Thread thread = new Thread(new Generator(canvas, matrix));
        thread.start();
    }

    /*
     * Класс для генерирования графа в потоке.
     * Инициализируется матрицей и ссылкой на canvas.
     */
    private static class Generator implements Runnable {

        GraphCanvas canvas;
        boolean[][] matrix;
        static boolean isRunning; //Контроль за обработкой, предотвращает одновременный запуск нескольких генераций.

        public Generator(GraphCanvas canvas, boolean[][] matrix) {
            this.canvas = canvas;
            this.matrix = matrix;
        }

        @Override
        public void run() {
            if (isRunning) //Реализация контроля единовременной обработки. Если обработка уже идёт, поток закроется.
                return;
            isRunning = true;

            canvas.reinit(); //Сброс текущего графа.

            Node[] nodes = new Node[matrix.length]; //Массив точек.
            HashSet<Integer> numbersOfNodes = new HashSet<>(); //Номера созданных точек, для контроля повторяющихся значений.

            //Расчёт шага и первой позиции. Неоптимален. Как сделать по другому, без понятия.
            double step = matrix.length * 2 <= 90 ? 100 - matrix.length * 2 : 10;
            double firstX = canvas.getWidth() / 2 - (matrix.length * step) / 8;
            double firstY = canvas.getHeight() / 2 - (matrix.length * step) / 8;

            //Нахождение "нулевой" точки - точки отсчёта графа.
            //Первая точка, у которой две связи.
            for (int i = 0; i < matrix.length; i++) {
                int count = 0;
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j] || matrix[j][i])
                        count++;
                }
                if (count == 2) {
                    nodes[0] = new Node(firstX, firstY, step / 10, i); //Создание "нулевой" точки.
                    numbersOfNodes.add(i);
                    break;
                }
            }


            int nextNode = 1;
            int currNode = 0;
            //Алгоритм построения графа. Граф строится от "нулевой" точки вправо и вниз.
            //Заметка.
            //При невозможности построить вправо или вниз надо добавить строительство вверх.
            while (true) {
                Node down = nodes[currNode].getLeft() != null ? nodes[currNode].getLeft().getDown() : null;
                Node right = nodes[currNode].getUp() != null ? nodes[currNode].getUp().getRight() : null;
                for (int i = 0; i < matrix.length; i++) {
                    if (i == nodes[currNode].getNumber())
                        continue;

                    boolean putted = false;

                    if (matrix[nodes[currNode].getNumber()][i] || matrix[i][nodes[currNode].getNumber()]) {
                        if (!numbersOfNodes.contains(i)) {
                            if (!putted && (down != null) && (matrix[down.getNumber()][i] || matrix[i][down.getNumber()])) {
                                nodes[nextNode++] = new Node(nodes[currNode].getCenterX(), nodes[currNode].getCenterY() + step, step / 10, i);
                                nodes[nextNode - 1].setUp(nodes[currNode]);
                                nodes[currNode].setDown(nodes[nextNode - 1]);
                                down.setRight(nodes[nextNode - 1]);
                                nodes[nextNode - 1].setLeft(down);
                                numbersOfNodes.add(i);
                                putted = true;
                            }

                            if (!putted && (right != null) && (matrix[right.getNumber()][i] || matrix[i][right.getNumber()])) {
                                nodes[nextNode++] = new Node(nodes[currNode].getCenterX() + step, nodes[currNode].getCenterY(), step / 10, i);
                                nodes[nextNode - 1].setLeft(nodes[currNode]);
                                nodes[currNode].setRight(nodes[nextNode - 1]);
                                right.setDown(nodes[nextNode - 1]);
                                nodes[nextNode - 1].setUp(right);
                                numbersOfNodes.add(i);
                                putted = true;
                            }

                            if (!putted && (right != null)) {
                                if (nodes[currNode].getDown() == null) {
                                    nodes[nextNode++] = new Node(nodes[currNode].getCenterX(), nodes[currNode].getCenterY() + step, step / 10, i);
                                    nodes[nextNode - 1].setUp(nodes[currNode]);
                                    nodes[currNode].setDown(nodes[nextNode - 1]);
                                } else {
                                    nodes[nextNode++] = new Node(nodes[currNode].getCenterX() + step, nodes[currNode].getCenterY(), step / 10, i);
                                    nodes[nextNode - 1].setLeft(nodes[currNode]);
                                    nodes[currNode].setRight(nodes[nextNode - 1]);
                                }
                                numbersOfNodes.add(i);
                                putted = true;
                            }

                            if (!putted) {
                                if (nodes[currNode].getRight() == null) {
                                    nodes[nextNode++] = new Node(nodes[currNode].getCenterX() + step, nodes[currNode].getCenterY(), step / 10, i);
                                    nodes[nextNode - 1].setLeft(nodes[currNode]);
                                    nodes[currNode].setRight(nodes[nextNode - 1]);
                                } else {
                                    nodes[nextNode++] = new Node(nodes[currNode].getCenterX(), nodes[currNode].getCenterY() + step, step / 10, i);
                                    nodes[nextNode - 1].setUp(nodes[currNode]);
                                    nodes[currNode].setDown(nodes[nextNode - 1]);
                                }
                                numbersOfNodes.add(i);
                            }

                        }
                    }

                }
                if (++currNode == nodes.length)
                    break;
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
            canvas.repaint();
            isRunning = false;
        }
    }

}
