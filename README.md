## 🚀 Lancer le projet en local

### Prérequis
- Java 17+
- Maven
- PostgreSQL 17

### Étapes

1. Cloner le repo
   git clone https://github.com/ntchikpo/fraude-api.git

2. Créer la base de données dans PostgreSQL
   CREATE DATABASE fraude_db;

3. Configurer application.properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/fraude_db
   spring.datasource.username=postgres
   spring.datasource.password=VOTRE_MOT_DE_PASSE

4. Lancer le backend
   mvn spring-boot:run

   → L'API tourne sur http://localhost:8080

   
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
