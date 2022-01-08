package backEnd;

import java.util.List;
import java.util.Set;

import MetaModel.Attribut;
import MetaModel.Collection;
import MetaModel.Entite;
import MetaModel.Modele;
import MetaModel.NamedElement;
import MetaModel.Type;
import MetaModel.Visitor;

public class Compilateur extends Visitor {

	java.lang.String prettyPrinterOutPut = "";
	int nbTab = 0;


	public void visitAttribut(Attribut e) {
		ajouterTextAvecTabAvant(e.getNom() + ":" + e.valeur()+";\n");
	}

	public void visitCollection(Collection e) {

		if(e.getMin() == 0) // C'est un tableau
			ajouterTextAvecTabAvant(e.getNom() + ": Array [" + e.getMax() +"] of " + e.valeur() + "\n");
		else
			ajouterTextAvecTabAvant(e.getNom() + ": List [" + e.getMin() +":"+ e.getMax() +"] of " + e.valeur()+"\n");
	}



	public void visitEntite(Entite e) {
		ajouterTextAvecTabAvant("entity " + e.getNom() + ";\n");
		nbTab++;
		for(NamedElement unType : e.valeur()) {
			unType.accept(this);

		}
		nbTab--;
		ajouterTextAvecTabAvant("end_entity;\n");
	}

	public void visitModele(Modele e) {
		ajouterTextAvecTabAvant("model " + e.getNom() + ";\n");
		nbTab++;
		for(NamedElement unType : e.valeur()) {
			unType.accept(this);
		}
		nbTab--;
		ajouterTextAvecTabAvant("end_model;");
	}

	public void ajouterTextAvecTabAvant(java.lang.String string) {
		for(int i = 0; i < nbTab ; i++) {
			prettyPrinterOutPut = prettyPrinterOutPut + "  ";
		}
		prettyPrinterOutPut = prettyPrinterOutPut + string;

	}

	public void afficher() {
		System.out.println(prettyPrinterOutPut);
	}
}
