<%--

       Copyright 2010-2022 the original author or authors.

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

          https://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.

--%>
<%@ include file="../diary/IncludeTopforDiary.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%--<div id="BackLink"><stripes:link--%>
<%--        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"--%>
<%--        event="viewProduct">--%>
<%--    <stripes:param name="productId" value="${actionBean.product.productId}" />--%>
<%--    Return to ${actionBean.product.productId}--%>
<%--</stripes:link></div>--%>

<style>
    div {
        display: block;
    }
    .image-wrap{
        width: 100%;
        border-radius: 10px;
        display: flex;
        flex: 1 1 30%;
        flex-direction: column;
    }
    .content-wrap{
        text-align: left;
        padding: 10px;
        height: 300px;
        background-color: #CCCCCC;
        border-radius: 10px;
    }
    .comments-title{
        text-align: left;
        font-size: 25px;
    }
    .comments-wrap{
    }
    .comments{
        padding: 10px;
        height: 65px;
        background-color: #CCCCCC;
        border-radius: 10px;
    }
    .comments-list{
    }
    .other-wrap{
        padding: 10px;
        margin: 10px;
        background-color: #CCCCCC;
        border-radius: 10px;
    }
    .diary-wrap {
        display: flex;
        margin-left: auto;
        margin-right: auto;
        flex-direction: column;
        width: 768px;
    }
    .diary-wrap h1 {
        font-size: 3rem;
        line-height: 1.5;
        letter-spacing: -0.004em;
        margin-top: 0px;
        font-weight: 800;
        margin-bottom: 2rem;
        word-break: keep-all;
    }
    .writer-info {
        display: flex;
        align-items: center;
        font-size: 1rem;
        justify-content: space-between;
    }
    .comments-writer {
        margin-bottom: 1.5rem;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .comments-content{
        text-align: left;
    }
    .comments-write{
        text-align: left;
        font-size: 25px;
    }

    th, td {
        background-color: white;
        border-bottom: 1px solid gray;
        padding: 3px;
    }

</style>

<div id="Catalog">
    <div style="padding:120px 10px 10px 10px;">
        <p style="color:gray;">'${actionBean.diary.userid}'의 다른 글</p>
        <br>
        <c:forEach var="diary" items="${actionBean.diaryListByUserid}">

            <div class="detail">
                <table id="detail-table" class="detail-table">
                    <td style="width: 500px; color:gray";>
                        <c:choose>
                            <c:when test ="${diary.no == actionBean.diary.no}">
                                <stripes:link class="detail-box" beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean" event="getDiaryContent">
                                <stripes:param name="diary.no" value="${diary.no}" />
                                <h5 style="color:black; display:inline">${diary.title}</h5>
                                <h6 style="color:mediumblue; display:inline">(댓글:${diary.comments}&nbsp 좋아요:${diary.likes})</h6>
                                </stripes:link></td>
                                <td style="width: 200px; color:black; font-size:12px; font-weight: bold; text-align: right"; >${diary.date}</td>
                            </c:when>
                            <c:otherwise>
                                <stripes:link class="detail-box" beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean" event="getDiaryContent">
                                <stripes:param name="diary.no" value="${diary.no}" />
                                <h5 style="color:dimgray;  display:inline">${diary.title}</h5>
                                <h6 style="color:royalblue; display:inline">(댓글:${diary.comments}&nbsp 좋아요:${diary.likes})</h6>
                                </stripes:link></td>
                                <td style="width: 200px; color:gray; font-size:12px; text-align: right;">${diary.date}</td>
                            </c:otherwise>
                    </c:choose>
                </table>
            </div>
        </c:forEach>
    </div>
    <div>
        <div>
            <c:if test="${actionBean.prev2}">
                <stripes:link style="color:gray;"
                              beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                              event="getDiaryContent">
                    ◁
                    <stripes:param name="page2" value="${actionBean.page2 - 1}" />
                </stripes:link>
            </c:if>
            <c:forEach begin="${actionBean.beginPage2}" end="${actionBean.endPage2}" step="1" var="index">
                <c:choose>
                    <c:when test="${actionBean.page2==index}">
                        ${index}
                    </c:when>
                    <c:otherwise>
                        <stripes:link style="color:gray;"
                                      beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                                      event="getDiaryContent">
                            ${index}
                            <stripes:param name="page2" value="${index}" />
                        </stripes:link>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${actionBean.next2}">
                <stripes:link style="color:gray;"
                              beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                              event="getDiaryContent">
                    ▷
                    <stripes:param name="page2" value="${actionBean.page2 + 1}" />
                </stripes:link>
            </c:if>
        </div>
    </div>
    <div class="diary-wrap">
        <div class="head-wrap">
            <br><br>
            <span style="font-size: 50px" align="left">
                <b>&lt;&nbsp;${actionBean.diary.title}&nbsp;&gt;</b>
            </span>
            <span style="margin-left: 60%">
                카테고리 : #${actionBean.diary.categoryid}
            </span>


            <div class="writer-info">
                <c:if test="${actionBean.diary.userid == sessionScope.accountBean.account.username}">
                <span style="margin-left: 88.8%; font-size: 15px; border-radius:15px; padding: 4px; background-color: #aaaaaa">
                        <stripes:link
                                beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                                event="getNewDiaryForm">
                            &nbsp;&nbsp;새 글 작성&nbsp;&nbsp;
                        </stripes:link>
                </span>
                </c:if>
            </div>

            <div class="writer-info">
                <span style="font-size: 15px; padding: 10px; margin: 10px; background-color: #CCCCCC; border-radius: 10px;">
                    ${actionBean.diary.userid}
                ••
                    ${actionBean.diary.date}
                </span>
                <!--게시글 작성자 아이디로 로그인이 되어있는지 조건에 대한 조건-->
                <c:if test="${actionBean.diary.userid == sessionScope.accountBean.account.username}">
                    <span style="margin-left: 55%; font-size: 15px; border-radius:15px; padding: 4px; background-color: #aaaaaa">
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                            event="getEditDiaryForm">
                        <stripes:param name="no" value="${actionBean.diary.no}" />
                        &nbsp;&nbsp;수정&nbsp;&nbsp;
                    </stripes:link>
                    </span>
                    <span style="margin-left: 1%; font-size: 15px; border-radius:15px; padding: 4px; background-color: #aaaaaa">
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                            event="deleteDiary">
                        <stripes:param name="no" value="${actionBean.diary.no}" />
                        &nbsp;&nbsp;삭제&nbsp;&nbsp;
                    </stripes:link>
                        </span>
                </c:if>
            </div>
            &nbsp;<br>

            <div class="image-wrap">
                <img src="https://share.shbox.kr/jpetstore_war/static/${actionBean.diary.imgurl}" style="border-radius: 10px;" />
            </div>
        </div>
        <br><br>
        <div class="content-wrap">
            ${actionBean.diary.content}
        </div>

        <c:if test="${sessionScope.accountBean != null}">
            <br><br>
            <span class="comments-write"><b>댓글 작성하기</b></span>
            <br>
            <div class="comments">
                <div>
                    <stripes:form beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                                  >
                        <stripes:text size="100%" name="comments.comment"/>
                        <stripes:param name="no" value="${actionBean.diary.no}" />
                        <br><br>
                        <stripes:submit name="insertComment" value="Submit" />
                    </stripes:form>
                </div>
            </div>
        </c:if>

        <br><br>
        <div class="comments-wrap">
            <div class="comments-title">
                <span class="comments-content"><b>${actionBean.diary.comments} 개의 덧글</b></span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span class="comments-content"><b>${actionBean.diary.likes} 개의 좋아요</b></span>
                <c:if test="${sessionScope.accountBean != null}">
                    <c:if test="${actionBean.clickedLike == 1}">
                    <span>
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                            event="deleteLike">
                        <stripes:param name="likes.d_no" value="${actionBean.diary.no}" />
                        💔
                    </stripes:link>
                    </span>
                    </c:if>
                    <c:if test="${actionBean.clickedLike == 0}">
                    <span>
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                            event="insertLike">
                        <stripes:param name="likes.d_no" value="${actionBean.diary.no}" />
                        💕
                    </stripes:link>
                    </span>
                    </c:if>
                </c:if>
            </div>

            <br>
            <div class="comments-list">
                <c:forEach var="comments" items="${actionBean.commentsList}">
                    <div class="other-wrap">
                        <div class="comments-writer">
                            <span style="color: #666666">작성자 : ${comments.userid}</span>
                            <span style="color: #666666">${comments.date}</span>
                        </div>

                        <div class="comments-content">
                                ${comments.comment}
                        </div>
                        <c:if test="${comments.userid == sessionScope.accountBean.account.username}">
                            <span style="margin-left: 87%; font-size: 10px; border-radius:15px; padding: 2px; background-color: #aaaaaa">
                            <stripes:link
                                    beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                                    event="updateComment">
                                <stripes:param name="comments.c_no" value="${comments.c_no}" />
                                <stripes:param name="no" value="${actionBean.diary.no}" />
                                &nbsp;&nbsp;수정&nbsp;&nbsp;
                            </stripes:link>
                            </span>
                            <span style="margin-left: 1%; font-size: 10px; border-radius:15px; padding: 2px; background-color: #aaaaaa">
                            <stripes:link
                                    beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                                    event="deleteComment">
                                <stripes:param name="comments.c_no" value="${comments.c_no}" />
                                <stripes:param name="no" value="${actionBean.diary.no}" />
                                &nbsp;&nbsp;삭제&nbsp;&nbsp;
                            </stripes:link>
                            </span>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>



