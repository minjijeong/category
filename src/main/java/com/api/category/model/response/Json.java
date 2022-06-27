package com.api.category.model.response;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class Json<T> implements CommonJson<T> {
    private Integer status;
    private String errorCode;
    private String message;
    private T result;

    public static <T> Json<T> createJson(T result) {
        Json<T> json = new Json();
        json.setResult(result);
        json.setStatus(HttpStatus.OK.value());
        json.setMessage(HttpStatus.OK.getReasonPhrase());
        return json;
    }

    public static <T> Json<T> createErrorJson(Integer status, String errorCode, String message) {
        Json<T> json = new Json();
        json.setStatus(status);
        json.setMessage(message);
        json.setErrorCode(errorCode);
        return json;
    }

    public static <T> Json<T> createErrorJson(Integer status, String message) {
        Json<T> json = new Json();
        json.setStatus(status);
        json.setMessage(message);
        return json;
    }

    public Boolean isError() {
        return this.status != HttpStatus.OK.value();
    }

    public Json() {
    }

//    public Integer getStatus() {
//        return this.status;
//    }
//
//    public String getErrorCode() {
//        return this.errorCode;
//    }
//
//    public String getMessage() {
//        return this.message;
//    }
//
//    public T getResult() {
//        return this.result;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public void setErrorCode(String errorCode) {
//        this.errorCode = errorCode;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public void setResult(T result) {
//        this.result = result;
//    }

    public String toString() {
        return "Json(status=" + this.getStatus() + ", errorCode=" + this.getErrorCode() + ", message=" + this.getMessage() + ", result=" + this.getResult() + ")";
    }

    public static class JsonBuilder {
        Map<String, Object> jsonResultMap = new HashMap();

        public JsonBuilder() {
        }

        public Json<Map<String, Object>> build() {
            Json<Map<String, Object>> jsonResult = new Json();
            jsonResult.setResult(this.jsonResultMap);
            return jsonResult;
        }

        public JsonBuilder map(String key, Object value) {
            this.jsonResultMap.put(key, value);
            return this;
        }

        public static JsonBuilder builder() {
            return new JsonBuilder();
        }
    }
}
