package mieuf.org.base.dell.treeStructure.node;

import lombok.Data;

/**
 * Created by Administrator on 2018/4/25.
 * TODO -----
 */
@Data
public class  TreeNode<T extends Comparable<T>> {
    private String data;
    private TreeNode leftChild;
    private TreeNode rightChild;
}