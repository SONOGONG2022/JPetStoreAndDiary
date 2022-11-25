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
    .diary-wrap {
        display: flex;
        margin: -1rem;
        flex-wrap: wrap;
    }
    .card-wrap {
        width:20rem;
        border-radius: 4px;
        margin: 1rem;
        display: flex;
        flex-direction: column;
        background: #1E1E1E;
    }
    .thumbnail {
        display: block;
        width: 100%;
        height: 100%;
        text-decoration: none;
    }
    .detail {
        padding: 1rem;
        display: flex;
        flex: 1 1 0%;
        flex-direction: column;
    }
    .detail-box {
        display: block;
        text-decoration: none;
    }

    .detail-box h4 {
        font-size: 1rem;
        margin: 0px 0px 0.25rem;
        line-height: 1.5;
        word-break: break-word;
        text-overflow: ellipsis;
        white-space: nowrap;
        overflow: hidden;
        color: #ECECEC;
    }
    .sub-info {
        font-size: 0.75rem;
        line-height: 1.5;
        color: #ECECEC;
    }
    .sub-sep {
        margin-left: 0.5rem;
        margin-right: 0.5rem;
        color: #ECECEC;
    }
    .writer-info {
        padding: 0.625rem 1rem;
        border-top: 1px solid #ECECEC;
        display: flex;
        font-size: 0.75rem;
        line-height: 1.5;
        justify-content: space-between;
    }
</style>

<div id="Catalog">
    <div class="diary-wrap">
        <c:forEach var="diary" items="${actionBean.diaryList}">
            <div class="card-wrap">
                <stripes:link class="thumbnail" beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean" event="detailDiary">
                    <img src="http://localhost:8080/jpetstore_war/static/${diary.imgurl}" style="width:100%; height: 100%;">
                </stripes:link>
                <div class="detail">
                    <stripes:link class="detail-box" beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean" event="detailDiary">
                        <h4>${diary.title}</h4>
                    </stripes:link>
                </div>
                <div class="sub-info">
                    <span>${diary.categoryid}</span>
                    <span class="sub-sep">.</span>
                    <span>${diary.date}</span>
                    <span class="sub-sep">.</span>
                    <span>${diary.comments} 개의 댓글</span>
                </div>
                <div class="writer-info">
                    <div style="display: flex; align-items: center; color: #ECECEC;">
                        <span>
                        by ${diary.userid}
                    </span>
                    </div>
                    <div style="display: flex; align-items: center; color:#ECECEC;">
                        ${diary.likes}
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <c:forEach begin="${actionBean.beginPage}" end="${actionBean.endPage}" step="1" var="index">
        <c:choose>
            <c:when test="${actionBean.pageNumber==index}">
                ${index}
            </c:when>
            <c:otherwise>
                <stripes:link
                        beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                        event="viewDiary">
                    ${index}
                    <stripes:param name="pageNumber" value="${index}" />
                </stripes:link>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>



