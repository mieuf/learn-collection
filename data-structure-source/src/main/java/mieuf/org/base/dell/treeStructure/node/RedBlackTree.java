package mieuf.org.base.dell.treeStructure.node;

import lombok.Data;
import org.w3c.dom.NodeList;
import sun.java2d.pipe.ValidatePipe;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

/**
 *  红黑树的规则:
 *  1.每个节点不是红色就是黑色的;
 *  2.根节点总是黑色的;
 *  3.如果节点是红色的，则它的子节点必须是黑色的（反之不一定）;
 *  4.从根节点到叶节点或空子节点的每条路径，必须包含相同数目的黑色节点（即相同的黑色高度）。
 *  在红-黑树中插入的节点都是红色的，这不是偶然的，因为插入一个红色节点比插入一个黑色节点违背红-黑规则的可能性更小。
 *  原因是:插入黑色节点总会改变黑色高度(违背规则4),但是插入红色节点只有一半的机会会违背规则3。
 *  另外违背规则3比违背规则4要更容易修正。
 *
 * Created by Administrator on 2018/4/25.
 * TODO -----
 */
@Data
public class RedBlackTree<T extends Comparable<T>>{
    private RBTreeNode<T> root;
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RedBlackTree() {
        this.root = null;
    }

    public RBTreeNode<T> parentOf(RBTreeNode<T> node) { //获得父节点
        return node != null? node.parent : null;
    }

    public void setParent(RBTreeNode<T> node, RBTreeNode<T> parent) { //设置父节点
        if(node != null)
            node.parent = parent;
    }
    /**
     * 节点内部类
     * @param <T>
     */
    public class RBTreeNode<T extends Comparable<T>> {
        private boolean color;
        private T key;
        RBTreeNode<T> leftNode;
        RBTreeNode<T> rightNode;
        RBTreeNode<T> parent;

        public RBTreeNode (T key, boolean color, RBTreeNode<T> parent,RBTreeNode<T> leftNode,RBTreeNode<T> rightNode) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
        public T getKey() {
            return this.key;
        }
        @Override
        public String toString() {
            return key + " : " + (this.color == false ? "Black" : "Red");
        }
    }
    public boolean isRed(RBTreeNode<T> node) { //判断节点的颜色
        return (node != null)&&(node.color == RED)? true : false;
    }

    public boolean isBlack(RBTreeNode<T> node) {
        return !isRed(node);
    }

    public void setRed(RBTreeNode<T> node) { //设置节点的颜色
        if(node != null)
            node.color = RED;
    }

    public void setBlack(RBTreeNode<T> node) {
        if(node != null) {
            node.color = BLACK;
        }
    }

    public void setColor(RBTreeNode<T> node, boolean color) {
        if(node != null)
            node.color = color;
    }
    /*************对红黑树节点x进行左旋操作 ******************/
    /*
    * 左旋示意图：对节点x进行左旋
    *     p                       p
    *    /                       /
    *   x                       y
    *  / \                     / \
    * lx  y      ----->       x  ry
    *    / \                 / \
    *   ly ry               lx ly
    * 左旋做了三件事：
    * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
    * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
    * 3. 将y的左子节点设为x，将x的父节点设为y
    */
    private void leftRotate(RBTreeNode<T> x) {
        //1.
        RBTreeNode<T> y = x.rightNode;
        x.rightNode = y.leftNode;
        if (y.leftNode != null) {
            y.leftNode.parent = x;
        }
        //2.
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else {
            if(x == x.parent.leftNode)
                x.parent.leftNode = y;
            else
                x.parent.rightNode = y;
        }
        //3.
        y.leftNode = x;
        x.parent = y;
    }

