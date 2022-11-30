<%@ include file="../diary/IncludeTopforDiary.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
%>

<%--<div id="BackLink"><stripes:link--%>
<%--        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"--%>
<%--        event="viewProduct">--%>
<%--    <stripes:param name="productId" value="${actionBean.product.productId}" />--%>
<%--    Return to ${actionBean.product.productId}--%>
<%--</stripes:link></div>--%>
<style>
    .comments{
        margin-left: 1%;
        padding: 10px;
        height: 50px;
        background-color: #CCCCCC;
        border-radius: 10px;
    }
    .comments_image{
        margin-left: 28%;
        padding: 10px;
        height: 65px;
        background-color: #CCCCCC;
        border-radius: 10px;
    }
</style>

<div id="Catalog">
    <div class="diary-wrap">
        <div class="head-wrap">
            <stripes:form beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean" focus="">
                &nbsp;<br><br>
                <span style="margin-left: 35%">
                    Categories:
                <stripes:select name="diary.categoryid">
                    <stripes:options-collection collection="${sessionScope.accountBean.categories}" />
                </stripes:select>
                </span>
                <br><br>
                <div>
                    <span style="font-size: 30px" align="left">
                        <b>제목 : </b>
                        <span class="comments">
                            <stripes:text size="80%" name="diary.title"/>
                        </span>
                    </span>
                </div><br><br>

                <div>
                    <span style="font-size: 30px" align="left">
                        <b>내용 : </b>
                        <span class="comments">
                            <stripes:text size="80%" name="diary.content"/>
                        </span>
                    </span>
                </div><br><br>

                <div>
                   <span class="comments_image">
                        <stripes:file name="petImage" accept=".jpg,.png,.jpeg"/>
                    </span>
                </div><br>

                ${sessionScope.accountBean.account.username}
                <stripes:param name="diary.userid" value="${sessionScope.accountBean.account.username}" />

                <stripes:submit name="insertDiary" value="Submit" />
            </stripes:form>
        </div>
    </div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>



