package br.edu.up.front;
public class MenuPrincipal {

	public static void main(String[] args) {
		int opc;
		
		System.out.println(Colors.GREEN_BOLD_BRIGHT +"\n\n88,dPYba,,adPYba,   ,adPPYba, 8b,dPPYba,  88       88  \r\n"
				+ "88P'   \"88\"    \"8a a8P_____88 88P'   `\"8a 88       88  \r\n"
				+ "88      88      88 8PP\"\"\"\"\"\"\" 88       88 88       88  \r\n"
				+ "88      88      88 \"8b,   ,aa 88       88 \"8a,   ,a88  \r\n"
				+ "88      88      88  `\"Ybbd8\"' 88       88  `\"YbbdP'Y8  " + Colors.RESET);
		
		System.out.println(Colors.GREEN_BOLD_BRIGHT +"88                                       88  \r\n"
				+ "\n\n88                       ,d              88  \r\n"
				+ "88                       88              88  \r\n"
				+ "88,dPPYba,   ,adPPYba, MM88MMM ,adPPYba, 88  \r\n"
				+ "88P'    \"8a a8\"     \"8a  88   a8P_____88 88  \r\n"
				+ "88       88 8b       d8  88   8PP\"\"\"\"\"\"\" 88  \r\n"
				+ "88       88 \"8a,   ,a8\"  88,  \"8b,   ,aa 88  \r\n"
				+ "88       88  `\"YbbdP\"'   \"Y888 `\"Ybbd8\"' 88  \r\n"
				+ "                                            " + Colors.RESET);
		do{
			System.out.println("-----------------------------------");
			System.out.println("1 - Menu Hóspede");
			System.out.println("2 - Menu Reserva");
			System.out.println("3 - Menu Quartos");
			System.out.println("4 - Sair");
			System.out.println("-----------------------------------");
			opc = Console.readInt("Opção: ");
			switch(opc){
				case 1:
					new MenuCadastrarHospede();
					break; 
				case 2:
					new MenuReserva();
					break;
				case 3:
					new MenuQuarto();
					break;
				
			}
		}while(opc != 4);
	}
	
}
