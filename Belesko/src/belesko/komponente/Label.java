package belesko.komponente;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import belesko.Boje;
import belesko.Fontovi;

public class Label extends JLabel {

	public Label() {
		setBorder(null);
		setForeground(Boje.LABEL_TEKST);
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
	}
	
	public Label(int x, int y, int w, int h) {
		setBounds(x, y, w, h);
		setBorder(null);
		setForeground(Boje.LABEL_TEKST);
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
	}
	
	public Label(String tekst) {
		setText(tekst);
		setBorder(null);
		setForeground(Boje.LABEL_TEKST);
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
	}
	
	public Label(String tekst, int x, int y, int w, int h) {
		setBounds(x, y, w, h);
		setText(tekst);
		setBorder(null);
		setForeground(Boje.LABEL_TEKST);
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
	}
}
