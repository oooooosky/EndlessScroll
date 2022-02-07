package com.test.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardPagingDTO {

    private Long boardId;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private String boardFilename;

}
