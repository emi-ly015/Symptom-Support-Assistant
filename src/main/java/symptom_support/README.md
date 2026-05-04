# Symptom Support Assistant

## Overview

Symptom Support Assistant is a console-based Java application that allows users to enter symptoms and receive structured guidance about possible conditions and next steps.

The system is designed to:
- Organize user symptoms into structured reports
- Validate symptom data before processing
- Predict up to three likely conditions using Java-based scoring logic
- Generate recommendations and urgent warnings
- Save completed reports as JSON files
- Use a fake AI service to enhance explanations while keeping core logic deterministic

## Core Concepts

- **Object-oriented design** using separate model, validation, engine, repository, and service classes
- **Encapsulation** through private fields, constructors, getters, and controlled setters
- **Interfaces and polymorphism** through the AIService interface and FakeAIService implementation
- **Collections** including List, Map, Set, ArrayList, and HashSet
- **Input validation and custom exceptions** for invalid symptoms, duplicate symptoms, and invalid reports
- **Rule-based prediction logic** using symptom-to-condition mappings
- **Risk assessment logic** for urgent symptom combinations
- **JSON persistence** for external medical data and saved reports
- **JUnit testing** for validation, prediction, recommendation, and persistence behavior

## System Architecture

The application is structured around several core components:

- **Symptom** : Represents one symptom entered by the user. Each symptom has a normalized name, severity from 1–10, and duration in days.

- **SymptomReport** : Represents one complete user session. It stores the submitted symptoms, generated predictions, recommendations, and whether high-risk symptoms were detected.

- **ConditionPrediction** : Represents one predicted condition, including a confidence score, matched symptoms, and explanation.

- **Recommendation** : Represents one guidance message. Each recommendation has a type and urgency level.

- **User** : Stores user information and report history for the current console session.

- **InputValidator** : Validates symptom reports before prediction. It checks for empty reports, invalid symptoms, duplicate symptoms, invalid severity values, and invalid durations.

- **Custom Exceptions** :The project uses custom exceptions such as:
  - InvalidSymptomException
  - InvalidSymptomReportException
  - DuplicateSymptomException

- **PredictionEngine** : Uses symptom-condition mappings to compare user symptoms against known condition patterns. It scores matches using symptom severity, calculates confidence scores, ranks predictions, and returns the top three results.

- **RiskAssessment** : Detects high-risk symptom combinations using deterministic Java rules. This class handles urgent safety logic such as chest pain with shortness of breath or high fever lasting multiple days.

- **RecommendationEngine** : Combines urgent warnings from RiskAssessment with general recommendations based on predicted conditions.

- **MedicalDataRepo** : Loads symptom-condition mappings from a JSON file. This keeps the condition data separate from prediction logic.

- **ReportRepo** : Saves completed symptom reports to JSON files.

- **AIService** : Defines the interface for AI-assisted explanation generation.

- **FakeAIService** : Mimics AI-generated explanations without calling an external API.

AI is used only to enhance explanation text for predictions. It does not control condition scoring, high-risk detection, urgent warnings, or final safety decisions. 


## Application Flow

The console application follows this pipeline:

1. User enters identifying information
2. User enters symptoms, severity, and duration
3. Symptoms are normalized and stored as Symptom objects
4. A SymptomReport is created
5. InputValidator validates the report
6. MedicalDataRepo loads symptom-condition mappings from JSON
7. PredictionEngine generates ranked condition predictions
8. FakeAIService enhances prediction explanations
9. RiskAssessment checks for urgent symptom combinations
10. RecommendationEngine generates final recommendations
11. SymptomReport stores predictions, recommendations, and high-risk status
12. ReportRepo saves the completed report as JSON
13. A summary is printed to the console

## External Libraries and Tools

This project uses:
- **Jackson Databind** for reading and writing JSON
- **Jackson Java Time Module** for supporting LocalDateTime serialization
- **JUnit 5** for unit testing
- **Maven** for dependency management and project structure
- **SLF4J / Logback** included from the course project setup

## Testing

JUnit tests are included for core behavior, including:
- Input validation
- Duplicate symptom detection
- Prediction ranking
- Fake AI explanation integration
- Urgent recommendation logic
- JSON report saving
- Basic Maven/JUnit sanity checking

