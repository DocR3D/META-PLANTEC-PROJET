package XMLIO;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import MetaModel.Attribut;
import MetaModel.Collection;
import MetaModel.Entite;
import MetaModel.Modele;
import MetaModel.NamedElement;
import MetaModel.StaticArray;

public class XMLAnalyser {

	protected Map<String, Element> elementIndex;
	protected Map<String, NamedElement> namedElementIndex;
	protected Map<String, ArrayList<Integer>> childsOfElements;

	public XMLAnalyser() {
		this.elementIndex = new HashMap<String, Element>();
		this.namedElementIndex = new HashMap<String, NamedElement>();
		this.childsOfElements = new HashMap<String, ArrayList<Integer>>();
	}

	protected Entite entityFromElement(Element e) {
		String name = e.getAttribute("name");
		Integer id = Integer.parseInt(e.getAttribute("id"));
		String lesAttributs = e.getAttribute("attributes");
		ArrayList<Integer> lesId = new ArrayList<Integer>();
		for(String unNombre : lesAttributs.split(" ")) {
			if(unNombre != "")
				lesId.add(Integer.parseInt(unNombre));
		}
		childsOfElements.put(id+"", lesId);

		return new Entite(name,id);
	}

	protected Modele modeleFromElement(Element e) {
		String name = e.getAttribute("name");
		Integer id = Integer.parseInt(e.getAttribute("id"));

		String lesEntites = e.getAttribute("entities");
		ArrayList<Integer> lesId = new ArrayList<Integer>();
		for(String unNombre : lesEntites.split(" ")) {
			lesId.add(Integer.parseInt(unNombre));
		}
		childsOfElements.put(id+"", lesId);

		return new Modele(name,id);
	}

	protected Attribut attributeFromElement(Element e) {
		String name = e.getAttribute("name");
		Integer id = Integer.parseInt(e.getAttribute("id"));
		String typeName = e.getAttribute("type");

		NamedElement unAttribut;
		if(this.namedElementIndex.get(typeName) == null) {
			unAttribut = new Attribut(null,typeName, -1);
		}else {
			unAttribut = this.namedElementIndex.get(typeName);
		}
		return new Attribut(unAttribut,name,id);
	}

	protected Collection collectionFromElement(Element e) {
		String name = e.getAttribute("name");
		String typeName = e.getAttribute("type");

		NamedElement type;
		if(this.namedElementIndex.get(typeName) == null)
			type = new Entite(typeName, -1);
		else type = this.namedElementIndex.get(typeName);		

		Integer id = Integer.parseInt(e.getAttribute("id"));
		Integer max = Integer.parseInt(e.getAttribute("max"));
		Integer min;
		if(!e.getAttribute("min").equals("")) {
			String typeCollectionName = e.getAttribute("typeCollectionName");
			min = Integer.parseInt(e.getAttribute("min"));
			return new Collection(type,name,id,min,max,typeCollectionName);
		}else return new StaticArray(type,name,id,max);
	}

	protected NamedElement NamedElementFromElement(Element e) {
		String id = e.getAttribute("id");
		NamedElement result = this.namedElementIndex.get(id);
		if (result != null) return result;
		String tag = e.getTagName();
		if (tag.equals("model")) {
			result = modeleFromElement(e);
		} else if(tag.equals("entity")){
			result = entityFromElement(e);
		} else if(tag.equals("attribute")) {
			result = attributeFromElement(e);
		}else if(tag.equals("collection")) {
			result = collectionFromElement(e);
		}
		this.namedElementIndex.put(id+"", result);
		return result;
	}

	protected void AddChildsToElement(NamedElement e) {
		if (e instanceof Modele && this.childsOfElements.get(e.getId()+"") != null) {
			for(Integer unNombre : this.childsOfElements.get(e.getId()+"")) {
				((Modele)e).addType(this.namedElementIndex.get(unNombre+""));
			}
		} else if(e instanceof Entite && this.childsOfElements.get(e.getId()+"") != null){
			for(Integer unNombre : this.childsOfElements.get(e.getId()+"")) {
				((Entite) e).addType((Attribut) this.namedElementIndex.get(unNombre+""));
			}
		}
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
				NamedElementFromElement((Element)n);
			}
		}
	}

	protected void thirdRound() {
		for(NamedElement unElement : namedElementIndex.values()) {
			AddChildsToElement(unElement);
			replaceFakeElement(unElement);
		}
	}


	private void replaceFakeElement(NamedElement unElement) {
		if(unElement instanceof Collection && !(unElement instanceof Attribut)) {
			Collection uneCollection = (Collection) unElement;
			for(NamedElement unAttributQueJeCherche : namedElementIndex.values()) {
				if(unAttributQueJeCherche instanceof Entite)
					if(unAttributQueJeCherche.getNom().equals(uneCollection.getType().getNom())) {
						uneCollection.setType(namedElementIndex.get(uneCollection.getType().getNom()));
					}
			}
		}else 
			if(unElement instanceof Attribut && !(unElement instanceof Collection)){
				Attribut unAttribut = (Attribut) unElement;
				for(NamedElement unAttributQueJeCherche : namedElementIndex.values()) {
					if(unAttributQueJeCherche instanceof Attribut && unAttributQueJeCherche.getNom().equals(unAttribut.getType().getNom())) {
						unAttribut.setType(namedElementIndex.get(unAttribut.getType().getNom()));
					}
				}
			}

	}

	public NamedElement getStartExpFromDocument(Document document) {
		Element e = document.getDocumentElement();
		firstRound(e);
		secondRound(e);
		thirdRound();
		return this.namedElementIndex.get("1"); // TODO : Recuperer proprement l'id
	}

	public NamedElement getRootFromInputStream(InputStream stream) {
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

	public NamedElement getRootFromString(String contents) {		
		InputStream stream = new ByteArrayInputStream(contents.getBytes());
		return getRootFromInputStream(stream);
	}

	public NamedElement getRootFromFile(File file) {		
		InputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getRootFromInputStream(stream);
	}

	public NamedElement getRootFromFilenamed(String filename) {
		File file = new File(filename);
		return getRootFromFile(file);
	}
}
