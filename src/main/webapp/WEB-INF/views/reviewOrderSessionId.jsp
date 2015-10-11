<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <meta name="description" content="responsive clothing store template">
    <meta name="author" content="afriq yasin ramadhan">
    <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.png" />">

    <title>Faux Shop</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/responsive.css" />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="<c:url value="/resources/js/html5shiv.js" />"></script>
      <script src="<c:url value="/resources/js/respond.min.js" />"></script>
    <![endif]-->
  </head>

  <body>

	<!-- begin:navbar -->
	<!-- We pull the table from another view: -->
	<jsp:include page="navbar.jsp" />

    <!-- begin:content -->
    <div class="container">
      <!-- begin:logo -->
      <div class="row">
        <div class="col-md-6 col-sm-6 col-xs-6">
          <div class="logo">
            <h1><a href="index">Faux<span>shop</span> </a></h1>
            <p>Durable, Fashionable, Animal Friendly</p>
          </div>
        </div>
        <div class="col-md-6 col-sm-6 col-xs-6">
          <div class="account">
            <ul>
              <li id="your-account">
                <div class="hidden-xs">
                  <h4><a href="account">Your Account</a></h4>										
                  	<p><a href="login">Log in</a></p>
                </div>
                <div class="visible-xs">
                  <a href="login" class="btn btn-primary"><i class="fa fa-user"></i></a>
                </div>
              </li>
              <li>
                <div class="hidden-xs">
                  <h4><a href="cart">Cart</a></h4>
                  <p><strong>${cartService.getCartBySessionId(currentSession).size()} Product(s)</strong></p>
                </div>
                <div class="visible-xs">
                  <a href="cart" class="btn btn-primary"><span class="cart-item">3</span> <i class="fa fa-shopping-cart"></i></a>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div> 
      <!-- end:logo -->

      <!-- begin:nav-menus -->
      <div class="row">
        <div class="col-md-12">
          <div class="nav-menus">
            <ul class="nav nav-pills">
              <li class="active"><a href="index">Home</a></li>
              <li><a href="#">Accessories</a></li>
              <li class="dropdown">
                <a href="#" data-toggle="dropdown" class="dropdown-toggle">Boy <b class="caret"></b></a>
                <ul class="dropdown-menu" id="menu1">
                  <li>
                    <a href="#">Shirts <b class="caret"></b></a>
                    <ul class="dropdown-menu sub-menu">
                      <li><a href="#">Shirts</a></li>
                      <li><a href="#">T-shirts</a></li>
                      <li><a href="#">Polo Shirts</a></li>
                      <li><a href="#">Tanktop</a></li>
                    </ul>
                  </li>
                  <li><a href="#">Jacket</a></li>
                  <li><a href="categories">Pants</a></li>
                  <li><a href="#">Boxer</a></li>
                  <li class="divider"></li>
                  <li><a href="#">SweatShirts</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" data-toggle="dropdown" class="dropdown-toggle">Girl <b class="caret"></b></a>
                <ul class="dropdown-menu" id="menu1">
                  <li><a href="#">Shirts</a></li>
                  <li><a href="#">Pants</a></li>
                  <li><a href="#">Skirts</a></li>
                </ul>
              </li>
              <li><a href="#">Edition</a></li>
              <li><a href="#">Authorized Dealer</a></li>
              <li><a href="about">About</a></li>
              <li><a href="contact">Contact</a></li>
            </ul>
          </div>
        </div>
      </div>
      <!-- end:nav-menus -->

      <!-- begin:article -->
      <div class="row">
        <!-- begin:sidebar -->
        <div class="col-md-3 col-sm-4 sidebar">
          <div class="row">
            <div class="col-md-12">
              <div class="widget">
                <div class="widget-title">
                  <h3>Cart</h3>
                </div>
                
              		<!-- We pull the table from another view: -->
              		<jsp:include page="cartlist.jsp"/>

              	</div>
              	<!-- break -->
              <div class="widget">
                <div class="widget-title">
                  <h3>Category</h3>
                </div>
                <ul class="nav nav-pills nav-stacked">
                    <li class="active"><a href="#">Acessories</a></li>
                    <li><a href="#">Girl</a></li>
                    <li><a href="#">Boy</a></li>
                    <li><a href="#">Edition</a></li>
                </ul>
              </div>
              <!-- break -->
              <div class="widget">
                <div class="widget-title">
                  <h3>Payment Confirmation</h3>
                </div>
                <p>Already make a payment ? please confirm your payment by filling <a href="confirm">this form</a></p>
              </div>

            </div>
          </div>
        </div>
        <!-- end:sidebar -->
	
        <!-- begin:content -->           
        <div class="col-md-9 col-sm-8 content">
          <div class="row">
            <div class="col-md-12">
                <ol class="breadcrumb">
                  <li><a href="#">Home</a></li>
                  <li class="active">Payment</li>
                </ol>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <ul class="nav nav-tabs">
                  <li><a href="cart">Cart</a></li>
                  <li><a href="login">Login</a></li>
                  <li><a href="account">Account</a></li>
                  <li><a href="shipping">Shipping</a></li>
                  <li><a href="payment">Payment</a></li>
                  <li class="active"><a href="#">Review Order</a></li>
              </ul>              
              
				<h3>Review Order</h3>
				
              		<!-- We pull the table from another view: -->
              		<jsp:include page="carttable.jsp"/>				
							
						<hr>				
																		
						<div class="row">
							<div class="col-md-12 col-sm-6">
								<div class="box">
									<div class="box-head">
										<h3>Delivery Address
										<button type="submit" style="float: right" title="Edit"
											onclick="history.back()" class="btn btn-default">
											<i class="fa fa-pencil"></i>
										</button>										
										</h3>
									</div>								
									<div class="box-content">
										<address>
											<strong>${account.getShipName()}</strong><br>
											${account.getShipAddress()}<br>
											${account.getShipAddress2()}<br>
											${account.getShipCity()}, ${account.getShipState()} ${account.getShipZip()}<br> 
											${account.getShipCountry()}<br>
											<abbr title="Phone">Phone :</abbr>
											${account.getShipPhone()}
										</address>
									</div>
								</div>
							</div>
						</div>
						<!-- break -->
			
	<form action="${flowExecutionUrl}" method="POST">
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>								
	<input type="hidden" name="_eventId_proceedToCheckout"/>			
			
              <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-head">
                            <h3>Message</h3>
                        </div>                                    
                        <div class="box-content">
                            <!-- <form role="form"> -->
                                <div class="form-group">
                                  <textarea rows="3" name="message" id="message" class="form-control" placeholder="Please use this textbox if you have any comments or special requests to go with your order."></textarea>
                            </div>
                        </div>
                    </div>                    
								
							<!-- stripe checkout button -->
									<script src="https://checkout.stripe.com/checkout.js"
										class="stripe-button"
										data-key="pk_test_kZTn53XkpSdxD32MAPgFkMzB" data-amount="${cartService.getCartTotalBySessionId(currentSession) * 100}"
										data-name="FauxShop" data-description="${cartService.getCartQuantityBySessionId(currentSession)} items"
										data-image="/128x128.png" data-locale="auto">										
									</script>						              
                                
                </div>
              </div>
              </form>
            </div>
          </div>
        </div>
        <!-- end:content -->
      </div>
      <!-- end:article -->
      
      <!-- begin:footer -->
      <div class="row">
        <div class="col-md-12 footer">
          <div class="row">
            <div class="col-md-3 col-sm-6">
              <div class="widget">
                <h3><span>Contact Info</span></h3>
                <address>
                  No. 22, Bantul, Yogyakarta, Indonesia<br>
                  Call Us : (0274) 4411005<br>
                  Email : avriqq@gmail.com<br>
                </address>
              </div>
            </div>

            <div class="col-md-3 col-sm-6">
              <div class="widget">
                <h3><span>Customer Support</span></h3>
                <ul class="list-unstyled list-star">
                  <li><a href="#">FAQ</a></li>
                  <li><a href="#">Payment Option</a></li>
                  <li><a href="#">Booking Tips</a></li>
                  <li><a href="#">Information</a></li>
                </ul>
              </div>
            </div>

            <div class="col-md-3 col-sm-6">
              <div class="widget">
                <h3><span>Discover our store</span></h3>
                <ul class="list-unstyled list-star">
                    <li><a href="#">California</a></li>
                    <li><a href="#">Bali</a></li>
                    <li><a href="#">Singapore</a></li>
                    <li><a href="#">Canada</a></li>
                </ul>
              </div>
            </div>

            <div class="col-md-3 col-sm-6">
              <div class="widget">
                <h3><span>Get Our Newsletter</span></h3>
                <p>Subscribe to our newsletter and get exclusive deals straight to your inbox!</p>
                <form>
                  <input type="email" class="form-control" name="email" placeholder="Your Email : "><br>
                  <input type="submit" class="btn btn-warning" value="Subscribe">
                </form>
              </div>
            </div>

          </div>
        </div>
      </div>
      <!-- end:footer -->

      <!-- begin:copyright -->
      <div class="row">
        <div class="col-md-12 copyright">
          <div class="row">
            <div class="col-md-6 col-sm-6 copyright-left">
              <p>Copyright &copy; Faux Shop 2012-2014. All right reserved.</p>
            </div>
            <div class="col-md-6 col-sm-6 copyright-right">
              <ul class="list-unstyled list-social">
                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                <li><a href="#"><i class="fa fa-facebook-square"></i></a></li>
                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                <li><a href="#"><i class="fa fa-instagram"></i></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <!-- end:copyright -->

    </div>
    <!-- end:content -->


    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/masonry.pkgd.min.js" />"></script>
    <script src="<c:url value="/resources/js/imagesloaded.pkgd.min.js" />"></script>
    <script src="<c:url value="/resources/js/script.js" />"></script>
    
	<!-- Stripe: -->
    <script type="text/javascript" src="https://js.stripe.com/v2/"></script>
    
    <script>
    Stripe.setPublishableKey('pk_test_kZTn53XkpSdxD32MAPgFkMzB');
    
    Stripe.card.createToken({
    	  number: $('.card-number').val(),
    	  cvc: $('.card-cvc').val(),
    	  exp_month: $('.cc-exp').val(),
    	  exp_year: $('.cc-csc').val()
    	}, stripeResponseHandler);
    
    function stripeResponseHandler(status, response) {
    	  var $form = $('#checkoutView');

    	  if (response.error) {
    	    // Show the errors on the form
    	    $form.find('.payment-errors').text(response.error.message);
    	    $form.find('button').prop('disabled', false);
    	  } else {
    	    // response contains id and card, which contains additional card details
    	    var token = response.id;
    	    // Insert the token into the form so it gets submitted to the server
    	    $form.append($('<input type="hidden" name="stripeToken" />').val(token));
    	    // and submit
    	    $form.get(0).submit();
    	  }
    	}    
    
    </script>

  </body>
</html>