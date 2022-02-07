package com.test.test.service;

import com.test.test.dto.BoardDetailDTO;
import com.test.test.dto.BoardPagingDTO;
import com.test.test.dto.BoardSaveDTO;
import com.test.test.entity.BoardEntity;
import com.test.test.page.PagingConst;
import com.test.test.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository br;

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) throws IOException {
        MultipartFile boardFile = boardSaveDTO.getBoardFile();
        String boardFilename = boardFile.getOriginalFilename();
        boardFilename = System.currentTimeMillis() + "-" + boardFilename;
        String savePath = "/Users/sky/EclipseJava/source/EndlessScrolling/src/main/resources/static/image/" + boardFilename;
        if (!boardFile.isEmpty()) {
            boardFile.transferTo(new File(savePath));
        }
        boardSaveDTO.setBoardFilename(boardFilename);
        System.out.println("boardSaveDTO.getBoardFilename() = " + boardSaveDTO.getBoardFilename());
        BoardEntity boardEntity = BoardEntity.toSaveBoardEntity(boardSaveDTO);
        return br.save(boardEntity).getId();
    }

    @Override
    public List<BoardDetailDTO> findAll() {
        List<BoardEntity> boardEntityList = br.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<BoardDetailDTO> boardDetailDTOList = BoardDetailDTO.toBoardDetailDTOList(boardEntityList);
        return boardDetailDTOList;
    }

    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1) ? 0 : (page - 1);
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<BoardPagingDTO> boardList = boardEntities.map(
                board -> new BoardPagingDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle(),
                        board.getBoardContents(),
                        board.getBoardFilename())
        );
        return boardList;
    }

}
