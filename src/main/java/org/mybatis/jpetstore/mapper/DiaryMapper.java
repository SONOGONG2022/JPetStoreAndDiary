package org.mybatis.jpetstore.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.jpetstore.domain.Diary;

import java.util.List;

public interface DiaryMapper {

    Diary getDiaryContent(int no);
    void insertDiary(Diary diary);

    void updateDiary(Diary diary);

    void deleteDiary(int no);

    int getDiaryCount();

    List<Diary> getDiaryList(String orderLikesOrComments, int page);

    int getCategoriedDiaryCount(String categoryid);

    List<Diary> getCategoriedDiaryList(int page, String categoryid, String orderLikesOrComments);

    List<Diary> getAnotherDiaryList(Diary diary);

    void updateDiaryLikes(int no, int likesCnt);
    void updateDiaryComments(int no, int commentsCnt);
}
