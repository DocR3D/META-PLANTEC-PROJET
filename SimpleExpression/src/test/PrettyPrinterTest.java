package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MetaModel.Modele;
import XMLIO.XMLAnalyser;
import backEnd.PrettyPrinter;

public class PrettyPrinterTest {
	
	private XMLAnalyser analyser;
	private PrettyPrinter pPrinter;
	private Modele modele;
	
	@BeforeEach
	public void setup() {
		analyser = new XMLAnalyser();
		pPrinter = new PrettyPrinter();
	}
	
	@AfterEach
	public void TearDown(){
		analyser = null;
		pPrinter = null;
		modele = null;
	}
	
	@Test
	void show_no_collection() {
		modele = (Modele) analyser.getRootFromFilenamed("ExempleXML01.xml");
		modele.accept(pPrinter);
		pPrinter.afficher();
	}

	@Test
	void show_collection() {
		XMLAnalyser analyser = new XMLAnalyser();
		modele = (Modele) analyser.getRootFromFilenamed("ExempleXML02.xml");
		modele.accept(pPrinter);
		pPrinter.afficher();
	}
}
