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

<div id="BackLink"><stripes:link
        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">
  Return to Main Menu</stripes:link></div>

<div id="Catalog">
  <table>
    <tr>
      <th>Product ID</th>
      <th>Name</th>
      <th></th>
    </tr>
    <c:forEach var="product" items="${actionBean.productList}">
      <tr>
        <td><stripes:link
                beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
                event="viewAllProduct">
          <stripes:param name="productId" value="${product.productId}" />
          ${product.productId}
        </stripes:link></td>
        <td>${product.name}</td>
        <td><stripes:link class="Button"
                          beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
                          event="editItem">
          <stripes:param name="productId" value="${product.productId}" />
          EDIT ITEM
        </stripes:link></td>
      </tr>
    </c:forEach>
  </table>

</div>






<%@ include file="../common/IncludeBottom.jsp"%>

