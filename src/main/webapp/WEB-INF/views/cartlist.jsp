<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${empty cart && empty cartSession}">
	No items in cart.
	</c:when>
	<c:otherwise>	
		<c:forEach var="cartItem"
			items="${cart}">
			<form method="post" action="${flowExecutionUrl}">
			<ul class="cart list-unstyled">
				<li>
					<div class="row">
						<div class="col-sm-7 col-xs-7">
							<span>[${cartItem.quantity}]</span>
							<a href="product_detail/${inventoryDetailService.getInventoryDetailByInventoryDetailId(cartItem.inventoryDetailId).getInventoryId()}?color=${inventoryDetailService.getInventoryDetailByInventoryDetailId(cartItem.inventoryDetailId).getColor()}">${inventoryService.getInventoryByInventoryDetailId(cartItem.inventoryDetailId).getInventoryTxt()}</a>
						</div>
						<div class="col-sm-5 col-xs-5 text-right">
							<strong>$${inventoryService.getInventoryByInventoryDetailId(cartItem.inventoryDetailId).getPriceUsd()}</strong>
							<a href="<c:url value='cartRemove/${cartItem.cartId}'/>" > <i class="fa fa-trash-o"></i> </a>
						</div>
					</div>
				</li>
			</ul>
			</form>
		</c:forEach>
		<c:forEach var="cartSession"
			items="${cartSession}">
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
		<c:when test="${not empty cart}">
		
		<ul class="list-unstyled total-price">
			<li>
				<div class="row">
					<div class="col-sm-8 col-xs-8">Shipping</div>
					<div class="col-sm-4 col-xs-4 text-right">$${cartShippingCost}</div>
				</div>
			</li>		
			<li>
				<div class="row">
					<div class="col-sm-8 col-xs-8">Tax</div>
					<div class="col-sm-4 col-xs-4 text-right">$${cartTaxCost}</div>
				</div>
			</li>
			<li>
				<div class="row">
					<div class="col-sm-8 col-xs-8">Total</div>
					<div class="col-sm-4 col-xs-4 text-right">$${cartTotalCost}</div>
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
					<div class="col-sm-4 col-xs-4 text-right">$${cartShippingCost}</div>
				</div>
			</li>		
			<li>
				<div class="row">
					<div class="col-sm-8 col-xs-8">Tax</div>
					<div class="col-sm-4 col-xs-4 text-right">$${cartTaxCost}</div>
				</div>
			</li>
			<li>
				<div class="row">
					<div class="col-sm-8 col-xs-8">Total</div>
					<div class="col-sm-4 col-xs-4 text-right">$${cartTotalCost}</div>
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