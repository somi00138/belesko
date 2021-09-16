package belesko.komponente;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import belesko.Boje;
import belesko.Fontovi;

public class Button extends JButton {
	
	public Button() {
		setBorder(null);
		setFocusable(false);
		setFocusPainted(false);
		setBackground(Boje.DUGME);
		setForeground(Boje.DUGME_TEKST);
		setFont(Fontovi.TITILLIUM_BOLD.deriveFont(14f));
		addMouseListener(Boje.HoverChange);
	}
	
	public Button(String tekst) {
		setText(tekst);
		setBorder(null);
		setFocusable(false);
		setFocusPainted(false);
		setBackground(Boje.DUGME);
		setForeground(Boje.DUGME_TEKST);
		setFont(Fontovi.TITILLIUM_BOLD.deriveFont(14f));
		addMouseListener(Boje.HoverChange);
	}
	
	public Button(String tekst, int x, int y, int w, int h) {
		setBounds(x, y, w, h);
		setText(tekst);
		setBorder(null);
		setFocusable(false);
		setFocusPainted(false);
		setBackground(Boje.DUGME);
		setForeground(Boje.DUGME_TEKST);
		setFont(Fontovi.TITILLIUM_BOLD.deriveFont(14f));
		addMouseListener(Boje.HoverChange);
	}
	
	public Button(ImageIcon ikonica) {
		setIcon(ikonica);
		setBorder(null);
		setFocusable(false);
		setFocusPainted(false);
		setBackground(Boje.DUGME);
		setForeground(Boje.DUGME_TEKST);
		setFont(Fontovi.TITILLIUM_BOLD.deriveFont(14f));
		addMouseListener(Boje.HoverChange);
	}
	
	public Button(ImageIcon ikonica, int x, int y, int w, int h) {
		setBounds(x, y, w, h);
		setIcon(ikonica);
		setBorder(null);
		setFocusable(false);
		setFocusPainted(false);
		setBackground(Boje.DUGME);
		setForeground(Boje.DUGME_TEKST);
		setFont(Fontovi.TITILLIUM_BOLD.deriveFont(14f));
		addMouseListener(Boje.HoverChange);
	}
	
	public Button(String tekst, ImageIcon ikonica) {
		setText(tekst);
		setIcon(ikonica);
		setBorder(null);
		setFocusable(false);
		setFocusPainted(false);
		setBackground(Boje.DUGME);
		setForeground(Boje.DUGME_TEKST);
		setFont(Fontovi.TITILLIUM_BOLD.deriveFont(14f));
		addMouseListener(Boje.HoverChange);
	}
	
	public Button(String tekst, ImageIcon ikonica, int x, int y, int w, int h) {
		setBounds(x, y, w, h);
		setText(tekst);
		setIcon(ikonica);
		setBorder(null);
		setFocusable(false);
		setFocusPainted(false);
		setBackground(Boje.DUGME);
		setForeground(Boje.DUGME_TEKST);
		setFont(Fontovi.TITILLIUM_BOLD.deriveFont(14f));
		addMouseListener(Boje.HoverChange);
	}
}
