

## Updated Controllers & fixed bugs.

## Endpoints

### 1. **Create Movie Entry**

**Endpoint**: `POST /movie`  
**Description**: Added default values and validation using Spring Boot Validation

#### Request Body Example:
```json
{
  "title": "The Shawshank Redemption",
  "durationInMin": 142,
  "language": "English",
  "releaseDate": "1994-09-22"
}
```

#### Valid Scenarios:
- **Movie creation with valid data**:
    - **Expected status code**: `200 OK`
    - **Expected data**: The created `Movie` object with the status "OK."
- **Movie creation with missing optional fields**:
    - **Expected status code**: `200 OK`
    - **Expected data**: The created `Movie` object with only the fields provided (defaults for missing fields).

#### Error Cases:
- **Invalid data provided** (e.g., missing required fields like `title`, `durationInMin`, or `language`):
    - **Expected status code**: `400 Bad Request`
    - **Expected data**: An error message explaining which required fields are missing.
- **Server-side error**:
    - **Expected status code**: `500 Internal Server Error`
    - **Expected data**: An error message like "Internal Server Error."

### 2. **Delete Movie Entry by ID**

**Endpoint**: `DELETE /movieId/{movieId}`  
**Description**: This endpoint allows you to delete a movie entry based on the movie's ID. Checked for a condition if movieId doesn't exist in database

#### Path Variable:
- `movieId`: The ID of the movie to delete.

#### Valid Scenarios:
- **Movie deletion with valid `movieId`**:
    - **Expected status code**: `204 No Content`
    - **Expected data**: No content returned as the movie has been deleted successfully.

