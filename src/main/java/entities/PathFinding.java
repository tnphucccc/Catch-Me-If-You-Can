package entities;

import core.TileManager;
import variables.Constant;

import java.util.ArrayList;

public class PathFinding {
    public ArrayList<Node> pathList = new ArrayList<>();
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = true;
    int step = 0;

    public PathFinding() {
        instantiateNodes();
    }

    public void instantiateNodes() {
        // create node
        node = new Node[Constant.MAX_SCREEN_COL][Constant.MAX_SCREEN_ROW];

        for (int col = 0; col < Constant.MAX_SCREEN_COL; col++) {
            for (int row = 0; row < Constant.MAX_SCREEN_ROW; row++) {
                node[col][row] = new Node(col, row);
            }
        }
    }

    public void resetNodes() {
        //reset node
        for (int col = 0; col < Constant.MAX_SCREEN_COL; col++) {
            for (int row = 0; row < Constant.MAX_SCREEN_ROW; row++) {
                node[col][row].open = false;
                node[col][row].checked = false;
                node[col][row].solid = false;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();
        //set the start and goal node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        for (int col = 0; col < Constant.MAX_SCREEN_COL; col++) {
            for (int row = 0; row < Constant.MAX_SCREEN_ROW; row++) {
                int tileNum = TileManager.map[col][row];
                if (TileManager.tiles[tileNum].collision) {
                    node[col][row].solid = true;
                }
                getCost(node[col][row]);
            }
        }
    }

    public void getCost(Node node) {
        //G cost
        int xDis = Math.abs(node.col - startNode.col);
        int yDis = Math.abs(node.row - startNode.row);
        node.gCost = xDis + yDis;
        //H cost
        xDis = Math.abs(node.col - goalNode.col);
        yDis = Math.abs(node.row - goalNode.row);
        node.hCost = xDis + yDis;
        //F cost
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (!goalReached && step < 500) {
            int col = currentNode.col;
            int row = currentNode.row;

            // check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            //Open the Up node
            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);
            }
            //Open the Left node
            if (col - 1 >= 0) {
                openNode(node[col - 1][row]);
            }
            //Open the Down node
            if (row + 1 < Constant.MAX_SCREEN_ROW) {
                openNode(node[col][row + 1]);
            }
            //Open the Right node
            if (col + 1 < Constant.MAX_SCREEN_COL) {
                openNode(node[col + 1][row]);
            }

            //Find the best node
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestNodeFCost) {
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).fCost;
                } else if (openList.get(i).fCost == bestNodeFCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            if (openList.size() == 0) {
                break;
            }
            currentNode = openList.get(bestNodeIndex);
            if (currentNode == goalNode) {
                goalReached = true;
                trackPath();
            }
            step++;
        }
        return goalReached;
    }

    public void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackPath() {
        Node current = goalNode;
        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }
}
