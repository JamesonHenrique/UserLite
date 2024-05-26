//package usermanagement.exception;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BeanPropertyBindingResult;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//
//@Nested
//@SpringBootTest
//class GlobalExceptionHandlerTest {
//    @InjectMocks
//    private GlobalExceptionHandler globalExceptionHandler;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//    }
//
//
//    @Test
//    void handleEmailAlreadyExistsException_with_different_message() {
//        EmailAlreadyExistsException exception = new EmailAlreadyExistsException("Another user already has this email");
//        WebRequest webRequest = mock(WebRequest.class);
//        when(webRequest.getDescription(anyBoolean())).thenReturn("description");
//
//        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleEmailAlreadyExistsException(exception, webRequest);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
//    }
//
//    @Test
//    void handleEmailAlreadyExistsException_with_null_message() {
//        EmailAlreadyExistsException exception = new EmailAlreadyExistsException(null);
//        WebRequest webRequest = mock(WebRequest.class);
//        when(webRequest.getDescription(anyBoolean())).thenReturn("description");
//
//        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleEmailAlreadyExistsException(exception, webRequest);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
//    }
//
//    @Test
//    void handleEmailAlreadyExistsException_with_empty_message() {
//        EmailAlreadyExistsException exception = new EmailAlreadyExistsException("");
//        WebRequest webRequest = mock(WebRequest.class);
//        when(webRequest.getDescription(anyBoolean())).thenReturn("description");
//
//        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleEmailAlreadyExistsException(exception, webRequest);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
//    }
//
//    @Test
//    void handleEmailAlreadyExistsException_with_null_exception() {
//        EmailAlreadyExistsException exception = new EmailAlreadyExistsException("");
//        WebRequest webRequest = mock(WebRequest.class);
//        when(webRequest.getDescription(anyBoolean())).thenReturn("Unexpected error");
//
//        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleEmailAlreadyExistsException(exception, webRequest);
//
//        if (response.getBody() != null) {
//            assertEquals("Unexpected error", response.getBody().getMessage());
//        } else {
//            assertEquals("Unexpected error", "No response body");
//        }
//    }
//
//    @Test
//    void handleEmailAlreadyExistsException_with_wrong_exception_type() {
//        EmailAlreadyExistsException exception = new EmailAlreadyExistsException("Unexpected error");
//        WebRequest webRequest = mock(WebRequest.class);
//        when(webRequest.getDescription(anyBoolean())).thenReturn("Unexpected error");
//
//
//        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleEmailAlreadyExistsException(exception, webRequest);
//
//        assertNotNull(response);
//
//        if (response.getBody() != null) {
//            assertEquals("Unexpected error", response.getBody().getMessage());
//        } else {
//            assertEquals("Unexpected error", "No response body");
//        }
//
//    }
//    @Test
//    void handleResourceNotFoundException() {
//        ResourceNotFoundException exception = new ResourceNotFoundException("User", "id", 1L);
//        WebRequest webRequest = mock(WebRequest.class);
//        when(webRequest.getDescription(anyBoolean())).thenReturn("description");
//
//        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleResourceNotFoundException(exception, webRequest);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//
//    }
//
//    @Test
//    void handleEmailAlreadyExistsException() {
//        EmailAlreadyExistsException exception = new EmailAlreadyExistsException("Email already exists for user");
//        WebRequest webRequest = mock(WebRequest.class);
//        when(webRequest.getDescription(anyBoolean())).thenReturn("description");
//
//        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleEmailAlreadyExistsException(exception, webRequest);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
//    }
//
//    @Test
//    void handleGlobalException() {
//        Exception exception = new Exception("Unexpected error");
//        WebRequest webRequest = mock(WebRequest.class);
//        when(webRequest.getDescription(anyBoolean())).thenReturn("description");
//
//        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleGlobalException(exception, webRequest);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    void handleMethodArgumentNotValid() {
//
//        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");
//
//
//        BindingResult
//                bindingResult = new BeanPropertyBindingResult(fieldError, "fieldError");
//        bindingResult.addError(fieldError);
//
//        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
//
//
//        ResponseEntity<Object> response = globalExceptionHandler.handleMethodArgumentNotValid(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, mock(WebRequest.class));
//
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertInstanceOf(Map.class, response.getBody());
//        Map<String, String> errors = (Map<String, String>) response.getBody();
//        assertEquals("defaultMessage", errors.get("field"));
//    }
//    }
//
