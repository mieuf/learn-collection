package mieuf.org.base.dell.treeStructure.method;

import mieuf.org.base.dell.treeStructure.node.TreeNode;

/**
 * Created by Administrator on 2018/4/23.
 *  树的所有操作
 */
public class TreeOperation {

    /**
     * 先序遍历树(递归)(非空树)
     * 1.先访问根节点
     * 2.先序遍历左子树
     * 3.先序遍历右子树
     * @param root
     */
    public void preOrder(TreeNode root) {
        if (root != null) {
            this.visit(root);
            preOrder(root.getLeftChild());
            preOrder(root.getRightChild());
        }
    }

    /**
     * 中序遍历(递归)(非空)
     * 1.中序遍历左子树
     * 2.访问根节点
     * 3.中序遍历右子树
     * @param root
     */
    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.getLeftChild());
            this.visit(root);
            inOrder(root.getRightChild());
        }
    }

    /**
     * 后序遍历(递归)(非空)
     * 1.后序遍历左子树
     * 2.后序遍历右子树
     * 3.访问根节点
     * @param root
     */
    public void postOrder (TreeNode root) {
        if (root != null) {
            postOrder(root.getLeftChild());
            postOrder(root.getRightChild());
            visit(root);
        }
    }

    /**
     * 访问节点
     * @param treeNode
     */
    public void visit(TreeNode treeNode) {
        System.out.println(treeNode.getData());
    }
}
