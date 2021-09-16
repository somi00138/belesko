package belesko.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import belesko.Boje;
import belesko.Datum;
import belesko.Fontovi;
import belesko.Glavna;
import belesko.Ikonice;
import belesko.Korisnik;
import belesko.Zadatak;
import belesko.komponente.Button;
import belesko.komponente.Label;
import belesko.komponente.TextArea;
import belesko.komponente.TextField;
import belesko.komponente.Window;

public class GUIUnosZadatka extends Window {
	
	public GUIUnosZadatka(Korisnik korisnik, String naslov, int sirina, int visina) {
		super(naslov, sirina, visina);
				
		dugmeIzadji.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIGlavna.get().getDugmeDodaj().setEnabled(true);
				
				setVisible(false);
				dispose();
			}
		});
		
		
		//=======================GLAVNI DEO=======================\\
		JLabel lblNaslov = new Label("Naslov", 30, 30, sirina - 60, 20);
		components.add(lblNaslov);
		
		JTextField textNaslov = new TextField(30, 60, sirina - 60, 20);
		components.add(textNaslov);
		
		JLabel lblOpis = new Label("Opis", 30, 90, sirina - 60, 20);
		components.add(lblOpis);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(30, 120, sirina - 60, 180);
		components.add(scrollPane);
		
		JTextArea textOpis = new TextArea();
		scrollPane.setViewportView(textOpis);
		
		JLabel lblDan = new Label("Dan", 100, 310, 100, 20);
		components.add(lblDan);
		
		JLabel lblMesec = new Label("Mesec", 210, 310, 100, 20);
		components.add(lblMesec);
		
		JLabel lblGodina = new Label("Godina", 320, 310, 100, 20);
		components.add(lblGodina);
		
		JComboBox<Integer> comboBoxDan = new JComboBox<Integer>();
		JComboBox<Integer> comboBoxGodina = new JComboBox<Integer>();
		JComboBox<String> comboBoxMesec = new JComboBox<String>();
		
		comboBoxDan.setForeground(Color.WHITE);
		comboBoxDan.setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
		comboBoxDan.setFocusable(true);
		comboBoxDan.setBackground(new Color(40, 40, 40));
		comboBoxDan.setBounds(100, 340, 100, 30);
		components.add(comboBoxDan);
		
		comboBoxMesec.setModel(new DefaultComboBoxModel<String>(new String[] {"Januar", "Februar", "Mart", "April", "Maj", "Jun", "Jul", "Avgust", "Septembar", "Oktobar", "Novembar", "Decembar"}));
		comboBoxMesec.setForeground(Color.WHITE);
		comboBoxMesec.setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
		comboBoxMesec.setFocusable(true);
		comboBoxMesec.setBackground(new Color(40, 40, 40));
		comboBoxMesec.setBounds(210, 340, 100, 30);
		comboBoxMesec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Datum.osvezi(comboBoxDan, comboBoxMesec.getSelectedItem().toString(), Integer.parseInt(comboBoxGodina.getSelectedItem().toString()));
			}
		});
		components.add(comboBoxMesec);
		
		comboBoxGodina.setForeground(Color.WHITE);
		comboBoxGodina.setFont(Fontovi.TITILLIUM_REGULAR.deriveFont(14f));
		comboBoxGodina.setFocusable(true);
		comboBoxGodina.setBorder(null);
		comboBoxGodina.setBackground(new Color(40, 40, 40));
		comboBoxGodina.setBounds(320, 340, 100, 30);
		comboBoxGodina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Datum.osvezi(comboBoxDan, comboBoxMesec.getSelectedItem().toString(), Integer.parseInt(comboBoxGodina.getSelectedItem().toString()));
			}
		});
		int trenutnaGodina = Calendar.getInstance().get(Calendar.YEAR);
		for(int i = trenutnaGodina; i <= trenutnaGodina + 100; i++)
			comboBoxGodina.addItem(i);
		components.add(comboBoxGodina);
		//=======================GLAVNI DEO=======================\\
		
		JButton dugmePotvrdi = new Button("Dodaj", Ikonice.DODAJ, 210, 380, 100, 30);
		dugmePotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Zadatak zadatak = new Zadatak(Glavna.baza.maxIDZadataka() + 1, textNaslov.getText(), textOpis.getText(), Datum.uString(comboBoxDan, comboBoxMesec, comboBoxGodina));
					Glavna.baza.unesiZadatak(zadatak, korisnik);
					
					GUIGlavna.get().osveziZadatke();
					GUIGlavna.get().getDugmeDodaj().setEnabled(true);
					
					setVisible(false);
					dispose();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		components.add(dugmePotvrdi);
	}
}
