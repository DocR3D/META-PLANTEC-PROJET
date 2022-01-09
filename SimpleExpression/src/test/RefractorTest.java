package test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MetaModel.Collection;
import MetaModel.Entite;
import MetaModel.Modele;
import XMLIO.XMLAnalyser;
import backEnd.Refractor;

public class RefractorTest {
	
	private XMLAnalyser analyser;
	private Modele modele;
	
	@BeforeEach
	public void setup() {
		analyser = new XMLAnalyser();
	}
	
	@AfterEach
	public void TearDown(){
		analyser = null;
		modele = null;
	}

	@Test
	void rename_no_collection() {
		modele = (Modele) analyser.getRootFromFilenamed("ExempleXML01.xml");
		
		assertTrue(modele.getNom().equals("Flotte"));
		Refractor renommage = new Refractor("Flotte", "SuperFlotte");
		modele.accept(renommage);
		assertTrue(modele.getNom().equals("SuperFlotte") );
	}

	@Test
	void rename_collection() {
		modele = (Modele) analyser.getRootFromFilenamed("ExempleXML02.xml");
		Entite entite = (Entite) modele.valeur().get(0);
		assertTrue(entite instanceof Entite);
		Collection collection= (Collection) entite.valeur().get(4);
		
		assertTrue(collection.getNom().equals("satellitees"));
		Refractor renommage = new Refractor("satellitees", "satelliteesSuper");
		modele.accept(renommage);
		assertTrue(collection.getNom().equals("satelliteesSuper") );
	}

}