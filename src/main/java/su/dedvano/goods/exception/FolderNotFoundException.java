package su.dedvano.goods.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FolderNotFoundException extends RuntimeException {

    public FolderNotFoundException() {
        super("Папка не найдена");
    }

}
