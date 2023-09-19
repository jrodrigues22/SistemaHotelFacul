package br.edu.up.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quarto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idQuarto;
	private float precoDiaria;
	private int capacidade;
	private boolean statusQuarto;
	
	public int getIdQuarto() {
		return idQuarto;
	}
	public void setIdQuarto(int idQuarto) {
		this.idQuarto = idQuarto;
	}
	public float getPrecoDiaria() {
		return precoDiaria;
	}
	public void setPrecoDiaria(float precoDiaria) {
		this.precoDiaria = precoDiaria;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public boolean isStatusQuarto() {
		return statusQuarto;
	}
	public void setStatusQuarto(boolean statusQuarto) {
		this.statusQuarto = statusQuarto;
	}
	
	
	
}
