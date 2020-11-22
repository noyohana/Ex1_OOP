package ex1.src;

import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms {
    //
    private weighted_graph graph;

    /**
     * default constructor
     */
    public WGraph_Algo()
    {
        graph = null;
    }

    /**
     * Contstructor that get weighted_graph
     * init the graph
     * @param g - weighted_graph
     */
    public WGraph_Algo(weighted_graph g)
    {
        init(g); //init the graph
    }

    /**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        graph = g;
    }

   
    @Override
    public weighted_graph getGraph() {
        return graph;
    } 
    
    @Override
    public weighted_graph copy() {
        WGraph_DS copy = new WGraph_DS(graph); 
        return copy; 
    }
    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node. 
     */
    @Override
    public boolean isConnected() {
        if(graph.nodeSize() == 0)
            return true;
        dijkstra(graph.getV().iterator().next());
        for(node_info node : graph.getV()) 
        {
            if(node.getTag() >= Double.MAX_VALUE) 
                return false;
        }
        return true; 
    }
    /**
     * returns the length of the shortest path between src to dest
     * if no  path,returns -1
     * @param src - start node
     * @param dest - end  node
     * @return double
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if(graph.getNode(src) == null || graph.getNode(dest) == null) 
            return -1;
        dijkstra(graph.getNode(src)); 
        if(graph.getNode(dest).getTag() >= Double.MAX_VALUE)
            return -1;
        return graph.getNode(dest).getTag(); 
    }
    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * 
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        LinkedList<node_info> list = new LinkedList<>(); 
        if(graph.getNode(src) == null || graph.getNode(dest) == null)
        {
            return null;
        }
        if(src == dest) 
        {
            list.add(graph.getNode(src)); 
            return list; 
        }
        HashMap<Integer,node_info> hash = dijkstra(this.graph.getNode(src));
        if(graph.getNode(dest).getInfo().equals("WHITE")) 
        {
            return null;
        }

        list.addFirst(graph.getNode(dest)); 
        node_info t = hash.get(dest); // t = next node

        while(t != null)
        {
            list.addFirst(graph.getNode(t.getKey())); 
            t = hash.get(t.getKey()); 
        }

        return list;
    }
    /**
     * Saves this weighted graph to the given
     * file name
     * @param file - the file name
     */
    @Override
    public boolean save(String file) {
        FileOutputStream f = null; 
        try {
            f = new FileOutputStream(file); 
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(graph);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        FileInputStream f = null;
        try {
            f = new FileInputStream(file);
            ObjectInputStream o = new ObjectInputStream(f);
            graph = (weighted_graph)o.readObject();
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
           
        }
        return true;
    }

    /**
     *
     * Algorithm dijksta:
     
     * White color - not visited nodes
     * Black color - visited nodes
    
     */
    private HashMap<Integer, node_info> dijkstra(node_info node)
    {
        PriorityQueue<node_info> q = new PriorityQueue<>(); 
        HashMap<Integer,node_info> path = new HashMap<>(); 
        for(node_info node1 : graph.getV()) //init 
        {
            node1.setTag(Double.MAX_VALUE); //set tag to Max_Value
            node1.setInfo("WHITE"); //  set \ info to WHITE
            path.put(node1.getKey(),null); 
            q.add(node1); 
        }
        node.setTag(0); 
        q.remove(node);
        q.add(node);
        while(!q.isEmpty()) 
        {
            node_info n = q.remove(); 
            for(node_info node2 : graph.getV(n.getKey())) {
            	
                if(node2.getInfo().equals("WHITE")) 
                {
                    if(n.getTag() < Double.MAX_VALUE) {
                        double dist = n.getTag() + graph.getEdge(n.getKey(), node2.getKey());
                        if (node2.getTag() > dist) { 
                          
                            path.put(node2.getKey(), n); 
                            node2.setTag(dist); 
                            q.remove(node2);
                            q.add(node2);
                        }
                    }
                }
            }
            n.setInfo("BLACK"); 
        }
        return path; 

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WGraph_Algo that = (WGraph_Algo) o;
        return Objects.equals(graph, that.graph);
    }

   
}
