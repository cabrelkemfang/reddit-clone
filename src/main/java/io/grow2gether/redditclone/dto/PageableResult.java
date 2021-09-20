package io.grow2gether.redditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageableResult<T> implements Serializable {
    private int page;
    private int size;
    private long totalOfItems;
    private int totalPage;
    private List<T> data;
}
