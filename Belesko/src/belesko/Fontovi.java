package belesko;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

public class Fontovi {
	public static Font TITILLIUM_REGULAR;
	public static Font TITILLIUM_BOLD;

	public static void ucitaj() {
		try {
			TITILLIUM_REGULAR = Font.createFont(Font.TRUETYPE_FONT, Glavna.class.getResourceAsStream("/TitilliumWeb-Regular.ttf"));
			TITILLIUM_BOLD = Font.createFont(Font.TRUETYPE_FONT, Glavna.class.getResourceAsStream("/TitilliumWeb-Bold.ttf"));
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			
			ge.registerFont(TITILLIUM_REGULAR);
			ge.registerFont(TITILLIUM_BOLD);
			
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
