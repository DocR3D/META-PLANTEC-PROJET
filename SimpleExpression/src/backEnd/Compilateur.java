package backEnd;

import MetaModel.Array;
import MetaModel.Attribut;
import MetaModel.Bag;
import MetaModel.Boolean;
import MetaModel.Entite;
import MetaModel.Integer;
import MetaModel.List;
import MetaModel.Modele;
import MetaModel.Real;
import MetaModel.Set;
import MetaModel.String;
import MetaModel.Type;
import MetaModel.Visitor;

public class Compilateur extends Visitor {

	java.lang.String prettyPrinterOutPut = "";
	int nbTab = 0;
	
	@Override
	public void visitInteger(Integer e) {
		prettyPrinterOutPut = prettyPrinterOutPut + " Integer;";

	}
	
	public void visitString(String e) {
		prettyPrinterOutPut = prettyPrinterOutPut + " :String;";
	}
	
	public void visitReal(Real e) {
		prettyPrinterOutPut = prettyPrinterOutPut + " :Real;";
	}
	
	public void visitBoolean(Boolean e) {
		prettyPrinterOutPut = prettyPrinterOutPut + " :Boolean;";
	}
	
	public void visitList(List e) {
		prettyPrinterOutPut = prettyPrinterOutPut  + " :List [" + e.getMin() + ":" + "] of " + e.getValeur()  ;
	}
	public void visitAttribut(Attribut e) {
		ajouterTextAvecTabAvant(e.getNom());
		e.valeur().accept(this);
	}
	
	public void visitArray(Array e) {
		prettyPrinterOutPut = prettyPrinterOutPut + " :Array;";

	}
	
	public void visitSet(Set set) {
		prettyPrinterOutPut = prettyPrinterOutPut + " :Set;";
	}
	
	public void visitBag(Bag e) {
		prettyPrinterOutPut = prettyPrinterOutPut + " :Bag;";
	}
	
	public void visitEntite(Entite e) {
		ajouterTextAvecTabAvant("entity " + e.getNom() + ";");
		nbTab++;
		for(Attribut unAttribut : e.valeur()) {
			unAttribut.accept(this);
		}
		nbTab--;
		ajouterTextAvecTabAvant("end_entity;");
	}
	
	public void visitModele(Modele e) {
		ajouterTextAvecTabAvant("model " + e.getNom() + ";");
		nbTab++;
		for(Entite uneEntite : e.valeur()) {
			uneEntite.accept(this);
		}
		nbTab--;
		ajouterTextAvecTabAvant("end_model;");

	}
	
	public void ajouterTextAvecTabAvant(java.lang.String string) {
		for(int i = 0; i < nbTab ; i++) {
			prettyPrinterOutPut = prettyPrinterOutPut + "/t";
		}
		prettyPrinterOutPut = prettyPrinterOutPut + string;
		
	}
	
}
