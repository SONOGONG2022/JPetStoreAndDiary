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
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<script>
  // 엔터키 제출 방지
  function captureReturnKey(e) {
    if(e.keyCode==13 && e.target.type != 'textarea')
      return false;
  }
  function checkEmpty(e){
    if(document.getElementById('att').value == ''){
      e.preventDefault()
      alert('Description을 입력하세요')}
    else if(document.getElementById('price').value == ''){
      e.preventDefault()
      alert('List Price를 입력하세요')}
    else if(document.getElementById('qtt').value == ''){
      e.preventDefault()
      alert('Quantity를 입력하세요')}
  }
</script>

<%@ include file="../common/IncludeTop.jsp"%>

<jsp:useBean id="catalog"
             class="org.mybatis.jpetstore.web.actions.CatalogActionBean" />

<div id="BackLink"><stripes:link
        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
        event="viewAllProduct">
  Return to Product
</stripes:link></div>

<div id="Catalog"><stripes:form  onkeydown="return captureReturnKey(event);" onsubmit="checkEmpty(event)"
        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
        focus="">

  <h2>${actionBean.product.name}</h2>

  <table>
    <tr>
      <th>Item ID</th>
      <th>Description</th>
      <th>List Price</th>
      <th>Quantity</th>
    </tr>
    <tr>
      <td>${actionBean.product.productId}</td>
      <td><stripes:text name="item.attribute1" id="att"/></td>
      <td><stripes:text name="item.listPrice" id="price"/></td>
      <td><stripes:text name="item.quantity" id="qtt"/></td>
    </tr>
  </table>
  <stripes:submit name="updateItem" value="submit" />
</stripes:form></div>

<%@ include file="../common/IncludeBottom.jsp"%>