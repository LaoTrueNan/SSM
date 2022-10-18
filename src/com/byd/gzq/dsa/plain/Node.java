package com.byd.gzq.dsa.plain;

/**
 * @author Leonard
 * @date 2022/10/12 10:35
 */

public class Node<T>{
    private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;


    public Node(T data, Node<T> leftChild, Node<T> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node() {
        this(null);
    }

    public Node(T data) {
        this(data,null,null);
    }

    public Node appendLeft(T data){
        return appendLeft(new Node<>(data));
    }

    public Node appendLeft(Node<T> node){
        return leftChild = node;
    }

    public Node appendRight(T data){
        return appendRight(new Node<>(data));
    }

    public Node appendRight(Node<T> node){
        return rightChild = node;
    }

    public void visit(){
        System.out.println(data);
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }
}
