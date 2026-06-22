## Lancer le projet en local

### Prérequis
- Java 17 ou supérieur
- Maven
- PostgreSQL 17
- Postman (optionnel, pour tester l'API)

### Étapes

**1. Cloner le repository**

```bash
git clone https://github.com/ntchikpo/fraude-api.git
cd fraude-api
```

**2. Créer la base de données**

Ouvrez pgAdmin, connectez-vous à votre serveur PostgreSQL et exécutez :

```sql
CREATE DATABASE fraude_db;
```

**3. Configurer la connexion à la base de données**

Ouvrez le fichier `src/main/resources/application.properties` et modifiez les lignes suivantes avec vos identifiants PostgreSQL :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/fraude_db
spring.datasource.username=postgres
spring.datasource.password=2005
```

**4. Lancer le backend**

```bash
mvn spring-boot:run
```

Le serveur démarre sur `http://localhost:8080`. Les tables sont créées automatiquement dans la base de données au démarrage.

**5. Tester l'API (optionnel)**

Avec Postman ou votre navigateur, vous pouvez tester les endpoints suivants :
GET http://localhost:8080/api/etudiants

GET http://localhost:8080/api/epreuves

GET http://localhost:8080/api/formulaires

GET http://localhost:8080/api/statistiques

**6. Lancer le frontend**

Le backend doit rester en cours d'exécution. Ouvrez un nouveau terminal et suivez les instructions du repository frontend pour lancer l'interface web :

👉 https://github.com/ntchikpo/fraude-frontend

   
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
