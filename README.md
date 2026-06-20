# Fraude API - Spring Boot

API REST de gestion des fraudes académiques, développée 
à partir d'une application console Java existante.

## Contexte
Ce projet est la transformation d'une application console 
de gestion des fraudes (ESEO Angers) en API REST fullstack 
connectée à une base de données PostgreSQL.

## Technologies
- Java 17
- Spring Boot 4.1
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven

## Architecture
### Entités
| Entité | Description |
|--------|-------------|
| Etudiant | Étudiant impliqué dans une fraude |
| Epreuve | Épreuve concernée |
| Formulaire | Relie épreuve, étudiants et fraudes |
| Fraude | Classe abstraite (héritage JOINED) |
| FraudeIAG | Fraude via IA générative |
| FraudeIAGConnectee | Fraude IAG avec accès réseau |
| FraudePapier | Fraude via document papier |
| FraudeCalculatrice | Fraude via calculatrice programmable |

### Héritage JPA
