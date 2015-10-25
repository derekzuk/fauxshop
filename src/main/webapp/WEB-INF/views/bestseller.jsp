<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

      <div class="row">
        <div class="col-md-12">
          <div class="page-header">
            <h2>Best Seller <small>Most sold product in this month</small></h2>
          </div>
        </div>
      </div>          
      
      <div class="row product-container">      
		<c:forEach var="inventory"
			items="${inventoryService.getBestSellerInventoryList()}">
        <div class="col-md-3 col-sm-3 col-xs-6">
          <div class="thumbnail product-item">
            <a href="product_detail/${inventory.inventoryId}?color=${inventoryDetailService.getInventoryDetailByInventoryId(inventory.getInventoryId()).get(0).getColor()}"><img alt="" src="<c:url value="${inventory.img}"/>"></a>
            <div class="caption">
              <h5>${inventory.inventoryTxt}</h5>
              <p>$${inventory.priceUsd}</p>
              <c:choose> 
	            <c:when test="${inventory.inStock}">
				  <p>Available</p>
				</c:when>
              	<c:otherwise>
            	  <p>Out of Stock</p>
              	</c:otherwise>
              </c:choose>                            
            </div>
          </div>
        </div>
		</c:forEach>
		</div>