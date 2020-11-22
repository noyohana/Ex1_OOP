package ex1.src;

import java.io.Serializable;
import java.util.*;

public class WGraph_DS implements weighted_graph, Serializable {

    private HashMap<Integer,HashMap<Integer,edge_info>> neighbors;
    private HashMap<Integer,node_info> InfoNode; 
    private int CounterEdge; // count number of edges in the graph
    private int mc; // count  changes in the graph

    /**
     * Contractor
     */
    public WGraph_DS()
    {
        CounterEdge = 0;
        mc = 0;
        neighbors = new HashMap<>();
        InfoNode = new HashMap<>();
    }

    /**
     * Copy Constructor 
     */
    public WGraph_DS(weighted_graph wgraph)
    {
        this();
        for(node_info node : wgraph.getV()) //go  all the vertices of the wgraph
        {
            addNode(node.getKey()); //add them to this graph
            InfoNode.get(node.getKey()).setTag(node.getTag());
            InfoNode.get(node.getKey()).setInfo(node.getInfo());
        }
        for(node_info node : wgraph.getV()) 
        {
            for(node_info n : wgraph.getV(node.getKey())) 
            {
                this.connect(n.getKey(),node.getKey(),wgraph.getEdge(n.getKey(),node.getKey())); //add for this graph edge like in wgraph
            }
        }
        this.mc = wgraph.getMC(); 
    }

    /**
     * return the node_data by the node_id,
     * @param  the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_info getNode(int key) {
        return InfoNode.get(key); 
    }

    /**
     *
     * @param node1 key of the node1
     * @param node2 key of the node2
     * NOTE: O(1)
     * @return true iff exist edge between node1 to node2
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if(InfoNode.containsKey(node1)) 
            return neighbors.get(node1).containsKey(node2); 
        else
            return false;
    }

    /**
     * 
     * @param key of node1
     * @param key of node2
     * Note: O(1)
     * @return if has edge between node1,node2 return the weight else -1
     */
    @Override
    public double getEdge(int node1, int node2) {
        if(hasEdge(node1,node2)) 
        {
            return neighbors.get(node1).get(node2).getWeight(); 
        }
        return -1; 
    }
    /**
     * add a new node to the graph .
     * @param key
     */
    @Override
    public void addNode(int key) {
        if(InfoNode.containsKey(key)) //if exist 
            return;
        InfoNode.put(key,new NodeInfo(key)); 
        ++mc; 
        neighbors.put(key,new HashMap<>()); 
      
    }

    /**
     * Connect an edge between node1 and node2
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if(node1 != node2) { 
            if (w < 0) //if the weight is negative throw exception
                throw new RuntimeException("your weight can't be negative");
            if(hasEdge(node1,node2) && getEdge(node1,node2) == w) 
            {
                return;
            }
            else if(getNode(node2) != null && getNode(node1) != null)
            {
                if(!hasEdge(node1,node2))
                    ++CounterEdge; //count of edge +1
                ++mc;
                neighbors.get(node1).put(node2, new EdgeInfo(w)); 
                neighbors.get(node2).put(node1, new EdgeInfo(w)); 
             

           }


        }
    }

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * @return Collection<node_info>
     */
    @Override
    public Collection<node_info> getV() {
        return InfoNode.values(); 
    }

    /**
     *
     * This method returns a Collection containing all the
     * nodes connected to node_id
     * Note: O(k) , k - being the degree of node_id.
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        LinkedList<node_info> list = new LinkedList<node_info>();
        if(InfoNode.containsKey(node_id))
        {
            for(Integer n : neighbors.get(node_id).keySet())
            {
                list.addLast(InfoNode.get(n));
            }
        }
        return list;
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key
     */
    @Override
    public node_info removeNode(int key) {
        if(InfoNode.containsKey(key))
        {
            for(Integer n : neighbors.get(key).keySet())
            {
            	 --CounterEdge;
                 ++mc;
                neighbors.get(n).remove(key);
               
            }
            neighbors.get(key).clear();
            ++mc;
        }

        return InfoNode.remove(key);
    }

   
    @Override
    public void removeEdge(int node1, int node2) {
        if(hasEdge(node1,node2))
        {
        	 --CounterEdge;
             ++mc;
            neighbors.get(node1).remove(node2);
            neighbors.get(node2).remove(node1);
           
        }
    }

    /**
     * @return the number of vertices  in the graph.
     */
    @Override
    public int nodeSize() {
        return InfoNode.size();
    }

    /**
     * @return the number of edges .
     */
    @Override
    public int edgeSize() {
        return CounterEdge;
    }


    @Override
    public int getMC() {
        return mc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WGraph_DS wGraph_ds = (WGraph_DS) o;
        return CounterEdge == wGraph_ds.CounterEdge &&
                mc == wGraph_ds.mc &&
                Objects.equals(neighbors, wGraph_ds.neighbors) &&
                Objects.equals(InfoNode, wGraph_ds.InfoNode);
    }


    private static class NodeInfo implements node_info,Comparable<node_info>,Serializable
    {

        private int key; 
        private String info;
        private double tag; 
       
        public NodeInfo(int key)
        {
            this.key = key;
            this.info = null;
            this.tag = 0;
        }

        @Override
        public int getKey() {
            return this.key;
        }


        @Override
        public String getInfo() {
            return this.info;
        }

        @Override
        public void setInfo(String s) {
            this.info = s;
        }

        @Override
        public double getTag() {
            return this.tag;
        }

        @Override
        public void setTag(double t) {
            this.tag = t;
        }
    

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NodeInfo nodeInfo = (NodeInfo) o;
            return key == nodeInfo.key &&
                    Double.compare(nodeInfo.tag, tag) == 0 &&
                    Objects.equals(info, nodeInfo.info);
        }

        
        @Override
        public int compareTo(node_info o) {
            if(this.getTag() > o.getTag())
                return 1;
            else if(this.getTag() == o.getTag())
                return 0;
            else
                return -1;
        }
    }

    }
