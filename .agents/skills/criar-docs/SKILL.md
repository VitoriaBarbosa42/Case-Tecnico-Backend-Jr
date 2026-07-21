---
name: criar-docs
description: Atua como Especialista em Documentação Técnica (Technical Writer) para analisar o refinamento técnico, código-fonte e skills do repositório, atualizando o README.md e gerando uma documentação completa e interativa em HTML na pasta report-docs.
---

# 📚 Skill: Criar Documentação Técnica (criar-docs)

Esta skill guia o agente no papel de **Technical Writer / Especialista em Documentação Técnica**. O objetivo é gerar documentações de alta qualidade, acessíveis e mantidas, cobrindo desde o processo de refinamento técnico até a instrução clara de instalação, execução e arquitetura da aplicação.

---

## 🎭 Persona: Especialista em Documentação Técnica (Technical Writer)

Você atua como um **Lead Technical Writer & Developer Experience Specialist (DX)**.
Sua missão é transformar conceitos complexos de software em documentações elegantes, precisas e fáceis de digerir.
Você valoriza:
- Experiência do Desenvolvedor (DX) fluida e onboarding rápido.
- Clareza absoluta no passo a passo de clone e execução local.
- Contextualização do "porquê" das decisões de arquitetura e tecnologia.
- Consistência de linguagem, formatação visual moderna e documentação interativa.

---

## 📋 Os 6 Pilares da Boa Documentação (Critérios de Aceite)

Toda documentação produzida por esta skill **DEVE obrigatoriamente seguir os 6 pilares abaixo**:

1. **👤 Foco no Leitor**:
   - Escreva sempre pensando em quem vai ler (desenvolvedores, avaliadores, recrutadores ou usuários).
   - Determine o nível de conhecimento prévio do público-alvo e explique claramente termos técnicos e siglas.

2. **⚡ Clareza e Concisão**:
   - Use linguagem simples, frases curtas e aborde apenas **um conceito por vez**.
   - Elimine redundâncias, ambiguidades e vá direto ao ponto.

3. **🧩 Organização Lógica**:
   - Apresente as informações na ordem exata em que o leitor precisará delas (do mais básico ao avançado).
   - Inclua **Sumários / Tabelas de Conteúdo** navegáveis em Markdown e HTML.

4. **🎨 Consistência**:
   - Mantenha o mesmo padrão de formatação (títulos, blocos de código, notas), estilo de escrita e terminologia do início ao fim.

5. **💡 O "Porquê"**:
   - Não documente apenas "como" fazer, mas contextualize os motivos e os trade-offs das decisões técnicas tomadas (arquitetura MVC/BFF, escolhas de bibliotecas, tratamento de exceções).

6. **🔄 Atualização**:
   - Mantenha a documentação sincronizada e alinhada com a realidade atual do código-fonte.

---

## 🔍 Fluxo de Execução & Análise Prévia

Antes de escrever ou atualizar qualquer documento, o agente **DEVE obrigatoriamente realizar 3 análises prévias**:

### 1. Análise do Refinamento Técnico (Documento à parte)
- Localize e leia o documento de refinamento técnico do projeto armazenado obrigatoriamente na pasta `.agents/skills/criar-docs/refinamentos/refinamento.md` (ou `/criar-docs/refinamentos`).
- Extraia os requisitos funcionais, regras de negócio, contratos de API e restrições técnicas acordadas (ex: integração com ViaCEP, remoção de campos desnecessários como IBGE, GIA, DDD e Siafe, conversão do logradouro em lowercase).

### 2. Análise do Código-Fonte
- Inspecione a estrutura de diretórios do projeto (`src/`, `pom.xml`, controladores, serviços, DTOs, exceções e arquivos de configuração).
- Identifique os endpoints disponíveis, parâmetros esperados e respostas de sucesso/erro.

