package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Comments;

import java.util.List;

public interface DiaryCommentsMapper {

    List<Comments> getDiaryComments(int d_no);
}
