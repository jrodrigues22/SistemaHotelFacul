package br.edu.up.persistencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.edu.up.entidades.Reserva;

	public class ReservaPersistencia{
			public static boolean incluir(Reserva reserva){
				try{
					EntityManager manager = EntityManagerFactory.getInstance();
					manager.getTransaction().begin();
					manager.persist(reserva);
					manager.getTransaction().commit();
					return true;			
				}
				catch(Exception e){
					return false;
				}
			}
			public static boolean alterar(Reserva reserva){
				try{
					EntityManager manager = EntityManagerFactory.getInstance();
					manager.getTransaction().begin();
					manager.persist(reserva);
					manager.getTransaction().commit();
					return true;			
					
				}
				catch(Exception e){
					return false;
				}
			}
			public static boolean excluir(Reserva reserva){
				try{
					EntityManager manager = EntityManagerFactory.getInstance();
					manager.getTransaction().begin();
					manager.remove(reserva);
					manager.getTransaction().commit();
					return true;			
					
				}
				catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
			public static Reserva procurarPorCPF(Reserva reserva){
				EntityManager manager = EntityManagerFactory.getInstance();
				Query consulta = manager.createQuery("from Reserva where cpf = :param");
				consulta.setParameter("param", reserva.getHospede().getCpf());
				List<Reserva> reservas = consulta.getResultList();
				if(!reservas.isEmpty()){
					return reservas.get(0);
				}
				return null;
			}
			public static Reserva procurarPorNome(Reserva reserva){
				EntityManager manager = EntityManagerFactory.getInstance();
				Query consulta = manager.createQuery("from Reserva where nome = :param");
				consulta.setParameter("param", reserva.getHospede().getNome());
				List<Reserva> reservas = consulta.getResultList();
				if(!reservas.isEmpty()){
					return reservas.get(0);
				}
				return null;
			}
			public static Reserva procurarPorId(Reserva reserva){
				EntityManager manager = EntityManagerFactory.getInstance();
				Query consulta = manager.createQuery("from Reserva where id = :param");
				consulta.setParameter("param", reserva.getIdReserva());
				List<Reserva> reservas = consulta.getResultList();
				if(!reservas.isEmpty()){
					return reservas.get(0);
				}
				return null;
			}
			public static List<Reserva> getReservas(Reserva reserva){
				EntityManager manager = EntityManagerFactory.getInstance();
				Query consulta = manager.createQuery("from Reserva where nome like :param");
				consulta.setParameter("param", "%" + reserva.getHospede().getNome() + "%");
				List<Reserva> reservas = consulta.getResultList();
				return reservas;
			}
			
			public static List<Reserva> getReserva(){
				EntityManager manager = EntityManagerFactory.getInstance();
				Query consulta = manager.createQuery("from Reserva Order by IdReserva");
				List<Reserva> reservas = consulta.getResultList();
				return reservas;
			}
			public static Reserva procurarPorIdHospede(Reserva reserva){
				EntityManager manager = EntityManagerFactory.getInstance();
				Query consulta = manager.createQuery("from Reserva where Hospede_Id = :param");
				consulta.setParameter("param", reserva.getHospede().getId());
				List<Reserva> reservas = consulta.getResultList();
				if(!reservas.isEmpty()){
					return reservas.get(0);
				}
				return null;
			}
		}


