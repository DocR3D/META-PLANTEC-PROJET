package XMLIO;

import java.util.ArrayDeque;
import java.util.Deque;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import SLMetaModel.AddExp;
import SLMetaModel.BinaryExp;
import SLMetaModel.DivExp;
import SLMetaModel.Exp;
import SLMetaModel.IntExp;
import SLMetaModel.MinusExp;
import SLMetaModel.MultExp;
import SLMetaModel.Visitor;

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
	
	private void addIdToElement(Element e) {
		this.counter++;
		Attr attr = this.doc.createAttribute("id");
		attr.setValue(this.counter.toString());
		e.setAttributeNode(attr);
	}
	
	private void maybeUpdateRootFrom(Element e) {
		String rootId = this.root.getAttribute("root");
		if (rootId.isEmpty()) {
			Attr attr = this.doc.createAttribute("root");
			attr.setValue(this.counter.toString());
			this.root.setAttributeNode(attr);
		}
	}

	@Override
	public void visitIntExp(IntExp e) {
		super.visitIntExp(e);
		Element elem = this.doc.createElement("IntExp");
		this.addIdToElement(elem);
		this.maybeUpdateRootFrom(elem);
		this.root.appendChild(elem);
		Attr attr = doc.createAttribute("val");
		attr.setValue(e.getVal().toString());
		elem.setAttributeNode(attr);
		
		stack.add(elem);
	}
	
	private void handleBinaryExp(Element elem, BinaryExp exp) {
		this.addIdToElement(elem);
		this.maybeUpdateRootFrom(elem);
		this.root.appendChild(elem);
		Exp g = exp.getOpg();
		Exp d = exp.getOpd();
		g.accept(this);
		d.accept(this);
		Element ed = this.stack.remove();
		Element eg = this.stack.remove();
		Attr attr;
		attr = doc.createAttribute("opg");
		attr.setValue(eg.getAttribute("id"));
		elem.setAttributeNode(attr);
		attr = doc.createAttribute("opd");
		attr.setValue(ed.getAttribute("id"));
		elem.setAttributeNode(attr);
		stack.add(elem);
	}

	@Override
	public void visitAddExp(AddExp e) {
		super.visitAddExp(e);
		Element elem = this.doc.createElement("AddExp");
		this.handleBinaryExp(elem, e);
	}

	@Override
	public void visitMinusExp(MinusExp e) {
		super.visitMinusExp(e);
		Element elem = this.doc.createElement("MinusExp");
		this.handleBinaryExp(elem, e);
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
