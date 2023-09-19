package br.edu.up.front;

import br.edu.up.entidades.Quarto;
import br.edu.up.persistencia.QuartoPersistencia;

public class MenuQuarto {
public MenuQuarto() {
	int sl, sl2;
	Quarto qt;
	do {
		System.out.println("\n\n");
		System.out.println("******** Menu Quartos ********");
		System.out.println("1- Criar novo quarto");
		System.out.println("2- Alterar Quarto");
		System.out.println("3- Excluir Quarto");
		System.out.println("4- Listar Quartos");
		System.out.println("5- Voltar");
		sl = Console.readInt("Opção: ");
		switch (sl) {
		case 1 : 
			qt = new Quarto();
			qt.setCapacidade(Console.readInt("Informe a Capacidade do quarto: "));
			qt.setStatusQuarto(false);
			qt.setPrecoDiaria(Console.readFloat("Informe o preco da diaria: "));
			System.out.println("\nQuarto Criado...");
			QuartoPersistencia.incluir(qt);
			break;
		case 2:
			qt = new Quarto();
			qt.setIdQuarto(Console.readInt("Informe ID do quarto: "));
			qt = QuartoPersistencia.procurarPorId(qt);
			if(qt != null) {
				qt.setPrecoDiaria(Console.readFloat("Informe o novo preço da diária: "));
				qt.setCapacidade(Console.readInt("Informe a nova capacidade do quarto: "));
				QuartoPersistencia.alterar(qt);
			}else {
				System.out.println("Quarto não encontrado...");
				
			}
		break;
		case 3 :
			qt = new Quarto();
			qt.setIdQuarto(Console.readInt("Informe ID do quarto : "));
			qt = QuartoPersistencia.procurarPorId(qt);
			if(qt != null) {
				sl2 = Console.readInt("Deseja realmente deletar esse quarto? 1 => Sim || 2 => Não: ");
				switch(sl2) {
				case 1: QuartoPersistencia.excluir(qt);
				break;
				case 2: System.out.println("Retornando ao menu...");
				break;
				}
			}else {
				System.out.println("Quarto não encontrado!");
				
			}
			break;
		case 4:			
			System.out.println("***********************");
			for(Quarto item: QuartoPersistencia.getQuartos()) {
				System.out.println("ID: " + item.getIdQuarto());
				System.out.println("Capacidade: " + item.getCapacidade());
				if(item.isStatusQuarto() == true) {
					System.out.println("Ocupado");
				}else {
					System.out.println("Vago");
				}
				System.out.println("--------------------------");			
			}
		}
	
		
	}while(sl != 5);
}
	

}
