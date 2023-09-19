package br.edu.up.front;
import br.edu.up.negocio.PagamentoNegocio;
import br.edu.up.entidades.Quarto;
import br.edu.up.entidades.Reserva;
import br.edu.up.persistencia.ReservaPersistencia;
import br.edu.up.persistencia.QuartoPersistencia;
public class MenuCheckOut {
    public MenuCheckOut() {
        int opc;
        String sl;
        do {
            System.out.println("\n\n");
            System.out.println("****** MENU CHECK OUT ******");
            System.out.println("-----------------------------------");
            System.out.println("1 - Realizar Pagamento");
            System.out.println("2 - Voltar");
            System.out.println("-----------------------------------");
            opc = Console.readInt("Opção: ");
            switch (opc) {
                case 1:
                   Reserva objReserva = new Reserva();
                   objReserva.setIdReserva(Console.readInt("Informe o ID da reserva para o CheckOut: "));
                   objReserva = ReservaPersistencia.procurarPorId(objReserva);
                   if (objReserva != null) {
                        	System.out.println("\n\n");
                            System.out.println("********* Hóspede *********");
                            System.out.println("Nome: " + objReserva.getHospede().getNome());
                            System.out.println("CPF: " + objReserva.getHospede().getCpf());
                            System.out.println("Quarto: " + objReserva.getQuarto().getIdQuarto());
                            System.out.println("Dias de Estadia: " + objReserva.getDiasEstadia());
                            System.out.println("Preço Diária: " + objReserva.getQuarto().getPrecoDiaria());
                            System.out.println("----------------------------------");
                            System.out.println("O Total da Reserva ficou: " + PagamentoNegocio.calcularSubTotal(objReserva));
                            System.out.println("Deseja realizar o pagamento da reserva? S/N");
                            sl = Console.readString("Opção: ");
                            if (sl.equalsIgnoreCase("S")) {
                                Quarto quarto = new Quarto();
                                quarto.setIdQuarto(objReserva.getQuarto().getIdQuarto());
                                quarto = QuartoPersistencia.procurarPorId(quarto);
                                quarto.setStatusQuarto(false);
                                QuartoPersistencia.alterar(quarto);
                                objReserva.setPago(true);
                                ReservaPersistencia.alterar(objReserva);
                                System.out.println("Pagamento realizado e Quarto liberado...");
                            } else {
                                System.out.println("\nRetornando ao menu...");
                            }
                        }else {
                            System.out.println("\nNão possui reserva...");
                        }
                    
                    break;
            }
        } while (opc != 2);
    }
}