package backEnd;

import org.junit.jupiter.api.Test;

import MetaModel.Modele;
import XMLIO2.XMLAnalyser;

public class CompilateurTest {

	@Test
	void test1() {
		XMLAnalyser analyser = new XMLAnalyser();
		Modele exp = (Modele) analyser.getRootFromFilenamed("ExempleXML01.xml");
		Compilateur calc = new Compilateur();
		exp.accept(calc);
		calc.afficher();
		//assertTrue(((IntExp)calc.result()).getVal() == 2);
	}

	@Test
	void test2() {
		XMLAnalyser analyser = new XMLAnalyser();
		Modele exp = (Modele) analyser.getRootFromFilenamed("ExempleXML02.xml");
		Compilateur calc = new Compilateur();
		exp.accept(calc);
		calc.afficher();
		//assertTrue(((IntExp)calc.result()).getVal() == 2);
	}

}
