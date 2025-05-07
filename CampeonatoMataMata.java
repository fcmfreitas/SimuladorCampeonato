import java.util.ArrayList;
import java.util.InputMismatchException;

public class CampeonatoMataMata extends Campeonato {
    

    public CampeonatoMataMata() {
        super();
    }

    @Override
    public void iniciarCampeonato() {
        while (jogadores.size() > 1) {
            realizaRodada();
        }
    
        System.out.println("Parabéns " + jogadores.get(0).getNome() + "! Você foi o grande campeão!");
    }

    public void realizaRodada() {
        int num_partida = 1;
        boolean validInput = false;
        int escolha = -1;
        sorteiaConfrontos(); //sorteia os confrontos para a rodada - classe Campeonato Genérica

        while (!jogadores.isEmpty()) {
            
            System.out.println("Escolha o número da partida a ser realizada: ");
            for (int i = 0; i < jogadores.size() -1; i += 2) {
                Jogador jogador1 = jogadores.get(i);
                Jogador jogador2 = jogadores.get(i + 1);
                System.out.println("\nPartida ["+num_partida+"]: " + jogador1.getNome() + " vs " + jogador2.getNome());
                num_partida++;
            }
            num_partida =1;
            
            while (!validInput) {
                try {
                    
                    escolha = sc.nextInt();
        
                    if (escolha >= 1 && escolha <= (jogadores.size() / 2)) {
                        validInput = true;
                    } else {
                        System.out.println("Escolha inválida! Por favor, digite um número entre 1 e "+(jogadores.size() / 2)+".");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, digite um número entre 1 e "+(jogadores.size() / 2)+".");
                    sc.next();
                }
            }
            validInput = false;

            switch (escolha) {
                case 1: {
                    Partida partida1 = new Partida(jogadores.get(0), jogadores.get(1));
                    partida1.simulaPartida();
                    Jogador vencedor = partida1.getVencedor();
                    vencedores.add(vencedor);
                    jogadores.remove(0); // Remove jogador 1
                    jogadores.remove(0); // Remove jogador 2
                    break;
                }
                case 2: {
                    Partida partida2 = new Partida(jogadores.get(2), jogadores.get(3));
                    partida2.simulaPartida();
                    Jogador vencedor = partida2.getVencedor();
                    vencedores.add(vencedor);
                    jogadores.remove(2); // Remove jogador 3
                    jogadores.remove(2); // Remove jogador 4
                    break;
                }
                case 3: {
                    Partida partida3 = new Partida(jogadores.get(4), jogadores.get(5));
                    partida3.simulaPartida();
                    Jogador vencedor = partida3.getVencedor();
                    vencedores.add(vencedor);
                    jogadores.remove(4); // Remove jogador 5
                    jogadores.remove(4); // Remove jogador 6
                    break;
                }
                case 4: {
                    Partida partida4 = new Partida(jogadores.get(6), jogadores.get(7));
                    partida4.simulaPartida();
                    Jogador vencedor = partida4.getVencedor();
                    vencedores.add(vencedor);
                    jogadores.remove(6); // Remove jogador 7
                    jogadores.remove(6); // Remove jogador 8
                    break;
                }
                default:
                    break;
            } 
        }
        this.jogadores = new ArrayList<>(vencedores);
        vencedores.clear();
        exibirVencedores();
    }

    private void exibirVencedores(){

        ordenaPorPontuacao(jogadores);

        System.out.println("Vencedores da rodada: \n");
        System.out.println("----------------------------------------------------------");
        for (Jogador jogador : jogadores) {
            System.out.println((jogadores.indexOf(jogador)+1) +". Nome: " + jogador.getNome() + ", Nickname: " + jogador.getNickname() + ", Pontuação: " + jogador.getPontuacao());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println();
    }
}
