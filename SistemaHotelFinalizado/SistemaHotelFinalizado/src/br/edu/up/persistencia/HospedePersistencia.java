package br.edu.up.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.up.entidades.Hospede;

public class HospedePersistencia {
		public static boolean incluir(Hospede hospede){
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
		public static boolean alterar(Hospede hospede){
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
		public static boolean excluir(Hospede hospede){
			try{
				EntityManager manager = EntityManagerFactory.getInstance();
				manager.getTransaction().begin();
				manager.remove(hospede);
				manager.getTransaction().commit();
				return true;			
				
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		public static Hospede procurarPorCPF(Hospede hospede){
			EntityManager manager = EntityManagerFactory.getInstance();
			Query consulta = manager.createQuery("from Hospede where cpf = :param");
			consulta.setParameter("param", hospede.getCpf());
			List<Hospede> hospedes = consulta.getResultList();
			if(!hospedes.isEmpty()){
				return hospedes.get(0);
			}
			return null;
		}
		public static Hospede procurarPorNome(Hospede hospede){
			EntityManager manager = EntityManagerFactory.getInstance();
			Query consulta = manager.createQuery("from Hospede where nome = :param");
			consulta.setParameter("param", hospede.getNome());
			List<Hospede> hospedes = consulta.getResultList();
			if(!hospedes.isEmpty()){
				return hospedes.get(0);
			}
			return null;
		}
		public static Hospede procurarPorId(Hospede hospede){
			EntityManager manager = EntityManagerFactory.getInstance();
			Query consulta = manager.createQuery("from Hospede where id = :param");
			consulta.setParameter("param", hospede.getId());
			List<Hospede> hospedes = consulta.getResultList();
			if(!hospedes.isEmpty()){
				return hospedes.get(0);
			}
			return null;
		}
		public static List<Hospede> getHospede(Hospede hospede){
			EntityManager manager = EntityManagerFactory.getInstance();
			Query consulta = manager.createQuery("from Hospede where nome like :param");
			consulta.setParameter("param", "%" + hospede.getNome() + "%");
			List<Hospede> hospedes = consulta.getResultList();
			return hospedes;
		}
	}

