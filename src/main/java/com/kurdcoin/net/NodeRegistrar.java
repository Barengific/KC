package com.kurdcoin.net;

public class NodeRegistrar {
    String nodeIp;
    String nodeName;
    String nodeId;
    int portNo;

    public NodeRegistrar() {
    }

    public NodeRegistrar(String nodeIp, String nodeName, String nodeId, int portNo) {
        this.nodeIp = nodeIp;
        this.nodeName = nodeName;
        this.nodeId = nodeId;
        this.portNo = portNo;
    }

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public int getPortNo() {
        return portNo;
    }

    public void setPortNo(int portNo) {
        this.portNo = portNo;
    }
}
