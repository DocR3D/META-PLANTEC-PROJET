package XMLIO2;



import java.io.File;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import MetaModel.Modele;
import MetaModel.NamedElement;

class XMLAnalyserTest {

	@Test
	void test1() {
		XMLAnalyser analyser = new XMLAnalyser();
		NamedElement exp = analyser.getRootFromFilenamed("ExempleXML01.xml");
		assert(exp instanceof Modele);
		System.out.println(((Modele)exp).valeur());


	}

	@Test
	void test3() throws ParserConfigurationException, TransformerException {
		XMLAnalyser analyser = new XMLAnalyser();
		NamedElement exp = analyser.getRootFromFilenamed("ExempleXML01.xml");
		XmlSerializer serializer = new XmlSerializer();
		exp.accept(serializer);
		Document document = serializer.result();
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		DOMSource source = new DOMSource(document);
		Result result = new StreamResult(new File("ExempleXML01-out.xml"));
		transformer.transform(source, result);
	}
	/*
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
	 */

}