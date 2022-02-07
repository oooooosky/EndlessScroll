package com.test.test.entity;

import com.test.test.dto.BoardSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "test_table")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column
    private String boardWriter;

    @Column
    private String boardFilename;

    @Column
    private String boardTitle;

    @Column
    private String boardContents;

    public static BoardEntity toSaveBoardEntity(BoardSaveDTO boardSaveDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardSaveDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        boardEntity.setBoardFilename(boardSaveDTO.getBoardFilename());
        return boardEntity;
    }
}
