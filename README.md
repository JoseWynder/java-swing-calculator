<h1 align="center">Swing Windows-Style Calculator</h1>

<p align="center">
Calculadora desktop desenvolvida em Java puro e Swing, inspirada na calculadora básica do Windows.
</p>

---

## 📌 Sobre o Projeto

Este projeto consiste em uma calculadora desktop desenvolvida em Java utilizando a biblioteca gráfica Swing.

A aplicação foi inicialmente criada durante um curso de Java e posteriormente refatorada e aprimorada de forma independente, com melhorias na organização do código, na lógica de funcionamento e no design da interface.

Durante esse processo foram trabalhados conceitos como programação orientada a objetos, gerenciamento de estado da aplicação e construção de interfaces gráficas desacopladas da lógica de negócio.

---

## ⚙️ Funcionalidades

* Operações básicas: adição, subtração, multiplicação e divisão
* Execução de operações sequenciais
* Dois displays (valor principal e operação atual)
* Suporte ao botão de backspace
* Tratamento de divisão por zero com mensagem no display
* Interface inspirada na calculadora básica do Windows

---

## 🧱 Estrutura do Projeto

A aplicação foi organizada separando gerenciamento de estado e interface gráfica.

```
src
 └─ br.com.josewynder.calculadora
     │
     ├─ Calculadora.java
     │
     ├─ modelo
     │   ├─ Memoria.java
     │   └─ MemoriaObserver.java
     │
     ├─ visao
     │   ├─ Botao.java
     │   ├─ Display.java
     │   └─ Teclado.java
     │
     └─ resources
         └─ images
             ├─ backSpace.png
             └─ calculadora.png
```

A lógica da calculadora é centralizada na classe **Memoria**, responsável pelo controle de estado da aplicação e processamento das operações.

A interface gráfica é composta por componentes visuais responsáveis pela interação do usuário e atualização do display.

A comunicação entre modelo e interface ocorre por meio de um padrão semelhante ao **Observer**, permitindo que alterações na lógica da calculadora sejam refletidas automaticamente na interface.

---

## 🖼️ Demonstração

### Calculadora em execução

Exemplo da aplicação em funcionamento durante uma operação.

<img width="420" height="572" alt="Captura de tela 2026-02-22 220918" src="https://github.com/user-attachments/assets/a60e6f1d-ac13-4e2b-8a82-93fff1a18155" />

---

### Evolução da interface

Comparação entre a versão inicial do projeto e a versão final refinada da interface.

Também demonstra a melhoria no tratamento de erros, como divisão por zero.

<img width="827" height="578" alt="Captura de tela 2026-02-22 221335" src="https://github.com/user-attachments/assets/3bc20721-9853-4091-afb0-34b6b719ffa9" />

---

### Fluxo arquitetural

Diagrama representando o fluxo de interação entre usuário, interface gráfica e lógica da calculadora.

Fluxo simplificado da aplicação:

1. O usuário interage com um botão da interface
2. O evento é processado pelo teclado da aplicação
3. O comando é enviado para a memória da calculadora
4. A memória processa a operação e atualiza o estado
5. O display é notificado e atualiza a interface

Esse fluxo demonstra o desacoplamento entre a lógica da aplicação e os componentes de interface gráfica.

<img width="1272" height="990" alt="Untitled-2026-02-19-1933 (2)" src="https://github.com/user-attachments/assets/3fdaa261-08b0-4780-a5d5-4070f3131d3b" />

---

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido como exercício prático para reforçar fundamentos de programação em Java, incluindo modelagem de estado, organização de aplicações desktop e construção de interfaces gráficas utilizando Swing.

A implementação da calculadora serviu como base para explorar conceitos de arquitetura simples, comunicação entre componentes e evolução incremental de um projeto iniciado em ambiente educacional.
