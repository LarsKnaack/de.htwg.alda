package Aufgabe1;

import java.awt.event.*;
import java.awt.*;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.*;

@SuppressWarnings({"unchecked", "rawtypes", "serial"})
public class SuchenLöschenPanel extends JPanel implements ActionListener {
	
	private JTextField tfKey;
	private JLabel lKey;
	private JButton bSearch;
	private JButton bRemove;
	public static JTextArea taResult;
	private JScrollPane scroll;
	
	public SuchenLöschenPanel () {
		
		JPanel searchRemove = new JPanel();
		tfKey = new JTextField("", 15);
		
		bSearch = new JButton("Suchen");
		bSearch.addActionListener(this);
		bRemove = new JButton("Löschen");
		bRemove.addActionListener(this);
		
		lKey = new JLabel("Schlüssel");
		
		taResult = new JTextArea();
		taResult.setEditable(false);
		
		scroll = new JScrollPane(taResult);
		
		searchRemove.setBorder(BorderFactory.createTitledBorder("Suchen/Löschen"));
		searchRemove.setLayout(new BoxLayout(searchRemove,BoxLayout.X_AXIS));
		
		JPanel tfPanel = new JPanel(new GridLayout(2,1));
		tfPanel.add(lKey);
		tfPanel.add(tfKey);
		
		JPanel result = new JPanel(new BorderLayout());
		result.add(scroll);
		result.setBorder(BorderFactory.createTitledBorder("Ausgabe"));
		
		searchRemove.add(tfPanel);
		searchRemove.add(bSearch);
		searchRemove.add(bRemove);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(searchRemove);
		this.add(result);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (DictionaryGUI.dic == null) {
			if (DictionaryMenuBar.arraydic.isSelected()) DictionaryGUI.dic = new SortedArrayDictionary();
			if (DictionaryMenuBar.hashmadic.isSelected()) DictionaryGUI.dic = new MapDictionary(new HashMap());
			if (DictionaryMenuBar.treemadic.isSelected()) DictionaryGUI.dic = new MapDictionary(new TreeMap());
			if (DictionaryMenuBar.hashdic.isSelected()) DictionaryGUI.dic = new HashDictionary();
		}
		
		if(source == bSearch) {
			
			if(DictionaryGUI.dic.search(tfKey.getText()) == null) {
				JOptionPane.showMessageDialog(new JFrame(), "Kein Eintrag gefunden");
				return;
			} else {
				String res = DictionaryGUI.dic.search(tfKey.getText()).toString();
				taResult.setText(res);
			}
		}
		if(source == bRemove) {
			String res = DictionaryGUI.dic.remove(tfKey.getText()).toString();
			DictionaryGUI.dic.remove(tfKey.getText());
			taResult.setText("gelöscht: " + res);
		}
	}
}
