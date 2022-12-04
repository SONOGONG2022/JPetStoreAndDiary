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

<div id="Catalog">

    <stripes:form beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                  focus="" onkeydown="return captureReturnKey(event);" onsubmit="checkEmpty(event)">

        <div style="font-size: 30px; margin-left: 25%;" align="left" >
            <b>댓글 수정하기 </b>
        </div><br>
        <div>
            <span style="font-size: 30px" align="left">
                <span class="title">
                        <stripes:text name="comments.comment" id="comment" value="${actionBean.comments.comment}"
                          style="
                                       font-size: 20px;
                                       width: 50%;
                                       height: 30px;
                                       resize: none;"/>
                </span>
            </span>
        </div><br>
        <stripes:submit name="updateComment" value="Submit" />
    </stripes:form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>