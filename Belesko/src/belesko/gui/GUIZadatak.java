package belesko.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import belesko.Boje;
import belesko.Fontovi;
import belesko.Glavna;
import belesko.Ikonice;
import belesko.Zadatak;
import belesko.komponente.Button;
import belesko.komponente.Label;

public class GUIZadatak extends JPanel {
	
	private int sirina = 460;
	private int visina = 250;
	
	private GUIZadatak instanca;
	
	private Button dugmeIzmeni;
	private Button dugmeObrisi;
	
	public GUIZadatak(Zadatak zadatak) {		
		setBackground(Boje.ZADATAK_TAMNA);
		setMinimumSize(new Dimension(sirina, visina));
		setPreferredSize(new Dimension(sirina, visina));
		setMaximumSize(new Dimension(sirina, visina));
		setFocusable(true);
		setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, Boje.PROZOR_SADRZAJ));
		setLayout(null);
	
		dugmeObrisi = new Button(Ikonice.OBRISI, sirina - 40, 10, 30, 30);
		dugmeObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Glavna.baza.obrisiZadatak(zadatak);
					GUIGlavna.get().osveziZadatke();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		add(dugmeObrisi);
		
		
		JLabel labelNaslovZadatka = new Label(zadatak.getNaslov(), 40, 10, sirina - 40 - 40, 30);
		labelNaslovZadatka.setFont(Fontovi.TITILLIUM_BOLD.deriveFont(14f));
		add(labelNaslovZadatka);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 50, sirina - 20, 180);
		add(scrollPane);
		
		JTextArea opisZadatka = new JTextArea(zadatak.getOpis());
		opisZadatka.setBackground(Boje.ZADATAK_SVETLA);
		opisZadatka.setForeground(Boje.TEXT_FIELD_TEKST);
		opisZadatka.setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
		opisZadatka.setCaretPosition(0);
		opisZadatka.setEditable(false);
		scrollPane.setViewportView(opisZadatka);
		
		instanca = this;
	
		dugmeIzmeni = new Button(Ikonice.IZMENI, 10, 10, 30, 30);
		dugmeIzmeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				if(dugmeIzmeni.isEnabled() || dugmeObrisi.isEnabled()) {
					new GUIIzmeniZadatak(zadatak, instanca, "Izmena zadatka", 520, 420).setVisible(true);
					dugmeIzmeni.setEnabled(false);
					dugmeObrisi.setEnabled(false);
				}
				
			}
		});
		add(dugmeIzmeni);
	}
	
	public Button getDugmeIzmeni() {
		return dugmeIzmeni;
	}
	
	public Button getDugmeObrisi() {
		return dugmeObrisi;
	}
}
