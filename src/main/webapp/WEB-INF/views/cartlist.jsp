<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${empty cartService.getCartByUserLogin(pageContext.request.userPrincipal.name) && empty cartService.getCartBySessionId(currentSession)}">
	No items in cart.
	</c:when>
	<c:otherwise>	
		<c:forEach var="cart"
			items="${cartService.getCartByUserLogin(pageContext.request.userPrincipal.name)}">
			<form method="post" action="${flowExecutionUrl}">
			<ul class="cart list-unstyled">
				<li>
					<div class="row">
						<div class="col-sm-7 col-xs-7">
							<span>[${cart.quantity}]</span>
							<a href="product_detail/${inventoryDetailService.getInventoryDetailByInventoryDetailId(cart.inventoryDetailId).getInventoryId()}?color=${inventoryDetailService.getInventoryDetailByInventoryDetailId(cart.inventoryDetailId).getColor()}">${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).getInventoryTxt()}</a>
						</div>
						<div class="col-sm-5 col-xs-5 text-right">
							<strong>$${inventoryService.getInventoryByInventoryDetailId(cart.inventoryDetailId).getPriceUsd()}</strong>
							<a href="<c:url value='cartRemove/${cart.cartId}'/>" > <i class="fa fa-trash-o"></i> </a>
						</div>
					</div>
				</li>
			</ul>
			</form>
		</c:forEach>
		<c:forEach var="cartSession"
			items="${cartService.getCartBySessionId(currentSession)}">
			<form method="post" action="${flowExecutionUrl}">
			<ul class="cart list-unstyled">
				<li>
					<div class="row">
						<div class="col-sm-7 col-xs-7">
							<span>[${cartSession.quantity}]</span>
							<a href="product_detail/${inventoryDetailService.getInventoryDetailByInventoryDetailId(cartSession.inventoryDetailId).getInventoryId()}?color=${inventoryDetailService.getInventoryDetailByInventoryDetailId(cartSession.inventoryDetailId).getColor()}">${inventoryService.getInventoryByInventoryDetailId(cartSession.inventoryDetailId).getInventoryTxt()}</a>
						</div>
						<div class="col-sm-5 col-xs-5 text-right">
							<strong>$${inventoryService.getInventoryByInventoryDetailId(cartSession.inventoryDetailId).getPriceUsd()}</strong>
							<a href="<c:url value='cartRemove/${cartSession.cartId}'/>" > <i class="fa fa-trash-o"></i> </a>
						</div>
					</div>
				</li>
			</ul>
			</form>
		</c:forEach>		
		
		<c:choose>
		<c:when test="${not empty cartService.getCartByUserLogin(pageContext.request.userPrincipal.name)}">
		
		<ul class="list-unstyled total-price">
			<li>
				<div class="row">
					<div class="col-sm-8 col-xs-8">Shipping</div>
					<div class="col-sm-4 col-xs-4 text-right">$${cartService.getCartShippingCostByUserLogin(pageContext.request.userPrincipal.name)}</div>
				</div>
			</li>		
			<li>
				<div class="row">
					<div class="col-sm-8 col-xs-8">Tax</div>
					<div class="col-sm-4 col-xs-4 text-right">$${cartService.getCartTaxCostByUserLogin(pageContext.request.userPrincipal.name)}</div>
				</div>
			</li>
			<li>
				<div class="row">
					<div class="col-sm-8 col-xs-8">Total</div>
					<div class="col-sm-4 col-xs-4 text-right">$${cartService.getCartTotalByUserLogin(pageContext.request.userPrincipal.name)}</div>
				</div>
			</li>
			<li>
				<div class="row">
					<div class="col-sm-6 col-xs-6">
						<a class="btn btn-default" href="cart">Cart</a>
					</div>
					<div class="col-sm-6 col-xs-6 text-right">
						<a class="btn btn-primary" href="login">Checkout</a>
					</div>
				</div>
			</li>
		</ul>		
		</c:when>
		<c:otherwise>
		
		<ul class="list-unstyled total-price">
			<li>
				<div class="row">
					<div class="col-sm-8 col-xs-8">Shipping</div>
					<div class="col-sm-4 col-xs-4 text-right">$${cartService.getCartShippingCostBySessionId(currentSession)}</div>
				</div>
			</li>		
			<li>
				<div class="row">
					<div class="col-sm-8 col-xs-8">Tax</div>
					<div class="col-sm-4 col-xs-4 text-right">$${cartService.getCartTaxCostBySessionId(currentSession)}</div>
				</div>
			</li>
			<li>
				<div class="row">
					<div class="col-sm-8 col-xs-8">Total</div>
					<div class="col-sm-4 col-xs-4 text-right">$${cartService.getCartTotalBySessionId(currentSession)}</div>
				</div>
			</li>
			<li>
				<div class="row">
					<div class="col-sm-6 col-xs-6">
						<a class="btn btn-default" href="cart">Cart</a>
					</div>
					<div class="col-sm-6 col-xs-6 text-right">
						<a class="btn btn-primary" href="login">Checkout</a>
					</div>
				</div>
			</li>
		</ul>		
		</c:otherwise>
		</c:choose>

	</c:otherwise>
</c:choose>