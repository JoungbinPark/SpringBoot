package com.himedia.g09.dao;

import com.himedia.g09.dto.BbsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BbsDao {

    @Autowired
    private JdbcTemplate template;

    public List<BbsDto> select() {
        String sql = "select * from bbs order by id desc";

        // 테이블의 필드명과 Dto의 멤버변수이름이 정확히 일치할 때
        List<BbsDto> list = template.query(
                sql,
                new BeanPropertyRowMapper<BbsDto>(BbsDto.class));

        // 테이블의 필드명과 멤버변수 이름이 정확히 일치하지 않을때
        List<BbsDto> list2 = template.query(
                sql,
                new RowMapper<BbsDto>() {
                    @Override
                    public BbsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        BbsDto bdto = new BbsDto();
                        bdto.setId(rs.getInt("id"));
                        bdto.setWriter(rs.getString("writer"));
                        bdto.setContent(rs.getString("content"));
                        bdto.setTitle(rs.getString("title"));
                        return bdto;    //리턴된 bdto는 list에 차곡차곡 쌓입니다.
                    }
                }
        );
        return list;
    }

    public void insert(BbsDto bdto) {
        String sql = "insert into bbs(writer, title, content) values(?,?,?)";
        template.update(sql, bdto.getWriter(), bdto.getTitle(), bdto.getContent());
    }

    public BbsDto getBbs(int id) {
        String sql = "select * from bbs where id = ?";
        //방법 #1
        List<BbsDto> list = template.query(
                sql,
                new BeanPropertyRowMapper<BbsDto>(BbsDto.class),
                id
        );
        BbsDto bdto1 = null;
        if(list.size()!=0) bdto1 = list.get(0);

        //방법 #2
        BbsDto bdto = template.queryForObject(
                sql,
                new BeanPropertyRowMapper<BbsDto>(BbsDto.class),
                id
        );

      /* -------------------------------------------------------------
        // 방법 #3
      List<BbsDto> list2 = template.query(
            sql,
            new RowMapper<BbsDto>() {
               @Override
               public BbsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                  BbsDto bdto = new BbsDto();
                  bdto.setId( rs.getInt("id") );
                  bdto.setWriter( rs.getString("writer") );
                  bdto.setContent( rs.getString("content") );
                  bdto.setTitle( rs.getString("title") );
                  return bdto;
               }
            }   , id
      );
      BbsDto bdto3=null;
      if( list2.size() != 0 ) bdto3 = list.get(0);
        */

        return bdto;
    }

    public void update(BbsDto bdto) {
        String sql ="update bbs set writer=?, title=?, content=? where id=?";
        template.update(
                sql,
                bdto.getWriter(), bdto.getTitle(), bdto.getContent(), bdto.getId());

    }

    public void delete(int id) {
        String sql ="delete from bbs where id=?";
        template.update(sql, id);
    }
}

