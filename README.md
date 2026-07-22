# Case Técnico - Backend Jr | Rede de Agências

## 📋 Sobre o Projeto

Aplicação web desenvolvida em **Java com Spring Boot** que funciona como um **BFF (Backend for Frontend)** para integração com a API pública dos Correios ([ViaCEP](https://viacep.com.br/)). O sistema permite que gerentes consultem informações de endereços de clientes durante o processo de venda, validando e comparando dados com o histórico no banco.

### 🎯 Objetivo

Implementar uma operação **READ (Leitura)** que:
- Consulta informações de logradouro através de CEP na API ViaCEP
- Remove campos desnecessários (IBGE, GIA, DDD, Siafe)
- Converte o logradouro para **lowercase** antes de retornar
- Apresenta as informações em um formato limpo e padronizado

---

## 🏗️ Arquitetura

O projeto segue o padrão **MVC (Model-View-Controller)** com as seguintes camadas:

```
src/main/java/com/desafio/casebackend/
│
├── controller/           # Camada de apresentação (REST API)
│   └── ViaCepController
│
├── service/              # Camada de negócio
│   └── ViaCepService
│
├── client/               # Cliente HTTP para API externa
│   └── ViaCepClient
│
├── DTOs/                 # Objetos de transferência de dados
│   └── ViaCepResponseDTO
│
├── utils/                # Utilitários e validações
│   └── Validadores
│
├── error/                # Tratamento de erros
│   ├── GlobalExceptionHandler
│   └── ErrorResponse
│
└── DesafioJuniorApplication  # Classe principal
```

### Fluxo de Requisição

```
HTTP Request (GET /viacep/{cep})
    ↓
ViaCepController
    ↓
ViaCepService (validação e orquestração)
    ↓
Validadores (validação de CEP)
    ↓
ViaCepClient (chamada à API ViaCEP)
    ↓
ViaCepResponseDTO (resposta filtrada)
    ↓
HTTP Response (JSON)
```

---

## 🚀 Como Executar

### Pré-requisitos

- **Java 21** ou superior
- **Maven 3.8.1** ou superior
- IDE recomendada: **IntelliJ IDEA**

### Instalação e Execução

1. **Clone o repositório**
   ```bash
   git clone https://github.com/VitoriaBarbosa42/Case-Tecnico-Backend-Jr.git
   cd Case-Tecnico-Backend-Jr/desafio-junior
   ```

2. **Instale as dependências**
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

   Ou compile e execute diretamente:
   ```bash
   mvn clean package
   java -jar target/desafio-junior-0.0.1-SNAPSHOT.jar
   ```

4. **Acesse a aplicação**
   - A aplicação estará disponível em: `http://localhost:8080`

---

## 📡 Endpoints da API

### Consultar Endereço por CEP

**Requisição:**
```http
GET /viacep/{cep}
```

**Parâmetros:**
- `cep` (path parameter): CEP no formato numérico (8 dígitos) ou com hífen (XXXXX-XXX)

**Exemplo de Requisição:**
```bash
curl -X GET "http://localhost:8080/viacep/01001000"
```

**Resposta Sucesso (200 OK):**
```json
{
  "cep": "01001-000",
  "logradouro": "praça da sé",
  "complemento": "lado ímpar",
  "bairro": "Sé",
  "localidade": "São Paulo"
}
```

**Exemplo com CEP formatado:**
```bash
curl -X GET "http://localhost:8080/viacep/01001-000"
```

---

## ✅ Validações Implementadas

### Validação de CEP (classe `Validadores`)

A validação garante:

1. **CEP não nulo nem vazio**
   - Lança `NullPointerException` se CEP for null ou em branco
   
2. **CEP com 8 dígitos numéricos**
   - Remove caracteres não-numéricos (ex: hífens)
   - Lança `IllegalArgumentException` se não contiver exatamente 8 dígitos

### Exemplos:

| CEP | Resultado |
|-----|-----------|
| `01001000` | ✅ Válido |
| `01001-000` | ✅ Válido (hífen removido) |
| `0100100` | ❌ Erro: menos de 8 dígitos |
| `010010000` | ❌ Erro: mais de 8 dígitos |
| `null` | ❌ Erro: CEP nulo |
| `` (vazio) | ❌ Erro: CEP em branco |

---

## ⚠️ Tratamento de Erros

O sistema implementa tratamento centralizado de erros através de `GlobalExceptionHandler`:

### Erro 400 - Requisição Inválida

**Causa:** CEP inválido, nulo ou com formato incorreto

**Resposta:**
```json
{
  "timestamp": "2024-01-15T10:30:45.123",
  "status": 400,
  "error": "Requisição Inválida",
  "message": "O CEP deve conter 8 caracteres numericos",
  "path": "/viacep/123"
}
```

### Tratamento de Exceções

- `NullPointerException`: CEP nulo ou em branco
- `IllegalArgumentException`: Formato de CEP inválido

---

## 🧪 Testes Unitários

O projeto inclui testes unitários para garantir a qualidade:

### Testes de Validação (`ValidadoresTest`)

- ✅ Validação de CEP com 8 dígitos
- ✅ Rejeição de CEP com menos de 8 dígitos
- ✅ Rejeição de CEP com mais de 8 dígitos
- ✅ Rejeição de CEP nulo ou vazio

### Testes de Serviço (`ViaCepServiceTest`)

- ✅ Retorno correto de resposta quando API externa responde com sucesso
- ✅ Mocking da chamada à ViaCepClient

### Executar Testes

```bash
mvn test
```

### Executar com Cobertura

```bash
mvn test jacoco:report
```

---

## 📦 Dependências

| Dependência | Versão | Propósito |
|-------------|--------|----------|
| Spring Boot | 3.3.4 | Framework principal |
| Spring Web | 3.3.4 | REST API |
| Spring Validation | 3.3.4 | Validações |
| Spring Cloud OpenFeign | 2023.0.3 | Cliente HTTP declarativo |
| Lombok | 1.18.36 | Redução de boilerplate |
| JUnit 5 | 5.x | Framework de testes |
| Mockito | 4.x | Mocking em testes |
| WireMock | 2.35.0 | Mock de APIs externas (testes) |

Todas as dependências estão definidas em `pom.xml`.

---

## 🛠️ Funcionalidades Implementadas

### ✅ Requisitos Atendidos

- [x] **Conexão com API ViaCEP** - Integração com endpoint `https://viacep.com.br/ws/{cep}/json`
- [x] **Operação READ** - Busca e retorno de dados de endereço
- [x] **Remoção de campos** - IBGE, GIA, DDD e Siafe removidos da resposta
- [x] **Lowercase no logradouro** - Campo convertido para minúsculas
- [x] **Padrão MVC** - Arquitetura com Controller, Service, Client
- [x] **Boas práticas** - Nomes descritivos, sem comentários desnecessários
- [x] **Testes unitários** - Cobertura de validações e serviço
- [x] **Tratamento de erros** - GlobalExceptionHandler com mensagens claras
- [x] **Versionamento Git** - Repositório público no GitHub

---

## 📊 Estrutura de Resposta

### Dados Retornados

```json
{
  "cep": "01001-000",                    // CEP formatado
  "logradouro": "praça da sé",           // Logradouro em lowercase
  "complemento": "lado ímpar",           // Informação complementar
  "bairro": "Sé",                        // Bairro
  "localidade": "São Paulo"              // Localidade/Cidade
}
```

### Dados Removidos (conforme requisito)

- ❌ `ibge` - Código IBGE
- ❌ `gia` - GIA
- ❌ `ddd` - DDD
- ❌ `siafe` - Siafe

---

## 🔍 Detalhes Técnicos

### Spring Cloud OpenFeign

O projeto utiliza **OpenFeign** para criar um cliente HTTP declarativo:

```java
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws/")
public interface ViaCepClient {
    @GetMapping("/{cep}/json")
    ViaCepResponseDTO getEnderecoCep(@PathVariable String cep);
}
```

**Vantagens:**
- Código mais limpo e legível
- Menos boilerplate comparado a RestTemplate
- Suporte automático a fallbacks (com circuit breaker)

### Records (Java 16+)

O projeto utiliza **Records** para DTOs, reduzindo boilerplate:

```java
public record ViaCepResponseDTO(
    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String localidade
) {}
```

**Vantagens:**
- Imutabilidade
- Getters, equals(), hashCode() e toString() gerados automaticamente
- Código mais conciso

---

## 🧠 Raciocínio de Design

### Escolhas Arquiteturais

1. **MVC com Service** - Separação clara de responsabilidades
2. **Client Externo** - Facilita testes e manutenção
3. **DTOs** - Contrato claro entre camadas
4. **Validação Centralizada** - Validadores reutilizáveis
5. **GlobalExceptionHandler** - Tratamento consistente de erros

### Por que OpenFeign?

- ✅ Declarativo e intuitivo
- ✅ Integração nativa com Spring Cloud
- ✅ Menos código que RestTemplate
- ✅ Facilita testes com mocks

### Por que Records?

- ✅ DTOs são imutáveis por design
- ✅ Menos linhas de código
- ✅ Melhor legibilidade
- ✅ Suporte completo no Java 21

---

## 🔧 Configuração

### application.properties

```properties
spring.application.name=desafio-junior
server.error.include-message=always
```

**Configurações:**
- Nome da aplicação: `desafio-junior`
- Incluir mensagens de erro sempre (útil para debugging)

---

## 📝 Exemplo de Uso Completo

### 1. Requisição para São Paulo

```bash
curl -X GET "http://localhost:8080/viacep/01001-000"
```

**Resposta:**
```json
{
  "cep": "01001-000",
  "logradouro": "praça da sé",
  "complemento": "lado ímpar",
  "bairro": "Sé",
  "localidade": "São Paulo"
}
```

### 2. Requisição com CEP inválido

```bash
curl -X GET "http://localhost:8080/viacep/123"
```

**Resposta (400 Bad Request):**
```json
{
  "timestamp": "2026-01-15T10:32:10.456",
  "status": 400,
  "error": "Requisição Inválida",
  "message": "O CEP deve conter 8 caracteres numericos",
  "path": "/viacep/123"
}
```

### 3. Requisição com CEP nulo

```bash
curl -X GET "http://localhost:8080/viacep/"
```

---

## 🚦 Status do Projeto

| Item | Status |
|------|--------|
| Conexão ViaCEP | ✅ Completo |
| Operação READ | ✅ Completo |
| Validações | ✅ Completo |
| Testes | ✅ Completo |
| Tratamento de Erros | ✅ Completo |
| Documentação | ✅ Completo |

---

## 🧰 Ferramentas de Automação & Skills do Projeto

Este repositório possui suporte a automações baseadas em agentes AI localizadas em `.agents/skills/`. Os desenvolvedores podem usá-las para auditoria e relatórios rápidos:

- 🔍 **code-review**: Analisa a conformidade SOLID, Clean Code e MVC, gerando relatórios em [code-review-report.html](file:///c:/Users/vitor/Downloads/case-jr/Case-Tecnico-Backend-Jr/report-review/code-review-report.html).
- 🛡️ **unit-testing**: Executa o diagnóstico da qualidade e cobertura de testes, gerando relatórios em `desafio-junior/report-testes/unit-testing-report.html`.
- 📚 **criar-docs**: Técnico de Documentação para gerar relatórios e manter o README, produzindo o interativo [documentation-report.html](file:///c:/Users/vitor/Downloads/case-jr/Case-Tecnico-Backend-Jr/report-docs/documentation-report.html).

---

## 📚 Recursos Adicionais

- [ViaCEP Documentation](https://viacep.com.br/)
- [Spring Boot Official Docs](https://spring.io/projects/spring-boot)
- [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
- [Java Records](https://docs.oracle.com/en/java/javase/21/docs/api/java.lang/Record.html)

---

## 👨‍💻 Autor

**Vitória Barbosa**  
GitHub: [@VitoriaBarbosa42](https://github.com/VitoriaBarbosa42)

---

## 📄 Licença

Este projeto é um case técnico para avaliação.

---

## 🤝 Contribuições

Este é um projeto de avaliação técnica. Sugestões e melhorias são bem-vindas através de issues ou pull requests.

