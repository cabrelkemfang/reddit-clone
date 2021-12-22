package io.grow2gether.redditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataResponse<T> {
    private String message;
    private int status;
    private T data;

    public DataResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
