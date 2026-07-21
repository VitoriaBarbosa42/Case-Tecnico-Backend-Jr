---
name: code-review
description: Realiza revisão de código em arquitetura MVC/BFF com análise SOLID e Clean Code, gerando relatório HTML estilizado com sugestões acionáveis na pasta report-review.
---

# 🔍 Skill: Code Review & Arquitetura MVC/BFF

Esta skill guia a revisão de código em aplicações estruturadas em padrão MVC e BFF (Backend for Frontend). Seu objetivo é identificar pontos de melhoria, garantindo conformidade com princípios SOLID, Clean Code e boas práticas de segurança e resiliência, gerando feedback acionável e um relatório interativo em HTML.

---

## 🎭 Persona: Especialista em Arquitetura de Software & Code Review

Você atua como um **Arquiteto de Software Principal / Code Reviewer Lead**.
Você valoriza:
- Arquitetura limpa, coesa e de fácil manutenção.
- Separação clara de responsabilidades por camada (MVC/BFF).
- Código legível, testável e seguro.
- Feedback construtivo com comparações claras de código (Antes x Depois).

---

## 📋 Checklist de Análise de Código

Ao revisar o código, valide rigorosamente os seguintes pilares:

### 1. Camadas MVC & BFF
- **Model**: Estrutura de dados coesa, imutabilidade quando aplicável, validações adequadas.
- **View / DTO**: DTOs bem definidos para contratos de entrada/saída, ausência de vazamento de entidades internas.
- **Controller**: Controladores enxutos (*thin controllers*), apenas roteamento e delegação, sem regra de negócio.
- **BFF (Integration / Client)**: Mapeamento correto com APIs externas, tolerância a falhas, tratamento de timeouts/status de erro, sanitização de dados.

### 2. Princípios SOLID
- **Single Responsibility (SRP)**: Cada classe/método possui apenas uma razão para mudar.
- **Open/Closed (OCP)**: Extensível sem necessidade de alterar o código existente.
- **Liskov Substitution (LSP)**: Subtipos podem substituir seus tipos base sem alterar a corretude.
- **Interface Segregation (ISP)**: Interfaces pequenas e focadas.
- **Dependency Inversion (DIP)**: Injeção de dependências e acoplamento a abstrações (interfaces) em vez de implementações concretas.

### 3. Clean Code & Boas Práticas
- Nomenclatura descritiva em inglês/português padronizado.
- Funções pequenas e focadas em uma única tarefa.
- Ausência de código duplicado (DRY).
- Tratamento de exceções específico (evitar capturar/lançar `Exception` genérica ou `NullPointerException` indevida).
- Presença e qualidade de testes unitários/integração.

---

## 🛠️ Fluxo de Trabalho do Agente

### 1. Coleta de Escopo
No início da execução, solicite confirmação ao usuário sobre o escopo da revisão:
> "Deseja revisar **todo o código do projeto** ou apenas as **alterações do último commit / branch**?"

### 2. Análise do Refinamento Técnico
Antes de analisar o código, consulte obrigatoriamente o documento de refinamento técnico em `.agents/skills/criar-docs/refinamentos/refinamento.md` (ou `/criar-docs/refinamentos`) para entender as regras de negócio acordadas (ex: consumo do ViaCEP, remoção dos campos IBGE, GIA, DDD e Siafe, conversão do logradouro para lowercase) e verificar se o código cumpre esses requisitos.

### 3. Análise de Código
Percorra os arquivos do escopo selecionado aplicando o checklist de qualidade (MVC, BFF, SOLID, Clean Code e alinhamento com o refinamento).

### 4. Categorização das Sugestões
Classifique cada apontamento conforme a estrutura:
- **Categoria**: `Crítico` (bloqueante / erro grave) | `Importante` (débito técnico / melhoria estrutural) | `Sugestão` (qualidade de código / refinamento)
- **Componente**: `Model` | `View` | `Controller` | `Service` | `BFF` | `Infra` | `Error Handler` | `Utils` | `Tests`
- **Título**: Resumo claro e acionável
- **Problema**: Localização exata (arquivo/linhas) e descrição da violação
- **Impacto**: Risco técnico e atributos afetados (Manutenibilidade, Performance, Segurança, Confiabilidade)
- **Solução Sugerida**: Passos de resolução e exemplo comparativo de código (Antes x Depois)

---

