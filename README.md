Architecture Overview:
This project follows a microservices architecture to ensure high availability, scalability, and independent deployability of core health features.

User Service: Manages user profiles.
Activity Service: Tracks and processes real-time fitness metrics and daily health logs.
AI Service (Gemini Integration): Consumes activity data to generate personalised wellness suggestions using the Gemini AI API.
Config Service: Centralised configuration management using Spring Cloud Config.
Gateway Service: Acts as the single entry point for all client requests, utilising Spring Cloud Gateway for routing.
Service Discovery: Implemented with Netflix Eureka for dynamic service registration and discovery.

Tech Stack:
Backend: Java 17, Spring Boot 3.x, Spring Data JPA.
Microservices: Spring Cloud (Gateway, Eureka, Config Server).
AI: Google Gemini AI API.
Databases: MongoDB.
Tools: Docker, Maven, Git.

Key Features:
Intelligent Insights: Uses Generative AI to analyse activity patterns and provide actionable health advice.
Centralised Configuration: Manage properties for all services from a single location.
Dynamic Routing: Secure and efficient request handling through the API Gateway.
Service Resiliency: Designed for fault tolerance and seamless service-to-service communication.

Getting Started
Clone the repo: git clone https://github.com/yourusername/health-tracker-microservices.git
Set Environment Variables: Add your GEMINI_API_KEY to the AI Service configuration.
Build: Run mvn clean install across all modules.
Run: Start the Config Server, followed by Eureka, and then the functional services.