    /*************对红黑树节点y进行右旋操作 ******************/
    /*
     * 右旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    private void rightRotate(RBTreeNode<T> y) {
        RBTreeNode<T> x = y.leftNode;
        y.leftNode = x.rightNode;

        if(x.rightNode != null)
            x.rightNode.parent = y;
        x.parent = y.parent;
        if(y.parent == null) {
            this.root = x;
        } else {
            if (y == y.parent.rightNode)
                y.parent.rightNode = x;
            else
                y.parent.leftNode = x;
        }

        x.rightNode = y;
        y.parent = x;
    }

    public T visitNode (RBTreeNode<T> node) {
        String color = node.color ? "Red" : "Black";
        System.out.println("key : " + node.getKey() + "  color : " + color);
        return node.getKey();
    }

    /********************* 前序遍历 ******************/
    public void preOrder () {
        preOrder(this.root);
    }

    private void preOrder(RBTreeNode<T> root) {
        if (root != null) {
            this.visitNode(root);
            this.preOrder(root.leftNode);
            this.preOrder(root.rightNode);
        }
    }

    /********************* 中序遍历 ******************/
    public void inOrder () {
        inOrder(this.root);
    }

    private void inOrder(RBTreeNode<T> root) {
        if (root != null) {
            this.inOrder(root.leftNode);
            this.visitNode(root);
            this.inOrder(root.rightNode);
        }
    }

    /********************* 后序遍历*******************/
    public void postOrder () {
        postOrder(this.root);
    }

    private void postOrder(RBTreeNode<T> root) {
        if (root != null) {
            this.postOrder(root.leftNode);
            this.postOrder(root.rightNode);
            this.visitNode(root);
        }
    }

    /*********************查找某个值***************************/
    public RBTreeNode<T> search(T key) {
        return search(this.root,key);
    }

    public RBTreeNode<T> search(RBTreeNode<T> x, T key) {
        while (x != null) {
            int compare = key.compareTo(x.key);
            if (compare < 0) x = x.leftNode;
            else if (compare > 0) x = x.rightNode;
            else return x;
        }
        return x;
    }

    /**
     * 递归方式查找
     * @param x
     * @param key
     * @return
     */
    public RBTreeNode<T> searchBy(RBTreeNode<T> x, T key) {
        if (x == null) return x;
        int compare = key.compareTo(x.key);
        if (compare < 0) return searchBy(x.leftNode, key);
        else if (compare >0) return searchBy(x.rightNode, key);
        else return x;
    }

    /**
     * 插入节点数据
     * @param key
     */
    public void insert (T key) {
        RBTreeNode<T> node = new RBTreeNode<>(key,RED,null,null,null);
        if (node != null) insert(node);
    }

    private void insert(RBTreeNode<T> node) {
        RBTreeNode<T> current = null;
        RBTreeNode<T> x = this.root;
        while (x != null) {
            current = x;
            int compareTo = node.getKey().compareTo(x.key);
            if (compareTo < 0) x = x.leftNode;
            else x = x.rightNode;
        }
        node.parent = current;

        if (current != null){
            int compareTo = node.key.compareTo(current.key);
            if (compareTo < 0) current.leftNode = node;
            else  current.rightNode = node;
        }else {
            this.root = node;
        }

        insertFixUp(node);

    }

