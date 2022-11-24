package org.mybatis.jpetstore.domain;

import java.io.Serializable;

public class Comments implements Serializable {

    private static final long serialVersionUID = 7883417166700218635L;

    private int c_no;
    private int d_no;
    private String userid;
    private String date;
    private String comment;

    public String getDate(){return date;}
    public void setDate(String date){this.date=date;}

    public String getUserid(){return userid;}
    public void setUserid(String userid){this.userid=userid;}

    public void setD_no(int d_no){this.d_no=d_no;}
    public int getD_no(){return d_no;}

    public int getC_no(){return c_no;}
    public void setC_no(int c_no){this.c_no=c_no;}

    public String getComment(){return comment;}
    public void setComment(String comment){this.comment=comment;}
}
