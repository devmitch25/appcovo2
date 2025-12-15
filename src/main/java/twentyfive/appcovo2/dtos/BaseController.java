package twentyfive.appcovo2.dtos;

import org.springframework.http.ResponseEntity;
import twentyfive.appcovo2.response.ResponseWrapper;

public abstract class BaseController {

    protected <T> ResponseEntity<ResponseWrapper<T>> ok(T data, String message) {
        return ResponseEntity.ok(ResponseWrapper.success(data, message));
    }

    protected <T> ResponseEntity<ResponseWrapper<T>> okWithFailure(String message) {
        return ResponseEntity.ok(ResponseWrapper.failure(message, 200));
    }

    protected ResponseEntity<ResponseWrapper<Void>> created(String message) {
        return ResponseEntity.status(201).body(ResponseWrapper.success(null, message));
    }

    protected ResponseEntity<ResponseWrapper<Void>> updated(String message) {
        return ResponseEntity.status(200).body(ResponseWrapper.success(null, message));
    }

    protected ResponseEntity<ResponseWrapper<Void>> badRequest(String message) {
        return ResponseEntity.badRequest().body(ResponseWrapper.failure(message, 400));
    }

    protected ResponseEntity<ResponseWrapper<Boolean>> internalError(String message) {
        return ResponseEntity.internalServerError().body(ResponseWrapper.failure(message, 500));
    }

    protected ResponseEntity<ResponseWrapper<Boolean>> badRequest() {
        return ResponseEntity.status(400).body(ResponseWrapper.failure("", 400));
    }

    protected ResponseEntity<ResponseWrapper<Void>> notFound(String message) {
        return ResponseEntity.status(404).body(ResponseWrapper.failure(message, 404));
    }

    protected ResponseEntity<ResponseWrapper<Void>> internalServerError(String message) {
        return ResponseEntity.status(500).body(ResponseWrapper.failure(message, 500));
    }

    protected ResponseEntity<ResponseWrapper<Boolean>> internalServerError() {
        return ResponseEntity.status(500).body(ResponseWrapper.failure("", 500));
    }

    protected ResponseEntity<ResponseWrapper<Void>> noContent(String message) {
        return ResponseEntity.status(204).body(ResponseWrapper.noContent(message,204));
    }

}

