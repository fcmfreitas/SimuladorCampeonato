import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Partida {

    private Jogador jogador1, jogador2;

    public Partida(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public void simulaPartida() {
        jogador1.atualizaPartidasJogadas();
        jogador2.atualizaPartidasJogadas();
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Partida entre " + jogador1.getNome() + " e " + jogador2.getNome() + " iniciada!\n");
        int escolha1 = getEscolha(sc, jogador1);
        calculaPontuacao(escolha1, jogador1);
        int escolha2 = getEscolha(sc, jogador2);
        calculaPontuacao(escolha2, jogador2);
        resultado();
        System.out.println("Partida encerrada!\n");
    }

    private void printEscolha(Jogador jogador_jogando) {
        System.out.println("Ecolha o evento do " + jogador_jogando.getNome()+ ":");
        System.out.println("[1] Jogada original             : +5 pontos");
        System.out.println("[2] Gafe                        : -3 pontos");
        System.out.println("[3] Posicionamento vantajoso    : +2 pontos");
        System.out.println("[4] Desrespeito ao adversário   : -5 pontos");
        System.out.println("[5] Ataque de fúria             : -7 pontos");
    }

    private int getEscolha(Scanner sc, Jogador jogador) {
        int escolha = -1;
        boolean validInput = false;
    
        while (!validInput) {
            try {
                printEscolha(jogador);
                escolha = sc.nextInt();
    
                if (escolha >= 1 && escolha <= 5) {
                    validInput = true;
                } else {
                    System.out.println("Escolha inválida! Por favor, digite um número entre 1 e 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um número entre 1 e 5.");
                sc.next();
            }
        }
    
        return escolha;
    }

    private void calculaPontuacao(int escolha, Jogador jogador) {
        switch (escolha) {
            case 1:
                System.out.println(jogador.getNome() + " +5 pontos.\n");
                jogador.atualizaPontuacao(5);
                break;
            case 2:
                System.out.println(jogador.getNome() + " -3 pontos.\n");
                jogador.atualizaPontuacao(-3);
                break;
            case 3:
                System.out.println(jogador.getNome() + " +2 pontos.\n");
                jogador.atualizaPontuacao(2);
                break;
            case 4:
                System.out.println(jogador.getNome() + " -5 pontos.\n");
                jogador.atualizaPontuacao(-5);
                break;
            case 5:
                System.out.println(jogador.getNome() + " -7 pontos.\n");
                jogador.atualizaPontuacao(-7);
                break;
            default:
                break;
        }
    }

    private void resultado() {
        if(jogador1.getPontuacao() > jogador2.getPontuacao()) {
            System.out.println("Vencedor: " + jogador1.getNome() + " com " + jogador1.getPontuacao() + " pontos.");
            jogador1.atualizaPontuacao(30);
        } else if (jogador1.getPontuacao() < jogador2.getPontuacao()) {
            System.out.println("Vencedor: " + jogador2.getNome() + " com " + jogador2.getPontuacao() + " pontos.");
            jogador2.atualizaPontuacao(30);

        } else {
            Random random = new Random();
            int vencedor = random.nextInt(2);
            System.out.println("Empate! Realizando desempate...");
            if(vencedor == 0) {
                jogador1.atualizaPontuacao(2); // add 2 : desempate
                jogador1.atualizaPontuacao(30);

                System.out.println("Vencedor: " + jogador1.getNome() + " com " + jogador1.getPontuacao() + " pontos.");
            } else if(vencedor == 1) {
                jogador2.atualizaPontuacao(2); // add 2 : desempate
                jogador2.atualizaPontuacao(30);

                System.out.println("Vencedor: " + jogador2.getNome() + " com " + jogador2.getPontuacao() + " pontos.");
            }
        }
    }

    public Jogador getVencedor() {
        if(jogador1.getPontuacao() > jogador2.getPontuacao()) {
            return jogador1;
        } else if (jogador1.getPontuacao() < jogador2.getPontuacao()) {
            return jogador2;
        } else {
            return null;
        }
    }
}