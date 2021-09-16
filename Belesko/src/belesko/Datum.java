package belesko;

import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Datum {
	public static String uString(JComboBox<Integer> dan, JComboBox<String> mesec, JComboBox<Integer> godina) {
		return dan.getSelectedItem().toString() + " " + mesec.getSelectedItem() + " " + godina.getSelectedItem().toString();
	}

	public static void izString(String datum, JComboBox<Integer> dan, JComboBox<String> mesec, JComboBox<Integer> godina) {
		String[] elementi = datum.split(Pattern.quote(" "));
		if (elementi.length < 3)
			JOptionPane.showMessageDialog(null, "Neispravan format datuma", "Greska", JOptionPane.ERROR_MESSAGE);

		osvezi(dan, elementi[1], Integer.parseInt(elementi[2]));

		dan.setSelectedItem(Integer.parseInt(elementi[0]));
		mesec.setSelectedItem(elementi[1]);
		godina.setSelectedItem(Integer.parseInt(elementi[2]));
	}

	public static void osvezi(JComboBox<Integer> dan, String mesec, int godina) {
		int selektovan = 1;

		if (dan != null && dan.getSelectedItem() != null)
			selektovan = Integer.parseInt(dan.getSelectedItem().toString());

		dan.removeAllItems();
		switch(mesec) {
			case "Januar":
			case "Mart":
			case "Maj":
			case "Jul": 
			case "Avgust":
			case "Oktobar":
			case "Decembar":
				for(int i = 1; i <= 31; i++)
					dan.addItem(i);
				if (selektovan <= 31)
					dan.setSelectedItem(selektovan);
				break;
	
			case "Februar": 
				if(godina % 400 == 0 || godina % 4 == 0) {
					for(int i = 1; i <= 29; i++)
						dan.addItem(i);	
					if (selektovan <= 29)
						dan.setSelectedItem(selektovan);
				} else {
					for(int i = 1; i <= 28; i++)
						dan.addItem(i);
					if (selektovan <= 28)
						dan.setSelectedItem(selektovan);
				}
				break;
	
			default: 
				for(int i = 1; i <= 30; i++)
					dan.addItem(i);
				if (selektovan <= 30)
					dan.setSelectedItem(selektovan);
		}
	}
}
