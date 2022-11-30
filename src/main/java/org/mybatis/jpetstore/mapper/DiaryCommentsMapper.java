package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Comments;

import java.util.List;

public interface DiaryCommentsMapper {
    List<Comments> getDiaryComments(int d_no);
    void insertComment(Comments comments);
    void updateComment(Comments comments);
    void deleteComment(Comments comments);
    int getCommentsCount(int no);
}
