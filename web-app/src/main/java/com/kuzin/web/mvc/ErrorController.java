package com.kuzin.web.mvc;




import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**Error handler controller.*/

@ControllerAdvice
@EnableWebMvc
public class ErrorController {

    private static final String ERROR = "error";
    private static final String MESSAGE = "message";
    private static final String REQUEST = "Request: ";
    private static final String RAISED = " raised ";
    private static final String CODE = "error code: ";

    Logger logger = LoggerFactory.getLogger(ErrorController.class);


    String output;

    @ExceptionHandler(value = NumberFormatException.class)
    protected String numberFormatHandler(HttpServletRequest request,
                                         Model model, Exception e) {
        output = REQUEST + request.getRequestURI() + RAISED + e;
        logger.error(output);

        model.addAttribute(ERROR, CODE + HttpServletResponse.SC_BAD_REQUEST);
        model.addAttribute(MESSAGE, "Wrong input format");


        return ERROR;
    }



    @ExceptionHandler(value = NullPointerException.class)
    protected String emptyInput(HttpServletRequest request,
                                         Model model, Exception e) {
        output = REQUEST + request.getRequestURI() + RAISED + e;
        logger.error(output);

        model.addAttribute(ERROR, CODE + HttpServletResponse.SC_BAD_REQUEST);
        model.addAttribute(MESSAGE, "Empty input");


        return ERROR;
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    protected String validateHandler(HttpServletRequest request, Model model, Exception e) {
        output = REQUEST + request.getRequestURI() + RAISED + e;

        logger.error(output);

        model.addAttribute(ERROR, CODE + HttpServletResponse.SC_NOT_FOUND);
        model.addAttribute(MESSAGE, "no items have been added yet");


        return ERROR;
    }

    @ExceptionHandler(value = PSQLException.class)
    protected String databaseHandler(HttpServletRequest request, Model model, Exception e) {
        output = REQUEST + request.getRequestURI() + RAISED + e;

        logger.error(output);

        model.addAttribute(ERROR, CODE + HttpServletResponse.SC_NOT_FOUND);
        model.addAttribute(MESSAGE, "Such information was not found! Check your input.");


        return ERROR;
    }

    @ExceptionHandler(value = DateTimeParseException.class)
    protected String dateHandler(HttpServletRequest request, Model model, Exception e) {
        output = REQUEST + request.getRequestURI() + RAISED + e;

        logger.error(output);

        model.addAttribute(ERROR, CODE + HttpServletResponse.SC_BAD_REQUEST);
        model.addAttribute(MESSAGE, "wrong date format");


        return ERROR;
    }


    @ExceptionHandler(value = AccessException.class)
    protected ResponseEntity<String> access(HttpServletRequest request, Model model, Exception e) {
        output = REQUEST + request.getRequestURI() + RAISED + e;

        logger.error(output);

        model.addAttribute(ERROR, CODE + HttpServletResponse.SC_FORBIDDEN);
        model.addAttribute(MESSAGE, "You cannot access the materials of the "
                + "department in which you do not work");


        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("You cannot access the materials of the department in which you do not work");
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    protected ResponseEntity<String> emptyResult(HttpServletRequest request,
                                                 Model model, Exception e) {
        output = REQUEST + request.getRequestURI() + RAISED + e;

        logger.error(output);

        model.addAttribute(ERROR, CODE + HttpServletResponse.SC_FORBIDDEN);
        model.addAttribute(MESSAGE, "These materials have not been added yet");


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Such data not found");
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<String> notSupportMethod(HttpServletRequest request,
                                                      Model model, Exception e) {
        output = REQUEST + request.getRequestURI() + RAISED + e;

        logger.error(output);

        model.addAttribute(ERROR, CODE + HttpServletResponse.SC_BAD_REQUEST);
        model.addAttribute(MESSAGE, "Such request not support");


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Such request not support");
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<String> illegalHandler(HttpServletRequest request,
                                                    Model model, Exception e) {
        output = REQUEST + request.getRequestURI() + RAISED + e;

        logger.error(output);

        model.addAttribute(ERROR, CODE + HttpServletResponse.SC_BAD_REQUEST);
        model.addAttribute(MESSAGE, "wrong type of input format");


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong user input");
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    protected ResponseEntity<String> deleteError(HttpServletRequest request,
                                                 Model model, Exception e) {
        output = REQUEST + request.getRequestURI() + RAISED + e;

        logger.error(output);

        model.addAttribute(ERROR, CODE + HttpServletResponse.SC_BAD_REQUEST);
        model.addAttribute(MESSAGE, "cannot delete information while it contains");


        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("cannot delete information while it contains");
    }





    @ExceptionHandler(value = DuplicateKeyException.class)
    protected String duplicateKey(HttpServletRequest request, Model model, Exception e) {
        output = REQUEST + request.getRequestURI() + RAISED + e;

        logger.error(output);

        model.addAttribute(ERROR, CODE + HttpServletResponse.SC_BAD_REQUEST);
        model.addAttribute(MESSAGE, "such object is already create, try "
                + "with another parameter");


        return ERROR;
    }





}
