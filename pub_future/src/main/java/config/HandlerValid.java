package config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Essa classe ela captura e agrupa todos os erros e transformam em ErrorFormDTOs
@RestControllerAdvice
public class HandlerValid {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorFormDTO> handler(MethodArgumentNotValidException exception) {
		List<ErrorFormDTO> dto = new ArrayList<>();
		List<FieldError> fieldError =  exception.getBindingResult().getFieldErrors();
		fieldError.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorFormDTO erro = new ErrorFormDTO(message, e.getField());
			dto.add(erro);
		});
		return dto;
	}
}
