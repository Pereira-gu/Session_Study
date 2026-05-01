# Studytime API ⏱️
## Problema / Objetivo
O controle impreciso de horas de estudo e a falta de confiabilidade nos dados (típico de sistemas 
onde o próprio cliente ou frontend calcula o tempo) dificultam a análise correta da produtividade.

O objetivo deste projeto é fornecer uma fonte de dados verídica. 
O próprio servidor calcula e consolida o tempo focado pelo usuário, protegendo a aplicação contra 
manipulações e garantindo métricas confiáveis para análises futuras (como gráficos e relatórios).

---

## 🚀 Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias do ecossistema Java e Spring:

* **Java 21**
* **Spring Boot** (Spring Web, Spring Data JPA, Validation)
* **PostgreSQL**
* **Lombok**
* **Maven**

---

## ⚙️ Arquitetura e Segurança

* **Cálculo no Servidor:** O sistema calcula a duração exata da sessão entre o horário de início e o de término no próprio backend, evitando que o usuário altere os dados manualmente.
* **Agregação Otimizada:** Consulta de soma de tempos otimizada diretamente no banco de dados utilizando a função `SUM()` do SQL, o que melhora a performance e evita o tráfego desnecessário de dados.
* **Testes Unitários:** Validação das regras de negócio e proteção contra sessões inválidas utilizando JUnit e Mockito.

---