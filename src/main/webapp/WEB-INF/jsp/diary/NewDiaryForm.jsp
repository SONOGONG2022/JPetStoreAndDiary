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
    .comment{
        margin-left: 1%;
        padding: 10px;
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
<script>
    // 엔터키 제출 방지
    function captureReturnKey(e) {
        if(e.keyCode==13 && e.target.type != 'textarea')
            return false;
    }
    function checkEmpty(e){
        if(document.getElementById('title').value == ''){
            e.preventDefault()//제출완료 페이지로 넘어가는 것 방지
            alert('제목을 입력하세요')}
        else if(document.getElementById('content').value == ''){
            e.preventDefault()
            alert('내용을 입력하세요')}
    }
</script>
<div id="Catalog">
    <div class="diary-wrap">
        <div class="head-wrap">
            <stripes:form beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean" focus="" onkeydown="return captureReturnKey(event);" onsubmit="checkEmpty(event)">
                &nbsp;<br><br>
                <span style="margin-left: 35%">
                    Categories:
                <stripes:select name="diary.categoryid">
                    <stripes:options-collection collection="${sessionScope.accountBean.categories}" />
                </stripes:select>
                </span>
                <br><br>
                <div style="font-size: 30px; margin-left: 25%;" align="left" >
                    <b>제목 </b>
                </div>
                <div>
                    <span style="font-size: 30px" align="left">
                        <span class="title">
                            <stripes:text  name="diary.title" id="title"
                                               style="
                                               font-size: 20px;
                                               width: 50%;
                                               height: 30px;
                                               resize: none;"/>
                        </span>
                    </span>
                </div><br><br>
                <div style="font-size: 30px; margin-left: 25%;" align="left" >
                    <b>내용 </b>
                </div>
                <div>
                    <span style="font-size: 30px" align="left">
                        <span class="content">
                            <stripes:textarea  name="diary.content" id="content"
                                               style="
                                               wrap=on;
                                               font-size: 20px;
                                               width: 50%;
                                               height: 500px;
                                               resize: none;"/>
                        </span>
                    </span>
                </div><br><br>
                <div>
                   <span class="comments_image">
                       <stripes:file name="petImage" accept=".jpg,.png,.jpeg"></stripes:file>
                    </span>
                </div><br>
                <stripes:param name="diary.userid" value="${sessionScope.accountBean.account.username}" />
                <stripes:submit name="insertDiary" value="Submit" />
            </stripes:form>
        </div>
    </div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>



