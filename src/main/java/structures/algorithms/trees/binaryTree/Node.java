package structures.algorithms.trees.binaryTree;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Node {

    private long value;
    private Node left;
    private Node right;
    private Node parent;

    public String toString() {
        return "value:" + this.value;
    }


}
