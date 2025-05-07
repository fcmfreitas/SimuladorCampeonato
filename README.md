# Sistema de Gerenciamento de Campeonatos

Este projeto é um sistema simples para gerenciar campeonatos de dois tipos: **Mata-Mata** e **Pontos Corridos**. O programa permite que o usuário escolha o tipo de campeonato, selecione os jogadores (manualmente ou aleatoriamente) e acompanhe o progresso do campeonato até o final.

## Funcionalidades

- Escolha entre dois tipos de campeonato:
  - **Mata-Mata**: Os jogadores competem em rodadas eliminatórias até que reste apenas um vencedor.
  - **Pontos Corridos**: Todos os jogadores competem entre si, acumulando pontos ao longo do campeonato.
- Adição de jogadores:
  - Seleção manual: O usuário insere os nomes e apelidos dos jogadores.
  - Seleção aleatória: O sistema seleciona jogadores de um arquivo de nomes.
- Listagem de jogadores participantes.
- Simulação de partidas e exibição dos vencedores.

## Estrutura do Projeto

- **`App.java`**: Classe principal que inicializa o programa e gerencia a interação com o usuário.
- **`Campeonato.java`**: Classe base que define as funcionalidades genéricas de um campeonato.
- **`CampeonatoMataMata.java`**: Subclasse de `Campeonato` que implementa o modelo de campeonato Mata-Mata.
- **`CampeonatoPontosCorridos.java`**: Subclasse de `Campeonato` que implementa o modelo de campeonato Pontos Corridos.
- **`Jogador.java`**: Classe que representa os jogadores, com atributos como nome, apelido e pontuação.

## Como Executar

1. **CERTIFIQUE-SE QUE VOCÊ ESTÁ NO MESMO DIRETÓRIO DOS ARQUIVOS JAVA. CASO CONTRÁRIO, A LEITURA DO ARQUIVO "nomes.txt" NÃO IRÁ FUNCIONAR**
2. Certifique-se de ter o [Java JDK] instalado.
2. Compile os arquivos `.java`:
   ```bash
   javac App.java