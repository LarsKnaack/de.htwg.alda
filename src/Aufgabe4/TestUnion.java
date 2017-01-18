package Aufgabe4;


public class TestUnion {
	public static void main(String[] args) {
		UnionFind a = new UnionFind();
		a.addElement();
		a.addElement();
		a.addElement();
		a.addElement();
		a.addElement();
		
		System.out.println(a.size());
		
		a.union(1, 2);
		a.union(3, 2);
		a.union(2, 4);
		a.union(3, 5);
		
		System.out.println(a.toString(5));
	}
}
