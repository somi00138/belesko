package belesko;

public class Zadatak {
	
	private int id;
	private int korisnikId;
	private String naslov;
	private String opis;
	private String datum;
	
	public Zadatak(int id, String naslov, String opis, String datum) {
		super();
		this.naslov = naslov;
		this.opis = opis;
		this.datum = datum;
		this.id = id;
	}
	
	public Zadatak(int id, int korisnikId, String naslov, String opis, String datum) {
		super();
		this.naslov = naslov;
		this.opis = opis;
		this.datum = datum;
		this.id = id;
		this.korisnikId = korisnikId;
	}
	
	public int getId() {
		return id;
	}

	public int getkorisnikId() {
		return korisnikId;
	}
	
	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	@Override
	public String toString() {
		return "Zadatak [id=" + id + ", naslov=" + naslov + ", opis=" + opis + ", datum=" + datum + "]";
	}
	
}
