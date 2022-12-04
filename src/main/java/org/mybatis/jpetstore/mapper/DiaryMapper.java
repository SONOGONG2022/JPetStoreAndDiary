package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Diary;

import java.util.List;

public interface DiaryMapper {

    Diary getDiaryContent(int no);
    int insertDiary(Diary diary);

    void updateDiary(Diary diary);

    void deleteDiary(int no);

    int getDiaryCount(String orderCategory, String keyword);

    List<Diary> getDiaryList(int page, String orderCategory, String orderLikesOrComments, String keyword);

    void updateDiaryLikes(int no, int likesCnt);
    void updateDiaryComments(int no, int commentsCnt);
    String getDiaryUser(int no);
    int getDiaryCountByUserid(String userid);
    List<Diary> getDiaryListByUserid(String userid, int page);

    int getLatestMyDiaryNo(String userid);
    String getFilename(int no);

    int doesDiaryExist(int no);
}
