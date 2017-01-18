package Aufgabe1;
import javax.swing.*;

import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings({"unchecked", "rawtypes"})
public class DictionaryMenuBar extends JMenuBar implements ActionListener {
	
	private static final long serialVersionUID = -5683214909339710424L;
	private JMenu datei;
	private JMenu implement;
	public static JMenuItem arraydic;
	public static JMenuItem hashmadic;
	public static JMenuItem treemadic;
	public static JMenuItem hashdic;
	public static JMenuItem treedic;
	private JMenuItem speichern;
	private JMenuItem laden;
	private JMenuItem schließen;
	public static String[] deutsch = new String[16000];
	public static String[] englisch = new String[16000];
	private int size = 0;
	
	public DictionaryMenuBar () {
		datei = new JMenu("Datei");
		speichern = new JMenuItem("Speichern");
		speichern.addActionListener(this);
		laden = new JMenuItem("Laden");
		laden.addActionListener(this);
		schließen = new JMenuItem("Schlie�en");
		schließen.addActionListener(this);
		
		datei.add(speichern);
		datei.add(laden);
		datei.add(schließen);
		
		implement = new JMenu("Implementierung");
		arraydic = new JRadioButtonMenuItem("Sorted Array Dictionary");
		hashmadic = new JRadioButtonMenuItem("Hash Map Dictionary");
		treemadic = new JRadioButtonMenuItem("Tree Map Dictionary");
		hashdic = new JRadioButtonMenuItem("Hashtabelle Dictionary");
		treedic = new JRadioButtonMenuItem("Tree Dictionary");
		
		ButtonGroup group = new ButtonGroup();
		group.add(arraydic);
		group.add(hashmadic);
		group.add(treemadic);
		group.add(hashdic);
		group.add(treedic);
		
		arraydic.addActionListener(this);
		hashmadic.addActionListener(this);
		treemadic.addActionListener(this);
		hashdic.addActionListener(this);
		treedic.addActionListener(this);
		
		implement.add(arraydic);
		implement.add(hashmadic);
		implement.add(treemadic);
		implement.add(hashdic);
		implement.add(treedic);
		
		this.add(datei);
		this.add(implement);
	}
	
	private void ensureCap (String[] old,int newCap) {
		if (newCap < size) {return;}
		String[] backup = old;
		old = new String[newCap];
		System.arraycopy(old, 0, backup, 0, size);
	}
	private void read(File f) {
		LineNumberReader in = null;
		try {
			in = new LineNumberReader(new FileReader(f));
			String line;
			int i = 0;
			while((line = in.readLine()) != null) {
				String[] res = line.split(" ");
				if(res.length == 2) {
					if (size >= deutsch.length){
						ensureCap(deutsch, size *2);
						ensureCap(englisch, size *2);
					}
					DictionaryGUI.dic.insert(res[0], res[1]);
					deutsch[i] = res[0];
					englisch[i] = res[1];
					size++;
					i++;
				} else {
					DictionaryGUI.dic.insert("Fehler", "Fehler");
				}
			}
			in.close();
		} catch (IOException ex) {
			Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NullPointerException x) {
			JOptionPane.showMessageDialog(new JFrame(), "Bitte zuerst Implementierung ausw�hlen");
			return;
		}
	}
	
	private void save (File f) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(f);
			out.println(DictionaryGUI.dic.toString());
			out.close();
		} catch (IOException ex) {
			Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();		
		JFileChooser fc = new JFileChooser();
		
		if (DictionaryGUI.dic == null) {
			if (arraydic.isSelected()) DictionaryGUI.dic = new SortedArrayDictionary();
			if (hashmadic.isSelected()) DictionaryGUI.dic = new MapDictionary(new HashMap());
			if (treemadic.isSelected()) DictionaryGUI.dic = new MapDictionary(new TreeMap());
			if (hashdic.isSelected()) DictionaryGUI.dic = new HashDictionary();
			if (treedic.isSelected()) DictionaryGUI.dic = new TreeDictionary();
		}
		
		if (source == laden) {
			int val = fc.showOpenDialog(this.getParent());
			if(val == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				double t1 = System.nanoTime();
				read(file);
				double t2 = System.nanoTime();
				double time = (t2 - t1) / 1000000000;
				JOptionPane.showMessageDialog(new JFrame(), "Einlesezeit: " + time + " Sekunden");
			}
		} else if (source == speichern) {
			int val = fc.showOpenDialog(this.getParent());
			if(val == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				save(file);
			}
		} else if (source == schließen) {
			int res = JOptionPane.showConfirmDialog(this.getParent(),
					"Sicher?",
					"Applikation verlassen",
					JOptionPane.YES_NO_OPTION);
			if(res == JOptionPane.YES_OPTION)
				System.exit(0);
		}
		
		if (source == arraydic) {
			DictionaryGUI.dic = new SortedArrayDictionary();
		} else if (source == hashmadic) {
			DictionaryGUI.dic = new MapDictionary(new HashMap());
		} else if (source == treemadic) {
			DictionaryGUI.dic = new MapDictionary(new TreeMap());
		} else if (source == hashdic) {
			DictionaryGUI.dic = new HashDictionary();
		} else if (source == treedic) {
			DictionaryGUI.dic = new TreeDictionary();
		}
	}

}
