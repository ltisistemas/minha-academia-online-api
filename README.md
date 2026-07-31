# Minha Academia Online API

Uma API RESTful escrita em Java para gerenciar os dados e operações da plataforma "Minha Academia Online" — um sistema completo para administração de alunos, instrutores, planos, aulas, presenças e pagamentos.

Este README foi escrito de forma objetiva, elegante e profissional, por um desenvolvedor sênior. Aqui você encontrará tudo o que precisa para rodar, desenvolver e colaborar com o projeto.

---

## Índice

- [Visão Geral](#visão-geral)
- [Principais Funcionalidades](#principais-funcionalidades)
- [Tecnologias e Arquitetura](#tecnologias-e-arquitetura)
- [Requisitos](#requisitos)
- [Instalação e Execução Local](#instalação-e-execução-local)
- [Configuração (variáveis de ambiente)](#configuração-variáveis-de-ambiente)
- [Banco de Dados e Migrations](#banco-de-dados-e-migrations)
- [Testes](#testes)
- [Documentação da API](#documentação-da-api)
- [CI / CD](#ci--cd)
- [Boas práticas e Contribuição](#boas-práticas-e-contribuição)
- [Roadmap](#roadmap)
- [Licença](#licença)
- [Contato / Maintainers](#contato--maintainers)

---

## Visão Geral

Minha Academia Online API expõe endpoints para gerenciar recursos essenciais de uma academia: cadastros (alunos, instrutores), planos e assinaturas, agendamento de aulas, registro de presença e integração com provedores de pagamento. A API foi projetada para ser segura, escalável e observável.

Objetivos deste repositório:

- Ser uma base sólida para o produto em produção;
- Ser fácil de entender e manter por novos desenvolvedores;
- Fornecer práticas modernas de desenvolvimento (testes, CI, containerização, documentação).


## Principais Funcionalidades

- Autenticação e autorização (JWT/OAuth — ajuste conforme implementação);
- CRUD completo para Alunos, Instrutores, Planos, Aulas e Matrículas;
- Agendamento de aulas e controle de presenças;
- Integração com gateway de pagamento (webhooks, conciliador);
- Relatórios básicos (faturamento, frequência);
- Health checks e métricas observáveis (Prometheus/OpenTelemetry — opcional).


## Tecnologias e Arquitetura

- Linguagem: Java (versão compatível: 11+ ou 17+ conforme build);
- Framework web: (ex.: Spring Boot) — ajustar conforme implementação real do projeto;
- Persistência: Banco relacional (ex.: PostgreSQL) com migrations (Flyway/Liquibase);
- Build: Maven ou Gradle;
- Contêineres: Docker + Docker Compose para desenvolvimento local;
- Testes: JUnit + Mockito / Testcontainers para testes de integração;
- Observabilidade: logs estruturados (JSON), métricas e health endpoints;

Observação: atualize as seções acima com as escolhas reais do projeto (framework, versão do Java e ferramentas).


## Requisitos

- Java 11 ou 17 (conforme configuração do projeto)
- Maven 3.6+ ou Gradle (conforme projeto)
- Docker & Docker Compose (recomendado para ambiente local)
- PostgreSQL (para desenvolvimento se não usar containers)


## Instalação e Execução Local

1. Clone o repositório:

```bash
git clone https://github.com/ltisistemas/minha-academia-online-api.git
cd minha-academia-online-api
```

2. Crie um arquivo de variáveis de ambiente `.env` (exemplo abaixo) ou configure via seu gerenciador de segredos.

3. Se estiver usando Docker Compose para o ambiente de desenvolvimento:

```bash
# Levantar serviços (app, db)
docker compose up --build
```

4. Rodar localmente via Maven:

```bash
# Compilar e executar
./mvnw clean spring-boot:run
# ou
mvn clean package
java -jar target/minha-academia-online-api-<versão>.jar
```

Substitua os comandos conforme a ferramenta de build usada no projeto.


## Configuração (variáveis de ambiente)

Crie um arquivo `.env` ou configure seu ambiente com as variáveis necessárias. Exemplo mínimo:

```dotenv
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/minha_academia
SPRING_DATASOURCE_USERNAME=sa
SPRING_DATASOURCE_PASSWORD=senha
SPRING_PROFILES_ACTIVE=local
JWT_SECRET=uma_chave_secreta_long_e_segura
PAYMENT_PROVIDER_API_KEY=insira_aqui
LOG_LEVEL=INFO
```

Anote: nomes exatos das variáveis podem variar dependendo da implementação — verifique `application.properties` ou `application.yaml`.


## Banco de Dados e Migrations

Este projeto utiliza migrations para controlar o esquema do banco de dados. Procedimentos recomendados:

- Desenvolver alterações de esquema via scripts de migration (Flyway ou Liquibase);
- Sempre rodar migrations em CI antes de executar os testes de integração;
- Evitar alterações breaking sem migração reversível ou sem plano de rollback.

Exemplo (Flyway):

```bash
# Aplicar migrations localmente
./mvnw flyway:migrate
```


## Testes

Estrutura de testes:

- Unitários: rápidos, isolados (Mockito, JUnit);
- Integração: cobrem integração com banco / web / provedores (use Testcontainers);
- End-to-end (opcional): testar fluxos completos com ambiente próximo ao de produção.

Como executar:

```bash
# Testes unitários e de integração
./mvnw test
```

Boas práticas:

- Mantenha testes confiáveis e determinísticos;
- Evite dependência direta de serviços externos — use stubs/mocks ou Testcontainers;
- Use coverage mínimo (ex.: 70%) como métrica, não como objetivo final.


## Documentação da API

- Documente todos os endpoints com Swagger/OpenAPI — inclua exemplos de request/response;
- Versione a API (ex.: /api/v1/...) para garantir compatibilidade;
- Forneça exemplos de uso (Postman collection, cURL) no repositório.

Exemplo de health endpoint:

```
GET /actuator/health
```


## CI / CD

Recomendações:

- Build, lint e testes em PRs;
- Migrations aplicadas em etapas controladas no deploy;
- Deploy automatizado para staging e manual para produção (ou com approvals);
- Fazer rollback rápido: versionamento de releases e backups de banco antes de migrações críticas.


## Boas práticas e Contribuição

Contribuições são bem-vindas. Algumas regras para manter a qualidade:

- Siga o guia de estilo de código (formatador/Checkstyle/SpotBugs);
- Escreva testes para novas funcionalidades e correções de bugs;
- Abra PRs pequenos e focados — descreva claramente o que muda e por quê;
- Use Branches com nomes descritivos: `feature/`, `fix/`, `chore/`;
- Atualize o README e documentação quando necessário.

Modelo de commit (conventional commits recomendado):

```
feat(pagamento): adicionar webhook para conciliação
fix(usuario): corrigir validação do CPF
chore(ci): ajustar cache do maven
```


## Segurança

- Nunca coloque segredos no repositório;
- Use vaults/secret managers para chaves em produção;
- Rotacione chaves e tokens periodicamente;
- Valide e sanitize entradas para evitar injeções;
- Monitore logs de segurança e configure alertas.


## Observabilidade

- Exponha métricas via Prometheus e traces via OpenTelemetry quando aplicável;
- Logs estruturados e centralizados (ex.: Loki/ELK);
- Health checks e readiness probes para orquestradores (Kubernetes).


## Roadmap (exemplos)

- MVP: CRUD completo e integração de pagamentos;
- v1.1: Módulo de relatórios e exportação CSV/PDF;
- v1.2: Mobile-first optimizations e endpoints de performance;
- v2.0: Multi-tenancy e internacionalização.


## Licença

Escolha e adicione uma licença apropriada (MIT, Apache-2.0, etc.). Se ainda não tiver, recomendo `MIT` ou `Apache-2.0` para projetos open-source.


## Contato / Maintainers

- Maintainer: LTISistemas
- Repositório: https://github.com/ltisistemas/minha-academia-online-api


---

Se desejar, posso:

- Ajustar o README com detalhes específicos (framework exato, comandos Maven/Gradle, exemplos de endpoints reais);
- Incluir um Postman collection ou exemplos práticos de chamadas;
- Adicionar um CONTRIBUTING.md e templates de PR/ISSUE.

Diga qual desses ajustes você prefere e eu implemento em seguida.
