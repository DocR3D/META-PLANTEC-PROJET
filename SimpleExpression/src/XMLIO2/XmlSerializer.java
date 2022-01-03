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
import MetaModel.Entite;
import MetaModel.Modele;
import MetaModel.NamedElement;
import MetaModel.Type;
import MetaModel.Visitor;
import SLMetaModel.AddExp;
import SLMetaModel.BinaryExp;
import SLMetaModel.DivExp;
import SLMetaModel.Exp;
import SLMetaModel.MinusExp;
import SLMetaModel.MultExp;

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
		
		this.maybeUpdateRootFrom(elem);
		this.root.appendChild(elem);
		
		attr = doc.createAttribute("id");
		attr.setValue(e.getId()+"");
		elem.setAttributeNode(attr);
		

	}

	@Override
	public void visitModele(Modele e) {
		super.visitModele(e);
		Element elem = this.doc.createElement("model");
		addIdAndName(e, elem);
		
		
		String entities = "";
		for(Type uneEntite : e.valeur()) {
			entities += uneEntite.getId();
			uneEntite.accept(this);
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
		for(Type unType : e.valeur()) {
			lesTypes += unType.getId();
			unType.accept(this);
		}

	}

	@Override
	public void visitAttribut(Attribut e) {
		super.visitAttribut(e);
		Element elem = this.doc.createElement("attribut");
		addIdAndName(e, elem);

	}

	@Override
	public void visitMultExp(MultExp e) {
		super.visitMultExp(e);
		Element elem = this.doc.createElement("MultExp");
		this.handleBinaryExp(elem, e);
	}

	@Override
	public void visitDivExp(DivExp e) {
		super.visitDivExp(e);
		Element elem = this.doc.createElement("DivExp");
		this.handleBinaryExp(elem, e);
	}

}
