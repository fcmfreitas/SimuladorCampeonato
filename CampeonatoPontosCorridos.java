import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CampeonatoPontosCorridos extends Campeonato {
    private List<Partida> partidas;
    private int rodadaAtual;

    public CampeonatoPontosCorridos() {
        super();
        this.partidas = new ArrayList<>();
        this.rodadaAtual = 1;
    }

    @Override
    public void iniciarCampeonato() {

        gerarPartidas();
        while (!partidas.isEmpty()) {
            realizaRodada();
        }
        System.out.println("\nCampeonato encerrado!");
        exibirClassificacao();
        
    }

    private void gerarPartidas() {

        for (int i = 0; i < jogadores.size(); i++) {
            for (int j = i + 1; j < jogadores.size(); j++) {
                partidas.add(new Partida(jogadores.get(i), jogadores.get(j)));
            }
        }
        Collections.shuffle(partidas); 
    }

    public void realizaRodada() {
        System.out.println("\nRodada " + rodadaAtual + ":");
        List<Jogador> jogadoresJaJogaram = new ArrayList<>(); 
    
        List<Partida> partidasDaRodada = new ArrayList<>();
        for (Partida partida : partidas) {
            if (!jogadoresJaJogaram.contains(partida.getJogador1()) &&
                !jogadoresJaJogaram.contains(partida.getJogador2())) {
                partidasDaRodada.add(partida);
                jogadoresJaJogaram.add(partida.getJogador1());
                jogadoresJaJogaram.add(partida.getJogador2());
            }
            if (jogadoresJaJogaram.size() == jogadores.size()) {
                break; // Todos os jogadores já jogaram na rodada
            }
        }
    
        partidas.removeAll(partidasDaRodada);
    
        for (Partida partida : partidasDaRodada) {
            partida.simulaPartida();
        }
    
        rodadaAtual++;
        exibirClassificacao();
    }

    private void exibirClassificacao() {
        ordenaPorPontuacao(jogadores);
        System.out.println("\nTabela:");
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            System.out.println((i + 1) + ". Nome: " + jogador.getNome() + 
                               ", Pontuação: " + jogador.getPontuacao() + 
                               ", Partidas Jogadas: " + jogador.getPartidasJogadas());
        }
        System.out.println("----------------------------------------------------------");
    }
}
