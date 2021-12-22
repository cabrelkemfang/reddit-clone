package io.grow2gether.redditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PageableResult<T> {
    private int page;
    private int size;
    private long totalOfItems;
    private int totalPages;
    private List<T> data;
}
