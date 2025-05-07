import java.util.Random;

public class Jogador {
    private String nome, nickname;
    private int ranking;
    private int pontuacao;
    private int partidasJogadas;

    public Jogador(String nome, String nickname) {
        this.nome = nome;
        this.nickname = nickname;
        this.ranking = new Random().nextInt(1500) + 1; // 1 a 1500
        this.pontuacao = 70; 
        this.partidasJogadas = 0;
    }

    public void atualizaPartidasJogadas() {
        this.partidasJogadas++;
    }

    public int getPartidasJogadas() {
        return partidasJogadas;
    }

    public String getNome() {
        return nome;
    }

    public String getNickname() {
        return nickname;
    }

    public int getRanking() {
        return ranking;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void atualizaPontuacao(int pontos) {
        this.pontuacao += pontos;
    }
}