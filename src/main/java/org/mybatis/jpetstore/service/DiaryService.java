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
    public int insertDiary(Diary diary){
        return diaryMapper.insertDiary(diary);
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
    public List<Diary> getDiaryList(int page, String orderCategory, String orderLikesOrComments, String keyword){
        return diaryMapper.getDiaryList(page, orderCategory, orderLikesOrComments, keyword);
    }

    public List<Comments> getCommentsList(int d_no){
        return diaryCommentsMapper.getDiaryComments(d_no);
    }

    public int getDiaryCount(String orderCategory, String keyword) {
        return diaryMapper.getDiaryCount(orderCategory, keyword);
    }

    public int didClickedLike(Likes likes){
        return diaryLikesMapper.didClickedLike(likes);
    }

    public int getDiaryCountByUserid(String userid){return diaryMapper.getDiaryCountByUserid(userid);}

    public List<Diary> getDiaryListByUserid(String userid, int offset){ return diaryMapper.getDiaryListByUserid(userid, offset);}

    @Transactional
    public void insertComment(Comments comments){
        diaryCommentsMapper.insertComment(comments);
        int commentsCnt = diaryCommentsMapper.getCommentsCount(comments.getD_no());
        diaryMapper.updateDiaryComments(comments.getD_no(), commentsCnt);
    }
    @Transactional
    public void updateComment(Comments comments){
        diaryCommentsMapper.updateComment(comments);
    }
    @Transactional
    public void deleteComment(Comments comments){
        diaryCommentsMapper.deleteComment(comments);
        int commentsCnt = diaryCommentsMapper.getCommentsCount(comments.getD_no());
        diaryMapper.updateDiaryComments(comments.getD_no(), commentsCnt);
    }
    @Transactional
    public void insertLike(Likes likes){
        diaryLikesMapper.insertLike(likes);
        int likesCnt = diaryLikesMapper.getLikesCount(likes.getD_no());
        diaryMapper.updateDiaryLikes(likes.getD_no(), likesCnt);
    }
    @Transactional
    public void deleteLike(Likes likes){
        diaryLikesMapper.deleteLike(likes);
        int likesCnt = diaryLikesMapper.getLikesCount(likes.getD_no());
        diaryMapper.updateDiaryLikes(likes.getD_no(), likesCnt);
    }

    @Transactional
    public String getCommentUser(int c_no) {
        return diaryCommentsMapper.getCommentUser(c_no);
    }

    @Transactional
    public String getDiaryUser(int no) {
        return diaryMapper.getDiaryUser(no);
    }

    public int getLatestMyDiaryNo(String userid){return diaryMapper.getLatestMyDiaryNo(userid);}
    public String getFilename(int no) { return diaryMapper.getFilename(no); }

    public int doesDiaryExist(int no){return diaryMapper.doesDiaryExist(no);}
}
