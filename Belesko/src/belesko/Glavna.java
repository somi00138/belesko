package belesko;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;

import belesko.gui.GUIPrijava;

public class Glavna {

	public static Dimension ekran = Toolkit.getDefaultToolkit().getScreenSize();
	public static BazaPodataka baza;
	public static GUIPrijava prijava;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					Fontovi.ucitaj();
					Ikonice.ucitaj();

					baza = new BazaPodataka("Baza");
										
					new GUIPrijava("Prijava", 300, 230).setVisible(true);
					
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}
			}
		});
	}	
}
