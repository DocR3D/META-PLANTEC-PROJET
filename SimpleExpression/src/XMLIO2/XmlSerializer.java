package XMLIO2;

import java.util.ArrayDeque;
import java.util.Deque;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import MetaModel.Attribut;
import MetaModel.Collection;
import MetaModel.Entite;
import MetaModel.Modele;
import MetaModel.NamedElement;
import MetaModel.Type;
import MetaModel.Visitor;
import SLMetaModel.DivExp;

public class XmlSerializer extends Visitor {
	Deque<Element> stack;
	Element root = null;
	Integer counter;
	Document doc;
	
	Document result() {
		return this.doc;
	}
	
	public XmlSerializer() throws ParserConfigurationException {
		this.stack = new ArrayDeque<>();
		this.counter = 0;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		this.doc = builder.newDocument();
		root = this.doc.createElement("Root");
		this.doc.appendChild(root);
	}
	
	private void maybeUpdateRootFrom(Element e) {
		String rootId = this.root.getAttribute("root");
		if (rootId.isEmpty()) {
			Attr attr = this.doc.createAttribute("root");
			attr.setValue(this.counter.toString());
			this.root.setAttributeNode(attr);
		}
	}
	
	public void addIdAndName(NamedElement e,  Element elem) {

		Attr attr = doc.createAttribute("name");
		attr.setValue(e.getNom().toString());
		elem.setAttributeNode(attr);
	

		Attr attr2 = doc.createAttribute("id");
		attr2.setValue(e.getId()+"");
		elem.setAttributeNode(attr2);
		
		this.maybeUpdateRootFrom(elem);
		this.root.appendChild(elem);

		

	}

	@Override
	public void visitModele(Modele e) {
		super.visitModele(e);
		Element elem = this.doc.createElement("model");
		addIdAndName(e, elem);
		
		
		String entities = "";
		for(NamedElement uneEntite : e.valeur()) {
			if(uneEntite != null) { //TODO C'est normal cette verification ?
				entities += " " + uneEntite.getId();
				uneEntite.accept(this);
			}
		}
		Attr attr = doc.createAttribute("entities");
		attr.setValue(entities);
		elem.setAttributeNode(attr);
		
		stack.add(elem);
	}
	

	@Override
	public void visitEntite(Entite e) {
		super.visitEntite(e);
		Element elem = this.doc.createElement("entity");
		
		addIdAndName(e, elem);
		String types = "";
		for(NamedElement unType : e.valeur()) {
			types += unType.getId();
			unType.accept(this);
		}

	}

	@Override
	public void visitAttribut(Attribut e) {
		super.visitAttribut(e);
		Element elem = this.doc.createElement("attribut");
		addIdAndName(e, elem);
		
		Attr attr = doc.createAttribute("type");
		attr.setValue(e.valeur());
		elem.setAttributeNode(attr);

	}

	@Override
	public void visitCollection(Collection e) {
		super.visitCollection(e);
		Element elem = this.doc.createElement("collection");
		addIdAndName(e, elem);


		if(e.getMin() != 0) {
			Attr attr = doc.createAttribute("min");
			attr.setValue(e.getMin()+"");
			elem.setAttributeNode(attr);
		}
		
		Attr attr = doc.createAttribute("max");
		attr.setValue(e.getMin()+"");
		elem.setAttributeNode(attr);
		
		attr = doc.createAttribute("type");
		attr.setValue(e.getNom());
		elem.setAttributeNode(attr);
		
	}

}
