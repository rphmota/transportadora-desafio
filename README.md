# Sistema de Gestão de Frota e Entregas

## Descrição do Projeto

Este projeto é um sistema de gerenciamento para uma transportadora, permitindo o controle de caminhões e motoristas, bem como o rastreamento de entregas e cargas. O sistema permitirá ao cliente acompanhar os detalhes da frota, como quantidade de caminhões, horários das entregas, tipos de cargas, e os valores totais das entregas por dia.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação.
- **Spring Boot**: Framework para desenvolvimento de aplicações Spring com mais rapidez e facilidade.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados.
- **Spring Data JPA**: Para persistência de dados de forma simplificada e abstrata.
- **Spring Security**: Para autenticação e segurança da aplicação.
- **Spring Web**: Para construção de APIs REST.
- **Lombok**: Para redução de código boilerplate.
- **Spring Boot DevTools**: Para desenvolvimento eficiente com reinicialização automática.
- **Thymeleaf**: Motor de template para aplicações web.
- **Validation**: Para validação de dados de entrada.

## Escopo do Projeto

O sistema permitirá:
- Cadastro e gerenciamento de caminhões e motoristas.
- Rastreamento e registro de entregas, incluindo detalhes sobre cargas, valores e destinos.
- Cálculo automático de valores adicionais baseados em regras específicas de destino das entregas.
- Indicação de entregas valiosas, perigosas e com seguro, conforme tipo de carga e valor.

## Diagrama de Classes

```mermaid
classDiagram
    
    note for Cliente "Cliente controla sua frota de caminhões."
    class Cliente {
        +int clienteId
        +String nome
        +List frota
    }
    
    note for Caminhao "Um caminhão está associado a um motorista e até 4 entregas por mês."
    class Caminhao {
        +int caminhaoId
        +String placa
        +int capacidade
        +Motorista motorista
        +List entregas
    }
    
    note for Motorista "Um motorista só pode fazer duas entregas por mês."
    class Motorista {
        +int motoristaId
        +String nome
        +Caminhao caminhao
        +List entregas
    }
    
    note for Entrega "Detalhes da Entrega: valores > 30mil (Valiosa), eletrônicos (Segurada), combustível (Perigosa)."    
    class Entrega {
        +int entregaId
        +String destino
        +double valorBase
        +double valorTotal
        +Carga carga
        +boolean isValiosa
        +boolean isPerigosa
        +boolean isSegurada
        +Caminhao caminhao
    }
    
    note for Carga "Carga está associada a uma entrega específica."
    class Carga {
        +int cargaId
        +String tipo
        +String descricao
        +Entrega entrega
    }
    
    
    Cliente "1" -- "*" Caminhao
    Caminhao "*" -- "1" Motorista
    Caminhao "1" -- "0..4" Entrega
    Motorista "1" -- "0..2" Entrega
    Entrega "1" -- "1" Carga
```