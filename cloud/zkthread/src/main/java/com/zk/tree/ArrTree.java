package com.zk.tree;/**
 * Created by husky on 2018/11/20.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 每个节点只有一个父节点
 *
 * @author zhangkai
 * @create 2018-11-20 16:45
 **/
public class ArrTree<T> {

    private int size = 100;  //树的大小,不设定默认100
    private Node<T>[] nodes;  //树的几点集合


    //树的节点,数据结构
    public static class Node<T> {

        T data;  //节点数据
        int parentInx;  //父节点的下标


        public Node(T data, int parentInx) {
            this.data = data;
            this.parentInx = parentInx;
        }

        public Node(T data) {
            this.data = data;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", parentInx=" + parentInx +
                    '}';
        }
    }


    /**
     * 创建树,根据默认节点个数
     *
     * @param data
     */
    public ArrTree(T data) {
        nodes = new Node[this.size];
        nodes[0] = new Node<T>(data, -1);  //创建默认的根节点
    }

    /**
     * 创建数,根据指定的节点个数
     */
    public ArrTree(T data, int size) {
        this.size = size;
        nodes = new Node[size];
        nodes[0] = new Node<T>(data, -1);  //创建默认的根节点
    }

    /**
     * 添加节点到树中,指定父节点
     *
     * @param data
     * @param parent
     * @return
     */
    public Node<T> addNote(T data, Node parent) {
        //给节点找位置
        for (int i = 0; i < size; i++) {
            if (nodes[i] == null) {
                //如果位置为空,说明没有放置元素
                Node<T> child = new Node(data, getParentIndex(parent));
                nodes[i] = child;
                return child;
            }
        }
        throw new RuntimeException("该树已满");
    }


    /**
     * 根据节点查询对应的节点位置,如果没有则认为是父节点
     *
     * @param node
     * @return
     */
    public int getParentIndex(Node node) {
        for (int i = 0; i < size; i++) {
            if (nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 判断节点树是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return nodes.length == 0;
    }


    /**
     * 返回指定节点的所有子节点
     */

    public List<Node<T>> getNodes(Node<T> node) {
        List<Node<T>> childs = new ArrayList<>();
        //首先获取节点的下标
        for (int i = 0; i < size; i++) {
            if (nodes[i] != null && nodes[i].parentInx == getParentIndex(node)) {
                //获取到对应的下标
                childs.add(nodes[i]);
            }
        }
        return childs;
    }

    //返回根节点
    public Node<T> getRoot() {
        return nodes[0];
    }


    //返回树的深度
    public int getDeep() {
        int max = 0;  //默认最大深度


        //遍历节点数组,因为数组不是满的保证遍历到 != null 一个就行
        for (int i = 0; i < size && nodes[i] != null; i++) {

            int deep = 1; //深度
            int parentInx = nodes[i].parentInx;
            while (parentInx != -1 && nodes[parentInx] != null) {
                deep++;
                parentInx = nodes[parentInx].parentInx;
            }

            if (deep > max) {
                max = deep;
            }
        }


        return max;
    }


    public static void main(String[] args) {

        //根元素为1,树的大小为5
        ArrTree<Integer> arrTree = new ArrTree<Integer>(10, 10);
        ArrTree.Node root = arrTree.getRoot();
        Node node6 = arrTree.addNote(6, root);
        arrTree.addNote(7, root);
        arrTree.addNote(8, root);

        arrTree.addNote(1, node6);
        Node node2 = arrTree.addNote(2, node6);
        arrTree.addNote(0,node2);


        System.out.println(arrTree.getNodes(node6));
        System.out.println(arrTree.getDeep());


    }


}
