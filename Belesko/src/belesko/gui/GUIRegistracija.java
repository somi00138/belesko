package belesko.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import belesko.Boje;
import belesko.Fontovi;
import belesko.Glavna;
import belesko.Ikonice;
import belesko.Korisnik;
import belesko.komponente.Button;
import belesko.komponente.Label;
import belesko.komponente.TextField;
import belesko.komponente.Window;

public class GUIRegistracija extends Window {

	private boolean vidljivaSifra = false;
	
	public GUIRegistracija(String naslov, int sirina, int visina) {
		super(naslov, sirina, visina);
		TextField textIme = new TextField(50, 60, sirina - 100, 20);
		TextField textPrezime = new TextField(50, 120, sirina - 100, 20);
		TextField textKorisnickoIme = new TextField(50, 180, sirina - 100, 20);
		JPasswordField textLozinka = new JPasswordField();

		JLabel lblIme = new Label("Ime", 30, 30, sirina - 60, 20);
		components.add(lblIme);

		textIme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					registracija(textIme.getText(), textPrezime.getText(), textKorisnickoIme.getText(), textLozinka.getText());
			}
		});
		components.add(textIme);
		
		JLabel lblPrezime = new Label("Prezime", 30, 90, sirina - 60, 20);
		components.add(lblPrezime);

		textPrezime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					registracija(textIme.getText(), textPrezime.getText(), textKorisnickoIme.getText(), textLozinka.getText());
			}
		});
		components.add(textPrezime);
		
		JLabel lblKorisnickoIme = new Label("Korisničko ime", 30, 150, sirina - 60, 20);
		components.add(lblKorisnickoIme);

		textKorisnickoIme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					registracija(textIme.getText(), textPrezime.getText(), textKorisnickoIme.getText(), textLozinka.getText());
			}
		});
		components.add(textKorisnickoIme);

		JLabel lblLozinka = new Label("Lozinka", 30, 210, sirina - 60, 20);
		components.add(lblLozinka);

		textLozinka.setEchoChar('\u00d7');
		textLozinka.setBorder(null);
		textLozinka.setBackground(Boje.TEXT_FIELD);
		textLozinka.setForeground(Boje.TEXT_FIELD_TEKST);
		textLozinka.setCaretColor(Boje.TEXT_FIELD_TEKST);
		textLozinka.setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
		textLozinka.setBounds(50, 240, sirina - 100 - 20, 20);
		textLozinka.addFocusListener(Boje.FocusChange);
		textLozinka.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					registracija(textIme.getText(), textPrezime.getText(), textKorisnickoIme.getText(), textLozinka.getText());
			}
		});
		components.add(textLozinka);

		Button dugmePrikaziSifru = new Button(Ikonice.PRIKAZI_SIFRU, 230, 240, 20, 20);
		dugmePrikaziSifru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vidljivaSifra = !vidljivaSifra;
				if (vidljivaSifra)
					textLozinka.setEchoChar((char)0);
				else
					textLozinka.setEchoChar('\u00d7');
			}
		});
		components.add(dugmePrikaziSifru);

		Button dugmeRegistracija = new Button("Prijavi se", 100, 270, 100, 30);
		dugmeRegistracija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registracija(textIme.getText(), textPrezime.getText(), textKorisnickoIme.getText(), textLozinka.getText());
			}
		});
		components.add(dugmeRegistracija);

		dugmeIzadji.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GUIPrijava("Prijava", 300, 230).setVisible(true);
				dispose();
			}
		});
	}

	void registracija(String ime, String prezime, String korisnickoIme, String lozinka) {
		try {
			Korisnik korisnik = new Korisnik(ime, prezime, korisnickoIme, lozinka);
			if (Glavna.baza.registracija(korisnik)) {
				setVisible(false);
				new GUIPrijava("Prijava", 300, 230).setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Korisnik već postoji");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
