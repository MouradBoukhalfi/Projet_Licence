package pobj.motx.tme2.test;

import static org.junit.Assert.assertEquals;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme1.GrillePlaces;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;

public class Test {

	public static void main(String[] args) {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/easy.grl");

		assertEquals(5, gr.nbCol());
		assertEquals(5, gr.nbLig());

		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		gp.getContraintes();
		
		for (int i = 0; i < gp.getMotsPot().size(); i++) {
			System.out.println(gp.getMotsPot().get(i).size());
		}
	}

}
