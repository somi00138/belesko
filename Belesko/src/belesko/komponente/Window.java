package belesko.komponente;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import belesko.Boje;
import belesko.Fontovi;
import belesko.Glavna;
import belesko.Ikonice;

public class Window extends JFrame {
	
	protected JPanel components;
	protected int sirina = 100;
	protected int visina = 100;
	protected int kursorX, kursorY;
	protected JButton dugmeIzadji, dugmeMinimizuj;
	protected JPanel naslov;

	public Window(String naslovProzora, int sirina, int visina) {
		setTitle(naslovProzora);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Glavna.ekran.width / 2 - sirina / 2, Glavna.ekran.height / 2 - visina / 2, sirina, visina);
		
		components = new JPanel();
		components.setBackground(Boje.PROZOR);
		components.setBorder(null);
		components.setLayout(null);
		setContentPane(components);
		
		naslov = new JPanel();
		naslov.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				kursorX = e.getX();
		        kursorY = e.getY();
			}
		});
		naslov.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
		        setLocation(e.getXOnScreen() - kursorX, e.getYOnScreen() - kursorY);
			}
		});
		naslov.setBounds(0, 0, sirina, 25);
		naslov.setBackground(Boje.PROZOR_NASLOV);
		naslov.setLayout(null);
		components.add(naslov);
		
		Label labelNaslovProzora = new Label(naslovProzora, 10, 0, 250, 25);
		labelNaslovProzora.setHorizontalAlignment(SwingConstants.LEADING);
		naslov.add(labelNaslovProzora);
		
		dugmeIzadji = new Button(Ikonice.IZADJI);
		dugmeIzadji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		dugmeIzadji.setBounds(sirina - 50, 0, 50, 25);
		naslov.add(dugmeIzadji);
		
		dugmeMinimizuj = new Button(Ikonice.MINIMIZUJ);
		dugmeMinimizuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(Frame.ICONIFIED);
			}
		});
		dugmeMinimizuj.setBounds(sirina - 100, 0, 50, 25);
		naslov.add(dugmeMinimizuj);
	}
}
