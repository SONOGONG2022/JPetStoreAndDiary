package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Diary;

import java.util.List;

public interface DiaryMapper {

    Diary getDiaryContent(int no);
    void insertDiary(Diary diary);

    void updateDiary(Diary diary);

    void deleteDiary(int no);

    List<Diary> getDiaryList(int page);

    List<Diary> getAnotherDiaryList(Diary diary);
}
