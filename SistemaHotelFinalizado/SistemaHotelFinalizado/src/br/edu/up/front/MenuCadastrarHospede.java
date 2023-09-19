package br.edu.up.front;
import br.edu.up.entidades.Hospede;

import br.edu.up.negocio.HospedeNegocio;
import br.edu.up.persistencia.HospedePersistencia;



public class MenuCadastrarHospede {

	public MenuCadastrarHospede() {
		int opc;
		do{
			System.out.println("\n\n");
			System.out.println("****** MENU HÓSPEDES ******");
			System.out.println("1 - Novo Hóspede");
			System.out.println("2 - Listar Hóspedes");
			System.out.println("3 - Consultar Hóspede");
			System.out.println("4 - Alterar Hóspede");
			System.out.println("5 - Excluir Hóspede");
			System.out.println("6 - Voltar");
			opc = Console.readInt("Opção: ");
			switch(opc){
				case 1:
					incluirHospede();
					break;
				case 2:
					listarHospede();
					break;
				case 3:
					consultarHospede();
					break;
				case 4:
					alterarHospede();
					break;
				case 5:
					excluirHospede();
					break;
			
			}
		}while(opc != 6);
	}
	
	
	private static void incluirHospede(){
		System.out.println("\n\n*** INCLUSÃO DE HÓSPEDES ***");
		Hospede objHospede = new Hospede();
		objHospede.setCpf(Console.readString("\n\nInforme o CPF do Hóspede: "));
		if(HospedeNegocio.isCPF(objHospede.getCpf())) {			
			if(HospedePersistencia.procurarPorCPF(objHospede) == null) {
				objHospede.setNome(Console.readString("Informe o nome do hóspede: "));
				objHospede.setIdade(Console.readInt("Informe a idade do hóspede: "));
				objHospede.setEnderecoCompleto(Console.readString("Informe o Endereço completo (Rua, Número, Cidade, Estado): "));
				objHospede.setContato(Console.readString("Informe o telefone(Com DDD): "));
				if(HospedePersistencia.incluir(objHospede) == true) {
					System.out.println("\n\nInclusão bem sucedida...");
				}
				else {
					System.out.println("\n\nA inclusão não foi realizada...");
				}
			}
			else {
				System.out.println("\n\nHóspede já cadastrado...");
			}
		}
		else {
			System.out.println("\n\nCPF inválido...");
		}
	}
	private static void listarHospede(){
		System.out.println("\n\n*** LISTAGEM DE HÓSPEDES ***");
		Hospede objHospede = new Hospede();
		objHospede.setNome(Console.readString("Informe uma parte do nome que deseja listar OU precione ENTER para listar todos: "));
		for(Hospede item: HospedePersistencia.getHospede(objHospede)) {
			System.out.println("\n\n---------------------------------");
			System.out.println("ID: " + item.getId());
			System.out.println("Nome: " + item.getNome());
			System.out.println("CPF: " + item.getCpf());
			System.out.println("Endereço: " + item.getEnderecoCompleto());
			System.out.println("Contato: " + item.getContato());
			System.out.println("-------------------------------------");
		}
	}
	private static void consultarHospede() {
		System.out.println("\n\n*** CONSULTA DE HÓSPEDES ***");
		Hospede objHospede = new Hospede();
		objHospede.setCpf(Console.readString("Informe o CPF a ser consultado: "));
		objHospede = HospedePersistencia.procurarPorCPF(objHospede);
		if(objHospede != null) {
			System.out.println("\n\n--------------------------------");
			System.out.println("ID: " + objHospede.getId());
			System.out.println("Nome: " + objHospede.getNome());
			System.out.println("CPF: " + objHospede.getCpf()) ;
			System.out.println("Endereço: " + objHospede.getEnderecoCompleto());
			System.out.println("Contato: " + objHospede.getContato());
			System.out.println("------------------------------------");
		}
		else {
			System.out.println("\n\nHóspede não encontrado...");
		}
	}
	private static void alterarHospede() {
		System.out.println("\n\n*** ALTERAÇÃO DE HÓSPEDES ***");
		Hospede objHospede = new Hospede();
		objHospede.setCpf(Console.readString("Informe o CPF a ser consultado: "));
		objHospede = HospedePersistencia.procurarPorCPF(objHospede);
		if(objHospede != null) {
			System.out.println("\n\n-------------------------------");
			System.out.println("ID: " + objHospede.getId());
			System.out.println("Nome: " + objHospede.getNome());
			System.out.println("CPF: " + objHospede.getCpf()) ;
			System.out.println("Endereço: " + objHospede.getEnderecoCompleto());
			System.out.println("Contato: " + objHospede.getContato());
			System.out.println("-----------------------------------");
			String resp = Console.readString("Deseja alterar os dados deste hóspede? Digite 'S' OU 'N': ");
			if(resp.equals("S")) {
				objHospede.setNome(Console.readString("Informe o novo nome para o hóspede: "));
				objHospede.setIdade(Console.readInt("Informe a nova idade do hóspede: "));
				objHospede.setEnderecoCompleto(Console.readString("Informe o novo endereço do hóspede: "));
				objHospede.setContato(Console.readString("Informe o novo contato do hóspede: "));
				if(HospedePersistencia.alterar(objHospede) == true) {
					System.out.println("A alteração foi realizada...");
				}
				else {
					System.out.println("A alteração não pôde ser realizada no momento...");
				}
			}
		}
		else {
			System.out.println("\n\nHóspede não encontrado...");
		}
	}
	private static void excluirHospede() {
		System.out.println("\n\n*** EXCLUSÃO DE HÓSPEDES ***");
		Hospede objHospede = new Hospede();
		objHospede.setCpf(Console.readString("Informe o CPF a ser excluido: "));
		objHospede = HospedePersistencia.procurarPorCPF(objHospede);
		if(objHospede != null) {
			System.out.println("\n\n--------------------------------");
			System.out.println("ID: " + objHospede.getId());
			System.out.println("Nome: " + objHospede.getNome());
			System.out.println("CPF: " + objHospede.getCpf()) ;
			System.out.println("Endereço: " + objHospede.getEnderecoCompleto());
			System.out.println("Contato: " + objHospede.getContato());
			System.out.println("------------------------------------");
			String resp = Console.readString("Quer excluir o hóspede?(S/N)");
			if(resp.equals("S")) {
				if(HospedePersistencia.excluir(objHospede) == true) {
					System.out.println("A exclusão foi realizada...");
				}
				else {
					System.out.println("A exclusão não pôde ser realizada no momento...");
				}
			}
		}
		else {
			System.out.println("\n\nHóspede não encontrado...");
		}
		
	}
}

