package Aufgabe3.src;

import Aufgabe2.AdjacencyListUndirectedGraph;
import Aufgabe2.Graph;
import Aufgabe2.GraphTraversion;
import Aufgabe3.sim.SYSimulation;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ScotlandYardTest {

	public static void main(final String[] args) throws FileNotFoundException {
		GraphTraversion gt = new GraphTraversion();
		Graph<Integer> g = new AdjacencyListUndirectedGraph<Integer>();
		File f = new File(ScotlandYardTest.class.getClassLoader().getResource("src/ScotlandYard.txt").getFile());
		Scanner in = new Scanner(f);
        while (in.hasNext()) {
        	int a = in.nextInt();
        	int b = in.nextInt();
            if (in.hasNext("Taxi")) {
            	g.addVertex(a);
            	g.addVertex(b);
            	g.addEdge(a, b);            	
            }
            in.nextLine();
        }
        in.close();
		List<Integer> l1 = gt.depthFirstSearch(g, new Integer(6));
		List<Integer> l2 = gt.breadthFirstSearch(g, new Integer(6));
		
        SYSimulation sim;
		try {
			sim = new SYSimulation();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		int pre = 0;
		sim.startSequence("Test1");
		for (int station : l1) {
			if(pre != 0) {
				sim.drive(pre, station, Color.RED);
			}
			pre = station;
			sim.visitStation(station);
		}
		sim.stopSequence();

		pre = 0;
		sim.startSequence("Test2");
		for (int station : l2) {
			if(pre != 0) {
				sim.drive(pre, station, Color.BLUE);
			}
			pre = station;
			sim.visitStation(station);
		}
		sim.stopSequence();
	}

}
