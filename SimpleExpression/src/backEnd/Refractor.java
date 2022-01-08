package backEnd;

import MetaModel.Attribut;
import MetaModel.Collection;
import MetaModel.Entite;
import MetaModel.Modele;
import MetaModel.NamedElement;
import MetaModel.Visitor;

public class Refractor extends Visitor {

	String prettyPrinterOutPut = "";
	int nbTab = 0;
	String ancienNomAChanger;
	String nouveauNomAChanger;


	public Refractor(String ancienNomAChanger, String nouveauNomAChanger) {
		super();
		this.ancienNomAChanger = ancienNomAChanger;
		this.nouveauNomAChanger = nouveauNomAChanger;
	}

	public void visitAttribut(Attribut e) {
		if(e.getNom().equals(this.ancienNomAChanger)) e.setNom(this.nouveauNomAChanger);
	}

	public void visitCollection(Collection e) {
		if(e.getNom().equals(this.ancienNomAChanger)) e.setNom(this.nouveauNomAChanger);
	}



	public void visitEntite(Entite e) {
		if(e.getNom().equals(this.ancienNomAChanger)) e.setNom(this.nouveauNomAChanger);
		else for(NamedElement unType : e.valeur()) {
			unType.accept(this);
		}
	}

	public void visitModele(Modele e) {
		if(e.getNom().equals(this.ancienNomAChanger)) e.setNom(this.nouveauNomAChanger);
		else for(NamedElement unType : e.valeur()) {
			unType.accept(this);
		}
	}
}
