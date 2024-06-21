package com.himedia.g13.dao;

import com.himedia.g13.dto.BoardDto;
import com.himedia.g13.dto.Paging;
import com.himedia.g13.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IBoardDao {

    int getAllCount();
    List<BoardDto> selectBoard(Paging paging);
    int getReplyCount(int boardnum);
    void insertBoard(BoardDto bdto);

    void plusOneReadcount(int num);
    BoardDto getBoard(int num);
    List<ReplyDto> selectReply(int num);

    void insertReply(ReplyDto rdto);
    void deleteReply(int replynum);

    void updateBoard(BoardDto boarddto);
    void deleteBoard(int num);
}
