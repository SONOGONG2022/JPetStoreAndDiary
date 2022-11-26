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
<%@ include file="../common/IncludeTop.jsp"%>
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
</style>

<div id="Catalog">
    <div class="diary-wrap">
        <div class="head-wrap">
            <h1>${actionBean.diary.title}</h1>
            <div class="writer-info">
                <span>
                    ${actionBean.diary.userid}
                </span>
                <span> . </span>
                <span>
                    ${actionBean.diary.date}
                </span>
                <c:if test="${actionBean.diary.userid == sessionScope.accountBean.account.username}">
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                            event="getEditDiaryForm">
                        Edit
                    </stripes:link>
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                            event="deleteDiary">
                        Delete
                    </stripes:link>
                </c:if>
            </div>
            <div>
                <img src="https://share.shbox.kr/jpetstore_war/static/${actionBean.diary.imgurl}" />
            </div>
        </div>
        <div class="content-wrap">
            ${actionBean.diary.content}
        </div>
        <div class="comments-wrap">
            <h4>${actionBean.diary.comments} 개의 덧글</h4>
            <h4>${actionBean.diary.likes} 좋아요</h4>
            <div class="comments">
                <div>
                    <stripes:form beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                                  focus="">
                        <stripes:text name="comments.comment"/>
                        <stripes:submit name="addDiary" value="Submit" />
                    </stripes:form>
                </div>
            </div>
            <div>
                <c:forEach var="comments" items="${actionBean.commentsList}">
                    <div class="other-wrap">
                        <div class="comments-writer">
                            <span>${comments.userid}</span>
                        </div>
                        <div>
                            <span>${comments.date}</span>
                        </div>
                        <div>
                            ${comments.comment}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>



