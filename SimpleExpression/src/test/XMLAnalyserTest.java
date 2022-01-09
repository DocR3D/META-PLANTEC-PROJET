package test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MetaModel.Collection;
import MetaModel.Entite;
import MetaModel.Modele;
import MetaModel.StaticArray;
import XMLIO.XMLAnalyser;

class XMLAnalyserTest {
	
	private XMLAnalyser analyser;
	Modele modele;
	
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
	void analyse_no_collection() {
		modele = (Modele) analyser.getRootFromFilenamed("ExempleXML01.xml");
		assertTrue(modele instanceof Modele);
		Entite entite = (Entite) modele.valeur().get(0);
		assertTrue(entite.getNom().equals("Satellite"));
	}
	
	@Test
	void analyse_array() {
		modele = (Modele) analyser.getRootFromFilenamed("ExempleXML02.xml");
		assertTrue(modele instanceof Modele);
		Entite entite = (Entite) modele.valeur().get(0);
		assertTrue(entite instanceof Entite);
		StaticArray array = (StaticArray) entite.valeur().get(3);
		assertTrue(array instanceof StaticArray);
		assertTrue(array.getNom().equals("satellitesSuper"));
	}
	
	@Test
	void analyse_collection() {
		modele = (Modele) analyser.getRootFromFilenamed("ExempleXML02.xml");
		assertTrue(modele instanceof Modele);
		Entite entite = (Entite) modele.valeur().get(0);
		assertTrue(entite instanceof Entite);
		Collection collection= (Collection) entite.valeur().get(4);
		assertTrue(collection.getNom().equals("satellitees"));
	}

	/**
	@Test
	void test2() {
		XMLAnalyser analyser = new XMLAnalyser();
		Modele modele = (Modele) analyser.getRootFromFilenamed("ExempleXML02.xml");
		assertTrue(modele instanceof Modele);
		Collection result = (Collection) modele.valeur().get(2);
		assertTrue(result.getOpg() instanceof IntExp);
		assertTrue(((IntExp) result.getOpg()).getVal() == 3);
		assertTrue(result.getOpd() instanceof IntExp);
		assertTrue(((IntExp) result.getOpd()).getVal() == 2);
	}
	/**
	@Test
	void test3() {
		String src = "<Root start=\"3\"> <IntExp id=\"3\" val=\"20\"/> </Root>";
		XMLAnalyser analyser = new XMLAnalyser();
		Exp exp = analyser.getRootFromString(src);
		assertTrue(exp instanceof IntExp);
		assertTrue(((IntExp)exp).getVal() == 20);
	}
	 */
}