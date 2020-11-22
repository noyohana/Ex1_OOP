package ex1.tests;
import ex1.src.*;
import org.junit.jupiter.api.Test;




import static org.junit.jupiter.api.Assertions.*;


class WGraph_DSTest {




    @Test
    void getNode() {
        weighted_graph g = new WGraph_DS();
        assertNull(g.getNode(0),"not exist"); 
        g.addNode(0); 
        assertNotNull(g.getNode(0)); 
        g.addNode(1);
        assertNotNull(g.getNode(1));
        assertNull(g.getNode(2)); 
        assertNull(g.getNode(3));
        g.addNode(2);
        assertNotNull(g.getNode(2));
    }

    @Test
    void hasEdge() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        assertFalse(g.hasEdge(0,1));
        assertFalse(g.hasEdge(0,2));
        assertFalse(g.hasEdge(0,3));
        assertFalse(g.hasEdge(1,2));
        assertFalse(g.hasEdge(1,3));
        assertFalse(g.hasEdge(1,0));
        assertFalse(g.hasEdge(2,3));
        assertFalse(g.hasEdge(0,0));
        assertFalse(g.hasEdge(1,1));
        assertFalse(g.hasEdge(2,2));
        assertFalse(g.hasEdge(3,3));
        g.connect(0,1,8);
        assertTrue(g.hasEdge(0,1));
        g.connect(0,2,2);
        assertTrue(g.hasEdge(2,0));
        g.connect(1,3,9);
        assertTrue(g.hasEdge(1,3));

    }

    @Test
    void getEdge() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.connect(0,1,1);
        g.connect(0,2,6);
        assertTrue(g.getEdge(0,1) == 1);
        assertTrue(g.getEdge(0,2) == 6); 
        g.connect(0,1,Integer.MAX_VALUE);
        assertTrue(g.getEdge(0,1) == Integer.MAX_VALUE);
        assertTrue(g.getEdge(-1,0) == -1); 

    assertTrue(g.getEdge(2,3) == -1); 
    }


    @Test
    void addNode() {
        weighted_graph g = new WGraph_DS();
        assertTrue(g.nodeSize() == 0);
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        assertTrue(g.nodeSize() == 4);

         g.addNode(1);
        assertTrue(g.nodeSize() == 4);

    }

    @Test
    void connect() {
        weighted_graph g = new WGraph_DS();
        g.addNode(2);
        g.addNode(4);
        g.addNode(6);
        g.addNode(8);
        assertTrue(g.edgeSize() == 0);
        g.connect(2, 4,1);
        assertTrue(g.edgeSize() == 1);
       assertTrue(g.hasEdge(2,4));
    assertTrue(g.edgeSize() == 1);
      g.connect(6,2,Integer.MAX_VALUE);
       assertTrue(g.edgeSize() == 2);
       try {
            g.connect(6,2,-3);
            fail("weight cant be negative");
      }
        catch (Exception e) {
            assertTrue(true);
       }
      



    }



    @Test
    void removeNode() {
        weighted_graph g = new WGraph_DS();
        assertTrue(g.nodeSize() == 0);
        g.addNode(0);
        assertTrue(g.nodeSize() == 1);
        g.removeNode(0);
        assertTrue(g.nodeSize() == 0);
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);
        g.connect(0,1,1);
        g.connect(0,2,1);
        g.connect(0,3,3);
        g.connect(0,4,4);
        g.connect(2,3,2);
        g.removeNode(0);
        assertFalse(g.hasEdge(0,1));
        assertFalse(g.hasEdge(0,2));
        assertFalse(g.hasEdge(0,3));
        assertFalse(g.hasEdge(0,4));
        assertTrue(g.hasEdge(2,3));
       


    }

    @Test
    void removeEdge() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        assertTrue(g.edgeSize() == 0);
        assertFalse(g.hasEdge(0,1));
        g.connect(0,1,7);
        g.removeEdge(0,1);
        assertTrue(g.edgeSize() == 0);
        assertFalse(g.hasEdge(0,1));



    }

    @Test
    void nodeSize() {
        weighted_graph graph = new WGraph_DS();
        graph.addNode(0);
        graph.addNode(1);
        assertTrue(graph.nodeSize() == 2);
        graph.removeNode(1);
        assertTrue(graph.nodeSize() == 1);


    }

    @Test
    void edgeSize() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        assertTrue(g.edgeSize() == 0);
        g.connect(0,1,1);
        g.connect(0,2,2);
        g.connect(0,3,3);
        g.connect(0,4,4);
        g.connect(1,2,2);
        g.connect(1,3,2);
        g.connect(0,3,4);

        assertTrue(g.edgeSize() == 6);
        g.removeNode(0);
        assertTrue(g.edgeSize() == 2);

    }

    @Test
    void getMC() {
        weighted_graph g = new WGraph_DS();
        assertTrue(g.getMC() == 0);
        g.addNode(0);
        assertTrue(g.getMC() == 1);
        g.addNode(1);
     
        g.addNode(2);
        
        g.addNode(3);
      
        g.addNode(4);
        assertTrue(g.getMC() == 5);
        g.connect(0,1,2);
        assertTrue(g.getMC() == 6);
        g.connect(0,2,6);
       

        assertTrue(g.getMC() == 7);
        g.removeNode(0);
        assertTrue(g.getMC() == 10);

    }



}