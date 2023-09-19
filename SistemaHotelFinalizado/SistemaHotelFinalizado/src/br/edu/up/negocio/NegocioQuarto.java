package br.edu.up.negocio;

import br.edu.up.entidades.Quarto;

public class NegocioQuarto {

	public static boolean checaQuarto(Quarto quarto){
		if(quarto.isStatusQuarto()== true) {
			return true;
		}
		return false;
	}
}
