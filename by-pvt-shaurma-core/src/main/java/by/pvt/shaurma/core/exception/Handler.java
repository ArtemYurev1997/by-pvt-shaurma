package by.pvt.shaurma.core.exception;

import by.pvt.shaurma.api.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class Handler {

    @ExceptionHandler(value = AccountException.class)
    @ResponseBody
    public ErrorResponse getAccountException(AccountException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorResponse;
    }

    @ExceptionHandler(value = PaymentException.class)
    @ResponseBody
    public ErrorResponse getPaymentException(PaymentException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorResponse;
    }

    @ExceptionHandler(value = TransactionException.class)
    @ResponseBody
    public ErrorResponse getTransactionException(TransactionException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorResponse;
    }

    @ExceptionHandler(value = ProgramException.class)
    @ResponseBody
    public ErrorResponse getProgramException(ProgramException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorResponse;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse getValidationException(MethodArgumentNotValidException e) {
        var errors = e.getBindingResult().getAllErrors();
        log.error(errors.get(0).getDefaultMessage(), e);
        var error = errors.get(0);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
        return errorResponse;
    }
}
