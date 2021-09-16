package belesko;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Ikonice {

	public static ImageIcon DODAJ, PRETHODNA, SLEDECA, IZADJI, OBRISI, IZMENI, MINIMIZUJ, NALOG, PRIKAZI_SIFRU;

	public static void ucitaj() {
		try {
			DODAJ = new ImageIcon(ImageIO.read(Glavna.class.getResourceAsStream("/Dodaj.png")).getScaledInstance(18, 18, Image.SCALE_SMOOTH));
			IZADJI = new ImageIcon(ImageIO.read(Glavna.class.getResourceAsStream("/Izadji.png")).getScaledInstance(18, 18, Image.SCALE_SMOOTH));
			IZMENI = new ImageIcon(ImageIO.read(Glavna.class.getResourceAsStream("/Izmeni.png")).getScaledInstance(18, 18, Image.SCALE_SMOOTH));
			MINIMIZUJ = new ImageIcon(ImageIO.read(Glavna.class.getResourceAsStream("/Minimizuj.png")).getScaledInstance(18, 18, Image.SCALE_SMOOTH));
			NALOG = new ImageIcon(ImageIO.read(Glavna.class.getResourceAsStream("/Nalog.png")).getScaledInstance(18, 18, Image.SCALE_SMOOTH));
			OBRISI = new ImageIcon(ImageIO.read(Glavna.class.getResourceAsStream("/Obrisi.png")).getScaledInstance(18, 18, Image.SCALE_SMOOTH));
			PRETHODNA = new ImageIcon(ImageIO.read(Glavna.class.getResourceAsStream("/Prethodna.png")).getScaledInstance(18, 18, Image.SCALE_SMOOTH));
			PRIKAZI_SIFRU = new ImageIcon(ImageIO.read(Glavna.class.getResourceAsStream("/Prikazi.png")).getScaledInstance(18, 18, Image.SCALE_SMOOTH));
			SLEDECA = new ImageIcon(ImageIO.read(Glavna.class.getResourceAsStream("/Sledeca.png")).getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}
}
