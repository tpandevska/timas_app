package mk.com.timas.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class CategoryIsAssignedToProduct extends RuntimeException{
    public CategoryIsAssignedToProduct() {
        super("This category is assigned to a product!!");
    }
}
