package com.test.test.controller;

import com.test.test.dto.BoardDetailDTO;
import com.test.test.dto.BoardPagingDTO;
import com.test.test.dto.BoardSaveDTO;
import com.test.test.page.PagingConst;
import com.test.test.repository.BoardRepository;
import com.test.test.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

    private final BoardService bs;
    private final BoardRepository br;

    @GetMapping("main")
    public String main() {
        return "board/main";
    }

    @GetMapping("save")
    public String saveForm() {
        return "board/save";
    }

    @PostMapping("save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO) throws IOException {
        Long boardId = bs.save(boardSaveDTO);
        return "board/main";
    }

    @GetMapping
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardPagingDTO> boardList = bs.paging(pageable);
        model.addAttribute("boardList", boardList);

        // startPage, endPage 계산
        // 현재 2페이지 일 때 시작 페이지 1, 끝 페이지 3
        // 현재 7페이지 일 때 시작 페이지 6, 끝 페이지 8
//        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
//        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
//
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);

        System.out.println("boardList.getContent() = " + boardList.getContent()); // 요청 페이지에 들어있는 데이터, toString이 없기 때문에 주소값이 출력
        System.out.println("boardList.getTotalElements() = " + boardList.getTotalElements()); // 전체 글 갯수
        System.out.println("boardList.getNumber() = " + boardList.getNumber()); // JPA 기준 요청 페이지
        System.out.println("boardList.getTotalPages() = " + boardList.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardList.getSize() = " + boardList.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardList.hasPrevious() = " + boardList.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardList.isFirst() = " + boardList.isFirst()); // 첫 페이지인지 여부
        System.out.println("boardList.isLast() = " + boardList.isLast()); // 마지막 페이지인지 여부

//        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
//        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();

//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);

        return "board/paging";
    }

//    @GetMapping
//    public String paging(Model model) {
//        List<BoardDetailDTO> boardList = bs.findAll();
//        model.addAttribute("boardList", boardList);
//        return "board/paging";
//    }

}
