package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.mybatis.jpetstore.domain.Comments;
import org.mybatis.jpetstore.domain.Diary;
import org.mybatis.jpetstore.domain.Likes;
import org.mybatis.jpetstore.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private int no;
    private int page;
    // 업로드된 이미지
    private FileBean petImage;
    // paging 처리
    private int totalCount;
    private int beginPage;
    private int endPage;
    private boolean next;
    private boolean prev;
    private Likes likes = new Likes();
    private int clickedLike;
    // 정렬 기준 가지고 있는 변수
    private String orderCategory;
    private String orderLikesOrComments;
    private Diary diary;
    private List<Diary> diaryList;
    private String myUserid;
    // 작성한 덧글
    private Comments comments = new Comments();
    private List<Comments> commentsList;
    public int getNo() {return no;}
    public void setNo(int no) {this.no = no;}
    public String getOrderCategory() {return orderCategory;}
    public void setOrderCategory(String orderCategory) {this.orderCategory = orderCategory;}
    public String getOrderLikesOrComments() {return orderLikesOrComments; }
    public void setOrderLikesOrComments(String orderLikesOrComments) {this.orderLikesOrComments = orderLikesOrComments;}
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
    public void setClickedLike(int clickedLike){this.clickedLike=clickedLike;}
    public int getClickedLike(){return clickedLike;}
    public Comments getComments() {return comments;}
    public void setComments(Comments comments) {
        this.comments = comments;
    }
    public void setC_no(int c_no){comments.setC_no(c_no);}
    public int getC_no(){return comments.getC_no();}
    public String getMyUserid(){return myUserid;}
    public Diary getDiary(){return diary;}
    public void setDiary(Diary diary){this.diary=diary;}
    public List<Diary> getDiaryList(){return diaryList;}
    public List<Comments> getCommentsList(){return commentsList;}
    public String getImgurl(){return diary.getImgurl();}
    public void setImgurl(String imgurl){diary.setImgurl(imgurl);}
    public FileBean getPetImage() {
        return petImage;
    }
    public void setPetImage(FileBean petImage) {
        this.petImage = petImage;
    }

    /**
     * param1 : no
     * @return Forward, 양육일기 상세 정보
     */
    @DefaultHandler
    public Resolution getDiaryContent(){
        if (no == 0)
            // no 파라미터가 넘어오지 않았을 경우, 잘못된 접근임!!
            return new RedirectResolution(DiaryActionBean.class, "viewDiaryBoard");
        diary=diaryService.getDiary(no);
        commentsList = diaryService.getCommentsList(no);
        clickedLike = 0;
        if(isAuthenticated() && isMyDiaryOrComments(diary.getUserid())) {
            likes = new Likes();
            likes.setUserid(myUserid);
            likes.setD_no(diary.getNo());
            clickedLike= diaryService.didClickedLike(likes);
        }
        return new ForwardResolution(VIEW_DIARY_CONTENT);
    }

    /**
     * param1 : no
     * @return Forward, 양육일기 수정 폼
     */
    public Resolution getEditDiaryForm(){
        if (!isAuthenticated() || !isMyDiaryOrComments(diaryService.getDiaryUser(no)) || no == 0)
            return new RedirectResolution(DiaryActionBean.class, "viewDiaryBoard");
        diary=diaryService.getDiary(no);
        return new ForwardResolution(VIEW_EDIT_DIARY_FORM);
    }
    /**
     * @return Forward, 양육일기 작성 폼
     */
    public Resolution getNewDiaryForm() {
        if (!isAuthenticated())
            return new RedirectResolution(DiaryActionBean.class, "viewDiaryBoard");
        clear();
        return new ForwardResolution(VIEW_NEW_DIARY_FORM);
    }
    /**
     * param : Diary[imgurl, title, content, categoryid, userid]
     * @return Redirect, 양육일기 작성
     */
    //임시로 삽입, 삭제, 수정 작업 후 메인 페이지로 이동
    public Resolution insertDiary(){
        if (!isAuthenticated())
            return new RedirectResolution(DiaryActionBean.class, "viewDiaryBoard");
        //테스트할 때는 톰캣이 종료 및 실행될때마다 정적 리소스가 초기화 되기 때문에, diary.imgurl = "default.png"로 다 넣으시면 됩니다
//        try {
//            fileUpload();
//        } catch (IOException e) {
//            setMessage("Image File Error");
//            return new ForwardResolution(MAIN);
//        }
        diary.setImgurl("default.png");
        diaryService.insertDiary(diary);
        // 시간적 여유가 있다면 insert 하고 key를 반환받은 다음 해당 게시글로 이동할 수 있도록 로직 추가
        // 그러기 위해서는 no를 따로 가지고 있는 것이 편함.
        clear();
        return new RedirectResolution(DiaryActionBean.class, "viewDiaryBoard");
    }
    /**
     * param : Diary[imgurl, title, content, categoryid, userid]
     * @return Redirect, 양육일기 수정
     */
    public Resolution updateDiary(){
        if (!isAuthenticated() || !isMyDiaryOrComments(diary.getUserid()) || diary == null)
            return new RedirectResolution(DiaryActionBean.class, "viewDiaryBoard");
        //테스트할 때는 톰캣이 종료 및 실행될때마다 정적 리소스가 초기화 되기 때문에, diary.imgurl = "default.png"로 다 넣으시면 됩니다
//        try {
//            fileUpload();
//        } catch (IOException e) {
//            setMessage("Image File Error");
//            return new ForwardResolution(MAIN);
//        }
        diary.setImgurl("default.png");
        diaryService.updateDiary(diary);
        int memory = diary.getNo();
        clear();
        no = memory;
        return new RedirectResolution(DiaryActionBean.class);
    }
    /**
     * param : no
     * @return Redirect, 양육일기 삭제
     */
    public Resolution deleteDiary(){
        if (!isAuthenticated() || !isMyDiaryOrComments(diary.getUserid()) || no == 0)
            return new ForwardResolution(MAIN);
        diaryService.deleteDiary(no);
        clear();
        return new RedirectResolution(DiaryActionBean.class, "viewDiaryBoard");
    }
    /**
     * param : page, orderCategory, orderLikesOrComments
     * @return Forward, 양육일기 리스트 조회
     */
    public ForwardResolution viewDiaryBoard(){
        if (page == 0) {
            page = 1;
        }
        paging();
        int offset = (page - 1) * 6;
        if (orderLikesOrComments == null) {
            orderLikesOrComments = "likes";
        }
        if (orderCategory == null || orderCategory.equals("ALL")) {
            diaryList=diaryService.getDiaryList(orderLikesOrComments, offset);
        } else {
            diaryList=diaryService.getCategoriedDiaryList(offset, orderCategory, orderLikesOrComments);
        }
        return new ForwardResolution(VIEW_PET_DIARY_BOARD);
    }

    public void paging() {
        if(orderCategory==null || orderCategory.equals("ALL")){
            totalCount = diaryService.getDiaryCount();
        }
        else{
            totalCount = diaryService.getCategoriedDiaryCount(orderCategory);
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
    /**
     * param : no
     * @return Redirect, 좋아요 삽입
     */
    public Resolution insertLike(){
        if (!isAuthenticated() || no == 0)
            return new RedirectResolution(DiaryActionBean.class);
        likes.setUserid(myUserid);
        clickedLike=diaryService.didClickedLike(likes);
        if(clickedLike==0){
            diaryService.insertLike(likes);
        }
        int memory = no;
        clear();
        no = memory;
        return new RedirectResolution(DiaryActionBean.class);
    }
    /**
     * param : no
     * @return Redirect, 좋아요 삭제
     */
    public Resolution deleteLike(){
        if (!isAuthenticated() || no == 0)
            return new RedirectResolution(DiaryActionBean.class);
        likes.setUserid(myUserid);
        clickedLike=diaryService.didClickedLike(likes);
        if(clickedLike==1){
            diaryService.deleteLike(likes);
        }
        int memory = no;
        clear();
        no = memory;
        return new RedirectResolution(DiaryActionBean.class);
    }
    /**
     * param : no
     * @return Redirect, 덧글 입력
     */
    public Resolution insertComment(){
        if (!isAuthenticated() || no == 0)
            return new RedirectResolution(DiaryActionBean.class);
        comments.setUserid(myUserid);
        comments.setD_no(no);
        diaryService.insertComment(comments);
        int memory = no;
        clear();
        no = memory;
        return new RedirectResolution(DiaryActionBean.class);
    }
    /**
     * param : no, Comments[c_no]
     * @return Redirect, 덧글 수정
     */
    public Resolution updateComment(){
        if (!isAuthenticated() || no == 0 || comments.getC_no() == 0 ||isMyDiaryOrComments(diaryService.getCommentUser(comments.getC_no())))
            return new RedirectResolution(DiaryActionBean.class);
        comments.setUserid(myUserid);
        diaryService.updateComment(comments);
        int memory = no;
        clear();
        no = memory;
        return new RedirectResolution(DiaryActionBean.class);
    }
    /**
     * param : no, Comments[c_no]
     * @return Redirect, 덧글 삭제
     */
    public Resolution deleteComment(){
        if (!isAuthenticated() && isMyDiaryOrComments(diaryService.getCommentUser(comments.getC_no())))
            return new RedirectResolution(DiaryActionBean.class);
        diaryService.deleteComment(comments);
        int memory = no;
        clear();
        no = memory;
        return new RedirectResolution(DiaryActionBean.class);
    }

    public void clear() {
        no = 0;
        page = 1;

        petImage = null;

        totalCount = 0;
        beginPage = 0;
        endPage = 0;
        next = false;
        prev = false;

        likes = new Likes();
        clickedLike = 0;

        orderCategory = null;
        orderLikesOrComments = null;

        diary = null;
        diaryList = null;

        myUserid = null;
    }
}
