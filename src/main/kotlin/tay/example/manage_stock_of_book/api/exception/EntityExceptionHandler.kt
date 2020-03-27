package tay.example.manage_stock_of_book.api.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.NoHandlerFoundException
import java.util.*

@ControllerAdvice
class EntityExceptionHandler{

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(Exception::class)
    final fun handleAllExceptions(e : Exception, request:WebRequest): ResponseEntity<ErrorResponse>{
        logger.info("Error Log", e)
        var errorResponse: ErrorResponse = ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, null)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)

    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e : MethodArgumentNotValidException, request: WebRequest) : ResponseEntity<ErrorResponse>{
        logger.info("Error Log", e)
        var errorResponse = ErrorResponse(ErrorCode.INVALID_INPUT_FORMAT, e.bindingResult.fieldError!!.field)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun methodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException, request: WebRequest) : ResponseEntity<ErrorResponse>{
        logger.info("Error Log", e)
        var errorResponse = ErrorResponse(ErrorCode.INVALID_DATA_TYPE, null)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(IDNotFoundException::class)
    fun handleNotFoundExceptions(e: IDNotFoundException, request:WebRequest) : ResponseEntity<ErrorResponse>{
        logger.info("Error Log", e)
        var errorResponse: ErrorResponse = ErrorResponse(ErrorCode.ID_NOT_FOUND, e.message)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException, request: WebRequest):ResponseEntity<ErrorResponse>{
        logger.info("Error Log : ",e)
        var errorResponse: ErrorResponse = ErrorResponse(ErrorCode.METHOD_NOT_ALLOW,null)
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse)
    }


    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(e: NoHandlerFoundException, request: WebRequest): ResponseEntity<ErrorResponse>{
        logger.info("Error Log : ",e)
        val errorResponse: ErrorResponse = ErrorResponse(ErrorCode.URL_NOT_FOUND,null)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }
}