#### Error Cases:
- **Movie ID not found** (trying to delete a movie that doesn't exist):
    - **Expected status code**: `404 Not Found`
    - **Expected data**: An error message like "Movie Not Found."
- **Server failure during deletion**:
    - **Expected status code**: `500 Internal Server Error`
    - **Expected data**: An error message explaining the issue.

---

### 3. **updateById**

**Endpoint**: `PUT /movieId/{movieId}`  
**Description**: This endpoint allows you to update a movie entry based on the movie's ID. Checked for a condition if movieId doesn't exist in database

#### Path Variable:
- `movieId`: The ID of the movie to delete.

#### Valid Scenarios:
- **Successfully updating a movie with valid changes.**:
    - **Expected status code**: `200 OK`
    - **Expected data**: The updated Movie object with the changes reflected.


- **Updating a movie with some optional fields, leaving others unchanged.**:
    - **Expected status code**: `200 OK`
    - **Expected data**: The updated Movie object, with updated fields and unchanged fields.


#### Error Cases:
- **Movie ID not found** (trying to update a movie that doesn't exist):
    - **Expected status code**: `404 Not Found`
    - **Expected data**: An error message like "Movie Not Found."


- **Invalid data in the request body (e.g., invalid genre or duration).**:
    - **Expected status code**: `400 Bad Request`
    - **Expected data**: "An error message explaining which fields are invalid."

- **Server failure during deletion**:
    - **Expected status code**: `500 Internal Server Error`
    - **Expected data**: An error message explaining the issue.

---

### 4. **createOrUpdateCinema**

**Endpoint**: `POST /cinema`  
#### Valid Scenarios:
- **Creating or updating a cinema with valid data.**:
    - **Expected status code**: `200 OK`
    - **Expected data**:The created/updated Cinema object.
#### Error Cases:
- **Invalid cinema data** (missing required fields like name or location:
    - **Expected status code**: `404 Not Found`
    - **Expected data**: An error message detailing missing or invalid fields"
- **Server failure during deletion**:
    - **Expected status code**: `500 Internal Server Error`
    - **Expected data**: An error message explaining the issue.

### 5. **DeleteById2**
- **Valid Scenarios:**
    - Successfully deleting a cinema.
        - **Expected status code:** `204 No Content`
        - **Expected data:** No content, as the cinema has been deleted successfully.
- **Error Cases:**
    - Cinema ID not found.
        - **Expected status code:** `404 Not Found`
        - **Expected data:** An error message like "Cinema not found."
    - Server failure during the deletion process.
        - **Expected status code:** `500 Internal Server Error`
        - **Expected data:** An error message explaining the failure.

### 6. **createEntry**
- **Valid Scenarios:**
    - Successfully creating a cinema hall with valid data..
        - **Expected status code:** `200 OK`
        - **Expected data:** The created CinemaHall object.
- **Error Cases:**
    - Cinema ID not found.
        - **Expected status code:** `400 BAD Request`
        - **Expected data:**An error message detailing which fields are missing or invalid."
    - Server failure during the deletion process.
        - **Expected status code:** `500 Internal Server Error`
        - **Expected data:** An error message like "Internal Server Error."


### 7. **getAllUser**
- **Valid Scenarios:**
    - Successfully retrieving a list of all users.
        - **Expected status code:** `200 OK`
        - **Expected data:** A list of User objects.
- **Error Cases:**
    - No users found (e.g., empty list of users).
        - **Expected status code:** `400 Not Found`
        - **Expected data:** An error message like "Missing required fields."
    - Server failure during the deletion process.
        - **Expected status code:** `500 Internal Server Error`
        - **Expected data:** An error message like "Internal Server Error."


### 8. **createAdminUser**
- **Valid Scenarios:**
    - Successfully creating a new admin user with valid data.
        - **Expected status code:** `200 OK`
        - **Expected data:** A message like "User created successfully."
- **Error Cases:**
    - Invalid user data (e.g., missing required fields).
        - **Expected status code:** `400 Bad Request`
        - **Expected data:** An error message like "Missing required fields."
    - Server failure during the deletion process.
        - **Expected status code:** `500 Internal Server Error`
        - **Expected data:** An error message like "Internal Server Error."


### 9. **getEntryById**
- **Valid Scenarios:**
    - Successfully retrieving a user entry by ID, and the logged-in user matches the requested user.
        - **Expected status code:** `200 OK`
        - **Expected data:** The User object with the requested ID".
- **Error Cases:**
    - User ID not found.
        - **Expected status code:** `400  Not Found`
    - The logged-in user does not have permission to access the requested user’s details.
        - **Expected status code:** `403 Forbidden`
        - **Expected data:** An error message like "You do not have permission to access this user."
    - Server failure during the deletion process.
        - **Expected status code:** `500 Internal Server Error`
        - **Expected data:** An error message like "Internal Server Error."

### Common HTTP Status Codes Overview:

- **`200 OK`:** The request was successful, and the response body contains the result.
- **`201 Created`:** The request was successful, and a new resource was created (e.g., new movie, cinema, user).
- **`204 No Content`:** The request was successful, but there is no content to return (e.g., deletion successful).
- **`400 Bad Request`:** The request was invalid due to incorrect or missing data.
- **`403 Forbidden`:** The user is not authorized to perform this action.
- **`404 Not Found`:** The requested resource (e.g., movie, user, cinema) was not found.
- **`500 Internal Server Error`:** The server encountered an unexpected condition that prevented it from fulfilling the request.

## Error Handling

### 1. **Method Argument Validation Exceptions**

If any required fields are missing or invalid, the system will return a `400 Bad Request` with a detailed error message. For example:

```json
{
  "title": "Title is required and cannot be blank",
  "durationInMin": "Duration must be at least 1 minute",
  "language": "Language is required and cannot be blank",
  "releaseDate": "Release date cannot be in the future"
}
```

### 2. **Server-side Errors**

In case of an internal server error (e.g., database issues), the system will return a `500 Internal Server Error` along with a generic error message like:
```json
{
  "message": "Internal Server Error"
}
```

---


## Dependencies

- Spring Boot 2.x+
- Spring Data JPA (or MongoDB if applicable)
- Hibernate Validator
- Spring Web

---

## Setup and Installation

### Prerequisites

- Java 11 or higher
- Spring Boot project set up with the required dependencies
- Database MySQL,

### Steps to Set Up:
1. Clone or download the repository.
2. Import the project into your preferred IDE (e.g., IntelliJ IDEA or Eclipse).
3. Configure your database settings in `application.properties` (or `application.yml`).
4. Run the application using the `@SpringBootApplication` class or using the command:
   ```bash
   mvn spring-boot:run
   ```
5. Test the API using Postman or a similar tool.

---

## Contribution

Feel free to fork the project and submit pull requests for any improvements or bug fixes.

---

## License

This project is licensed under the MIT License.

