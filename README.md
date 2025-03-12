# Bipa Mobile Coding Challenge - README

## Build tools & versions used

*   **Android Studio:** Giraffe | 2022.3.1 or newer.
*   **Kotlin:** 1.8.20 or newer.
*   **Gradle:** 8.0 or newer.
*   **Android Gradle Plugin:** 8.1.0 or newer.
*   **Compose BOM:** 2023.10.01
*   **Kotlin Coroutines:** 1.7.3 or newer.
*   **Koin:** 3.5.3
*   **Jetpack Compose:** 1.5.4 or newer.
* **Compose Material3:** 1.3.1 or newer.
*   **Lifecycle Compose:** 2.8.7 or newer.
*   **Mockito-Kotlin:** 5.2.1 or newer.

## Steps to run the app

1.  **Clone the repository:**
2.  **Open in Android Studio:**
    *   Open Android Studio and select "Open an Existing Project."
    *   Navigate to the cloned directory and select the root folder.
3.  **Build the project:**
    *   Let Android Studio sync the project with Gradle.
    *   Click on the "Build" menu and select "Make Project."
4.  **Run the app:**
    *   Connect an Android emulator or a physical device.
    *   Click on the green "Run" button in Android Studio.
    * Select the desired device, if more than one is available.

## What areas of the app did you focus on?

My primary focus was on the following areas:

*   **UI Development with Jetpack Compose:** Building a clean and responsive user interface using modern Jetpack Compose principles.
*   **State Management:** Implementing a robust state management system to handle UI updates efficiently and correctly, using Kotlin Flows and a ViewModel.
*   **Dependency Injection:** Using Koin for dependency injection to ensure clean, testable code and modularity.
*   **Error Handling:** Implementing a proper error-handling strategy to gracefully handle API or any other kind of failures.
*   **Unit Testing:** Creating comprehensive unit tests to guarantee the correctness of the core logic, ensuring code quality and stability, without the use of external libraries like Mockito.
*   **Clean Code:** Apply patterns and write clean and readable code.

## What was the reason for your focus? What problems were you trying to solve?

My focus was driven by the desire to address the following problems:

*   **UI Consistency and Maintainability:** Jetpack Compose helps ensure a consistent look and feel across the app while also making the UI code more maintainable and less verbose.
*   **Testability:** Using dependency injection and separating UI logic from business logic makes the code more testable and reduces the complexity of unit tests.
*   **Data Flow:** Correctly manage the data flow between the UI and the data sources to prevent UI glitches and inconsistencies.
*   **Error Resilience:** Making the app more robust against network or other potential failures and showing user-friendly error messages.
*   **Code Readability:** Writing clean code to facilitate the project's evolution.
*   **Code organization:** Apply good practices to create a well organized code.

## How long did you spend on this project?

I spent approximately **[Insert your time here]** hours on this project. This time was spent on:

*   Setting up the project structure.
*   Designing and implementing the UI.
*   Implementing the state management and data handling logic.
*   Writing unit tests.
*   Refactoring and improving the code quality.
*   Writing the readme.

## Did you make any trade-offs for this project? What would you have done differently with more time?

Yes, I made some trade-offs to balance feature completion with the time constraints:

*   **Simplified API Integration:** The API integration is mocked with dummy data, and with more time, it would have been implemented with a real API.
*   **Limited UI Customization:** While the UI is functional and clean, further customization could be added, with more time.
*   **Remove libraries:** At the beginning the project used Mockito, but with more time I removed the need for it.

With more time, I would have done the following:

*   **Implement a real API:** Create a full integration with a real API to retrieve the data and allow dynamic content.
*   **More Complex UI Features:** Add more interactive UI elements and animations.
*   **More Tests:** Write more unit tests for other components.
*   **Implement Instrumented Test:** Write instrumented tests to guarantee that the app works well with the Android system.
*   **Implement Continuous Integration:** Create a pipeline that automatically creates and tests the application.

## What do you think is the weakest part of your project?

The weakest part of the project is the **lack of full integration with a real API.** While the current implementation is a great example of UI development and clean architecture, the full potential can only be realized with real, dynamic data. This could expose potential edge cases or complexities not covered by the current mock data.

## Is there any other information you’d like us to know?

*   **Learning Process:** This project was a valuable learning opportunity for me to deepen my understanding of Jetpack Compose, state management, and testing in Android.
*   **Clean Architecture Principles:** I've made a conscious effort to apply clean architecture principles to make the code organized, testable, and scalable.
*   **Future Work:** I'm eager to continue improving this project, especially by adding the full API integration and more complex UI features, as discussed in the "Trade-offs" section.
*   **Design System:** I try to use and extend the design system that was provided.
*   **Koin:** I use Koin as a dependency injection because it is a lightweight library, and it does not have a big impact on performance.