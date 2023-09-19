package br.edu.up.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.up.entidades.Quarto;

public class QuartoPersistencia {

	public static boolean incluir(Quarto hospede){
		try{
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(hospede);
			manager.getTransaction().commit();
			return true;			
		}
		catch(Exception e){
			return false;
		}
	}
	public static boolean alterar(Quarto hospede){
		try{
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.persist(hospede);
			manager.getTransaction().commit();
			return true;			
			
		}
		catch(Exception e){
			return false;
		}
	}
	public static boolean excluir(Quarto quarto){
		try{
			EntityManager manager = EntityManagerFactory.getInstance();
			manager.getTransaction().begin();
			manager.remove(quarto);
			manager.getTransaction().commit();
			return true;			
			
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static Quarto procurarPorId(Quarto quarto){
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Quarto where IdQuarto = :param");
		consulta.setParameter("param", quarto.getIdQuarto());
		List<Quarto> quartos = consulta.getResultList();
		if(!quartos.isEmpty()){
			return quartos.get(0);
		}
		return null;
	}
	public static List<Quarto> getQuartos(){
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("from Quarto Order by IdQuarto");
		List<Quarto> quartos = consulta.getResultList();
		return quartos;
	}
	
	public static List<Quarto> listaQuartoVago (Quarto quarto){
		EntityManager manager = EntityManagerFactory.getInstance();
		Query consulta = manager.createQuery("From Quarto Where Capacidade >= :paramCapacidade and StatusQuarto = :paramOcupado");
		consulta.setParameter("paramCapacidade", quarto.getCapacidade());
		consulta.setParameter("paramOcupado", quarto.isStatusQuarto());
		List<Quarto> quartos = consulta.getResultList();
		return quartos;
	}

}
