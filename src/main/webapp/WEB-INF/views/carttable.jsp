<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:choose>
	<c:when
		test="${empty cartService.getCartByUserLogin(pageContext.request.userPrincipal.name) && empty cartService.getCartBySessionId(currentSession)}">
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

				<c:forEach var="cart"
					items="${cartService.getCartByUserLogin(currentUser.getPrincipal().getUsername())}">
					<input type="hidden" name="_flowExecutionKey"
						value="${flowExecutionKey}" />
					<input type="hidden" name="cartId" id="cartId"
						value="${cart.cartId}" />
					<tr>
						<td><img
							src="<c:url value="${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).getImg()}"/>"
							class="img-cart" /></td>
						<td><strong>${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).getInventoryTxt()}</strong>
							<p>
								Size :
								${inventoryDetailService.getInventoryDetailByInventoryDetailId(cart.inventoryDetailId).getSize()}
								<br> Color :
								${inventoryDetailService.getInventoryDetailByInventoryDetailId(cart.inventoryDetailId).getColor()}
							</p></td>
						<td><c:url var="updateAction"
								value="cart/updateQuantity/${cart.cartId}"></c:url> <c:url
								var="deleteAction" value="cartRemove/${cart.cartId}"></c:url> <!-- Both the Edit and Delete buttons navigate away from SWF to a controller class: -->
							<form:form action="${deleteAction}" commandName="Cart">
								<button type="submit" style="float: right" title="Delete"
									class="btn btn-primary">
									<i class="fa fa-trash-o"></i>
								</button>
							</form:form> <form:form action="${updateAction}" commandName="Cart">
								<button type="submit" style="float: right" title="Update"
									class="btn btn-default">
									<i class="fa fa-pencil"></i>
								</button>

								<div style="overflow: hidden; padding-right: .5em;">
									<input class="form-control" type="number" min="1"
										value="${cart.quantity}" name="quantity" id="quantity" />
								</div>
							</form:form></td>
						<td>$${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).getPriceUsd()}</td>
						<td>$${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).getPriceUsd()
							* cart.quantity}</td>
					</tr>
				</c:forEach>

				<!-- We look at the sessionId if the user is not logged in. -->
				<c:forEach var="cartSession"
					items="${cartService.getCartBySessionId(currentSession)}">
					<input type="hidden" name="_flowExecutionKey"
						value="${flowExecutionKey}" />
					<input type="hidden" name="cartId" id="cartId"
						value="${cartSession.cartId}" />
					<tr>
						<td><img
							src="<c:url value="${inventoryService.getInventoryByInventoryDetailId(cartSession.inventoryDetailId).getImg()}"/>"
							class="img-cart" /></td>
						<td><strong>${inventoryService.getInventoryByInventoryDetailId(cartSession.inventoryDetailId).getInventoryTxt()}</strong>
							<p>
								Size :
								${inventoryDetailService.getInventoryDetailByInventoryDetailId(cartSession.inventoryDetailId).getSize()}
								<br> Color :
								${inventoryDetailService.getInventoryDetailByInventoryDetailId(cartSession.inventoryDetailId).getColor()}
							</p></td>
						<td><c:url var="updateAction"
								value="cart/updateQuantity/${cartSession.cartId}"></c:url> <c:url
								var="deleteAction" value="cartRemove/${cartSession.cartId}"></c:url>

							<!-- Both the Edit and Delete buttons navigate away from SWF to a controller class: -->
							<form:form action="${deleteAction}" commandName="Cart">
								<button type="submit" style="float: right" title="Delete"
									class="btn btn-primary">
									<i class="fa fa-trash-o"></i>
								</button>
							</form:form> <form:form action="${updateAction}" commandName="Cart">
								<button type="submit" style="float: right" title="Update"
									class="btn btn-default">
									<i class="fa fa-pencil"></i>
								</button>

								<div style="overflow: hidden; padding-right: .5em;">
									<input class="form-control" type="number" min="1"
										value="${cartSession.quantity}" name="quantity" id="quantity" />
								</div>
							</form:form></td>
						<td>$${inventoryService.getInventoryByInventoryDetailId(cartSession.inventoryDetailId).getPriceUsd()}</td>
						<td>$${inventoryService.getInventoryByInventoryDetailId(cartSession.inventoryDetailId).getPriceUsd()
							* cartSession.quantity}</td>
					</tr>
				</c:forEach>

				<c:choose>
					<c:when
						test="${not empty cartService.getCartByUserLogin(currentUser.getPrincipal().getUsername())}">
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
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4" class="text-right">Total Product</td>
							<td>$${cartService.getCartItemCostBySessionId(currentSession)}</td>
						</tr>
						<tr>
							<td colspan="4" class="text-right">Total Shipping</td>
							<td>$${cartService.getCartShippingCostBySessionId(currentSession)}</td>
						</tr>
						<tr>
							<td colspan="4" class="text-right">Total Tax</td>
							<td>$${cartService.getCartTaxCostBySessionId(currentSession)}</td>
						</tr>
						<tr>
							<td colspan="4" class="text-right"><strong>Total</strong></td>
							<td>$${cartService.getCartTotalBySessionId(currentSession)}</td>
						</tr>
					</c:otherwise>
				</c:choose>


			</tbody>

		</table>

	</c:otherwise>
</c:choose>