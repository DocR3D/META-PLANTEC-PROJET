package XMLIO;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import SLMetaModel.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class XMLAnalyser {

	protected Map<String, Element> elementIndex;
	protected Map<String, Exp> expIndex;

	public XMLAnalyser() {
		this.elementIndex = new HashMap<String, Element>();
		this.expIndex = new HashMap<String, Exp>();
	}

	protected BinaryExp binaryExpFromElement(Element e) {
		String tn = e.getTagName();
		Exp opg = expFromElement(this.elementIndex.get(e.getAttribute("opg")));
		Exp opd = expFromElement(this.elementIndex.get(e.getAttribute("opd")));
		BinaryExp result = null;
		try {
			Class<?> cls = Class.forName("SLMetaModel."+tn);
			result = (BinaryExp) cls.getDeclaredConstructor(Exp.class, Exp.class).newInstance(opg, opd);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}

		return result;
	}
	
	protected IntExp intExpFromElement(Element e) {
		Integer val = Integer.parseInt(e.getAttribute("val"));
		return new IntExp(val);
	}

	protected Exp expFromElement(Element e) {
		String id = e.getAttribute("id");
		Exp result = this.expIndex.get(id);
		if (result != null) return result;
		String tag = e.getTagName();
		if (tag.equals("IntExp")) {
			result = intExpFromElement(e);
		} else  {
			result = binaryExpFromElement(e);
		} 
		this.expIndex.put(id, result);
		return result;
	}

	protected void firstRound(Element el) {
		NodeList nodes = el.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (n instanceof Element) {
				Element child = (Element) n;
				String id = child.getAttribute("id");
				this.elementIndex.put(id, child);
			}
		}
	}
	protected void secondRound(Element el) {
		NodeList nodes = el.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (n instanceof Element) {
				expFromElement((Element)n);
			}
		}
	}

	public Exp getStartExpFromDocument(Document document) {
		Element e = document.getDocumentElement();
		firstRound(e);
		secondRound(e);
		return this.expIndex.get(e.getAttribute("start"));
	}
	
	public Exp getRootFromInputStream(InputStream stream) {
		try {
			// création d'une fabrique de documents
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

			// création d'un constructeur de documents
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			Document document = constructeur.parse(stream);
			return getStartExpFromDocument(document);

		} catch (ParserConfigurationException pce) {
			System.out.println("Erreur de configuration du parseur DOM");
			System.out.println("lors de l'appel à fabrique.newDocumentBuilder();");
		} catch (SAXException se) {
			System.out.println("Erreur lors du parsing du document");
			System.out.println("lors de l'appel à construteur.parse(xml)");
		} catch (IOException ioe) {
			System.out.println("Erreur d'entrée/sortie");
			System.out.println("lors de l'appel à construteur.parse(xml)");
		}
		return null;
	}
	
	public Exp getRootFromString(String contents) {		
		InputStream stream = new ByteArrayInputStream(contents.getBytes());
		return getRootFromInputStream(stream);
	}
	
	public Exp getRootFromFile(File file) {		
		InputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getRootFromInputStream(stream);
	}

	public Exp getRootFromFilenamed(String filename) {
			File file = new File(filename);
			return getRootFromFile(file);
	}
}
