package backEnd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import SLMetaModel.Exp;
import SLMetaModel.IntExp;
import XMLIO.XMLAnalyser;

class CalculatorTest {

	@Test
	void test1() {
		XMLAnalyser analyser = new XMLAnalyser();
		Exp exp = analyser.getRootFromFilenamed("Exemple1.xml");
		Calculator calc = new Calculator();
		exp.accept(calc);
		assertTrue(((IntExp)calc.result()).getVal() == 2);
	}
	@Test
	void test2() {
		XMLAnalyser analyser = new XMLAnalyser();
		Exp exp = analyser.getRootFromFilenamed("Exemple2.xml");
		Calculator calc = new Calculator();
		exp.accept(calc);
		assertTrue(((IntExp)calc.result()).getVal() == 5);
	}
	@Test
	void test3() {
		IntExp exp = new IntExp(3);
		Calculator calc = new Calculator();
		exp.accept(calc);
		assertTrue(((IntExp)calc.result()).getVal() == 3);
	}

}
