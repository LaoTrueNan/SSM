package com.byd.gzq.dsa.plain;

import java.util.Stack;

/**
 * @author Leonard
 * @date 2022/10/12 10:41
 */

public class TestNode {
    public static void main(String[] args) {
        Node<String> node1 = new Node<>("一");
        Node node2 = node1.appendLeft("二");
        Node node3 = node1.appendRight("三");
        Node node4 = node2.appendLeft("四");
        Node node5 = node2.appendRight("五");
        Node node6 = node3.appendLeft("六");
        Node node7 = node3.appendRight("七");


        Stack<Node> stack = new Stack<>();
        stack.push(node1);
        while(!stack.isEmpty()){
            Node top = stack.pop();
            top.visit();
            if(top.getRightChild()!=null){
                stack.push(top.getRightChild());
            }
            if(top.getLeftChild()!=null){
                stack.push(top.getLeftChild());
            }
        }

    }
}
