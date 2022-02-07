package com.test.test.dto;

import com.test.test.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailDTO {

    private Long boardId;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private String boardFilename;

    public static BoardDetailDTO toBoardDetailDTO(BoardEntity boardEntity) {
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardId(boardEntity.getId());
        boardDetailDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDetailDTO.setBoardContents(boardEntity.getBoardContents());
        boardDetailDTO.setBoardFilename(boardEntity.getBoardFilename());
        return boardDetailDTO;
    }

    public static List<BoardDetailDTO> toBoardDetailDTOList(List<BoardEntity> boardEntityList) {
        List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();
        for (BoardEntity b: boardEntityList) {
            boardDetailDTOList.add(toBoardDetailDTO(b));
        }
        return boardDetailDTOList;
    }
}
