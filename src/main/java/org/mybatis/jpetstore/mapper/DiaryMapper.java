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

    List<Diary> getDiaryList(int page);

    List<Diary> getDiaryListOrderByComments(int page);

    List<Diary> getDiaryListOrderByLikes(int page);

    int getCategoriedDiaryCount(String categoryid);

    List<Diary> getCategoriedDiaryList(@Param("page") int page,@Param("categoryid") String categoryid);

    List<Diary> getCategoriedDiaryListOrderByComments(@Param("page") int page,@Param("categoryid") String categoryid);

    List<Diary> getCategoriedDiaryListOrderByLikes(@Param("page") int page,@Param("categoryid") String categoryid);



    List<Diary> getAnotherDiaryList(Diary diary);
}