### 3. Análise das Skills Existentes
- Inspecione o diretório `.agents/skills/` para identificar ferramentas operacionais do projeto (`code-review`, `unit-testing`, `roll-dice`, `criar-docs`).
- Documente como os desenvolvedores podem invocar e se beneficiar destas skills.

---

## 🚀 Conteúdo Obrigatório da Documentação

A documentação gerada precisa conter, no mínimo, as seguintes seções estruturadas:

1. **📌 Visão Geral & Objetivo**: O que é o projeto, qual problema resolve e quem se beneficia.
2. **🛠️ Tecnologias & Pré-requisitos**: Versão do Java (ex: Java 21), Maven, Git e IDEs recomendadas.
3. **📥 Como Baixar e Executar na Sua Máquina (Passo a Passo Rápido)**:
   - **Clonar o Repositório**:
     ```bash
     git clone https://github.com/seu-usuario/seu-repositorio.git
     cd seu-repositorio
     ```
   - **Compilar o Projeto**:
     ```bash
     ./mvnw clean package
     ```
   - **Executar a Aplicação**:
     ```bash
     ./mvnw spring-boot:run
     ```
     *(ou `mvnw.cmd spring-boot:run` no Windows)*
   - **Testar os Endpoints**: Exemplos claros com `curl` ou links para requisições no navegador (ex: `http://localhost:8080/viacep/01001000`).
4. **🏛️ Arquitetura & Decisões Técnicas ("O Porquê")**:
   - Padrão MVC/BFF, fluxo de chamadas externas, tratamento global de exceções e validação de CEP.
5. **🧰 Guia de Skills & Automação**:
   - Como usar as skills do repositório (`code-review`, `unit-testing`, etc.).
6. **🧪 Execução de Testes**: Como rodar os testes unitários (`./mvnw test`) e gerar os relatórios de cobertura.

---

## 📊 Entregáveis Obrigatórios da Skill

Ao executar esta skill, o agente **DEVE realizar duas entregas**:

### 1. Atualização do `README.md`
- Atualizar o arquivo `README.md` na raiz do projeto com formatação limpa em GitHub Flavored Markdown, emojis explicativos, sumário navegável, comandos copiáveis e diagramas de fluxo se aplicável.

### 2. Geração do Relatório HTML em `report-docs/`
- **Garantir a existência da pasta `report-docs/`** na raiz do projeto.
- **Gerar o arquivo HTML interativo** em `report-docs/documentation-report.html`.
- O relatório HTML deve possuir design responsivo, barra lateral de navegação, cards interativos, tabelas estilizadas e sintaxe destacada para comandos e requisições.

---

## 🎨 Template Estrutural do Relatório HTML (`report-docs/documentation-report.html`)

Ao gerar `report-docs/documentation-report.html`, utilize o padrão visual e de marcação abaixo:

