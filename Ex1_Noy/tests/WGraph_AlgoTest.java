
package ex1.tests;

import org.junit.jupiter.api.Test;
import ex1.src.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class WGraph_AlgoTest {

    private weighted_graph buildFirstGraph()
    {
        weighted_graph graph = new WGraph_DS();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.connect(1,2,5);
        return graph;
    }

    
    @Test
    void init() {
        weighted_graph graph = buildFirstGraph();
        weighted_graph_algorithms graph_algo = new WGraph_Algo();
        graph_algo.init(graph);
        assertTrue(graph_algo.getGraph() == graph);
       


    }

    @Test
    void getGraph() {
        weighted_graph_algorithms graph_algo = new WGraph_Algo();
        weighted_graph graph = buildFirstGraph();
        weighted_graph_algorithms graph_algo1 = new WGraph_Algo(graph);
        weighted_graph graph1 = graph_algo1.getGraph();
        assertTrue(graph1.nodeSize() == graph.nodeSize());
        assertTrue(graph1.edgeSize() == graph.edgeSize());
        assertTrue(graph1.hasEdge(1,2));
        assertFalse(graph1.hasEdge(1,3));



    }

    @Test
    void copy() {
        weighted_graph graph1 = buildFirstGraph();
        weighted_graph_algorithms graph_algo1 = new WGraph_Algo(graph1);
        weighted_graph copyGraph1 = graph_algo1.copy();
        assertEquals(graph1,copyGraph1);
        graph1.addNode(4);
        assertNotEquals(graph1,copyGraph1);
       


    }

    @Test
    void isConnected() {
        weighted_graph graph = buildFirstGraph();
        weighted_graph_algorithms graph_algo = new WGraph_Algo(graph);
        assertFalse(graph_algo.isConnected());


    }

    @Test
    void shortestPathDist() {
        weighted_graph graph = buildFirstGraph();
        weighted_graph_algorithms graph_algo = new WGraph_Algo(graph);
        assertTrue(graph_algo.shortestPathDist(1,2) == 5);
      assertTrue(graph_algo.shortestPathDist(1,1) == 0);


    }

    @Test
    void saveANDload()
    {
    	 weighted_graph graph = buildFirstGraph();
         weighted_graph_algorithms graph_algo = new WGraph_Algo(graph);
         graph_algo.save("test1");
         weighted_graph graph1 = new WGraph_DS();
         weighted_graph_algorithms graph_algo1 = new WGraph_Algo(graph);
         graph_algo1.load("test1");

         assertEquals(graph_algo,graph_algo1);

    }
}