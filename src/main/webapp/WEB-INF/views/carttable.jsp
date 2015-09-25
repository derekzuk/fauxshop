<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
			<input type="hidden" name="cartId" id="cartId" value="${cart.cartId}"/>					
				<tr>
                  <td><img src="<c:url value="${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).getImg()}"/>" class="img-cart" /></td>                  
                  <td><strong>${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).getInventoryTxt()}</strong>
                  <p>Size : ${inventoryDetailService.getInventoryDetailByInventoryDetailId(cart.inventoryDetailId).getSize()} <br>
                  Color : ${inventoryDetailService.getInventoryDetailByInventoryDetailId(cart.inventoryDetailId).getColor()}</p></td>
                  <td>                                    
                      <c:url var="updateAction" value="cart/updateQuantity/${cart.cartId}" ></c:url>
                      <c:url var="deleteAction" value="cartRemove/${cart.cartId}" ></c:url>					  

                      <%-- <a href="${flowExecutionUrl}&_eventId=removeFromCart&cartId=${cart.cartId}" type="submit" style="float: right" rel="tooltip" title="Delete" class="btn btn-primary"><i class="fa fa-trash-o"></i></a> --%>
                      <!-- Both the Edit and Delete buttons navigate away from SWF to a controller class: -->
                      <form:form action="${deleteAction}" commandName="Cart">
                      <button type="submit" style="float: right" title="Delete" class="btn btn-primary"><i class="fa fa-trash-o"></i></button>
                      </form:form>
                      
                      <form:form action="${updateAction}" commandName="Cart">
                      <button type="submit" style="float: right" title="Update" class="btn btn-default"><i class="fa fa-pencil"></i></button>                      
                      				
						<div style="overflow: hidden; padding-right: .5em;">                      					  
                      	<input class="form-control" type="number" value="${cart.quantity}" name="quantity" id="quantity" />
						</div>
					  </form:form>
					  					
                  </td>
                  <td>$${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).getPriceUsd()}</td>
                  <td>$${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).getPriceUsd() * cart.quantity}</td>
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