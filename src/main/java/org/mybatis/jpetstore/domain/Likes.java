package org.mybatis.jpetstore.domain;

import java.io.Serializable;

public class Likes implements Serializable {

    private static final long serialVersionUID = 1313319218136418241L;

    private int d_no;
    private String userid;

    public void setUserid(String userid){this.userid=userid;}
    public String getUserid(){return this.userid;}

    public void setD_no(int d_no){this.d_no=d_no;}
    public int getD_no(){return this.d_no;}
}
