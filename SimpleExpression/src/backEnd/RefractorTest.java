package backEnd;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import MetaModel.Modele;
import XMLIO2.XMLAnalyser;

public class RefractorTest {

	@Test
	void test1() {
		XMLAnalyser analyser = new XMLAnalyser();
		Modele exp = (Modele) analyser.getRootFromFilenamed("ExempleXML01.xml");
		PrettyPrinter calc2 = new PrettyPrinter();
		exp.accept(calc2);
		calc2.afficher();
		assertTrue(exp.getNom().equals("Flotte") );
		Refractor renommage = new Refractor("Flotte", "SuperFlotte");
		exp.accept(renommage);
		PrettyPrinter calc = new PrettyPrinter();
		exp.accept(calc);
		calc.afficher();
		
		assertTrue(exp.getNom().equals("SuperFlotte") );
	}

	@Test
	void test2() {
		XMLAnalyser analyser = new XMLAnalyser();
		Modele exp = (Modele) analyser.getRootFromFilenamed("ExempleXML02.xml");
		
		
		
		PrettyPrinter calc = new PrettyPrinter();
		exp.accept(calc);
		calc.afficher();
		//assertTrue(((IntExp)calc.result()).getVal() == 2);
	}

}
