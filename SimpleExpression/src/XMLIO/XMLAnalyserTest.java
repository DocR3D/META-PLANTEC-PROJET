package XMLIO;



import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import SLMetaModel.AddExp;
import SLMetaModel.Exp;
import SLMetaModel.IntExp;

class XMLAnalyserTest {

	@Test
	void test1() {
		XMLAnalyser analyser = new XMLAnalyser();
		Exp exp = analyser.getRootFromFilenamed("Exemple1.xml");
		assertTrue(exp instanceof IntExp);
		assertTrue(((IntExp)exp).getVal() == 2);
	}
	@Test
	void test2() {
		XMLAnalyser analyser = new XMLAnalyser();
		Exp exp = analyser.getRootFromFilenamed("Exemple2.xml");
		assertTrue(exp instanceof AddExp);
		AddExp result = (AddExp) exp;
		assertTrue(result.getOpg() instanceof IntExp);
		assertTrue(((IntExp) result.getOpg()).getVal() == 3);
		assertTrue(result.getOpd() instanceof IntExp);
		assertTrue(((IntExp) result.getOpd()).getVal() == 2);
	}
	
	@Test
	void test3() {
		String src = "<Root start=\"3\"> <IntExp id=\"3\" val=\"20\"/> </Root>";
		XMLAnalyser analyser = new XMLAnalyser();
		Exp exp = analyser.getRootFromString(src);
		assertTrue(exp instanceof IntExp);
		assertTrue(((IntExp)exp).getVal() == 20);
	}

}