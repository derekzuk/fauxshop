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
			<form method="post" action="${flowExecutionUrl}">
            <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>        
        
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
                  <li class="active"><a href="payment">Payment</a></li>
                  <li><a href="#">Review Order</a></li>
              </ul>              
              
				<h3>Payment</h3>
						<input type="radio" name="cardType" id="cardType" value="visa">Visa
						<input type="radio" name="cardType" id="cardType" value="mastercard" style="margin-left: 3em">MasterCard
						<br><br>
						<table style="width: 100%">
							<tr>
								<td style="width: 20%"><strong>Credit Card Number: </strong></td>
								<td><input type="text" class="form-control"
									name="cardNumber" id="cardNumber" placeholder="Credit Card Number (no spaces)"></td>
							</tr>
						</table>
						<br>
						<table style="width: 100%">
							<tr>
								<td style="width: 20%"><strong>Security Code: </strong></td>
								<td><input type="text" class="form-control"
									name="cardSecurityCode" id="cardSecurityCode" placeholder="Security Code (no spaces)"></td>
							</tr>
						</table>
						<hr>
																		
<!-- 						<div class="row">
							<div class="col-md-6 col-sm-6">
								<div class="box"> -->
								
                <h3>Billing Address Information</h3>
                <hr />
                
                <div class="form-group">
                  <label class="col-sm-3 control-label">Address</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${addressValue}" placeholder="Address" id=address name=address>
                    	<span class="help-block">Street address, P.O. box, company name, c/o</span>                                  
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Address (Line 2)</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${address2Value}" placeholder="Address (Line 2)" id=address2 name=address2>
                    	<span class="help-block">Apartment, suite, unit, building, floor, etc.</span>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">City</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${cityValue}" placeholder="City" id=city name=city><br>
                  </div>
                </div>
                <br>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Country</label>
                  <div class="col-sm-9">
                    	<select class="form-control" id=country name=country>
                    	<c:choose>
                    		<c:when test="${null != countryValue}">
								<option>${countryValue}
							</c:when>   
							<c:otherwise>                 
                      			<option>United States</option>
                      			<option>Canada</option>
							</c:otherwise>  
						</c:choose>                    
                    	</select>     
                    	<br>               
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">State</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${stateValue}" placeholder="State" id=state name=state><br>
                  </div>
                </div>
                <br>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Zip Code</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${zipValue}" placeholder="#####" id=zip name=zip><br>
                  </div>
                </div>              
                <div class="form-group">
                  <label class="col-sm-3 control-label">Phone</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${phoneNumberValue}" placeholder="(###)###-####" id=phoneNumber name=phoneNumber><br>
                  </div>
                </div>
                
                
                <!-- Shipping Address -->
                <h3>Shipping Address Information</h3>
                
				<div class="checkbox">
					<label>
                        <input type="checkbox" value="shippingAddressCheckbox" name="shippingAddressCheckbox" id="shippingAddressCheckbox" checked/>
                          Shipping address is the same as billing address                	
                          <br>
                          <br>
                	</label>
                </div>
                   
                <div id="shippingInfo" style="display:none;">     
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping Name</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${shipNameValue}" placeholder="Ship Name" id=shipName name=shipName>
                    	<span class="help-block">The name you would like to have packages shipped to.</span>
                  </div>
                </div> 
                <br>                   
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping Address</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${shipAddressValue}" placeholder="Ship Address" id=shipAddress name=shipAddress>
                    	<span class="help-block">Street address, P.O. box, company name, c/o</span>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping Address (Line 2)</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${shipAddress2Value}" placeholder="Ship Address (Line 2)" id=shipAddress2 name=shipAddress2>
                    	<span class="help-block">Apartment, suite, unit, building, floor, etc.</span>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping City</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${shipCityValue}" placeholder="Ship City" id=shipCity name=shipCity><br>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping Country</label>
                  <div class="col-sm-9">
                    	<select class="form-control" id=shipCountry name=shipCountry>
                    	<c:choose>
                    		<c:when test="${null != countryValue}">
								<option>${shipCountryValue}
							</c:when>   
							<c:otherwise>                 
                      			<option>United States</option>
                      			<option>Canada</option>
							</c:otherwise>  
						</c:choose>                    
                    	</select>
                    	<br>                    
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping State</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${shipStateValue}" placeholder="Ship State" id=shipState name=shipState><br>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping Zip Code</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${shipZipValue}" placeholder="#####" id=shipZip name=shipZip><br>
                  </div>                  
                </div>                
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping Phone</label>
                  <div class="col-sm-9">
                    	<input type="text" class="form-control" value="${shipPhoneNumberValue}" placeholder="(###)###-####" id=shipPhone name=shipPhone><br><br>
                  </div>
                </div>          
                </div>  
						<!-- break -->
						
<%-- 			<form method="post" action="${flowExecutionUrl}">
            <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/> --%>												
              <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-head">
                            <h3>Message</h3>
                        </div>                                    
                        <div class="box-content">
                            <!-- <form role="form"> -->
                                <div class="form-group">
                                  <label for="message">If you would like to add a comment about your order, please write it below.</label>
                                  <textarea rows="3" name="message" id="message" class="form-control"></textarea>
                                </div>
                        </div>
                    </div>
                   <!-- <input type="submit" class="btn btn-primary" onclick="return confirm('Are you sure ?')" name="_eventId_proceedToCheckout" value="Confirm Order" /> -->
                   <input type="submit" class="btn btn-primary" name="_eventId_next" value="Review Order" />                    
                </div>
              </div>
              
            </div>
          </div>
        </div>
        <!-- end:content -->
		</form>
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

    <script>
    var checkbox = document.getElementById('shippingAddressCheckbox');
    var shipping_div = document.getElementById('shippingInfo');
    var showHiddenDiv = function(){
       if(!checkbox.checked) {
    	   shipping_div.style['display'] = 'block';
       } else {
    	   shipping_div.style['display'] = 'none';
       } 
    }
    checkbox.onclick = showHiddenDiv;
    showHiddenDiv();
    </script>
    
  </body>
</html>