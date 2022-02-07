package com.test.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveDTO {

    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private MultipartFile boardFile;
    private String boardFilename;

}
