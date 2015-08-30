<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${empty cartService.getCartByUserLogin(pageContext.request.userPrincipal.name)}">
		<p>No items in cart.</p>
	</c:when>
	<c:otherwise>
		
              <table class="table table-bordered table-striped">              
               <thead>
                <tr>
                  <th>Product</th>
                  <th>Description</th>
                  <th>Qty</th>
                  <th>Price Per Item</th>
                  <th>Total</th>
                </tr>
               </thead>
               <tbody>
			<c:forEach var="cart" items="${cartService.getCartByUserLogin(currentUser.getPrincipal().getUsername())}">
				<tr>
                  <td><img src="<c:url value="${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetail).img}"/>" class="img-cart" /></td>                  
                  <td><strong>${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).inventoryTxt}</strong><p>Size : ${inventoryDetailService.getInventoryDetailByInventoryDetailId(cart.inventoryDetailId).size}</p></td>
                  <td>                                    
                    <form class="form-inline" method="post" action="${flowExecutionUrl}">
                      <input class="form-control" type="text" value="${cart.quantity}" />
                      <button rel="tooltip" title="Update" class="btn btn-default"><i class="fa fa-pencil"></i></button>
                      <a href="${flowExecutionUrl}&_eventId=removeFromCart&cartId=${cart.cartId}" type="submit" rel="tooltip" title="Delete" class="btn btn-primary"><i class="fa fa-trash-o"></i></a>
                    </form>
                  </td>
                  <td>$${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).priceUsd}</td>
                  <td>$${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).priceUsd * cart.quantity}</td>
                </tr>
			</c:forEach>               
                <tr>
                  <td colspan="6">&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="text-right">Total Product</td>
                  <td>$${cartService.getCartItemCostByUserLogin(currentUser.getPrincipal().getUsername())}</td>
                </tr>
                <tr>
                  <td colspan="4" class="text-right">Total Shipping</td>
                  <td>$${cartService.getCartShippingCostByUserLogin(currentUser.getPrincipal().getUsername())}</td>
                </tr>
                <tr>
                  <td colspan="4" class="text-right">Total Tax</td>
                  <td>$${cartService.getCartTaxCostByUserLogin(currentUser.getPrincipal().getUsername())}</td>
                </tr>                
                <tr>
                  <td colspan="4" class="text-right"><strong>Total</strong></td>
                  <td>$${cartService.getCartTotalByUserLogin(currentUser.getPrincipal().getUsername())}</td>
                </tr>
               </tbody>       
              </table>		
		
	</c:otherwise>
</c:choose>