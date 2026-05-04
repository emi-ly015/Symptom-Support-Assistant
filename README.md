# Symptom Support Assistant (Java)

> **Note:** This project is currently in progress as part of an applied Java programming course. The core architecture and models are implemented, with additional features under active development.

## Overview

This project is a healthcare-focused decision support application that allows users to input symptoms and receive structured guidance about possible conditions and next steps.

The system is designed to help users make informed decisions by:
- Organizing symptom data into structured reports
- Analyzing symptoms to generate likely condition predictions
- Providing recommendations such as self-care advice or when to seek medical attention

## Core Concepts

Several key software engineering and Java programming concepts are explored throughout this project:

- **Object-oriented design** with clearly defined domain models
- **Encapsulation and immutability** for safe and predictable data handling
- **Separation of concerns** between validation, prediction, persistence, and user interaction
- **Input validation and exception handling** to ensure robust behavior
- **Collections usage** (lists, sets) for managing symptom data and detecting duplicates
- **Data normalization** for consistent processing and comparison of inputs
- **File-based persistence (JSON)** for saving and retrieving symptom reports
- **Testable architecture** where core logic can be verified independently of I/O or AI components

## System Architecture

The application is structured around several core components:

### Data Models

- **Symptom**  
  Represents a single symptom with a name, severity (1–10), and duration.  
  Input is normalized to ensure consistent comparisons.

- **SymptomReport**  
  Represents a full user session containing multiple symptoms, along with generated predictions and recommendations.

- **ConditionPrediction**  
  Represents a predicted condition with a confidence score and explanation.

- **Recommendation**  
  Stores structured guidance such as self-care advice or escalation steps, categorized by type and urgency.

### Supporting Components

- **InputValidator**  
  Validates symptom reports before processing.  
  Handles rules such as:
    - Empty reports
    - Duplicate symptoms
    - Invalid or missing data

- **PredictionEngine** *(in progress)*  
  Processes symptom reports and generates ranked condition predictions based on symptom patterns and severity.

- **RecommendationEngine** *(in progress)*  
  Generates guidance based on predictions and detected risk levels.

- **MedicalDataRepository** *(planned)*  
  Loads symptom-condition mappings from structured data (JSON or similar).

- **ReportRepository** *(planned)*  
  Handles saving and retrieving reports using JSON-based file persistence.

- **AIService** *(planned)*  
  Provides optional AI-assisted explanations or ranking refinement while keeping core logic deterministic and testable.

## Application Flow

The system follows a structured pipeline:

1. User inputs symptoms
2. Symptoms are normalized and validated
3. A `SymptomReport` is created
4. `InputValidator` verifies report correctness
5. `PredictionEngine` generates likely conditions
6. `RecommendationEngine` produces guidance and warnings
7. Results are stored and optionally saved to JSON
8. A summary report is displayed to the user

## Technical Themes

This project focuses on building a complete Java application rather than isolated features:

- Designing systems with multiple interacting classes
- Maintaining clear boundaries between logic layers
- Ensuring safe handling of invalid input and edge cases
- Structuring code to remain maintainable as features are added
- Building functionality that remains reliable even with optional AI components

## Running the Project

This project is currently under development and does not yet have a finalized runnable interface.

Once completed, the application will be runnable as a console-based program with file-based persistence.
