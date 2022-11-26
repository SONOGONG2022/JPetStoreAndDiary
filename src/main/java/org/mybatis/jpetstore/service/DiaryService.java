package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.Comments;
import org.mybatis.jpetstore.domain.Diary;
import org.mybatis.jpetstore.domain.Likes;
import org.mybatis.jpetstore.mapper.DiaryCommentsMapper;
import org.mybatis.jpetstore.mapper.DiaryLikesMapper;
import org.mybatis.jpetstore.mapper.DiaryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiaryService {
    private final DiaryMapper diaryMapper;
    private final DiaryCommentsMapper diaryCommentsMapper;
    private final DiaryLikesMapper diaryLikesMapper;

    public DiaryService(DiaryMapper diaryMapper, DiaryCommentsMapper diaryCommentsMapper, DiaryLikesMapper diaryLikesMapper) {
        this.diaryMapper = diaryMapper;
        this.diaryCommentsMapper = diaryCommentsMapper;
        this.diaryLikesMapper = diaryLikesMapper;
    }

    @Transactional
    public void insertDiary(Diary diary){
        diaryMapper.insertDiary(diary);
    }

    @Transactional
    public void updateDiary(Diary diary){
        diaryMapper.updateDiary(diary);
    }

    public Diary getDiary(int no){return diaryMapper.getDiaryContent(no);}

    @Transactional
    public void deleteDiary(int no){
        diaryMapper.deleteDiary(no);
    }

    //not used yet
    public List<Diary> getDiaryList(int page){
        return diaryMapper.getDiaryList(page);
    }

    public List<Comments> getCommentsList(int d_no){
        return diaryCommentsMapper.getDiaryComments(d_no);
    }

    public int getDiaryCount() {
        return diaryMapper.getDiaryCount();
    }

    public int didClickedLike(Likes likes){
        return diaryLikesMapper.didClickedLike(likes);
    }

    public List<Diary> getDiaryListOrderByComments(int page){return diaryMapper.getDiaryListOrderByComments(page);}

    public List<Diary> getDiaryListOrderByLikes(int page){return diaryMapper.getDiaryListOrderByLikes(page);}

    public int getCategoriedDiaryCount(String categoryid){return diaryMapper.getCategoriedDiaryCount(categoryid);}

    public List<Diary> getCategoriedDiaryList(int page, String categoryid){return diaryMapper.getCategoriedDiaryList(page,categoryid);}

    public List<Diary> getCategoriedDiaryListOrderByComments(int page, String categoryid){return  diaryMapper.getCategoriedDiaryListOrderByComments(page,categoryid);}

    public List<Diary> getCategoriedDiaryListOrderByLikes(int page, String categoryid){return  diaryMapper.getCategoriedDiaryListOrderByLikes(page,categoryid);}

    @Transactional
    public void insertComment(Comments comments){
        diaryCommentsMapper.insertComment(comments);
    }
    @Transactional
    public void updateComment(Comments comments){
        diaryCommentsMapper.updateComment(comments);
    }
    @Transactional
    public void deleteComment(int c_no){
        diaryCommentsMapper.deleteComment(c_no);
    }
    @Transactional
    public void insertLike(Likes likes){
        diaryLikesMapper.insertLike(likes);
    }
    @Transactional
    public void deleteLike(Likes likes){
        diaryLikesMapper.deleteLike(likes);
    }
}
