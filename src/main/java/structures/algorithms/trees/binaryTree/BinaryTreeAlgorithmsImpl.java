package structures.algorithms.trees.binaryTree;

import java.util.Stack;

public class BinaryTreeAlgorithmsImpl {

    public static String traverseTree(Node root) {
        Stack<Node> stack = new Stack<Node>();
        stack.add(root);
        StringBuffer str = new StringBuffer("[");
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            str.append(" "+node.getValue()+",");
            if(node.getRight()!=null){
                stack.push(node.getRight());
            }
            if(node.getLeft()!=null){
                stack.push(node.getLeft());
            }
        }
        return str.replace(str.length()-1,str.length()," ]").toString();
    }

    public static Node addItemToTree(long value, Node root) {

        boolean inserted = false;
        Node currentNode = root;
        while (!inserted) {
            if (currentNode == null) {
                root =  Node.builder().value(value).build();
                inserted = true;
            } else if (currentNode.getValue() > value) {
                //add to left
                if (currentNode.getLeft() == null) {
                    Node newNode =  Node.builder().value(value).parent(currentNode).build();
                    currentNode.setLeft(newNode);
                    inserted = true;
                    break;
                } else {
                    currentNode = currentNode.getLeft();
                    continue;
                }

            } else if (currentNode.getValue() < value) {
                //add to the right
                if (currentNode.getRight() == null) {
                    Node newNode = Node.builder().value(value).parent(currentNode).build();
                    currentNode.setRight(newNode);
                    inserted = true;
                    break;
                } else {
                    currentNode = currentNode.getRight();
                    continue;
                }
            }
        }
        return root;
    }

    public static Node findNode(int value, Node root) {

        Node currentNode = root;
        Node result = null;
        while (currentNode != null) {
            if (currentNode.getValue() == value) {
                result = currentNode;
                break;
            } else if (currentNode.getValue() > value) {
                currentNode = currentNode.getLeft();
                continue;
            } else {
                currentNode = currentNode.getRight();
                continue;
            }
        }

        return result;
    }

    public static void insertNodeParent(Node node, Node root) {

        boolean inserted = false;
        Node tempParent = root;
        while (!inserted) {
            if (tempParent.getLeft() == null && node.getValue() < tempParent.getValue()) {
                tempParent.setLeft(node);
                node.setParent(tempParent);
                inserted = true;
            } else if (tempParent.getRight() == null && node.getValue() > tempParent.getValue()) {
                tempParent.setRight(node);
                node.setParent(tempParent);
                inserted = true;
            } else if (tempParent.getValue() > node.getValue()) {
                tempParent = tempParent.getLeft();
                continue;
            } else if (tempParent.getValue() < node.getValue()) {
                tempParent = tempParent.getRight();
            }
        }
    }

    public static Node removeItem(int value, Node root) {
        Node currentNode = root;
        Node parentNode = root;
        while (currentNode != null) {
            if (currentNode.getValue() > value) {
                parentNode = currentNode;
                currentNode = currentNode.getLeft();
                continue;
            } else if (currentNode.getValue() < value) {
                parentNode = currentNode;
                currentNode = currentNode.getRight();
                continue;
            }
            if (currentNode.getValue() == value) {
                // node without childs
                if (currentNode.getRight() == null && currentNode.getLeft() == null) {
                    updateParent(value, currentNode.getParent());
                    currentNode = null;
                    break;
                } else if (currentNode.getLeft() != null && currentNode.getRight() != null) {
                    // have right & left childs
                    currentNode.getParent().setLeft(currentNode.getLeft());
                    currentNode.getLeft().setParent(currentNode.getParent());
                    insertNodeParent(currentNode.getRight(), currentNode.getParent());
                    currentNode = null;
                    break;
                } else if (currentNode.getLeft() != null) {
                    // have left child only
                    Node newLeft = currentNode.getLeft();
                    newLeft.setParent(currentNode.getParent());
                    newLeft.getParent().setLeft(newLeft);
                    currentNode = null;
                    break;
                } else if (currentNode.getRight() != null) {
                    // have right child only
                    Node newRight = currentNode.getRight();
                    newRight.setParent(currentNode.getParent());
                    currentNode.getParent().setRight(newRight);
                    currentNode = null;
                    break;
                }

            }
        }
        return root;
    }

    private static void updateParent(int value, Node parent) {
        if (value > parent.getValue()) {
            parent.setRight(null);
        } else {
            parent.setLeft(null);
        }
    }
}
