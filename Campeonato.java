import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Campeonato {
    protected ArrayList<Jogador> jogadores;
    protected ArrayList<Jogador> vencedores = new ArrayList<>();
    protected Scanner sc;

    public Campeonato() {
        jogadores = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void removerJogador(Jogador jogador) {
        jogadores.remove(jogador);
    }

    public Jogador getJogador(int index) {
        return jogadores.get(index);
    }

    public void iniciarCampeonato() {}

    public void listarJogadores() {
        System.out.println("\nJogadores Selecionados:");
        for (Jogador jogador : jogadores) {
            System.out.println("Nome: " + jogador.getNome() + ", Nickname: " + jogador.getNickname() + ", Ranking: " + jogador.getRanking());
        }
        System.out.println();
    }

    public void sorteiaConfrontos() {
        if (jogadores.size() % 2 != 0) {
            Jogador maiorPontuacao = jogadores.get(0);
            int indiceMaior = 0;

            for (int i = 0; i < jogadores.size() - 1; i++) {
                if (jogadores.get(i).getPontuacao() > maiorPontuacao.getPontuacao()) {
                    maiorPontuacao = jogadores.get(i);
                    indiceMaior = i;
                }
            }
            Collections.swap(jogadores, indiceMaior, jogadores.size() - 1);
            Jogador jogadorFora = jogadores.remove(jogadores.size() - 1);
            vencedores.add(jogadorFora);
            System.out.println("Número ímpar de jogadores. O jogador com maior pontuação pulou uma rodada: " 
                               + jogadorFora.getNome() + " com " + jogadorFora.getPontuacao() + " pontos.(+30 vitória)");

            jogadorFora.atualizaPontuacao(30);
        }
        System.out.println("Jogadores sendo sorteados para as partidas...");
        Collections.shuffle(jogadores);
    }

    public boolean isInCampeonato(String apelido) {
        for (Jogador jogador : jogadores) {
            if (jogador.getNickname().equals(apelido)) {
                return true;
            }
        }
        return false;
    }

    public void selecionarJogadoresAleatorios(String caminhoArquivo) {
        int[] opcoes = {4, 6, 8};
        int numeroJogadores = opcoes[new Random().nextInt(opcoes.length)];
        String nome = null;
        String apelido = null;
        Random random = new Random();

        for (int i = 0; i < numeroJogadores; i++) {
            int linhaAlvo = random.nextInt(100);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("nomes.txt"), StandardCharsets.UTF_8))) {
                String linha;
                int contador = 0;
                while ((linha = br.readLine()) != null) {
                    contador++;
                    if (contador == linhaAlvo) {
                        String[] partes = linha.split(",\\s*");
                        nome = partes[0];
                        apelido = partes[1];
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isInCampeonato(apelido)) {
                i--;
            } else {
                Jogador jogador = new Jogador(nome, apelido);
                adicionarJogador(jogador);
            }
        }
    }

    public void escolherJogadores() {
        System.out.println("Escolha os jogadores para o campeonato: ");
        System.out.println("[1] Selecionar jogadores aleatórios");
        System.out.println("[2] Selecionar jogadores manualmente");
        System.out.print("Digite sua escolha: ");
        int escolha = sc.nextInt();
        while (escolha != 1 && escolha != 2) {
            System.out.print("Número inválido! Digite novamente (1 ou 2): ");
            escolha = sc.nextInt();
        }

        switch (escolha) {
            case 1:
                selecionarJogadoresAleatorios("nomes.txt");
                break;
            case 2:
                System.out.println("\nQuantos jogadores deseja adicionar a competição? (4, 6 ou 8) ");
                escolha = sc.nextInt();
                sc.nextLine();
                while (escolha != 4 && escolha != 6 && escolha != 8) {
                    System.out.println("Número inválido! Digite novamente (4, 6 ou 8): ");
                    escolha = sc.nextInt();
                    sc.nextLine();
                }
                for (int i = 0; i < escolha; i++) {
                    System.out.println("\nDigite o nome do jogador " + (i + 1) + ": ");
                    String nome = sc.nextLine();
                    System.out.println("\nDigite o apelido do jogador " + (i + 1) + ": ");
                    String apelido = sc.nextLine();
                    Jogador jogador = new Jogador(nome, apelido);
                    adicionarJogador(jogador);
                }
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public ArrayList<Jogador> ordenaPorPontuacao(ArrayList<Jogador> lista) {
        Collections.sort(lista, (j1, j2) -> Integer.compare(j2.getPontuacao(), j1.getPontuacao()));
        return lista;
    }

    public void fecharScanner() {
        if (sc != null) {
            sc.close(); // Fecha o scanner quando não for mais necessário
        }
    }
}