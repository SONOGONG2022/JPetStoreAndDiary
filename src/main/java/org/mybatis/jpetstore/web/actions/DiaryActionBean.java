package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Comments;
import org.mybatis.jpetstore.domain.Diary;
import org.mybatis.jpetstore.domain.Likes;
import org.mybatis.jpetstore.service.DiaryService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SessionScope
public class DiaryActionBean extends AbstractActionBean{

    private static final long serialVersionUID = -934545943912607823L;

    @SpringBean
    private transient DiaryService diaryService;

    private static final String VIEW_PET_DIARY_BOARD = "/WEB-INF/jsp/diary/PetDiary.jsp";
    private static final String VIEW_DIARY_CONTENT = "/WEB-INF/jsp/diary/PetDiaryContent.jsp";
    private static final String VIEW_NEW_DIARY_FORM="/WEB-INF/jsp/diary/NewDiaryForm.jsp";
    private static final String VIEW_EDIT_DIARY_FORM="/WEB-INF/jsp/diary/EditDiaryForm.jsp";
    private static final String MAIN="/WEB-INF/jsp/catalog/Main.jsp";

    //diary no
    //private int no;
    //public void setNo(int no){this.no=no;}
    //public int getNo(){return no;}
    //diary board page
    private int page;

    // 업로드된 이미지
    private FileBean petImage;
    public FileBean getPetImage() {
        return petImage;
    }
    public void setPetImage(FileBean petImage) {
        this.petImage = petImage;
    }

    // paging 처리
    private int totalCount;
    private int beginPage;
    private int endPage;
    private boolean next;
    private boolean prev;

    public boolean getNext() {
        return next;
    }
    public boolean getPrev() {
        return prev;
    }
    public int getBeginPage() {
        return beginPage;
    }
    public int getEndPage() {
        return endPage;
    }

    public void setPage(int page){this.page=page;}
    public int getPage(){return this.page;}
    private int clickedLike;
    public void setClickedLike(int clickedLike){this.clickedLike=clickedLike;}
    public int getClickedLike(){return clickedLike;}

    // 작성한 덧글
    private Comments comments;

    public Comments getComments() {
        return comments;
    }
    public void setComments(Comments comments) {
        this.comments = comments;
    }

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

    public String getCategoryid(){return diary.getCategoryid();}
    public void setCategoryid(String categoryid){diary.setCategoryid(categoryid);}

    private String boardCategoryid;//게시글의 카테고리가 아니라 양육일지 게시판의 카테고리
    public String getBoardCategoryid(){return boardCategoryid;}
    public void setBoardCategoryid(String boardCategoryid){this.boardCategoryid = boardCategoryid;}

    public ForwardResolution getDiaryContent(){
        diary=diaryService.getDiary(diary.getNo());
        commentsList = diaryService.getCommentsList(diary.getNo());
        clickedLike = 0;
        if(isAuthenticated() && isMyDiaryOrComments(diary.getUserid())) {
            likes = new Likes();
            likes.setUserid(myUserid);
            likes.setD_no(diary.getNo());
            System.out.println(clickedLike);
            clickedLike= diaryService.didClickedLike(likes);
        }
        return new ForwardResolution(VIEW_DIARY_CONTENT);
    }

    public ForwardResolution getEditDiaryForm(){
        if (!isAuthenticated() && !isMyDiaryOrComments(diary.getUserid()))
            return new ForwardResolution(MAIN);
        diary=diaryService.getDiary(diary.getNo());
        return new ForwardResolution(VIEW_EDIT_DIARY_FORM);
    }

    public ForwardResolution getNewDiaryForm() {
        if (!isAuthenticated())
            return new ForwardResolution(MAIN);
        return new ForwardResolution(VIEW_NEW_DIARY_FORM);
    }

    //임시로 삽입, 삭제, 수정 작업 후 메인 페이지로 이동
    public ForwardResolution insertDiary(){
        if (!isAuthenticated())
            return new ForwardResolution(MAIN);
        //테스트할 때는 톰캣이 종료 및 실행될때마다 정적 리소스가 초기화 되기 때문에, diary.imgurl = "default.png"로 다 넣으시면 됩니다
//        try {
//            fileUpload();
//        } catch (IOException e) {
//            setMessage("Image File Error");
//            return new ForwardResolution(MAIN);
//        }
        diary.setImgurl("default.png");
        diaryService.insertDiary(diary);
        paging();
        diaryList=diaryService.getDiaryList(0);
        return new ForwardResolution(VIEW_PET_DIARY_BOARD);
    }

    public ForwardResolution updateDiary(){
        if (!isAuthenticated() && !isMyDiaryOrComments(diary.getUserid()))
            return new ForwardResolution(MAIN);
        //테스트할 때는 톰캣이 종료 및 실행될때마다 정적 리소스가 초기화 되기 때문에, diary.imgurl = "default.png"로 다 넣으시면 됩니다
//        try {
//            fileUpload();
//        } catch (IOException e) {
//            setMessage("Image File Error");
//            return new ForwardResolution(MAIN);
//        }
        diary.setImgurl("default.png");
        diaryService.updateDiary(diary);
        diary=diaryService.getDiary(diary.getNo());
        commentsList = diaryService.getCommentsList(diary.getNo());
        if(myUserid!=null){
            likes.setUserid(myUserid);
            likes.setD_no(diary.getNo());
            clickedLike= diaryService.didClickedLike(likes);
        }
        return new ForwardResolution(VIEW_DIARY_CONTENT);
    }

