package com.test.test.service;

import com.test.test.dto.BoardDetailDTO;
import com.test.test.dto.BoardPagingDTO;
import com.test.test.dto.BoardSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    Long save(BoardSaveDTO boardSaveDTO) throws IOException;

    List<BoardDetailDTO> findAll();

    Page<BoardPagingDTO> paging(Pageable pageable);
}
