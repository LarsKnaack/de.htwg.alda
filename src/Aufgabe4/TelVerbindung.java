package Aufgabe4;

public class TelVerbindung {
	public final TelKnoten start;
	public final TelKnoten ende;
	
	public TelVerbindung(TelKnoten start, TelKnoten ende) {
		this.start = start;
		this.ende = ende;
	}
	
	public String toString() {
		return start.toString() + "-----" + ende.toString();
	}
}
