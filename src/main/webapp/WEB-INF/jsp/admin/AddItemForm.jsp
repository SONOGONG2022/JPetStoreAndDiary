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

    if(document.getElementById('id').value == ''){
      e.preventDefault()//제출완료 페이지로 넘어가는 것 방지
      alert('Item ID를 입력하세요')}
    else if(document.getElementById('att').value == ''){
      e.preventDefault()
      alert('Description을 입력하세요')}
    else if(document.getElementById('price').value == ''){
      e.preventDefault()
      alert('List Price를 입력하세요')}
    else if(document.getElementById('qtt').value == ''){
      e.preventDefault()
      alert('Quantity를 입력하세요')}

    if (isNaN(document.getElementById('price').value)) {
      e.preventDefault()//제출완료 페이지로 넘어가는 것 방지
      alert("List Price는 숫자만 입력 가능합니다");
    }
    else if (isNaN(document.getElementById('qtt').value)) {
      e.preventDefault()//제출완료 페이지로 넘어가는 것 방지
      alert("Quantity는 숫자만 입력 가능합니다");
    }

    if (document.getElementById('price').value > 99999999.99) {
      e.preventDefault()//제출완료 페이지로 넘어가는 것 방지
      alert("List Price는 99,999,999.99이하까지만 입력가능합니다.");
    }
    else if (document.getElementById('price').value <= 0) {
      e.preventDefault()//제출완료 페이지로 넘어가는 것 방지
      alert("List Price는 0 이상만 입력가능합니다.");
    }
    else if (document.getElementById('qtt').value < 0) {
      e.preventDefault()//제출완료 페이지로 넘어가는 것 방지
      alert("Quantity는 0 이상만 입력가능합니다.");
    }
    else if (!Number.isInteger(Number(document.getElementById('qtt').value))) {
      e.preventDefault()//제출완료 페이지로 넘어가는 것 방지
      alert("Quantity는 정수만 입력 가능합니다");
    }
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

<div id="Catalog"><stripes:form onsubmit="checkEmpty(event)"
        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
        focus="">

  <h2>${actionBean.product.name}</h2>

  <table>
    <tr>
      <th>Product ID</th>
      <th>Item ID</th>
      <th>Description</th>
      <th>List Price</th>
      <th>Quantity</th>
    </tr>
    <tr>
      <td>${actionBean.product.productId}</td>
      <td><stripes:text name="item.itemId" id="id" maxlength="10"/></td>
      <td><stripes:text name="item.attribute1" id="att" maxlength="80"/></td>
      <td><stripes:text name="item.listPrice" id="price" maxlength="11" formatType="number"/></td>
      <td><stripes:text name="item.quantity" id="qtt" maxlength="11" formatType="number"/></td>
    </tr>
  </table>
  <stripes:submit name="addItem" value="submit" />
</stripes:form></div>

<%@ include file="../common/IncludeBottom.jsp"%>





