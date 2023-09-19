package br.edu.up.negocio;
import br.edu.up.entidades.Reserva;

public class PagamentoNegocio {
	public static float calcularSubTotal(Reserva reservaTotal) {
		return reservaTotal.getDiasEstadia() * reservaTotal.getQuarto().getPrecoDiaria();
	}
}
