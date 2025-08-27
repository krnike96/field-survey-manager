# Field Survey Data Manager & Report Generator

A robust, offline desktop application designed to streamline the process of data collection and analysis for field surveys. This tool empowers organizations to create custom survey forms, collect data without an internet connection, and transform that data into insightful reports and visualizations.

## Features

- **Offline Operation**: Runs locally, storing all data in a single, file-based SQLite database.
- **Role-Based Access**: Secure login system with distinct interfaces for Admin and Survey Taker roles.
- **Dynamic Form Designer**: Admins can create and customize survey forms with various question types (e.g., text, number, multiple choice).
- **Multiple Data Submissions**: Survey takers can efficiently submit multiple responses for the same survey.
- **Interactive Reporting**: Admins can generate and view dynamic charts and graphs (bar, pie, etc.) to analyze survey data.
- **Data Export**: Export raw data to CSV and generated reports to PDF.

## Technologies Used

- **Language**: Java
- **UI Framework**: JavaFX
- **Database**: SQLite (Embedded, file-based)
- **Database Driver**: SQLite JDBC Driver
- **Export Libraries**: iText for PDF generation, Apache POI for CSV export

## How to Run the Project

### Prerequisites
- Ensure you have the **Java Development Kit (JDK) 8** or higher installed.

### Steps
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/krnike96/field-survey-manager.git
   cd field-survey-data-manager
   ```

2. **Database Setup**:
   - The application automatically creates the `survey_data.db` file and necessary tables on its first run. No separate database server installation is required.

3. **Run**:
   - Execute the main class to launch the JavaFX application.

## Project Workflow

The application is designed for a seamless data collection process:
1. An **admin** logs in to create a new survey form, which is saved locally.
2. A **survey taker** logs in, selects the created survey, and submits responses.
3. Each response is saved as a new record in the SQLite database.
4. The **admin** can later return to view, analyze, and export the collected data as CSV or PDF reports.