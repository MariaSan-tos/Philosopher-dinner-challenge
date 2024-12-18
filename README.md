# Jantar dos Filósofos - Prova de Programação Paralela e Distribuída

## Descrição

Cinco filósofos estão sentados em uma mesa redonda. Cada filósofo precisa de dois garfos para comer, mas cada garfo só pode ser usado por um filósofo por vez. O código implementa um servidor que simula o comportamento dos filósofos utilizando múltiplas conexões via Telnet. Cada filósofo pode alternar entre o estado de **pensando** e **comendo**, com a possibilidade de pensar por um tempo aleatório e comer por até 1 minuto.

## Instruções de Uso

1. **Compilação**:
   Para compilar o código, basta usar o comando `javac`:

   ```bash
   javac Server.java
   ```

2. **Execução**:
   Após a compilação, execute o servidor com o comando:

   ```bash
   java Server
   ```

   O servidor será iniciado e ficará aguardando conexões de clientes na porta **12345**.

3. **Conexão via Telnet**:
   Em outro terminal, você pode usar o Telnet para se conectar ao servidor. Execute o seguinte comando:

   ```bash
   telnet localhost 12345
   ```

   Ao se conectar, o servidor enviará uma mensagem de saudação e você poderá interagir com os filósofos usando os comandos:

   - **HELLO**: Recebe uma saudação do servidor.
   - **THINK**: O filósofo começa a pensar, quando o garfo é liberado, ele come.
   - **EAT**: O filósofo tenta comer, se não tem um garfo, ele pensa. O tempo de refeição será aleatório, mas com no máximo 1 minuto.

   Você pode abrir várias conexões Telnet (até 5) para simular os filósofos tentando comer.

4. **Comando de Sair**:
   Para desconectar, basta fechar a conexão Telnet.

## Funcionamento

- **Filosofos**: São representados por clientes que se conectam ao servidor via Telnet. Cada cliente recebe um ID exclusivo.
- **Garfos**: São recursos compartilhados entre os filósofos. Cada filósofo precisa de dois garfos para comer.
- **Comportamento**: Filósofos alternam entre os estados **pensando** e **comendo**. Se os garfos estiverem disponíveis, o filósofo come; caso contrário, ele pensa até que os garfos fiquem disponíveis.

## Limitações

- O servidor limita o número de refeições para **10 vezes** por filósofo, após o que ele se considera "cheio".
- O tempo de pensamento é gerado aleatoriamente, com um valor entre **10 e 70 segundos**.
- O tempo de refeição é garantido para ser **no maxímo 1 minuto**, com um valor aleatório dentro desse limite.

## Objetivo

Este código foi desenvolvido para demonstrar o uso de programação paralela e distribuída, simulando um sistema onde múltiplos filósofos (clientes) tentam acessar recursos compartilhados (garfos) enquanto alternam entre os estados de **pensando** e **comendo**.
