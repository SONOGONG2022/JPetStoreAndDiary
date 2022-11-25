package org.mybatis.jpetstore.domain;

import net.sourceforge.stripes.validation.Validate;

import java.io.Serializable;

public class Diary implements Serializable  {

    private static final long serialVersionUID = 4815593794478315239L;

    private String imgurl;
    private String userid;
    private String date;
    private String title;
    private String content;
    private int views;

    private String categoryid;

    private int likes;

    private int comments;

    private int no;

    private int page;

    public String getImgurl(){return imgurl;}
    public void setImgurl(String imgurl){this.imgurl=imgurl;}

    public String getUserid(){return userid;}
    @Validate(required = true, on = {"insertDiary"})
    public void setUserid(String userid){this.userid=userid;}

    public String getDate(){return date;}
    public void setDate(String date){this.date=date;}

    public String getTitle(){return title;}

    @Validate(required = true, on = {"insertDiary"})
    public void setTitle(String title){this.title=title;}

    public String getContent(){return content;}
    @Validate(required = true, on = {"insertDiary"})
    public void setContent(String content){this.content=content;}

    public int getViews(){return views;}
    public void setViews(int views){this.views=views;}

    public int getNo(){return no;}
    public void setNo(int no){this.no=no;}

    public int getLikes(){return likes;}
    public void setLikes(int likes){this.likes=likes;}

    public int getComments(){return comments;}
    public void setComments(int comments){this.comments=comments;}

    public String getCategoryid(){return categoryid;}

    @Validate(required = true, on = {"insertDiary"})
    public void setCategoryid(String category){this.categoryid=category;}

    public int getPage(){return page;}
    public void setPage(int page){this.page=page;}

}
