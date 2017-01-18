package Aufgabe4;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
public class TelNet {
	//private int lbg;
	private int idx = 1;
	private final static int anzahl = 100;
	UnionFind union = new UnionFind();
	
	static List<TelVerbindung> connect = new LinkedList<>();
	HashMap<Integer,TelKnoten> map = new HashMap<Integer, TelKnoten>();
	
	
	
	public static void main(String[] args) {
		drawOptTelNet(100,100);
		/*TelNet telnetz = new TelNet();
		telnetz.addTelKnoten(1, 1);
		telnetz.addTelKnoten(3, 1);
		telnetz.addTelKnoten(4, 2);
		telnetz.addTelKnoten(3, 4);
		telnetz.addTelKnoten(2, 6);
		telnetz.addTelKnoten(4, 7);
		telnetz.addTelKnoten(7, 5);		
		telnetz.computeOptTelNet();
		System.out.println(connect.size());
		System.out.println(telnetz.getOptTelNet());
		System.out.println(telnetz.getOptTelNetKosten());
		
		connect.clear();
		TelNet telnetz2 = new TelNet();
		telnetz2.generateRandomTelNet(5, 10, 10);
		telnetz2.computeOptTelNet();
		System.out.println(telnetz2.getOptTelNet());
		*/
	}

	/*
	Fügt einen neuen Telefonknoten 
	mit Koordinate (x,y) dazu
	*/
	public void addTelKnoten(int x, int y) {
		map.put(idx, new TelKnoten(x, y));
		union.addElement();
		idx++;
	}
	
	/*
	 * Berechnet ein optimales Telefonnetz als minimal 
	 * aufspannenden Baum mit dem Algorithmus von Kruskal.
	 */
	public void computeOptTelNet() {
		int distance = Integer.MAX_VALUE;
		int start = 0; 
		int ende = 0;
		for (int i = 1; i < map.size(); i++) {
			for(int k = 1; k < map.size() + 1; k++){
				if(union.find(i) != union.find(k) && distance(map.get(i), map.get(k)) < distance) {
					distance = distance(map.get(i), map.get(k));
					start = i;
					ende = k;
				}			
			}
			connect.add(new TelVerbindung(map.get(start), map.get(ende)));
			union.union(start, ende);
			distance = Integer.MAX_VALUE;
		}
	}


	private int distance(TelKnoten telKnoten, TelKnoten telKnoten2) {
		return Math.abs(telKnoten.x - telKnoten2.x) + Math.abs(telKnoten.y - telKnoten2.y);
	}

	/*
	 * Zeichnet das gefundene optimale Telefonnetz 
	 * mit der Größe xMax*yMax in ein Fenster.
	 */
	public static void drawOptTelNet(double xMax, double yMax) {
		double xdMax = 1/xMax;
		double ydMax = 1/yMax;
		TelNet draw = new TelNet();
		draw.generateRandomTelNet(anzahl,(int) xMax,(int) yMax);
		/*telnetz.addTelKnoten(1, 1);
		telnetz.addTelKnoten(3, 1);
		telnetz.addTelKnoten(4, 2);
		telnetz.addTelKnoten(3, 4);
		telnetz.addTelKnoten(2, 6);
		telnetz.addTelKnoten(4, 7);
		telnetz.addTelKnoten(7, 5);*/
		
		draw.computeOptTelNet();
		System.out.println(draw.getOptTelNetKosten());
		for(TelVerbindung tv : connect) {
			StdDraw.filledCircle(xdMax * tv.start.x, ydMax * tv.start.y, 0.005);
			StdDraw.line(xdMax * tv.start.x, ydMax * tv.start.y,xdMax * tv.start.x,ydMax * tv.ende.y);
			StdDraw.line(xdMax * tv.start.x, ydMax * tv.ende.y, xdMax * tv.ende.x, ydMax * tv.ende.y);
			StdDraw.filledCircle(xdMax * tv.ende.x, ydMax * tv.ende.y, 0.005);
		}
	}

	/*
	 * Fügt n zufällige Telefonknoten zum Netz dazu 
	 * mit x-Koordinate aus [0,xMax] und y-Koordinate aus [0,yMax].
	 */
	public void generateRandomTelNet(int n, int xMax, int yMax) {
		for(int i = 0; i < n; i++) {
			int x = (int)(Math.random() * xMax) + 1;
			int y = (int) (Math.random() * yMax) +1;
			addTelKnoten(x, y);
		}
	}
	
	/*
	 * Liefert ein optimales Telefonnetz als 
	 * Liste von Telefonverbindungen zurück.
	 */
	public List<TelVerbindung> getOptTelNet() {
		return connect;
	}
	
	/*
	 * Liefert die Gesamtkosten eines 
	 * optimalen Telefonnetzes zurück.
	 */
	public int getOptTelNetKosten() {
		int summe = 0;
		for(TelVerbindung tv : connect) {
			summe += distance(tv.start, tv.ende);
		}
		return summe;
	}
}