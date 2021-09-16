package belesko;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BazaPodataka {
	
	private File fajlBaza;
	private Connection konekcija;
	
	public BazaPodataka(String naziv) throws IOException, SQLException {
		fajlBaza = Paths.get(System.getProperty("user.dir"), naziv + ".db").toFile();
		povezivanjeSaBazom();
		pravljenjeTabela();
	}
	
	private void povezivanjeSaBazom() throws IOException{
		boolean postoji = fajlBaza.exists();
		
		if (!postoji)
			fajlBaza.createNewFile();
		try {
			konekcija = DriverManager.getConnection("jdbc:sqlite:" + fajlBaza.getAbsolutePath());	
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	private void pravljenjeTabela() throws SQLException {
		String tabelaKorisnici = "CREATE TABLE IF NOT EXISTS Korisnici (" 
				+ "KorisnikID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
				+ "KorisnickoIme NVARCHAR(50) NOT NULL," 
				+ "Sifra NVARCHAR(50) NOT NULL,"
				+ "Ime NVARCHAR(50) NOT NULL," 
				+ "Prezime NVARCHAR(50) NOT NULL"
				+ ");";
		
		String tabelaStrane = "CREATE TABLE IF NOT EXISTS Strane ("
				+ "StranaID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
				+ "Datum DATE"
				+ ");";
		
		String tabelaZadaci = "CREATE TABLE IF NOT EXISTS Zadaci ("
				+ "ZadatakID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
				+ "Naslov NVARCHAR(50),"
				+ "Opis NVARCHAR(200),"
				+ "Datum DATE,"
				+ "StranaID INTEGER NOT NULL,"
				+ "KorisnikID INTEGER NOT NULL,"
				+ "FOREIGN KEY(StranaID) REFERENCES Strane(StranaID),"
				+ "FOREIGN KEY(KorisnikID) REFERENCES Korisnici(KorisnikID)"
				+ ");";
		
		Statement stmt = konekcija.createStatement();
		stmt.execute(tabelaKorisnici);
		stmt.execute(tabelaStrane);
		stmt.execute(tabelaZadaci);
		
        stmt.close();
		
		System.out.println("Uspesno napravljene tabele!");	
	}
	
	
	
	//=======================STRANE=======================\\
	public ArrayList<Strana> vratiStrane(Korisnik korisnik) throws SQLException {
		String upit = "SELECT * FROM Strane;";
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		ResultSet rs = stmt.executeQuery(upit);
		ArrayList<Strana> lista = new ArrayList<Strana>();
		ArrayList<Strana> listaGlavna = new ArrayList<Strana>();
		
		while (rs.next())
			lista.add(new Strana(rs.getInt("StranaID"), rs.getString("Datum")));
		
		for(Strana strana : lista) {
			boolean imaZadatak = false;
			ArrayList<Zadatak> zadaci = vratiZadatke(strana, korisnik);
			
			for(Zadatak zadatak : zadaci) {
				if (zadatak.getkorisnikId() == korisnik.getId()) {
					imaZadatak = true;
					break;
				}
			}
			
			if (imaZadatak)
				listaGlavna.add(strana);
		}
		
		rs.close();
		stmt.close();
		
		return listaGlavna;
	}
	
	public int vratiStranaID(String datum) throws SQLException {
		String upit = "SELECT StranaID FROM Strane WHERE Datum = '" + datum + "';";
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		ResultSet rs = stmt.executeQuery(upit);
		
		if (!rs.isBeforeFirst()) {
			unesiStranu(new Strana(maxIDStrana() + 1, datum));
			rs = stmt.executeQuery(upit);
		}
				
		return rs.getInt("StranaID");
	}
	
	public void unesiStranu(Strana strana) throws SQLException {
		String upit = "INSERT INTO Strane(StranaID, Datum) VALUES (" + strana.getId() 
					+ ", '" + strana.getDatum() 
					+ "');";
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		stmt.execute(upit);
        stmt.close();
	}
	//=======================STRANE=======================\\
	
	
	
	//=======================KORISNICI=======================\\
	public int korisnikID(String korisnickoIme) throws SQLException {
		String upit = "SELECT KorisnikID FROM Korisnici WHERE KorisnickoIme = '" + korisnickoIme + "';";
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		ResultSet rs = stmt.executeQuery(upit);
		
		return rs.getInt("KorisnikID");
	}
	
	public boolean registracija(Korisnik korisnik) throws SQLException {
		String upit = "SELECT * FROM Korisnici WHERE KorisnickoIme = '" + korisnik.getKorisnickoIme() + "';";
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		ResultSet rs = stmt.executeQuery(upit);
        
		if (!rs.isBeforeFirst()) {
			String upitDodaj = "INSERT INTO Korisnici(KorisnikID, Ime, Prezime, KorisnickoIme, Sifra) VALUES (" + (maxIDKorisnika() + 1) 
								+ ", '" + korisnik.getIme()
								+ "', '" + korisnik.getPrezime() 
								+ "', '" + korisnik.getKorisnickoIme() 
								+ "', '" + korisnik.getLozinka()
								+ "');";
			stmt.execute(upitDodaj);
			rs.close();
	        stmt.close();
			return true;
		}
		
		return false;
	}
	
	public boolean autentifikacija(Korisnik korisnik) throws SQLException {
		String upit = "SELECT * FROM Korisnici WHERE KorisnickoIme = '" + korisnik.getKorisnickoIme() + "' AND Sifra = '" + korisnik.getLozinka() + "';";
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		ResultSet rs = stmt.executeQuery(upit);
        
		if (!rs.isBeforeFirst())
			return false;
		
		korisnik.setId(rs.getInt("KorisnikID"));
		korisnik.setIme(rs.getString("Ime"));
		korisnik.setPrezime(rs.getString("Prezime"));
		return true;
	}
	//=======================KORISNICI=======================\\
	
	
	
	//=======================ZADACI=======================\\
	public ArrayList<Zadatak> vratiZadatke(Strana strana, Korisnik korisnik) throws SQLException {
		String upit = "SELECT ZadatakID, KorisnikID, Naslov, Opis, Datum FROM Zadaci WHERE Datum = '" + strana.getDatum() + "' AND KorisnikID = '" + korisnikID(korisnik.getKorisnickoIme()) + "';";
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		ResultSet rs = stmt.executeQuery(upit);
		ArrayList<Zadatak> lista = new ArrayList<Zadatak>();
		
		while (rs.next()) 
			lista.add(new Zadatak(rs.getInt("ZadatakID"), rs.getInt("KorisnikID"), rs.getString("Naslov"), rs.getString("Opis"), rs.getString("Datum")));
		
		rs.close();
		stmt.close();
		
		return lista;
	}
	
	public void unesiZadatak(Zadatak zadatak, Korisnik korisnik) throws SQLException {
		String upit = "INSERT INTO Zadaci(ZadatakID, StranaID, KorisnikID, Naslov, Opis, Datum) VALUES (" + zadatak.getId() 
					+ ", " + vratiStranaID(zadatak.getDatum()) 
					+ ", " + korisnikID(korisnik.getKorisnickoIme())
					+ ", '" + zadatak.getNaslov() 
					+ "', '" + zadatak.getOpis() 
					+ "', '" + zadatak.getDatum() 
					+ "');";
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		stmt.execute(upit);
		stmt.close();
	}
	
	public void izmeniZadatak(Zadatak zadatak) throws SQLException {
		int idStrane = vratiStranaID(zadatak.getDatum());
		
		String upit = "UPDATE Zadaci SET Naslov = '" + zadatak.getNaslov() 
						+ "', Opis = '" + zadatak.getOpis()
						+ "', Datum = '" + zadatak.getDatum()
						+ "', StranaID = '" + idStrane
						+ "' WHERE ZadatakID = " + zadatak.getId() 
						+ ";";
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		stmt.execute(upit);
		stmt.close();
	}
	
	public void obrisiZadatak(Zadatak zadatak) throws SQLException {
		String upit = "DELETE FROM Zadaci WHERE ZadatakID = " + zadatak.getId();
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		stmt.execute(upit);
		stmt.close();
	}
	//=======================ZADACI=======================\\
	
	
	
	//=======================ID-evi=======================\\
	public int maxIDKorisnika() throws SQLException {
		String upit = "SELECT MAX(KorisnikID) as MAX FROM Korisnici;";
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		ResultSet rs = stmt.executeQuery(upit);
		
		return rs.getInt("MAX");
	}
	
	public int maxIDStrana() throws SQLException {
		String upit = "SELECT MAX(StranaID) as MAX FROM Strane;";
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		ResultSet rs = stmt.executeQuery(upit);
		
		return rs.getInt("MAX");
	}
	
	public int maxIDZadataka() throws SQLException {
		String upit = "SELECT MAX(ZadatakID) as MAX FROM Zadaci;";
		System.out.println(upit);
		Statement stmt = konekcija.createStatement();
		ResultSet rs = stmt.executeQuery(upit);
        
		return rs.getInt("MAX");
	}
	//=======================ID-evi=======================\\
}
