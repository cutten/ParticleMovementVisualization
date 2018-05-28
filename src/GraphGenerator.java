import java.util.HashSet;

public class GraphGenerator {
    // TODO: Генерация графа на основе матрицы смежности.


    public static void generate(boolean[][] matrix, GraphCanvas canvas) {

        canvas.reinit();

        Node[] nodes = new Node[matrix.length];
        HashSet<Integer> numbersOfNodes = new HashSet<>();

        double step = matrix.length * 2 <= 90 ? 100 - matrix.length * 2 : 10;
        double firstX = canvas.getWidth() / 2 - (matrix.length * step) / 8;
        double firstY = canvas.getHeight() / 2 - (matrix.length * step) / 8;


        for (int i = 0; i < matrix.length; i++) {
            int count = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] || matrix[j][i])
                    count++;
            }
            if (count == 2) {
                nodes[0] = new Node(firstX, firstY, step / 10, i);
                numbersOfNodes.add(i);
                break;
            }
        }


        int nextNode = 1;
        int currNode = 0;
        while (true) {
            //int count = 0;
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

//                        if (!putted && (down != null)) {
//                            if (nodes[currNode].getRight() == null) {
//                                nodes[nextNode++] = new Node(nodes[currNode].getCenterX() + step, nodes[currNode].getCenterY(), step / 10, i);
//                                nodes[nextNode - 1].setLeft(nodes[currNode]);
//                                nodes[currNode].setRight(nodes[nextNode - 1]);
//                            } else {
//                                nodes[nextNode++] = new Node(nodes[currNode].getCenterX(), nodes[currNode].getCenterY() + step, step / 10, i);
//                                nodes[nextNode - 1].setUp(nodes[currNode]);
//                                nodes[currNode].setDown(nodes[nextNode - 1]);
//                            }
//                            numbersOfNodes.add(i);
//                            putted = true;
//                        }

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

//                        if (nodes[currNode].getRight() == null) {
//                            nodes[currNode].setRight(nodes[nextNode - 1]);
//                        } else if (nodes[currNode].getDown() == null) {
//                            nodes[nextNode++] = new Node(nodes[currNode].getCenterX(), nodes[currNode].getCenterY() + step, step / 10, i);
//                            nodes[currNode].setDown(nodes[nextNode - 1]);
//                            numbersOfNodes.add(i);
//                        }

//                        switch (count) {
//                            case 0:
//                                nodes[nextNode++] = new Node(nodes[currNode].getCenterX(), nodes[currNode].getCenterY() + step, step / 10, i);
//                                numbersOfNodes.add(i);
//                                break;
//                            case 1:
//                                nodes[nextNode++] = new Node(nodes[currNode].getCenterX() + step, nodes[currNode].getCenterY(), step / 10, i);
//                                numbersOfNodes.add(i);
//                                break;
//                            case 2:
//                                nodes[nextNode++] = new Node(nodes[currNode].getCenterX(), nodes[currNode].getCenterY() - step, step / 10, i);
//                                numbersOfNodes.add(i);
//                                break;
//                            case 3:
//                                nodes[nextNode++] = new Node(nodes[currNode].getCenterX() - step, nodes[currNode].getCenterY(), step / 10, i);
//                                numbersOfNodes.add(i);
//                                break;
//                        }
//                        count++;
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




/*        for (int j = 1; j < matrix.length; j++) {
            if(matrix[0][j] || matrix[j][0]){
                Node nodeCenter = new Node(firstX, firstY, step/10);
            }
        }*/

/*        for (int i = 0; i < matrix.length; i++) {
            boolean first = true;
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j] || matrix[j][i]) {
                    int var;
                    Node node1, node2;
                    if (matrix[i][j] && matrix[j][i])
                        var = 3;
                    else if (matrix[i][j])
                        var = 1;
                    else
                        var = 2;
                    if (first) {
                        if (nodes[i] == null) {
                            nodes[i] = new Node(firstX, firstY, step / 10);
                            canvas.addNode(nodes[i]);
                        }
                        if (nodes[j] == null) {
                            nodes[j] = new Node(firstX, firstY + step, step / 10);
                            canvas.addNode(nodes[j]);
                        }
                        first = false;
                    } else {
                        if (nodes[i] == null) {
                            nodes[i] = new Node(firstX, firstY, step / 10);
                            canvas.addNode(nodes[i]);
                        }
                        if (nodes[j] == null) {
                            nodes[j] = new Node(firstX + step, firstY, step / 10);
                            canvas.addNode(nodes[j]);
                        }
                    }
                    PathLine pathLine = new PathLine(nodes[i], nodes[j], var);
                    canvas.addPath(pathLine);
                }
            }

        }*/

/*        for (int i = 0; i < matrix.length; i++) {
            if (nodes[i] == null)
                nodes[i] = new Node(firstX, firstY, step / 10);
            for (int j = i + 1; j < matrix.length; j++) {
                if(matrix[i][j] || matrix[j][i]){
                    nodes[j] = new Node()
                }
            }
        }*/


    }

}
