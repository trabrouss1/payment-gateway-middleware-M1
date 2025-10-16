# Middleware de Passerelle de Paiement Sécurisée

Ce projet implémente un middleware de passerelle de paiement sécurisée pour une banque numérique, visant à centraliser et sécuriser les échanges entre les applications internes et les partenaires externes.

## Architecture

L'architecture est basée sur des microservices Spring Boot et inclut les composants principaux suivants :

*   **Eureka Server** : Serveur de découverte de services pour l'enregistrement et la découverte des microservices.
*   **API Gateway (Spring Cloud Gateway)** : Point d'entrée unique pour toutes les requêtes externes, gérant le routage, la sécurité et la résilience.
*   **Transaction Orchestrator (Spring Boot)** : Microservice responsable de l'orchestration des transactions inter-services (paiement, comptabilité, notification).
*   **Payment Service (Spring Boot)** : Microservice gérant le traitement des paiements.
*   **Notification Service (Spring Boot)** : Microservice responsable de l'envoi des notifications.
*   **Accounting Service (Spring Boot)** : Microservice gérant la logique comptable.
*   **Kafka** : Message broker pour la communication asynchrone entre les microservices.
*   **ELK Stack (Elasticsearch, Logstash, Kibana)** : Pour la journalisation centralisée.
*   **Prometheus & Grafana** : Pour la surveillance et la visualisation des métriques.

## Technologies Utilisées

*   Java 21
*   Spring Boot 3.5.6
*   Spring Cloud (Gateway, Netflix Eureka)
*   Apache Kafka
*   Maven
*   Docker
*   Spring Security

## Structure du Projet

```
payment-gateway-middleware/
├── api-gateway/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/com/example/apigateway/
│           │   ├── config/SecurityConfig.java
│           │   └── ApiGatewayApplication.java
│           └── resources/application.yml
├── transaction-orchestrator/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/com/example/orchestrator/
│           │   ├── config/SecurityConfig.java
│           │   ├── controller/TransactionController.java
│           │   ├── service/TransactionService.java
│           │   └── TransactionOrchestratorApplication.java
│           └── resources/application.yml
├── payment-service/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/com/example/paymentservice/
│           │   ├── config/SecurityConfig.java
│           │   ├── controller/PaymentController.java
│           │   └── PaymentServiceApplication.java
│           └── resources/application.yml
├── notification-service/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/com/example/notificationservice/
│           │   ├── config/SecurityConfig.java
│           │   ├── listener/TransactionEventListener.java
│           │   └── NotificationServiceApplication.java
│           └── resources/application.yml
├── accounting-service/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/com/example/accountingservice/
│           │   ├── listener/TransactionEventListener.java
│           │   └── AccountingServiceApplication.java
│           └── resources/application.yml
├── eureka-server/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/com/example/eurekaserver/EurekaServerApplication.java
│           └── resources/application.yml
├── documentation/
│   └── C4_model.mmd
│   └── openapi-spec.yaml
└── README.md
```

## Démarrage Rapide

1.  **Prérequis** :
    *   Java 21
    *   Maven
    *   Docker et Docker Compose

2.  **Cloner le dépôt** :
    ```bash
    git clone <URL_DU_DEPOT>
    cd payment-gateway-middleware
    ```

3.  **Démarrer Kafka et Zookeeper (via Docker Compose)** :
    Créez un fichier `docker-compose.yml` à la racine du projet avec les services Kafka et Zookeeper.
    ```yaml
    version: '3.8'
    services:
      zookeeper:
        image: confluentinc/cp-zookeeper:7.0.1
        hostname: zookeeper
        container_name: zookeeper
        ports:
          - "2181:2181"
        environment:
          ZOOKEEPER_CLIENT_PORT: 2181
          ZOOKEEPER_TICK_TIME: 2000

      kafka:
        image: confluentinc/cp-kafka:7.0.1
        hostname: kafka
        container_name: kafka
        ports:
          - "9092:9092"
          - "9093:9093"
        environment:
          KAFKA_BROKER_ID: 1
          KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
          KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:9093
          KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
          KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
          KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
          KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
          KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
        depends_on:
          - zookeeper
    ```
    Puis exécutez :
    ```bash
    docker-compose up -d
    ```

4.  **Construire et démarrer les microservices (dans l'ordre)** :

    *   **Eureka Server** :
        ```bash
        cd eureka-server
        mvn clean install
        mvn spring-boot:run
        ```
    *   **API Gateway** :
        ```bash
        cd ../api-gateway
        mvn clean install
        mvn spring-boot:run
        ```
    *   **Transaction Orchestrator** :
        ```bash
        cd ../transaction-orchestrator
        mvn clean install
        mvn spring-boot:run
        ```
    *   **Payment Service** :
        ```bash
        cd ../payment-service
        mvn clean install
        mvn spring-boot:run
        ```
    *   **Notification Service** :
        ```bash
        cd ../notification-service
        mvn clean install
        mvn spring-boot:run
        ```
    *   **Accounting Service** :
        ```bash
        cd ../accounting-service
        mvn clean install
        mvn spring-boot:run
        ```

    Assurez-vous que chaque service est démarré avant de passer au suivant. Vous pouvez vérifier l'état des services via le tableau de bord Eureka à `http://localhost:8761`.

## Utilisation

Pour initier une transaction, envoyez une requête POST à l'API Gateway :

```bash
curl -X POST -H "Content-Type: application/json" -d '{"amount": 100, "currency": "EUR", "accountId": "12345"}' http://localhost:8080/payment/transactions/initiate -u user:password
```

L'API Gateway routera la requête vers l'orchestrateur de transactions, qui à son tour enverra des événements Kafka aux services de paiement, de notification et de comptabilité.

## Documentation OpenAPI

La documentation OpenAPI sera générée pour chaque microservice. Vous pouvez y accéder via l'API Gateway (par exemple, `http://localhost:8080/v3/api-docs` pour l'API Gateway elle-même, ou via les routes des microservices).

## Surveillance

Les métriques des applications sont exposées via Spring Boot Actuator et peuvent être collectées par Prometheus et visualisées dans Grafana. Les logs sont centralisés via l'ELK Stack.

## Déploiement Dockerisé

Des fichiers Dockerfile et un `docker-compose.yml` complet seront fournis pour un déploiement facile de l'ensemble de l'architecture.

## Tests

Des tests unitaires et d'intégration seront développés pour assurer la qualité et la fiabilité du système.