## 📊 Geração Obrigatória de Relatório HTML

Ao finalizar a análise, o agente **DEVE obrigatoriamente**:

1. **Garantir a existência da pasta `report-review/`** na raiz do projeto (criando a pasta se ela não existir).
2. **Gerar o arquivo de relatório HTML final** em `report-review/code-review-report.html`.

### Conteúdo Mínimo do Relatório:
- **Header & Metadados**: Data da revisão, escopo analisado, linguagem/framework, score ou resumo executivo.
- **Estatísticas Visuais**: Total de achados por severidade (Crítico, Importante, Sugestão) e por camada.
- **Seção de Achados Críticos**: Apresentação detalhada com código Antes x Depois lado a lado ou empilhado.
- **Seção de Achados Importantes e Sugestões**: Agrupados por componente.
- **Matriz de Risco & Esforço**: Risco associado e estimativa de esforço para correção.
- **Roadmap / Próximos Passos**: Recomendações priorizadas de ação.

---

## 🎨 Template Estrutural do Relatório HTML

Ao gerar `report-review/code-review-report.html`, utilize a estrutura e os estilos CSS abaixo como padrão de design:

```html
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Relatório de Code Review</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
            line-height: 1.6;
            color: #333;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh;
            padding: 20px;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            border-radius: 12px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.15);
            overflow: hidden;
        }

        header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 40px;
            text-align: center;
        }

        header h1 {
            font-size: 2.3em;
            margin-bottom: 10px;
        }

        header p {
            font-size: 1.1em;
            opacity: 0.9;
        }

        .metadata {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            padding: 25px 40px;
            background: #f8f9fa;
            border-bottom: 1px solid #e9ecef;
        }

        .meta-item {
            text-align: center;
        }

        .meta-label {
            font-size: 0.85em;
            color: #6c757d;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-bottom: 6px;
        }

        .meta-value {
            font-size: 1.3em;
            font-weight: bold;
            color: #667eea;
        }

        nav {
            padding: 15px 40px;
            border-bottom: 2px solid #e9ecef;
            display: flex;
            gap: 15px;
            flex-wrap: wrap;
            background: #fff;
        }

        nav a {
            text-decoration: none;
            color: #667eea;
            font-weight: 600;
            padding: 8px 16px;
            border-radius: 6px;
            transition: all 0.3s ease;
        }

        nav a:hover {
            background: #667eea;
            color: white;
        }

        .content {
            padding: 40px;
        }

        section {
            margin-bottom: 50px;
        }

        h2 {
            font-size: 1.8em;
            color: #667eea;
            margin-bottom: 25px;
            padding-bottom: 10px;
            border-bottom: 3px solid #667eea;
            display: flex;
            align-items: center;
            gap: 12px;
        }

        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(160px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }

        .stat-card {
            background: #f8f9fa;
            border-left: 5px solid #667eea;
            padding: 20px;
            border-radius: 8px;
            text-align: center;
        }

        .stat-card.critical { border-left-color: #dc3545; }
        .stat-card.warning { border-left-color: #ffc107; }
        .stat-card.info { border-left-color: #17a2b8; }

        .stat-number {
            font-size: 2.2em;
            font-weight: bold;
            color: #667eea;
        }

        .stat-card.critical .stat-number { color: #dc3545; }
        .stat-card.warning .stat-number { color: #d99b00; }
        .stat-card.info .stat-number { color: #17a2b8; }

        .stat-label {
            color: #6c757d;
            font-size: 0.9em;
            margin-top: 6px;
            font-weight: 500;
        }

        .review-item {
            background: #f8f9fa;
            border-left: 5px solid #667eea;
            padding: 25px;
            margin-bottom: 25px;
            border-radius: 8px;
            transition: all 0.3s ease;
        }

        .review-item.critical {
            border-left-color: #dc3545;
            background: #fff5f5;
        }

        .review-item.warning {
            border-left-color: #ffc107;
            background: #fffbf0;
        }

        .review-item.info {
            border-left-color: #17a2b8;
            background: #f0f8f9;
        }

        .review-header {
            display: flex;
            align-items: center;
            gap: 12px;
            margin-bottom: 12px;
        }

        .badge {
            display: inline-block;
            padding: 4px 12px;
            border-radius: 20px;
            font-size: 0.8em;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .badge.critical { background: #dc3545; color: white; }
        .badge.warning { background: #ffc107; color: #333; }
        .badge.info { background: #17a2b8; color: white; }
        .badge.component { background: #667eea; color: white; text-transform: none; }

        .review-title {
            font-size: 1.25em;
            font-weight: 700;
            color: #2d3748;
        }

        .review-section {
            margin-top: 15px;
        }

        .review-section-title {
            font-weight: 600;
            color: #4a5568;
            margin-bottom: 6px;
            font-size: 0.95em;
        }

        .code-comparison {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            margin: 15px 0;
        }

        @media (max-width: 768px) {
            .code-comparison { grid-template-columns: 1fr; }
        }

        .comparison-label {
            font-size: 0.85em;
            font-weight: 700;
            text-transform: uppercase;
            margin-bottom: 6px;
            color: #4a5568;
        }

        .code-block {
            background: #1e1e1e;
            color: #d4d4d4;
            padding: 16px;
            border-radius: 6px;
            overflow-x: auto;
            font-family: 'Consolas', 'Monaco', 'Courier New', monospace;
            font-size: 0.88em;
            line-height: 1.5;
        }

        .code-block.before { border-left: 4px solid #dc3545; }
        .code-block.after { border-left: 4px solid #28a745; }

        .impact {
            display: flex;
            gap: 20px;
            margin-top: 15px;
            padding-top: 15px;
            border-top: 1px solid rgba(0, 0, 0, 0.08);
        }

        .impact-item { flex: 1; }

        .impact-label {
            font-size: 0.8em;
            font-weight: 700;
            color: #667eea;
            text-transform: uppercase;
        }

        .impact-value { color: #4a5568; font-size: 0.95em; }

        footer {
            background: #f8f9fa;
            padding: 25px 40px;
            text-align: center;
            color: #6c757d;
            border-top: 1px solid #e9ecef;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1>📋 Relatório de Code Review</h1>
            <p>Análise de Qualidade, Arquitetura MVC/BFF, SOLID e Clean Code</p>
        </header>

        <div class="metadata">
            <div class="meta-item">
                <div class="meta-label">Data</div>
                <div class="meta-value"><!-- DATA DE EXECUÇÃO --></div>
            </div>
            <div class="meta-item">
                <div class="meta-label">Escopo</div>
                <div class="meta-value"><!-- ESCOPO SELECIONADO --></div>
            </div>
            <div class="meta-item">
                <div class="meta-label">Arquitetura</div>
                <div class="meta-value"><!-- ARQUITETURA --></div>
            </div>
            <div class="meta-item">
                <div class="meta-label">Linguagem / Stack</div>
                <div class="meta-value"><!-- STACK DO PROJETO --></div>
            </div>
        </div>

        <nav>
            <a href="#resumo">📊 Resumo</a>
            <a href="#criticos">⚡ Críticos</a>
            <a href="#importantes">📝 Importantes</a>
            <a href="#sugestoes">💡 Sugestões</a>
            <a href="#proximos">➡️ Próximos Passos</a>
        </nav>

        <div class="content">
            <section id="resumo">
                <h2>Resumo Executivo</h2>
                <div class="stats-grid">
                    <div class="stat-card critical">
                        <div class="stat-number"><!-- QTD CRÍTICOS --></div>
                        <div class="stat-label">Críticos</div>
                    </div>
                    <div class="stat-card warning">
                        <div class="stat-number"><!-- QTD IMPORTANTES --></div>
                        <div class="stat-label">Importantes</div>
                    </div>
                    <div class="stat-card info">
                        <div class="stat-number"><!-- QTD SUGESTÕES --></div>
                        <div class="stat-label">Sugestões</div>
                    </div>
                </div>
            </section>

            <section id="criticos">
                <h2>Achados Críticos</h2>
                <!-- ITENS CRÍTICOS COM COMPARAÇÃO ANTES/DEPOIS -->
            </section>

            <section id="importantes">
                <h2>Achados Importantes</h2>
                <!-- ITENS IMPORTANTES -->
            </section>

            <section id="sugestoes">
                <h2>Sugestões de Melhoria</h2>
                <!-- SUGESTÕES -->
            </section>

            <section id="proximos">
                <h2>Próximos Passos & Roadmap</h2>
                <!-- PLANO DE AÇÃO -->
            </section>
        </div>

        <footer>
            Gerado automaticamente por Code Review Skill • Agentic AI
        </footer>
    </div>
</body>
</html>
```