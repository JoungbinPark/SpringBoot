package com.himedia.g13.service;

import com.himedia.g13.dao.IBoardDao;
import com.himedia.g13.dto.BoardDto;
import com.himedia.g13.dto.Paging;
import com.himedia.g13.dto.ReplyDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class BoardService {

    @Autowired
    IBoardDao bdao;

    public HashMap<String, Object> selectBoard(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        HttpSession session = request.getSession();

        // paging 객체 작업
        int page = 1;
        if( request.getParameter("page") != null){
            page = Integer.parseInt( request.getParameter("page"));
            session.setAttribute("page", page);
        } else if( session.getAttribute("page") != null){
            page = (Integer)session.getAttribute("page");
        } else{
            session.removeAttribute("page");
        }

        Paging paging = new Paging();
        paging.setPage(page);

        int count = bdao.getAllCount();
        paging.setTotalCount(count);
        paging.calPaging();
        paging.setStartNum( paging.getStartNum()-1 );

        List<BoardDto> list = bdao.selectBoard(paging);

        for(BoardDto board: list){
            int cnt = bdao.getReplyCount(board.getNum());
            board.setReplycount( cnt );
        }

        result.put("boardlist", list);
        result.put("paging", paging);

        return result;
    }

    public void insertBoard(BoardDto bdto) {
        bdao.insertBoard(bdto);
    }

    public HashMap<String, Object> boardView(int num){
        HashMap<String, Object> result = new HashMap<String, Object>();
        //num 번호의 게시물 조회수 +1
        bdao.plusOneReadcount(num);
        // num으로 게시물 조회
        BoardDto bdto = bdao.getBoard(num);
        // 댓글 조회
        List<ReplyDto> list = bdao.selectReply(num);
        // 게시물, 댓글을 해시맵에 저장
        result.put("board", bdto);
        result.put("replylist", list);
        return result;
    }

    public HashMap<String, Object> boardViewWithoutCnt(int num) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        // num으로 게시물 조회
        BoardDto bdto = bdao.getBoard(num);
        // 댓글 조회
        List<ReplyDto> list = bdao.selectReply(num);
        // 게시물, 댓글을 해시맵에 저장
        result.put("board", bdto);
        result.put("replylist", list);
        return result;
    }

    public void insertReply(ReplyDto replydto) {
        bdao.insertReply(replydto);
    }

    public void deleteReply(int replynum) {
        bdao.deleteReply(replynum);
    }

    public BoardDto getBoard(int num) {
        return bdao.getBoard(num);
    }

    public void updateBoard(BoardDto boarddto) {
        bdao.updateBoard(boarddto);
    }

    public void deleteBoard(int num) {
        bdao.deleteBoard(num);
    }
}
