<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		
        <!-- begin:content -->
        <div class="col-md-9 col-sm-8 content">
          <div class="row">
            <div class="col-md-12">
                <ol class="breadcrumb">
                  <li><a href="#">Home</a></li>
                  <li><a href="#">Categories</a></li>
                  <li><a href="#">Girl</a></li>
                  <li class="active">Pants</li>
                </ol>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">                          
              <h3>${inventoryCategoryCode.inventoryCatTxt}</h3>
              <hr />		

              <div class="row product-container">			  
	<c:forEach var="item" items="${inventoryService.getInventoryListByInventoryCatCd(inventoryCatCd)}">
<%-- 	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
	<input type="hidden" name="cartId" id="cartId" value="${cart.cartId}"/> --%>

              <!-- begind:item -->
                <div class="col-md-4 col-sm-4 col-xs-6">
                  <div class="thumbnail product-item">
                    <a href="product_detail"><img alt="" src="<c:url value="${item.img}"/>"></a>
                    <div class="caption">
                      <h5>${item.inventoryTxt}</h5>
                      <p>$${item.priceUsd}</p>
                      <p>${item.inStock}</p>
                    </div>
                    <!-- <div class="product-item-badge">New</div> -->
                  </div>
                </div>
              </c:forEach>				
                </div>
              <!-- end:item -->
              
              <!-- begin:pagination -->
              <div class="row">
                <div class="col-md-12">
                  <ul class="pagination">
                  <li class="disabled"><a href="#">&lsaquo;</a></li>
                  <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">&rsaquo;</a></li>
                </ul>
                </div>
              </div>
              <!-- end:pagination -->
            </div>
          </div>
        </div>
        <!-- end:content -->
					