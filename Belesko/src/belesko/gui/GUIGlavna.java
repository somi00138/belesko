package belesko.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import belesko.Boje;
import belesko.Glavna;
import belesko.Ikonice;
import belesko.Korisnik;
import belesko.Strana;
import belesko.Zadatak;
import belesko.komponente.Button;
import belesko.komponente.Label;
import belesko.komponente.Window;

public class GUIGlavna extends Window {

	private JPanel panel;
	private Label labelDatum;
	private Button dugmeDodaj;

	private Korisnik korisnik;

	private ArrayList<String> citati;

	private int trenutnaStranaID = 0;
	private Strana trenutnaStrana;
	private ArrayList<Strana> strane = null;
	
	private static GUIGlavna instanca;
	public static GUIGlavna get() {
		return instanca;
	}

	public GUIGlavna(Korisnik korisnik, String naslov, int sirina, int visina) throws SQLException  {
		super(naslov, sirina, visina);
		
		this.korisnik = korisnik;

		try (InputStream citatiFajl = Glavna.class.getResourceAsStream("/Citati.txt")) {
			citati = (ArrayList<String>)new BufferedReader(new InputStreamReader(citatiFajl, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		promeniStranu(korisnik);

		
		dugmeIzadji.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		JLabel lblCitat = new Label(0, 40, sirina, 25);
		lblCitat.setText(citati.get(new Random().nextInt(citati.size())));
		lblCitat.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				lblCitat.setText(citati.get(new Random().nextInt(citati.size())));
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		components.add(lblCitat);

		labelDatum = new Label(0, 80, sirina, 15);
		osveziDatum(labelDatum);
		components.add(labelDatum);

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Boje.PROZOR_SADRZAJ);
		osveziZadatke();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setBorder(null);
		scrollPane.setBounds(0, 160, sirina, visina - 160 - 50);
		scrollPane.setViewportView(panel);
		components.add(scrollPane);

		dugmeDodaj = new Button("Dodaj", Ikonice.DODAJ, sirina / 2 - 50, 120, 100, 30);
		dugmeDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dugmeDodaj.isEnabled()) {
					new GUIUnosZadatka(korisnik, "Unos zadatka", 520, 420).setVisible(true);
					dugmeDodaj.setEnabled(false);
				}
			}
		});
		components.add(dugmeDodaj);

		Button dugmePrethodna = new Button(Ikonice.PRETHODNA, 10, scrollPane.getY() + scrollPane.getHeight() + 10, 100, 30);
		dugmePrethodna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(trenutnaStranaID >= 1) {
					trenutnaStranaID--;
					promeniStranu(korisnik);
					osveziZadatke();
				}
			}
		});
		components.add(dugmePrethodna);

		Button dugmeSledeca = new Button(Ikonice.SLEDECA, sirina - 100 - 10, scrollPane.getY() + scrollPane.getHeight() + 10, 100, 30);
		dugmeSledeca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(trenutnaStranaID + 1 < strane.size()) {
					trenutnaStranaID++;
					promeniStranu(korisnik);	
					osveziZadatke();
				}
			}
		});
		components.add(dugmeSledeca);

		Button dugmeIzlogujSe = new Button(korisnik.getIme() + " " + korisnik.getPrezime(), Ikonice.NALOG, 120, scrollPane.getY() + scrollPane.getHeight() + 10, sirina - 240, 30);
		dugmeIzlogujSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new GUIPrijava("Prijava", 300, 230).setVisible(true);
			}
		});
		dugmeIzlogujSe.setIconTextGap(5);
		components.add(dugmeIzlogujSe);
		
		instanca = this;
	}

	private void promeniStranu(Korisnik korisnik) {
		try {
			strane =  Glavna.baza.vratiStrane(korisnik);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (strane != null) {
			if (!strane.isEmpty()) {
				if (trenutnaStranaID < strane.size())
					trenutnaStrana = strane.get(trenutnaStranaID);
				else if (trenutnaStranaID == strane.size() && trenutnaStranaID >= 1)
					trenutnaStrana = strane.get(--trenutnaStranaID);
				try {
					trenutnaStrana.setZadaci(Glavna.baza.vratiZadatke(trenutnaStrana, korisnik));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					if (trenutnaStrana != null)
						trenutnaStrana.setZadaci(Glavna.baza.vratiZadatke(trenutnaStrana, korisnik));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
	}

	private void osveziDatum(Label datum) {
		if (trenutnaStrana != null)  {
			int brZadataka = trenutnaStrana.brZadataka();

			if (brZadataka == 0)
				datum.setText("");
			else if (brZadataka == 1)
				datum.setText(brZadataka + " zadatak za " + trenutnaStrana.getDatum());
			else
				datum.setText(brZadataka + " zadataka za " + trenutnaStrana.getDatum());			
		}
	}

	public void osveziZadatke() {
		panel.removeAll();
		promeniStranu(korisnik);
		osveziDatum(labelDatum);
		if (trenutnaStrana != null)  {
			for(Zadatak zadatak : trenutnaStrana.getZadaci()) {
				GUIZadatak a = new GUIZadatak(zadatak);
				panel.add(a);
				a.updateUI();			
			}			
		}
		panel.updateUI();
	}
	
	public Button getDugmeDodaj() {
		return dugmeDodaj;
	}
}
