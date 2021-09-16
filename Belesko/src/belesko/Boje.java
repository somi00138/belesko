package belesko;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Boje {
	public static Color ZADATAK_TAMNA = new Color(15, 150, 60);
	public static Color ZADATAK_SVETLA = new Color(20, 180, 70);
	
	public static Color PROZOR_NASLOV = new Color(40, 40, 40);
	public static Color PROZOR = new Color(20, 20, 20);
	public static Color PROZOR_SADRZAJ = new Color(30, 30, 30);
	
	public static Color DUGME = new Color(40, 40, 40);
	public static Color DUGME_HOVER = new Color(82, 82, 82);
	public static Color DUGME_TEKST = new Color(255, 255, 255);

	public static Color LABEL_TEKST = new Color(255, 255, 255);

	public static Color TEXT_FIELD = new Color(40, 40, 40);
	public static Color TEXT_FIELD_FOCUS = new Color(82, 82, 82);
	public static Color TEXT_FIELD_TEKST = new Color(255, 255, 255);

	public static MouseAdapter HoverChange = new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getComponent() instanceof JButton)
				((JButton)e.getComponent()).setBackground(DUGME_HOVER);
			else
				return;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getComponent() instanceof JButton)
				((JButton)e.getComponent()).setBackground(DUGME);
			else
				return;
		}
	};

	public static FocusAdapter FocusChange = new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			if (e.getComponent() instanceof JTextField)
				((JTextField)e.getComponent()).setBackground(TEXT_FIELD_FOCUS);
			else if (e.getComponent() instanceof JTextArea)
				((JTextArea)e.getComponent()).setBackground(TEXT_FIELD_FOCUS);
			else
				return;
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (e.getComponent() instanceof JTextField)
				((JTextField)e.getComponent()).setBackground(TEXT_FIELD);
			else if (e.getComponent() instanceof JTextArea)
				((JTextArea)e.getComponent()).setBackground(TEXT_FIELD);
			else
				return;
		}
	};
}
