# SPRING REST KAFKA H2 EXAMPLE

This project implements a backend system based on the [PicPay Backend Technical Challenge](https://github.com/PicPay/picpay-desafio-backend) using **Spring Boot**, **Kafka**, **Spring Web (REST)**, and **H2 Database**. Kafka is used for sending notifications asynchronously, ensuring efficient message processing across services.

## Technologies Used

- **Spring Boot**: Framework for building the backend application.
- **Kafka**: Real-time messaging system for sending notifications asynchronously.
- **Spring Web (REST)**: Provides RESTful APIs for communication with the frontend.
- **H2 Database**: In-memory database for fast, lightweight data storage during development and testing.

## Features

- **Asynchronous notifications**: Kafka is used to send notifications asynchronously, allowing for scalable and decoupled communication.
- **RESTful APIs**: Exposes endpoints to interact with the backend services.
- **In-memory storage**: Utilizes H2 Database for temporary data storage in development.