    public Resolution deleteDiary(){
        if (!isAuthenticated() && !isMyDiaryOrComments(diary.getUserid()))
            return new ForwardResolution(MAIN);
        diaryService.deleteDiary(diary.getNo());
        return new ForwardResolution(MAIN);
        //return goMain();
        //return viewDiaryBoard();
    }

    public ForwardResolution viewDiaryBoard(){
        paging();
        int offset = (page - 1) * 6;
        diaryList=diaryService.getDiaryList(offset);
        return new ForwardResolution(VIEW_PET_DIARY_BOARD);
    }
    public ForwardResolution viewDiaryBoardOrderByComments(){//전체 카테고리 댓글순 정렬
        paging();
        int offset = (page - 1) * 6;
        diaryList=diaryService.getDiaryListOrderByComments(offset);
        return new ForwardResolution(VIEW_PET_DIARY_BOARD);
    }
    public ForwardResolution viewDiaryBoardOrderByLikes(){//전체 카테고리 좋아요순 정렬
        paging();
        int offset = (page - 1) * 6;
        diaryList=diaryService.getDiaryListOrderByLikes(offset);
        return new ForwardResolution(VIEW_PET_DIARY_BOARD);
    }
    public ForwardResolution viewCategoriedDiaryBoard(){//선택된 카테고리
        paging();
        int offset = (page - 1) * 6;
        diaryList=diaryService.getCategoriedDiaryList(offset, boardCategoryid);
        return new ForwardResolution(VIEW_PET_DIARY_BOARD);
    }
    public ForwardResolution viewCategoriedDiaryBoardOrderByComments(){//선택된 카테고리 댓글순 정렬
        paging();
        int offset = (page - 1) * 6;
        diaryList=diaryService.getCategoriedDiaryListOrderByComments(offset, boardCategoryid);
        return new ForwardResolution(VIEW_PET_DIARY_BOARD);
    }
    public ForwardResolution viewCategoriedDiaryBoardOrderByLikes(){//선택된 카테고리 좋아요순 정렬
        paging();
        int offset = (page - 1) * 6;
        diaryList=diaryService.getCategoriedDiaryListOrderByLikes(offset, boardCategoryid);
        return new ForwardResolution(VIEW_PET_DIARY_BOARD);
    }

    public void paging() {
        if(boardCategoryid==null){
            totalCount = diaryService.getDiaryCount();
        }
        else{
            totalCount = diaryService.getCategoriedDiaryCount(boardCategoryid);
        }
        endPage = ((int)Math.ceil(page / (double)10)) * 10;

        beginPage = endPage - (10 - 1);

        int totalPage = (int)Math.ceil(totalCount / (double)6);

        if (totalPage < endPage) {
            endPage = totalPage;
            next = false;
        } else {
            next = true;
        }
        prev = beginPage!=1;
    }

    public void fileUpload() throws IOException {
        String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
        String saveDir = context.getRequest().getSession().getServletContext().getRealPath("/static");
        File dir = new File(saveDir);
        diary.setImgurl("default.png");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (petImage != null && petImage.getSize() != 0) {
            try {
                String fileName = petImage.getFileName();
                int i = -1;
                i = fileName.lastIndexOf("."); // 파일 확장자 위치
                String realFileName = now + fileName.substring(i, fileName.length());  //현재시간과 확장자 합치기
                diary.setImgurl(realFileName);
                //System.out.println(saveDir + "/" + realFileName);
                petImage.save(new File(saveDir + "/" + realFileName));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isAuthenticated() {
        HttpSession session = context.getRequest().getSession();
        AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
        if (accountBean == null || !accountBean.isAuthenticated()) {
            //setMessage("You must sign on before attempting to check out.  Please sign on and try checking out again.");
            return false;
        } else if (accountBean.isAuthenticated()) {
            myUserid = accountBean.getAccount().getUsername();
            return true;
        } else {
            return false;
        }
    }

    public boolean isMyDiaryOrComments(String userid) {
        if (myUserid == null)
            return false;
        else if (myUserid.equals(userid))
            return true;
        else
            return false;
    }

    public ForwardResolution goMain(){
        return new ForwardResolution(MAIN);
    }

    public ForwardResolution insertLike(){
        likes = new Likes();
        likes.setUserid(myUserid);
        likes.setD_no(diary.getNo());
        diaryService.insertLike(likes);
        return getDiaryContent();
    }
    public ForwardResolution deleteLike(){
        likes = new Likes();
        likes.setUserid(myUserid);
        likes.setD_no(diary.getNo());
        diaryService.deleteLike(likes);
        return getDiaryContent();
    }

    public ForwardResolution insertComment(){
        comments.setUserid(myUserid);
        comments.setD_no(diary.getNo());
        diaryService.insertComment(comments);
        return getDiaryContent();
    }
    public ForwardResolution updateComment(){
        comments.setUserid(myUserid);
        comments.setD_no(diary.getNo());
        diaryService.updateComment(comments);
        return getDiaryContent();
    }
    public ForwardResolution deleteComment(){
        diaryService.deleteComment(comments.getC_no());
        return getDiaryContent();
    }

}
