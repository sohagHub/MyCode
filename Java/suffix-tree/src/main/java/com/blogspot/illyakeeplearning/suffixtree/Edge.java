/**
 * Refactored java-code originally based on Mark Nelson's C++ implementation of Ukkonen's algorithm.
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
package com.blogspot.illyakeeplearning.suffixtree;

public class Edge {
    private int beginIndex;     // can't be changed
    private int endIndex;
    private Node startNode;
    private Node endNode;       // can't be changed, could be used as edge id

    // each time edge is created, a new end node is created
    public Edge(int beginIndex, int endIndex, Node startNode) {
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
        this.startNode = startNode;
        this.endNode = new Node(startNode, null);
    }

    public Node splitEdge(Suffix suffix) {
        remove();
        Edge newEdge = new Edge(beginIndex, beginIndex + suffix.getSpan(), suffix.getOriginNode());
        newEdge.insert();
        newEdge.endNode.setSuffixNode(suffix.getOriginNode());
        beginIndex += suffix.getSpan() + 1;
        startNode = newEdge.getEndNode();
        insert();
        return newEdge.getEndNode();
    }

    public void insert() {
        startNode.addEdge(beginIndex, this);
    }

    public void remove() {
        startNode.removeEdge(beginIndex);
    }

    public int getSpan() {
        return endIndex - beginIndex;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    @Override
    public String toString() {
        return endNode.toString();
    }
}