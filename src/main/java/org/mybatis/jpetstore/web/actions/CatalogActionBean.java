/*
 *    Copyright 2010-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.jpetstore.web.actions;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.mybatis.jpetstore.domain.Category;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;
import org.mybatis.jpetstore.service.CatalogService;

/**
 * The Class CatalogActionBean.
 *
 * @author Eduardo Macarron
 */
@SessionScope
public class CatalogActionBean extends AbstractActionBean {

  private static final long serialVersionUID = 5849523372175050635L;

  private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
  private static final String VIEW_CATEGORY = "/WEB-INF/jsp/catalog/Category.jsp";
  private static final String VIEW_PRODUCT = "/WEB-INF/jsp/catalog/Product.jsp";
  private static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/Item.jsp";
  private static final String SEARCH_PRODUCTS = "/WEB-INF/jsp/catalog/SearchProducts.jsp";
  private static final String VIEW_PRODUCT_LIST = "/WEB-INF/jsp/admin/AdminDashboard.jsp";
  private static final String VIEW_ADMIN_PRODUCT = "/WEB-INF/jsp/admin/AdminProduct.jsp";
  private static final String ADD_ITEM = "/WEB-INF/jsp/admin/AddItemForm.jsp";
  private static final String UPDATE_ITEM = "/WEB-INF/jsp/admin/UpdateItemForm.jsp";
  private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

  @SpringBean
  private transient CatalogService catalogService;

  private String keyword;

  private String categoryId;
  private Category category;
  private List<Category> categoryList;

  private String productId;

  private String attribute1;
  private Product product;
  private List<Product> productList;

  private String itemId;
  private Item item;
  private List<Item> itemList;

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getAttribute1() {
    return attribute1;
  }

  public void setAttribute1(String attribute1) {
    this.attribute1 = attribute1;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public List<Category> getCategoryList() {
    return categoryList;
  }

  public void setCategoryList(List<Category> categoryList) {
    this.categoryList = categoryList;
  }

  public List<Product> getProductList() {
    return productList;
  }

  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }

  public List<Item> getItemList() {
    return itemList;
  }

  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
  }

  @DefaultHandler
  public ForwardResolution viewMain() {
    return new ForwardResolution(MAIN);
  }

  /**
   * View category.
   *
   * @return the forward resolution
   */
  public ForwardResolution viewCategory() {
    if (categoryId != null) {
      productList = catalogService.getProductListByCategory(categoryId);
      category = catalogService.getCategory(categoryId);
    }
    return new ForwardResolution(VIEW_CATEGORY);
  }

  /**
   * View product.
   *
   * @return the forward resolution
   */
  public ForwardResolution viewProduct() {
    if (productId != null) {
      itemList = catalogService.getItemListByProduct(productId);
      product = catalogService.getProduct(productId);
    }
    return new ForwardResolution(VIEW_PRODUCT);
  }

  /**
   * View item.
   *
   * @return the forward resolution
   */
  public ForwardResolution viewItem() {
    item = catalogService.getItem(itemId);
    product = item.getProduct();
    return new ForwardResolution(VIEW_ITEM);
  }

  /**
   * Search products.
   *
   * @return the forward resolution
   */
  public ForwardResolution searchProducts() {
    if (keyword == null || keyword.length() < 1) {
      setMessage("Please enter a keyword to search for, then press the search button.");
      return new ForwardResolution(ERROR);
    } else {
      productList = catalogService.searchProductList(keyword.toLowerCase());
      return new ForwardResolution(SEARCH_PRODUCTS);
    }
  }

  public ForwardResolution viewAdminProduct() {
    if (!isAdminUser()) {
      setMessage("You are not authorized to access this page.");
      return new ForwardResolution(ERROR);
    }
    if (productId != null) {
      itemList = catalogService.getItemListByProduct(productId);
      product = catalogService.getProduct(productId);
    }
    return new ForwardResolution(VIEW_ADMIN_PRODUCT);
  }

  public ForwardResolution viewAllProduct() {
    if (!isAdminUser()) {
      setMessage("You are not authorized to access this page.");
      return new ForwardResolution(ERROR);
    }
    productList = catalogService.getProductList();
    return new ForwardResolution(VIEW_PRODUCT_LIST);
  }

  public ForwardResolution deleteItem() {
    if (!isAdminUser()) {
      setMessage("You are not authorized to access this page.");
      return new ForwardResolution(ERROR);
    }
    if (itemId != null) {
      catalogService.deleteItem(itemId);
      itemId = null;
      item = new Item();
    }
    itemList = catalogService.getItemListByProduct(productId);
    return new ForwardResolution(VIEW_ADMIN_PRODUCT);
  }

  public ForwardResolution addItemForm() {
    if (!isAdminUser()) {
      setMessage("You are not authorized to access this page.");
      return new ForwardResolution(ERROR);
    }
    item = new Item();
    return new ForwardResolution(ADD_ITEM);
  }

  public Resolution addItem() {
    if (!isAdminUser()) {
      setMessage("You are not authorized to access this page.");
      return new ForwardResolution(ERROR);
    }
    item.setProductId(productId);
    catalogService.addItem(item);
    itemList = catalogService.getItemListByProduct(productId);
    product = catalogService.getProduct(productId);
    return new ForwardResolution(VIEW_ADMIN_PRODUCT);
  }

  public ForwardResolution updateItemForm() {
    if (!isAdminUser()) {
      setMessage("You are not authorized to access this page.");
      return new ForwardResolution(ERROR);
    }
    item = catalogService.getItem(itemId);
    return new ForwardResolution(UPDATE_ITEM);
  }

  public Resolution updateItem() {
    if (!isAdminUser()) {
      setMessage("You are not authorized to access this page.");
      return new ForwardResolution(ERROR);
    }
    item.setItemId(itemId);
    catalogService.updateItem(item);
    itemList = catalogService.getItemListByProduct(productId);
    product = catalogService.getProduct(productId);
    return new ForwardResolution(VIEW_ADMIN_PRODUCT);
  }

  public boolean isAdminUser() {
    HttpSession session = context.getRequest().getSession();
    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
    if(accountBean == null || !accountBean.isAuthenticated()) {
      return false;
    }
    int role = accountBean.getAccount().getRole();
    return role == 1;
  }

  /**
   * Clear.
   */
  public void clear() {
    keyword = null;

    categoryId = null;
    category = null;
    categoryList = null;

    productId = null;
    product = null;
    productList = null;

    itemId = null;
    item = null;
    itemList = null;
  }

}