package Aufgabe1;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.*;

@SuppressWarnings({"unchecked", "rawtypes", "serial"})
public class EinfügenPanel extends JPanel implements ActionListener{
	
	private JTextField tfKey;
	private JTextField tfValue;
	private JLabel lKey;
	private JLabel lValue;
	private JButton bInsert;
	private JButton bShow;
	
	public EinfügenPanel () {
		
		JPanel insert = new JPanel();
		tfKey = new JTextField("",15);
		tfValue = new JTextField("",15);
		lKey = new JLabel ("Schlüssel");
		lValue = new JLabel("Wert");
		bInsert = new JButton("Einfügen");
		bInsert.addActionListener(this);
		bShow = new JButton("Alles Anzeigen");
		bShow.addActionListener(this);
		
		JPanel tfPanel = new JPanel(new GridLayout(4,1));
		tfPanel.add(lKey);
		tfPanel.add(tfKey);
		tfPanel.add(lValue);
		tfPanel.add(tfValue);
		
		insert.add(tfPanel);
		insert.add(bInsert);
		insert.add(bShow);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder("Einfügen"));
		this.add(insert);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (DictionaryGUI.dic == null) {
			if (DictionaryMenuBar.arraydic.isSelected()) DictionaryGUI.dic = new SortedArrayDictionary();
			if (DictionaryMenuBar.hashmadic.isSelected()) DictionaryGUI.dic = new MapDictionary(new HashMap());
			if (DictionaryMenuBar.treemadic.isSelected()) DictionaryGUI.dic = new MapDictionary(new TreeMap());
			if (DictionaryMenuBar.hashdic.isSelected()) DictionaryGUI.dic = new HashDictionary();
		}
		
		if (source == bInsert) {
			DictionaryGUI.dic.insert(tfKey.getText(), tfValue.getText());
		}
		
		if (source == bShow) {
			SuchenLöschenPanel.taResult.setText(DictionaryGUI.dic.toString());
		}
	}

}
