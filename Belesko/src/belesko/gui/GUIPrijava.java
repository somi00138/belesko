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

public class GUIPrijava extends Window {
	private boolean vidljivaSifra = false;

	public GUIPrijava(String naslov, int sirina, int visina) {
		super(naslov, sirina, visina);
		
		dugmeIzadji.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		
		JPasswordField textLozinka = new JPasswordField();

		JLabel lblKorisnickoIme = new Label("Korisničko ime", 30, 30, sirina - 60, 20);
		components.add(lblKorisnickoIme);

		TextField textKorisnickoIme = new TextField(50, 60, sirina - 100, 20);
		textKorisnickoIme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					prijava(textKorisnickoIme.getText(), textLozinka.getText());
			}
		});
		components.add(textKorisnickoIme);

		JLabel lblLozinka = new Label("Lozinka", 30, 90, sirina - 60, 20);
		components.add(lblLozinka);

		textLozinka.setFocusable(true);
		textLozinka.setEchoChar('\u00d7');
		textLozinka.setBorder(null);
		textLozinka.setBackground(Boje.TEXT_FIELD);
		textLozinka.setForeground(Boje.TEXT_FIELD_TEKST);
		textLozinka.setCaretColor(Boje.TEXT_FIELD_TEKST);
		textLozinka.setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
		textLozinka.setBounds(50, 120, sirina - 100 - 20, 20);
		textLozinka.addFocusListener(Boje.FocusChange);
		textLozinka.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					prijava(textKorisnickoIme.getText(), textLozinka.getText());
			}
		});
		components.add(textLozinka);

		Button dugmePrikaziSifru = new Button(Ikonice.PRIKAZI_SIFRU, 230, 120, 20, 20);
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

		Button dugmePrijava = new Button("Prijavi se", 100, 150, 100, 30);
		dugmePrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prijava(textKorisnickoIme.getText(), textLozinka.getText());
			}
		});
		components.add(dugmePrijava);

		Button dugmeRegistracija = new Button("Registruj se", 100, 190, 100, 30);
		dugmeRegistracija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new GUIRegistracija("Registracija", 300, 310).setVisible(true);
				setVisible(false);
			}
		});
		components.add(dugmeRegistracija);
	}

	void prijava(String korisnickoIme, String lozinka) {
		try {
			Korisnik korisnik = new Korisnik(korisnickoIme, lozinka);
			if (Glavna.baza.autentifikacija(korisnik)) {
				new GUIGlavna(korisnik, "Beleško", 520, 700).setVisible(true);
				setVisible(false);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Neuspešna prijava");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
