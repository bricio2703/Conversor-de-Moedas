# 💱 Conversor de Moedas em Java (Console)

Este é um projeto de **conversor de moedas em tempo real**, desenvolvido em **Java**, com interface textual no console. Ele permite que usuários convertam valores em **Reais (BRL)** para diversas moedas estrangeiras, utilizando dados atualizados de câmbio fornecidos pela **ExchangeRate API**.

---

## 📌 Descrição do Projeto

O conversor foi criado com foco em simplicidade, organização e experiência do usuário. Ele fornece um **menu interativo**, trata entradas inválidas e permite múltiplas conversões na mesma execução.

As moedas atualmente suportadas para conversão são:
- Dólar Americano (USD)
- Euro (EUR)
- Libra Esterlina (GBP)
- Peso Argentino (ARS)
- Iene Japonês (JPY)

---

## ✅ Funcionalidades

- 💵 Conversão de valores de BRL para várias moedas internacionais.
- 🌐 Integração com a ExchangeRate API para obter taxas em tempo real.
- 🔄 Interface de console com menu interativo e repetição de operações.
- 🛡️ Validação de entrada (impede letras, valores negativos, opções inválidas).
- 🚫 Tratamento de erros de conexão com a internet ou com a API.

---

## ▶️ Como utilizar

### Requisitos
- Java 8 ou superior.
- A biblioteca `org.json` (usada para ler o JSON da API).

### Passos

1. Clone ou baixe este repositório.
2. Certifique-se de ter o Java instalado:
   ```bash
   java -version

3. Adicione a dependência da biblioteca `org.json`:
   - Se estiver usando Maven, adicione ao `pom.xml`:
     ```xml
     <dependency>
       <groupId>org.json</groupId>
       <artifactId>json</artifactId>
       <version>20240303</version>
     </dependency>
     ```
   - Ou baixe manualmente em: https://mvnrepository.com/artifact/org.json/json

4. Compile e execute a classe principal:
   ```bash
   javac ConversorMoedas.java
   java ConversorMoedas

5. No console, selecione a moeda desejada, insira o valor e veja o resultado da conversão.

❓ Onde encontrar ajuda
Caso encontre dificuldades para compilar ou executar o projeto:

Verifique se você tem o Java instalado corretamente (java -version).

Confirme que adicionou corretamente a dependência org.json.

Consulte a documentação oficial da API ExchangeRate.

Ou abra uma issue neste repositório com a sua dúvida.

👥 Autores
Fabrício Santos
Estudante em Java e entusiasta de projetos educacionais.
GitHub: https://github.com/bricio2703