```html
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Documentação Técnica do Projeto</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
            line-height: 1.6;
            color: #2d3748;
            background: #f7fafc;
            display: flex;
            min-height: 100vh;
        }

        /* Sidebar Navigation */
        sidebar {
            width: 280px;
            background: #1a202c;
            color: #e2e8f0;
            padding: 30px 20px;
            position: fixed;
            height: 100vh;
            overflow-y: auto;
        }

        sidebar h2 { font-size: 1.3em; margin-bottom: 20px; color: #63b3ed; }
        sidebar ul { list-style: none; }
        sidebar li { margin-bottom: 12px; }
        sidebar a {
            color: #cbd5e0;
            text-decoration: none;
            font-size: 0.95em;
            transition: color 0.2s;
        }
        sidebar a:hover { color: #63b3ed; }

        /* Main Content */
        main {
            margin-left: 280px;
            padding: 40px;
            flex: 1;
            max-width: 1000px;
        }

        .header-card {
            background: linear-gradient(135deg, #3182ce 0%, #2b6cb0 100%);
            color: white;
            padding: 40px;
            border-radius: 12px;
            margin-bottom: 30px;
            box-shadow: 0 10px 25px rgba(49, 130, 206, 0.2);
        }

        .header-card h1 { font-size: 2.3em; margin-bottom: 10px; }
        .header-card p { font-size: 1.1em; opacity: 0.9; }

        section {
            background: white;
            padding: 30px;
            border-radius: 10px;
            margin-bottom: 25px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
        }

        h2 { font-size: 1.6em; color: #2b6cb0; margin-bottom: 20px; border-bottom: 2px solid #e2e8f0; padding-bottom: 8px; }
        h3 { font-size: 1.2em; color: #2d3748; margin: 15px 0 10px 0; }

        .code-block {
            background: #1e1e1e;
            color: #d4d4d4;
            padding: 16px;
            border-radius: 8px;
            overflow-x: auto;
            font-family: 'Consolas', 'Monaco', monospace;
            font-size: 0.9em;
            margin: 15px 0;
        }

        .pill {
            display: inline-block;
            background: #ebf8ff;
            color: #2b6cb0;
            padding: 4px 12px;
            border-radius: 15px;
            font-size: 0.85em;
            font-weight: 600;
            margin-right: 8px;
        }

        .step-list { list-style: none; counter-reset: step-counter; }
        .step-list li {
            position: relative;
            padding-left: 45px;
            margin-bottom: 20px;
        }
        .step-list li::before {
            content: counter(step-counter);
            counter-increment: step-counter;
            position: absolute;
            left: 0;
            top: 0;
            width: 30px;
            height: 30px;
            background: #3182ce;
            color: white;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: bold;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #e2e8f0; }
        th { background: #f7fafc; color: #4a5568; font-weight: 600; }
    </style>
</head>
<body>
    <sidebar>
        <h2>📖 Navegação</h2>
        <ul>
            <li><a href="#visao-geral">Visão Geral</a></li>
            <li><a href="#refinamento">Refinamento Técnico</a></li>
            <li><a href="#quickstart">Como Baixar & Executar</a></li>
            <li><a href="#arquitetura">Arquitetura & Decisões</a></li>
            <li><a href="#skills">Guia de Skills</a></li>
            <li><a href="#testes">Execução de Testes</a></li>
        </ul>
    </sidebar>

    <main>
        <div class="header-card">
            <h1>📘 Documentação Técnica do Projeto</h1>
            <p>Guia Completo de Instalação, Arquitetura e Decisões de Desenvolvimento</p>
        </div>

        <section id="visao-geral">
            <h2>Visão Geral</h2>
            <!-- CONTEÚDO VISÃO GERAL -->
        </section>

        <section id="refinamento">
            <h2>Refinamento Técnico</h2>
            <!-- REQUISITOS E CONTEXTO EXTRAÍDOS DE .agents/skills/criar-docs/refinamente/refinamento.md -->
        </section>

        <section id="quickstart">
            <h2>🚀 Como Baixar e Executar Localmente</h2>
            <ol class="step-list">
                <li>
                    <h3>Clonar o Repositório</h3>
                    <div class="code-block">git clone https://github.com/usuario/repositorio.git</div>
                </li>
                <li>
                    <h3>Compilar e Testar</h3>
                    <div class="code-block">./mvnw clean test</div>
                </li>
                <li>
                    <h3>Executar a Aplicação</h3>
                    <div class="code-block">./mvnw spring-boot:run</div>
                </li>
            </ol>
        </section>

        <section id="arquitetura">
            <h2>🏛️ Arquitetura & Decisões ("O Porquê")</h2>
            <!-- EXPLICAÇÃO MVC/BFF E MOTIVAÇÕES -->
        </section>

        <section id="skills">
            <h2>🧰 Guia de Skills do Repositório</h2>
            <!-- DETALHAMENTO DAS SKILLS DISPONÍVEIS -->
        </section>

        <section id="testes">
            <h2>🧪 Execução de Testes</h2>
            <!-- COMO RODAR TESTES E ACESSAR RELATÓRIOS -->
        </section>
    </main>
</body>
</html>
```
