package br.edu.up.front;
import br.edu.up.entidades.Hospede;
import br.edu.up.entidades.Quarto;
import br.edu.up.entidades.Reserva;
import br.edu.up.negocio.HospedeNegocio;
import br.edu.up.persistencia.HospedePersistencia;
import br.edu.up.persistencia.QuartoPersistencia;
import br.edu.up.persistencia.ReservaPersistencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MenuReserva{
	public MenuReserva() {
		int opc;
		do {
		System.out.println("******* MENU RESERVA *******");
		System.out.println("-----------------------");
		System.out.println("1 - Nova Reserva ");
		System.out.println("2 - Listar Reserva");
		System.out.println("3 - Alterar Reserva ");
		System.out.println("4 - Consultar Reserva");
		System.out.println("5 - Realizar CheckOut ");
		System.out.println("6 - Excluir Reserva");
		System.out.println("7 - Voltar");
		System.out.println("----------------------");
		opc = Console.readInt("Informe a opção: ");
		switch(opc){
		case 1:
			criarReserva();
			break;
		case 2:			
			listarReserva();
			break;
		case 3:
			alterarReserva();
			break;
		case 4:
			consultarReserva();
			break;
		case 5:
			new MenuCheckOut();
			break;
		
		case 6:
			excluirReserva();
			break;
		
			}
		}while(opc != 7);
	}
	
	private static void criarReserva() {
		String data1;
		Boolean dataInvalida = true;
		Date dataDate = null;
		Date dataDate2 = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    Quarto qt = new Quarto();
	    System.out.println("\n\n*** CRIAÇÃO DE RESERVA ***");
	    Hospede objHospede = new Hospede();
	    objHospede.setCpf(Console.readString("\n\nInforme o CPF do Hóspede: "));
	    if (HospedeNegocio.isCPF(objHospede.getCpf()) == true) {
	        objHospede = HospedePersistencia.procurarPorCPF(objHospede);
	        if (objHospede != null) {
	            Reserva objReserva = new Reserva();
	            objReserva.setHospede(objHospede);
	            do {
	            	data1 = Console.readString("Informe a Data de Entrada(dd/MM/yyyy): ");
	            	try{
	            		
	            		dataDate = (Date) formato.parse(data1);
						dataInvalida = false;
					} 
					catch (ParseException e){		
						System.out.println("\nData inválida. Informe novamente.");
					}
	            }while(dataInvalida == true);
	            objReserva.setDataEntrada(dataDate);
	            dataInvalida = true;
	            
	            do {
	            	data1 = Console.readString("Informe a Data de Saída(dd/MM/yyyy): ");
	            	try{
	            		dataDate2 = (Date) formato.parse(data1);
						dataInvalida = false;
					} 
					catch (ParseException e){		
						System.out.println("\nData inválida. Informe novamente.");
					}
	            }while(dataInvalida == true);
	            objReserva.setDataSaida(dataDate2);
	            
	            long diferencaEmMilissegundos = (objReserva.getDataSaida().getTime() - objReserva.getDataEntrada().getTime());
	            long diasDeEstadia = TimeUnit.MILLISECONDS.toDays(diferencaEmMilissegundos);   
	            
	            
	            
	            objReserva.setDiasEstadia(diasDeEstadia);
	            qt.setCapacidade(Console.readInt("Informe a capacidade do quarto: "));
	            qt.setStatusQuarto(false);
	            System.out.println("--------------------------------------");
		    for(Quarto item: QuartoPersistencia.listaQuartoVago(qt)) {                    
		        System.out.println("Quarto vago com capacidade de: "+item.getCapacidade() + " pessoas    ID do quarto: " +item.getIdQuarto());
		    }
		    System.out.println("--------------------------------------");
		    qt = new Quarto();
		    qt.setIdQuarto(Console.readInt("Informe o ID do quarto escolhido: "));
		    qt = QuartoPersistencia.procurarPorId(qt);
		    if(qt != null) {
		    	objReserva.setPago(false);
		        objReserva.setQuarto(qt);
		        ReservaPersistencia.incluir(objReserva);
		        qt.setStatusQuarto(true);
		        QuartoPersistencia.alterar(qt);
		        System.out.println("\nReserva criada com sucesso!");
				
				}else {
					System.out.println("\nQuarto não encontrado...");
				}
			
		}else {
				System.out.println("\n\nHóspede não cadastrado...");
			}
		}
		else {
			System.out.println("\n\nCPF inválido...");
		}
	}
	private static void listarReserva(){
		System.out.println("\n\n****** LISTAGEM DE RESERVA ******");
		List<Reserva> ReservaCheck = ReservaPersistencia.getReserva();
		if(ReservaCheck.size() != 0) {
		for(Reserva item: ReservaPersistencia.getReserva()) {
			System.out.println("\n\n------------------------------");
			System.out.println("Data Inicial: " + item.getDataEntrada());
			System.out.println("Data Final: " +  item.getDataSaida());
			System.out.println("Nome: " + item.getHospede().getNome());
			System.out.println("CPF: " + item.getHospede().getCpf());
			System.out.println("ID quarto : " + item.getQuarto().getIdQuarto());
			System.out.println("ID da reserva: " + item.getIdReserva());
			if(item.getPago() == false) {
				System.out.println("Reserva em aberta, necessita pagamento");
			}else {
				System.out.println("Reserva paga...");
			}
			System.out.println("----------------------------------");
			}
		}else {
			System.out.println("\nNão existe nenhuma reserva...");
		}
	}
	private static void consultarReserva() {
		System.out.println("\n\n*** CONSULTA DE RESERVA  ***");
		Hospede objHospede = new Hospede();
		objHospede.setCpf(Console.readString("Informe o CPF da reserva a ser consultado: "));
		objHospede = HospedePersistencia.procurarPorCPF(objHospede);
		if(objHospede != null) {
			Reserva objReserva = new Reserva();
			objReserva.setHospede(objHospede);
			objReserva = ReservaPersistencia.procurarPorIdHospede(objReserva);
			if(objReserva != null) {
			System.out.println("\n\n---------------------------------");
			System.out.println("ID Reserva: " + objReserva.getIdReserva());
			System.out.println("Nome: " + objReserva.getHospede().getNome());
			System.out.println("CPF: " + objReserva.getHospede().getCpf());
			System.out.println("Endereço: " + objReserva.getHospede().getEnderecoCompleto());
			System.out.println("Contato: " + objReserva.getHospede().getContato());
			System.out.println("Dias totais de estadia: " +  objReserva.getDiasEstadia());
			System.out.println("Dia Entrada: " + objReserva.getDataEntrada());
			System.out.println("Dia Saída: " + objReserva.getDataSaida());
			System.out.println("---------------------------------------");
		}
		else {
			System.out.println("\n\nHóspede não encontrado...");
		}
	}else {
		System.out.println("\n\nHóspede não encontrado...");
	}
}
	private static void alterarReserva() {
		System.out.println("\n\n*** ALTERAÇÃO DE RESERVA ***");
		Hospede objHospede = new Hospede();
		objHospede.setCpf(Console.readString("Informe o CPF a ser consultado: "));
		objHospede = HospedePersistencia.procurarPorCPF(objHospede);
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		if(objHospede != null) {
			Reserva objReserva = new Reserva();
			objReserva.setHospede(objHospede);
			objReserva = ReservaPersistencia.procurarPorIdHospede(objReserva);
			int sl;
			sl = Console.readInt("Selecione uma opção para alteração: \n1 - Data entrada \n2 - Data saida \n3 - Quarto \nOpção: ");
			switch (sl) {
			case 1:
			    String novaDataEntradaStr = Console.readString("Informe a nova data de entrada (dd/MM/yyyy): ");
			    try {
			        Date novaDataEntrada = formatoData.parse(novaDataEntradaStr);
			        objReserva.setDataEntrada(novaDataEntrada);
			        ReservaPersistencia.alterar(objReserva);
			        System.out.println("\nAlterado!");
			    } catch (ParseException e) {
			        System.out.println("\nFormato de data inválido!");
			    }
			    break;

			case 2:
			    String novaDataSaidaStr = Console.readString("Informe a nova data de saída (dd/MM/yyyy): ");
			    try {
			        Date novaDataSaida = formatoData.parse(novaDataSaidaStr);
			        objReserva.setDataSaida(novaDataSaida);
			        ReservaPersistencia.alterar(objReserva);
			        System.out.println("\nAlterado...");
			    } catch (ParseException e) {
			        System.out.println("\nFormato de data inválido!");
			    }
			    break;				
			case 3:	
				Quarto qtvelho = objReserva.getQuarto();
				Quarto qt = new Quarto();
				qt.setCapacidade(Console.readInt("Informe nova capacidade do quarto: "));
				qt.setStatusQuarto(false);
				System.out.println("--------------------------------------");
				for(Quarto item: QuartoPersistencia.listaQuartoVago(qt)) {					
					System.out.println("Quarto vago com capacidade: " + item.getIdQuarto() + " ID: ");
				}
				System.out.println("--------------------------------------");
				qt= new Quarto();
				qt.setIdQuarto(Console.readInt("Informe o ID do quarto escolhido: "));
				qt = QuartoPersistencia.procurarPorId(qt);
				if(qt != null) {
				 objReserva.setQuarto(qt);
				 ReservaPersistencia.alterar(objReserva);
				 qt.setStatusQuarto(true);
				 QuartoPersistencia.alterar(qt);
				 qtvelho.setStatusQuarto(false);
				 QuartoPersistencia.alterar(qtvelho);
				 System.out.println("\nReserva criada com sucesso!");
				 break;
				
				}else {
					System.out.println("\nQuarto não encontrado...");
				}
			}	
		}else {
			System.out.println("\nHóspede não encontrado...");
		}
	}

		private static void excluirReserva() {
		System.out.println("\n\n*** EXCLUSÃO DE RESERVA ***");
		Reserva objReserva = new Reserva();
		objReserva.setIdReserva(Console.readInt("Informem o ID da reserva: "));
		objReserva = ReservaPersistencia.procurarPorId(objReserva);
		if(objReserva != null) {
			System.out.println("\n\n-------------------------");
			System.out.println("ID da Reserva: " + objReserva.getIdReserva());
			System.out.println("Nome: " + objReserva.getHospede().getNome());
			System.out.println("CPF: " + objReserva.getHospede().getCpf()) ;
			System.out.println("-------------------------");
			String resp = Console.readString("Quer excluir a reserva?(S/N)");
			if(resp.equals("S")) {
					objReserva.getQuarto().setStatusQuarto(false);
					QuartoPersistencia.alterar(objReserva.getQuarto());
					ReservaPersistencia.excluir(objReserva);
					System.out.println("\nA exclusão foi realizada...");
				
			}else {
				System.out.println("\nVoltando menu principal!");
		}		
	}else{
		System.out.println("\n\nCPF não encontrado nas reservas...");
		}
	}
}



