<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${empty cartService.getCartByUserLogin(currentUser.getPrincipal().getUsername())}">
		<p>Cart contains no items.</p>
	</c:when>
	<c:otherwise>
		
              <table class="table table-bordered table-striped">              
               <thead>
                <tr>
                  <th>Product</th>
                  <th>Description</th>
                  <th>Qty</th>
                  <th>Price</th>
                  <th>Total</th>
                </tr>
               </thead>
               <tbody>
			<c:forEach var="item" items="${cartService.getCartByUserLogin(currentUser.getPrincipal().getUsername())}">
				<tr>
                  <td><img src="<c:url value="/resources/img/product1.jpg"/>" class="img-cart" /></td>                  
                  <td><strong>${inventoryService.getInventoryById(item.inventoryId).inventoryTxt}</strong><p>Size : 26</p></td>
                  <td>
                    <form class="form-inline">
                      <input class="form-control" type="text" value="1" />
                      <button rel="tooltip" title="Update" class="btn btn-default"><i class="fa fa-pencil"></i></button>
                      <a href="#" class="btn btn-primary" rel="tooltip" title="Delete"><i class="fa fa-trash-o"></i></a>
                    </form>
                  </td>
                  <td>${inventoryService.getInventoryById(item.inventoryId).priceUsd}</td>
                  <td>${inventoryService.getInventoryById(item.inventoryId).priceUsd}</td>
                </tr>
			</c:forEach>               
                <tr>
                  <td colspan="6">&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="text-right">Total Product</td>
                  <td>$86.00</td>
                </tr>
                <tr>
                  <td colspan="4" class="text-right">Total Shipping</td>
                  <td>$2.00</td>
                </tr>
                <tr>
                  <td colspan="4" class="text-right"><strong>Total</strong></td>
                  <td>$88.00</td>
                </tr>
               </tbody>       
              </table>		
		
	</c:otherwise>
</c:choose>
