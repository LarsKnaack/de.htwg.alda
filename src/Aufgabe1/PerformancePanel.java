package Aufgabe1;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


@SuppressWarnings({"serial", "unchecked"})
public class PerformancePanel extends JPanel implements ActionListener {

	private JButton success_8000;
	private JButton success_16000;
	private JButton fail_8000;
	private JButton fail_16000;
	private JLabel lResult;
	private JTextField tfResult;
	
	public PerformancePanel () {
		
		JPanel performance = new JPanel();
		JPanel buttons = new JPanel();
		
		success_8000 = new JButton("Erfolg 8000 Elemente");
		success_8000.addActionListener(this);
		success_16000 = new JButton("Erfolg 16000 Elemente");
		success_16000.addActionListener(this);
		fail_8000 = new JButton("Fehlschlag 8000 Elemente");
		fail_8000.addActionListener(this);
		fail_16000 = new JButton("Fehlschlag 16000 Elemente");
		fail_16000.addActionListener(this);
		
		lResult = new JLabel("Ergebnis");
		tfResult = new JTextField("", 15);
		tfResult.setEditable(false);
		
		buttons.setLayout(new GridLayout(2,2));
		buttons.add(success_8000);
		buttons.add(success_16000);
		buttons.add(fail_8000);
		buttons.add(fail_16000);
		
		JPanel resultPanel = new JPanel(new GridLayout(2,1));
		resultPanel.add(lResult);
		resultPanel.add(tfResult);
		
		performance.setBorder(BorderFactory.createTitledBorder("Performancemessung"));
		performance.setLayout(new GridLayout(2,1));
		
		performance.add(buttons);
		performance.add(resultPanel);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(performance);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(DictionaryGUI.dic == null){
			JOptionPane.showMessageDialog(new JFrame(), "Bitte zuerst Implementieren!");
			return;
		}
		
		if(source == fail_8000){
			double t1 = System.nanoTime();
			for (int i = 0 ; i <= 8000; i++) {
				int j = ((int) Math.random()) % 8000;
				String s = DictionaryMenuBar.englisch[j];
				DictionaryGUI.dic.search(s);
			}
			double t2 = System.nanoTime();
			double time = (t2 - t1) /1000000000;
			tfResult.setText("Zeit: " + time + " Sekunden");
			
		}
		if(source == fail_16000){
			double t1 = System.nanoTime();
			for (int i = 0 ; i <= 16000; i++) {
				int j = ((int) Math.random()) % 16000;
				String s = DictionaryMenuBar.englisch[j];
				DictionaryGUI.dic.search(s);
			}
			double t2 = System.nanoTime();
			double time = (t2 - t1) /1000000000;
			tfResult.setText("Zeit: " + time + " Sekunden");
			
		}
		
		if (source == success_8000) {
			double t1 = System.nanoTime();
			for (int i = 0 ; i <= 8000; i++) {
				int j = ((int) Math.random()) % 8000;
				String s = DictionaryMenuBar.deutsch[j];
				DictionaryGUI.dic.search(s);
			}
			double t2 = System.nanoTime();
			double time = (t2 - t1) /1000000000;
			tfResult.setText("Zeit: " + time + " Sekunden");
			
		}
		
		if (source == success_16000) {
			double t1 = System.nanoTime();
			for (int i = 0 ; i <= 16000; i++) {
				int j = ((int) Math.random()) % 16000;
				String s = DictionaryMenuBar.deutsch[j];
				DictionaryGUI.dic.search(s);
			}
			double t2 = System.nanoTime();
			double time = (t2 - t1) /1000000000;
			tfResult.setText("Zeit: " + time + " Sekunden");
			
		}
	}
	
	
}
