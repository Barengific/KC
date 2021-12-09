package com.kurdcoin.net;

import com.kurdcoin.core.Sha256;

public class NodeRegistrar {
    public static String nodeIp;
    public static String nodeName;
    public static String nodeId;
    public static int portNo;

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

    public int getPortNo() {
        return portNo;
    }

    public void setPortNo(int portNo) {
        this.portNo = portNo;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeIp, int portNo, String nodeName) {
        this.nodeId = Sha256.hashes(nodeIp + portNo + nodeName);
    }

}
