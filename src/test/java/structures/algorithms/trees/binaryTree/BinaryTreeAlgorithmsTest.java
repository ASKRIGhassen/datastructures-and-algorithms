package structures.algorithms.trees.binaryTree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeAlgorithmsTest {

   private static Node root ;
    @BeforeAll
    public static void setup() {
        int[] allElement = {50, 1, 5, 125, 10, 33, 2, 15, 63, 9, 17, 13};
        for (int i : allElement) {
            root = BinaryTreeAlgorithmsImpl.addItemToTree(i, root);
        }
    }

    @Test
    public void testNodeTraversing(){

        String nodeStr = BinaryTreeAlgorithmsImpl.traverseTree(root);
        assertEquals("[ 50, 1, 5, 2, 10, 9, 33, 15, 13, 17, 125, 63 ]",nodeStr);
    }

    @Test
    public void testSearchValue(){

        Node searched10 = BinaryTreeAlgorithmsImpl.findNode(10,root);
        assertNotNull(searched10);
        Node searched100 = BinaryTreeAlgorithmsImpl.findNode(100,root);
        assertNull(searched100);
    }

    @Test
    public void testAddValue(){

        Node searched1542 = BinaryTreeAlgorithmsImpl.findNode(1542,root);
        assertNull(searched1542);
        BinaryTreeAlgorithmsImpl.addItemToTree(1542,root);
        searched1542 = BinaryTreeAlgorithmsImpl.findNode(1542,root);
        assertNotNull(searched1542);
    }

    @Test
    public void removeItemTest(){

        Node node15 = BinaryTreeAlgorithmsImpl.findNode(15,root);
        assertNotNull(node15);
        BinaryTreeAlgorithmsImpl.removeItem(15,root);
        node15 = BinaryTreeAlgorithmsImpl.findNode(15,root);
        assertNull(node15);
    }
}
