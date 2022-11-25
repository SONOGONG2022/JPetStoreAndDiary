package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Comments;
import org.mybatis.jpetstore.domain.Diary;
import org.mybatis.jpetstore.domain.Likes;
import org.mybatis.jpetstore.service.DiaryService;

import java.util.List;

@SessionScope
public class DiaryActionBean extends AbstractActionBean{

    private static final long serialVersionUID = -934545943912607823L;

    @SpringBean
    private transient DiaryService diaryService;

    private static final String VIEW_PET_DIARY_BOARD = "/WEB-INF/jsp/diary/NewDiaryForm.jsp";
    private static final String VIEW_DIARY_CONTENT = "/WEB-INF/jsp/diary/PetDiaryContent.jsp";
    private static final String VIEW_NEW_DIARY_FORM="/WEB-INF/jsp/diary/NewDiaryForm.jsp";
    private static final String VIEW_EDIT_DIARY_FORM="/WEB-INF/jsp/diary/EditDiaryForm.jsp";
    private static final String MAIN="/WEB-INF/jsp/catalog/Main.jsp";

    //diary no
    //private int no;
    //public void setNo(int no){this.no=no;}
    //public int getNo(){return no;}
    //diary board page
    private int page = 1;
    public void setPage(int page){this.page=page;}
    public int getPage(){return this.page;}
    private int clickedLike;
    public void setClickedLike(int clickedLike){this.clickedLike=clickedLike;}
    public int getClickedLike(){return clickedLike;}

    //my userid
    private String myUserid;
    public String getMyUserid(){return myUserid;}
    public void setMyuserid(String myUserid){this.myUserid=myUserid;}
    private Diary diary;
    public Diary getDiary(){return diary;}
    public void setDiary(Diary diary){this.diary=diary;}
    private List<Diary> diaryList;
    public List<Diary> getDiaryList(){return diaryList;}
    private List<Comments> commentsList;
    public List<Comments> getCommentsList(){return commentsList;}

    private Likes likes;

    public String getImgurl(){return diary.getImgurl();}
    public void setImgurl(String imgurl){diary.setImgurl(imgurl);}

    public String getUserid(){return diary.getUserid();}
    public void setUserid(String userid){diary.setUserid(userid);}

    public String getDate(){return diary.getDate();}
    public void setDate(String date){diary.setDate(date);}

    public String getTitle(){return diary.getTitle();}
    public void setTitle(String title){diary.setTitle(title);}

    public String getContent(){return diary.getContent();}
    public void setContent(String content){diary.setContent(content);}

    public int getViews(){return diary.getViews();}
    public void setViews(int views){diary.setViews(views);}

    public int getNo(){return diary.getNo();}
    public void setNo(int no){diary.setNo(no);}



    public ForwardResolution getDiaryContent(){
        diary=diaryService.getDiary(diary.getNo());
        commentsList = diaryService.getCommentsList(diary.getNo());
        if(myUserid!=null){
            likes.setUserid(myUserid);
            likes.setD_no(diary.getNo());
            clickedLike= diaryService.didClickedLike(likes);
        }
        return new ForwardResolution(VIEW_DIARY_CONTENT);
    }

    public ForwardResolution getEditDiaryForm(){
        diary=diaryService.getDiary(diary.getNo());
        return new ForwardResolution(VIEW_EDIT_DIARY_FORM);
    }

    //임시로 삽입, 삭제, 수정 작업 후 메인 페이지로 이동
    public ForwardResolution insertDiary(){
        diaryService.insertDiary(diary);
        return goMain();
        //return viewDiaryBoard();
    }

    public ForwardResolution updateDiary(){
        diaryService.updateDiary(diary);
        return goMain();
        //return viewDiaryBoard();
    }

    public ForwardResolution deleteDiary(){
        diaryService.deleteDiary(diary.getNo());
        return goMain();
        //return viewDiaryBoard();
    }

    //not yet
    public ForwardResolution viewDiaryBoard(){
        diaryList=diaryService.getDiaryList(page);
        return new ForwardResolution(VIEW_PET_DIARY_BOARD);
    }
    public ForwardResolution goMain(){
        return new ForwardResolution(MAIN);
    }

}
