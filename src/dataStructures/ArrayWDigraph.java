
/** array adjacency list representation of a weighted digraph */

package dataStructures;

import java.util.*;

public class ArrayWDigraph extends ArrayDigraph
{
   // constructors
   public ArrayWDigraph(int theVertices)
      {super(theVertices);}
   
   // default is a 0 vertex graph
   public ArrayWDigraph()
      {this(0);}
   
   /** put theEdge into the digraph 
     * @throws IllegalArgumentException when
     * theEdge is invalid */
   public void putEdge(Object theEdge)
   {
      WeightedEdge edge =  (WeightedEdge) theEdge;
      int v1 = edge.vertex1;
      int v2 = edge.vertex2;
      if (v1 < 1 || v2 < 1 || v1 > n || v2 > n || v1 == v2)
         throw new IllegalArgumentException
               ("(" + v1 + "," + v2 + ") is not a permissible edge");

      int index = aList[v1].indexOf(new EdgeNode(v2));
      if (index == -1)  // new edge
      {
         // put v2 at end of aList[v1]
         aList[v1].add(aList[v1].size(),
                       new WeightedEdgeNode(v2, edge.weight));
         e++;
      }
      else
      {// update edge weight
         WeightedEdgeNode e = (WeightedEdgeNode) aList[v1].get(index);
         e.weight = edge.weight;
      }
   }


   /** test program */
   public static void main(String [] args)
   {
      ArrayWDigraph g = new ArrayWDigraph(4);
      System.out.println("Edges = " + g.edges());
      System.out.println();

      g.putEdge(new WeightedEdge(2, 4, new Integer(6)));
      g.putEdge(new WeightedEdge(1, 3, new Integer(2)));
      g.putEdge(new WeightedEdge(2, 1, new Integer(3)));
      g.putEdge(new WeightedEdge(1, 4, new Integer(4)));
      g.putEdge(new WeightedEdge(4, 2, new Integer(5)));
      g.putEdge(new WeightedEdge(2, 4, new Integer(1)));
      System.out.println("The graph is");
      g.output();
      System.out.println();

      g.removeEdge(2,1);
      System.out.println("The graph after deleting (2,1) is");
      g.output();
      System.out.println();

      System.out.println("existsEdge(3,1) = " + g.existsEdge(3,1));
      System.out.println("existsEdge(1,3) = " + g.existsEdge(1,3));
      System.out.println("inDegree(3) = " + g.inDegree(3));
      System.out.println("outDegree(1) = " + g.outDegree(1));
      System.out.println("Edges = " + g.edges());
      System.out.println();

      // test iterator
      Iterator iter = g.iterator(4);
      System.out.println("Edges out of vertex 4 are");
      while (iter.hasNext())
      {
         WeightedEdgeNode w = (WeightedEdgeNode) iter.next();
         System.out.println("(4, " + w + ")");
      }
   }
}
