package test;

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

import MetaModel.NamedElement;
import XMLIO2.XMLAnalyser;
import XMLIO2.XMLSerializer;

class XMLSerializerTest {
	
	@Test
	void serializer_no_collection() throws ParserConfigurationException, TransformerException {
		XMLAnalyser analyser = new XMLAnalyser();
		NamedElement exp = analyser.getRootFromFilenamed("ExempleXML01.xml");
		XMLSerializer serializer = new XMLSerializer();
		exp.accept(serializer);
		Document document = serializer.result();
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		DOMSource source = new DOMSource(document);
		Result result = new StreamResult(new File("ExempleXML01-out.xml"));
		transformer.transform(source, result);
	}
	
	@Test
	void serializer_collection() throws ParserConfigurationException, TransformerException {
		XMLAnalyser analyser = new XMLAnalyser();
		NamedElement exp = analyser.getRootFromFilenamed("ExempleXML02.xml");
		XMLSerializer serializer = new XMLSerializer();
		exp.accept(serializer);
		Document document = serializer.result();
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		DOMSource source = new DOMSource(document);
		Result result = new StreamResult(new File("ExempleXML02-out.xml"));
		transformer.transform(source, result);
	}
	/**
	@Test
	void test3() throws ParserConfigurationException, TransformerException {
		XMLAnalyser analyser = new XMLAnalyser();
		NamedElement exp = analyser.getRootFromFilenamed("ExempleXML02.xml");
		XMLSerializer serializer = new XMLSerializer();
		exp.accept(serializer);
		Document document = serializer.result();
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		DOMSource source = new DOMSource(document);
		Result result = new StreamResult(new File("ExempleXML02.xml"));
		transformer.transform(source, result);
	}
	@Test
	void test4() throws ParserConfigurationException, TransformerException {
		XMLAnalyser analyser = new XMLAnalyser();
		NamedElement exp = analyser.getRootFromFilenamed("ExempleXML02.xml");
		XMLSerializer serializer = new XMLSerializer();
		exp.accept(serializer);
		Document document = serializer.result();
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		DOMSource source = new DOMSource(document);
		Result result = new StreamResult(new File("ExempleXML02.xml"));
		transformer.transform(source, result);
	}
	**/

}
