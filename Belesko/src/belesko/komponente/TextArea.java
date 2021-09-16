package belesko.komponente;

import javax.swing.JTextArea;

import belesko.Boje;
import belesko.Fontovi;

public class TextArea extends JTextArea {

	public TextArea() {
		setBorder(null);
		setFocusable(true);
		setBackground(Boje.TEXT_FIELD);
		setForeground(Boje.TEXT_FIELD_TEKST);
		setCaretColor(Boje.TEXT_FIELD_TEKST);
		setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
		addFocusListener(Boje.FocusChange);
	}

	public TextArea(int x, int y, int w, int h) {
		setBounds(x, y, w, h);
		setBorder(null);
		setFocusable(true);
		setBackground(Boje.TEXT_FIELD);
		setForeground(Boje.TEXT_FIELD_TEKST);
		setCaretColor(Boje.TEXT_FIELD_TEKST);
		setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
		addFocusListener(Boje.FocusChange);
	}

	public TextArea(String tekst) {
		setText(tekst);
		setBorder(null);
		setFocusable(true);
		setBackground(Boje.TEXT_FIELD);
		setForeground(Boje.TEXT_FIELD_TEKST);
		setCaretColor(Boje.TEXT_FIELD_TEKST);
		setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
		addFocusListener(Boje.FocusChange);
	}

	public TextArea(String tekst, int x, int y, int w, int h) {
		setBounds(x, y, w, h);
		setText(tekst);
		setBorder(null);
		setFocusable(true);
		setBackground(Boje.TEXT_FIELD);
		setForeground(Boje.TEXT_FIELD_TEKST);
		setCaretColor(Boje.TEXT_FIELD_TEKST);
		setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
		addFocusListener(Boje.FocusChange);
	}
}
