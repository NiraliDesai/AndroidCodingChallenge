# Project Overview

## MVP (Model-View-Presenter) Architecture Overview

### MVP (Model-View-Presenter)

The project includes the MVP architecture to segregate the presentation layer from the business
logic.

- **View:** Specifies the contract for updating the UI, such as showing data or errors.
- **Presenter:** Serves as an intermediary, managing UI logic, interfacing with the Use Case, and
  updating the View according to the Model's response.
- **Model:** Encapsulates business logic and supplies data to the Presenter through repository and
  use case layers.

### Key Implementations

1. **Retrofit:** Used for making API requests to fetch book data.
2. **Dagger:** Managed dependencies across the project to ensure a clean and modular structure.
3. **Glide:** Efficiently loaded and displayed book images from the API.
4. **Data Binding:** Makes it easier to connect UI components to data sources, allowing for dynamic
   updates automatically without manual effort.
5. **Mockito:** Used for unit testing and mocking dependencies, ensuring the app is thoroughly
   tested and reliable.
6. **JUnit:** The primary testing framework, used to write and run unit tests.

### Assumptions

* The app focuses on the basic functionality of fetching and displaying books, without advanced
  features like searching or filtering.

### Unit Testing Overview

This PR includes unit tests to ensure the integrity of the app’s core components:

#### HomePresenter Tests

Checks the presenter's actions in different situations, such as:

* Successfully retrieving data and updating the view with book information.
* Handling API failures by showing appropriate error messages.
* Managing loading indicators, making sure they appear and disappear at the right times.

These tests ensure the app handles both expected and unusual cases correctly, maintaining a high
level of reliability.

### Notes

* The app is designed with simplicity and clarity in mind, focusing on the core functionality as
  required by the challenge.
