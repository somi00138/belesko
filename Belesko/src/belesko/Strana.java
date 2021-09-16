package belesko;

import java.util.ArrayList;

public class Strana {

	private ArrayList<Zadatak> zadaci = new ArrayList<Zadatak>();
	private int id;
	private String datum;

	public Strana(int ID, String datum) {
		this.id = ID;
		this.datum = datum;
	}

	public int brZadataka() {
		return zadaci.size();
	}

	public ArrayList<Zadatak> getZadaci() {
		return zadaci;
	}

	public void setZadaci(ArrayList<Zadatak> zadaci) {
		this.zadaci = zadaci;
	}

	public int getId() {
		return id;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	@Override
	public String toString() {
		return "Strana [ID=" + id + ", datum=" + datum + "]";
	}

}
