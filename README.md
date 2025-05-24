# CurrencyC: Conversor de Moedas via Terminal

Bem-vindo ao CurrencyC, um conversor de moedas simples e eficiente desenvolvido em Java. Esta aplicação permite que você converta valores entre diferentes moedas utilizando taxas de câmbio atualizadas fornecidas pela ExchangeRate-API.

## Como Funciona

O CurrencyC é uma aplicação de linha de comando. Você interage com ele diretamente pelo seu terminal.

1.  **Escolha de Moedas:** Você será solicitado a inserir o código da moeda de origem (ex: `USD` para Dólar Americano, `BRL` para Real Brasileiro).
2.  **Moeda de Destino:** Em seguida, você informará o código da moeda para a qual deseja converter (ex: `EUR` para Euro, `CAD` para Dólar Canadense).
3.  **Valor:** Por fim, você digitará o valor que deseja converter.
4.  **Resultado:** A aplicação fará uma requisição à API ExchangeRate-API, obterá as taxas de câmbio mais recentes e exibirá o valor convertido no terminal.

## Requisitos

Para executar esta aplicação, você precisa ter o seguinte instalado em seu sistema:

* **Java Development Kit (JDK) 17 ou superior**
* **Maven** (para gerenciar as dependências do projeto e compilar o código)

## Configuração e Execução

Siga estes passos para configurar e executar o CurrencyC em sua máquina:

### 1. Obtenha sua API Key

Esta aplicação utiliza a [ExchangeRate-API](https://www.exchangerate-api.com/) para obter as taxas de câmbio. Você precisará de uma chave de API gratuita:

1.  Acesse o site [ExchangeRate-API](https://www.exchangerate-api.com/).
2.  Crie uma conta e faça login.
3.  Sua chave de API será exibida no painel de controle. Copie-a.

### 2. Configure a API Key no Código

1.  Abra o arquivo `src/main/java/io/github/davikosta/conversormoedas/main/ConversorApp.java`.
2.  Localize a linha:
    ```java
    private static final String API_KEY = "1738e9e943368c6dff7af3fc"; // <<< MUDAR AQUI PELA SUA CHAVE REAL!
    ```
3.  Substitua `"1738e9e943368c6dff7af3fc"` pela sua chave de API real que você obteve no passo anterior.
    Exemplo: `private static final String API_KEY = "sua_chave_aqui";`
4.  Salve o arquivo.

### 3. Compile a Aplicação (Caso não queira executar através de uma IDE da sua preferência, como o IntelliJ IDEA)

Navegue até a raiz do projeto no seu terminal (onde o arquivo `pom.xml` está localizado) e execute o seguinte comando Maven para compilar o projeto e baixar as dependências:

```bash
mvn clean install
```
### 3.1. Execute a Aplicação

Após a compilação bem-sucedida, execute a aplicação com o seguinte comando Maven:

```bash
java -jar target/conversor-moedas-1.0-SNAPSHOT-jar-with-dependencies.jar
```

A aplicação será iniciada e você poderá começar a converter moedas seguindo as instruções no terminal.
Códigos de Moedas

### Códigos de Moedas
Você pode encontrar uma lista completa dos códigos de moedas suportados pela ExchangeRate-API (ISO 4217) em sua documentação oficial. Exemplos comuns incluem:

- USD: Dólar Americano
- BRL: Real Brasileiro    
- EUR: Euro
- GBP: Libra Esterlina
- JPY: Iene Japonês
- CAD: Dólar Canadense
- AUD: Dólar Australiano
- CHF: Franco Suíço

### Estrutura do Projeto

- ```src/main/java/io/github/davikosta/conversormoedas/main/ConversorApp.java```: Classe principal que inicia e gerencia a interação com o usuário.
- ```src/main/java/io/github/davikosta/conversormoedas/model/ExchangeRateResponse.java```: Representa a estrutura dos dados JSON retornados pela API de taxas de câmbio.
- ```src/main/java/io/github/davikosta/conversormoedas/service/ExchangeRateApiService.java```: Responsável por fazer as requisições HTTP à ExchangeRate-API e processar as respostas.
- ```src/main/java/io/github/davikosta/conversormoedas/util/InputHandler.java```: Classe utilitária para lidar com a entrada do usuário de forma segura.
- ```pom.xml```: Arquivo de configuração do Maven, contendo as dependências do projeto (OkHttp para requisições HTTP e Gson para parsear JSON).

### Licença

Este projeto está licenciado sob a licença MIT License. Consulte o arquivo LICENSE para mais detalhes.

