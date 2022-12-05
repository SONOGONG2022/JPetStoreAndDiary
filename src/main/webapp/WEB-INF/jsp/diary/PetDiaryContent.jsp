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
    .right-box{
        float: right;
    }
    .left-box{
        display:inline-block;
        float: left;
    }
</style>

<script>
    // 엔터키 제출 방지
    function captureReturnKey(e) {
        if(e.keyCode==13 && e.target.type != 'textarea')
            return false;
    }
    function checkEmpty(e){
        if(document.getElementById('content').value == ''){
            e.preventDefault()
            alert('내용을 입력하세요')}
    }
</script>

<div id="Catalog">
    <div class="diary-wrap">
        <div class="head-wrap">
            <br><br>
            <span style="font-size: larger">
                ${actionBean.diary.userid} 님의 양육일기
            </span>
            <hr style="border: solid 2px black;">

            <c:forEach var="diary" items="${actionBean.diaryListByUserid}">
                <c:choose>
                <c:when test ="${diary.no == actionBean.diary.no}">
                    <div style="text-align: left">
                    <stripes:link class="thumbnail" beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean" event="getDiaryContent">
                        <stripes:param name="no" value="${diary.no}" />
                        <stripes:param name="page2" value="1" />
                        <b  style="color:black;">
                            <span>[${diary.categoryid}]</span>
                            <span style="display: inline-block;  max-width:400px; overflow:hidden;text-overflow:ellipsis;">${diary.title}</span>
                            <span>[${diary.comments}]</span>
                        </b>
                    </stripes:link>
                        <span style="float: right">
                        ${diary.likes} 💕&nbsp;&nbsp;
                                ${diary.date}
                        </span>
                    </div>
                    <hr>
                </c:when>
                <c:otherwise>
                    <div style="text-align: left">
                        <stripes:link class="thumbnail" beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean" event="getDiaryContent">
                            <stripes:param name="no" value="${diary.no}" />
                            <stripes:param name="page2" value="1" />
                            <span>[${diary.categoryid}]</span>
                            <span style="display: inline-block;  max-width:400px; overflow:hidden;text-overflow:ellipsis;">${diary.title}</span>
                            <span>[${diary.comments}]</span>
                        </stripes:link>
                        <span style="float: right">
                            ${diary.likes} 💕&nbsp;&nbsp;
                                ${diary.date}
                        </span>
                    </div>
                    <hr>
                </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${actionBean.prev2}">
                <stripes:link
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
                        <stripes:link
                                beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                                event="getDiaryContent">
                            ${index}
                            <stripes:param name="page2" value="${index}" />
                        </stripes:link>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${actionBean.next2}">
                <stripes:link
                        beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                        event="getDiaryContent">
                    ▷
                    <stripes:param name="page2" value="${actionBean.page2 + 1}" />
                </stripes:link>
            </c:if>


            <br><br>
            <div text-align="left" style="font-size: 40px;overflow:hidden;word-wrap:break-word;">
                <span>
                <b>&lt;&nbsp;${actionBean.diary.title}&nbsp;&gt;</b>
            </span>
            </div>
            <span style="margin-left: 60%">
                카테고리 : #${actionBean.diary.categoryid}
            </span>

            <br>
            <div class="writer-info">
            <c:if test="${actionBean.diary.userid == sessionScope.accountBean.account.username}">
                <span style="margin-left: 88%; font-size: 15px; border-radius:15px; padding: 4px; background-color: #aaaaaa">
                        <stripes:link
                                beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                                event="getNewDiaryForm">
                            &nbsp;새 글 작성&nbsp;&nbsp;
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
                        <stripes:param name="diary.no" value="${actionBean.diary.no}" />
                        &nbsp;&nbsp;수정&nbsp;&nbsp;
                    </stripes:link>
                    </span>
                    <span style="margin-left: 1%; font-size: 15px; border-radius:15px; padding: 4px; background-color: #aaaaaa">
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                            event="deleteDiary">
                        <stripes:param name="diary.no" value="${actionBean.diary.no}" />
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
            <span style="white-space:pre-wrap; word-break: break-all">${actionBean.diary.content}</span>
        </div>

        <c:if test="${sessionScope.accountBean != null}">
            <br><br>
            <span class="comments-write"><b>댓글 작성하기</b></span>
            <br>
            <div class="comments">
                <div>
                    <stripes:form beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean" onkeydown="return captureReturnKey(event);" onsubmit="checkEmpty(event)">
                        <stripes:text size="100%" name="comments.comment" id="content" maxlength="1000"/>
                        <br><br>
                        <stripes:submit name="insertComment" value="Submit" />
                    </stripes:form>
                </div>
            </div>
        </c:if>

        <br><br>
        <div class="comments-wrap">
            <div class="comments-title">
            <span class="comments-content"><b>${actionBean.diary.comments} 개의 댓글</b></span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span class="comments-content"><b>${actionBean.diary.likes} 개의 좋아요</b></span>
            <c:if test="${sessionScope.accountBean != null}">
                <c:if test="${actionBean.clickedLike == 1}">
                    <span>
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                            event="deleteLike">
                        💔
                    </stripes:link>
                    </span>
                </c:if>
                <c:if test="${actionBean.clickedLike == 0}">
                    <span>
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                            event="insertLike">
                        💕
                    </stripes:link>
                    </span>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.accountBean == null}">
                💕
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
                            <span  style="width:300px;overflow:hidden;word-wrap:break-word;">${comments.comment}</span>
                        </div>
                        <c:if test="${comments.userid == sessionScope.accountBean.account.username}">
                            <span style="margin-left: 87%; font-size: 10px; border-radius:15px; padding: 2px; background-color: #aaaaaa">
                            <stripes:link
                                    beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                                    event="getUpdateCommentForm">
                                <stripes:param name="comments.c_no" value="${comments.c_no}" />
                                &nbsp;&nbsp;수정&nbsp;&nbsp;
                            </stripes:link>
                            </span>
                            <span style="margin-left: 1%; font-size: 10px; border-radius:15px; padding: 2px; background-color: #aaaaaa">
                            <stripes:link
                                    beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                                    event="deleteComment">
                                <stripes:param name="comments.c_no" value="${comments.c_no}" />
                                <stripes:param name="comments.d_no" value="${actionBean.diary.no}" />
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



