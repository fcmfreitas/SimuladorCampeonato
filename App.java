import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Campeonato campeonato;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEscolha o modelo de campeonato:\n\n[1] Mata-Mata (tradicional)\n[2] Pontos Corridos");
        int escolha = validaInput(sc);

        if(escolha == 1) {
            System.out.println("\nCampeonato Mata-Mata selecionado.\n");
            campeonato = new CampeonatoMataMata();
        } else {
            System.out.println("\nCampeonato Pontos Corridos selecionado.\n");
            campeonato = new CampeonatoPontosCorridos();
        }

        campeonato.escolherJogadores(); // personalizado ou aleatório
        campeonato.listarJogadores();
        campeonato.iniciarCampeonato();

        sc.close();
    }

    public static int validaInput(Scanner sc) {
        boolean validInput = false;
        int escolha = -1;
    
        while (!validInput) {
            try {
                escolha = sc.nextInt();
    
                if (escolha >= 1 && escolha <= 2) {
                    validInput = true;
                } else {
                    System.out.println("Escolha inválida! Por favor, digite um número entre 1 e 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um número entre 1 e 2.");
                sc.next();
            }
        }
    
        // Retorna a escolha válida
        return escolha;
    }
}