    private void insertFixUp (RBTreeNode<T> node) {
        RBTreeNode<T> parent, gParent;
        //需要修整的条件：父节点存在，且父节点的颜色是红色
        while ((parent = this.parentOf(node))!= null && isRed(parent)){
            gParent = parentOf(parent);//获得祖父节点
            //若父节点是祖父节点的左子节点，下面else与其相反
            if (parent == gParent.leftNode) {
                //获取叔叔节点
                RBTreeNode<T> uncle = gParent.rightNode;
                //叔叔节点也是红色
                if (uncle !=null && isRed(uncle)) {
                    setBlack(parent);//父节点和叔叔节点涂黑
                    setBlack(uncle);
                    setRed(gParent);//把祖父节点涂红
                    node = gParent;//将位置放到祖父节点
                    continue;
                }
                //叔叔节点是黑色，且当前节点是右子节点
                if (node == parent.rightNode) {
                    leftRotate(parent);//父节点左旋
                    //交换父节点和自己
                    RBTreeNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }
                //叔叔节点是黑的，当前节点是左子节点
                setBlack(parent);
                setRed(gParent);
                rightRotate(gParent);

            }else {//若父节点是祖父节点的右子节点，与上面的完全相反，本质一样
                RBTreeNode<T> uncle = gParent.leftNode;
                //叔叔节点是红色
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gParent);
                    node = gParent;
                    continue;
                }
                //叔叔节点是黑的，当前节点是右子节点
                if (node == parent.leftNode) {
                    rightRotate(parent);
                    RBTreeNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }
                //叔叔节点是黑色，当前节点是右子节点
                setBlack(parent);
                setRed(gParent);
                leftRotate(gParent);

            }

        }
        setBlack(this.root);
    }

    public void remove (T key) {
        RBTreeNode<T> node;
        if ((node = search(key)) != null) {
            remove(node);
        }
    }

    public void remove (RBTreeNode<T> node) {
        RBTreeNode<T> child,parent;
        boolean color;
        Map<String,String> a= new HashMap<>();
        int sum = 10, b = 12, c;
        TreeMap
    }

    static {
        for (RBTreeNode<K,V> xp, xpl, xpr;;)  {
            if (x == null || x == root)
                return root;
            else if ((xp = x.parent) == null) {
                x.red = false;
                return x;
            }
            else if (x.red) {
                x.red = false;
                return root;
            }
            else if ((xpl = xp.left) == x) {
                if ((xpr = xp.right) != null && xpr.red) {
                    xpr.red = false;
                    xp.red = true;
                    root = rotateLeft(root, xp);
                    xpr = (xp = x.parent) == null ? null : xp.right;
                }
                if (xpr == null)
                    x = xp;
                else {
                    HashMap.TreeNode<K,V> sl = xpr.left, sr = xpr.right;
                    if ((sr == null || !sr.red) &&
                            (sl == null || !sl.red)) {
                        xpr.red = true;
                        x = xp;
                    }
                    else {
                        if (sr == null || !sr.red) {
                            if (sl != null)
                                sl.red = false;
                            xpr.red = true;
                            root = rotateRight(root, xpr);
                            xpr = (xp = x.parent) == null ?
                                    null : xp.right;
                        }
                        if (xpr != null) {
                            xpr.red = (xp == null) ? false : xp.red;
                            if ((sr = xpr.right) != null)
                                sr.red = false;
                        }
                        if (xp != null) {
                            xp.red = false;
                            root = rotateLeft(root, xp);
                        }
                        x = root;
                    }
                }
            }
            else { // symmetric
                if (xpl != null && xpl.red) {
                    xpl.red = false;
                    xp.red = true;
                    root = rotateRight(root, xp);
                    xpl = (xp = x.parent) == null ? null : xp.left;
                }
                if (xpl == null)
                    x = xp;
                else {
                    HashMap.TreeNode<K,V> sl = xpl.left, sr = xpl.right;
                    if ((sl == null || !sl.red) &&
                            (sr == null || !sr.red)) {
                        xpl.red = true;
                        x = xp;
                    }
                    else {
                        if (sl == null || !sl.red) {
                            if (sr != null)
                                sr.red = false;
                            xpl.red = true;
                            root = rotateLeft(root, xpl);
                            xpl = (xp = x.parent) == null ?
                                    null : xp.left;
                        }
                        if (xpl != null) {
                            xpl.red = (xp == null) ? false : xp.red;
                            if ((sl = xpl.left) != null)
                                sl.red = false;
                        }
                        if (xp != null) {
                            xp.red = false;
                            root = rotateRight(root, xp);
                        }
                        x = root;
                    }
                }
            }
        }
    }

}
