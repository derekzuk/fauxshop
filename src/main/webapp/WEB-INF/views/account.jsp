<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">                  	
					<p>Welcome, ${pageContext.request.userPrincipal.name}</p>
						<c:url var="logoutAction" value="/j_spring_security_logout"></c:url>	
						<form action="${logoutAction}" method="post">
						<!-- This obviously needs to be fixed: -->
						<a href="/j_spring_security_logout">Log Out</a>
						</form>
					</c:when>
					<c:otherwise>										
                  	<p><a href="login">Log in</a></p>
                  </c:otherwise>
                  </c:choose>  
                </div>
                <div class="visible-xs">
                  <a href="login" class="btn btn-primary"><i class="fa fa-user"></i></a>
                </div>
              </li>
              <li>
                <div class="hidden-xs">
                  <h4><a href="cart">Cart</a></h4>
                  <p><strong>${cartService.getCartByUserLogin(pageContext.request.userPrincipal.name).size()} Product(s)</strong></p>
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
              <li><a href="#">Acessories</a></li>
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
                  <li class="active">Account</li>
                </ol>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <ul class="nav nav-tabs">
                  <li><a href="cart">Cart</a></li>
                  <li><a href="login">Login</a></li>
                  <li class="active"><a href="account">Account</a></li>
                  <li><a href="#">Shipping</a></li>
                  <li><a href="#">Payment</a></li>
                  <li><a href="#">Review Order</a></li>
              </ul>
              
				<!-- Display error messages -->
						<c:forEach var="message"
							items="${flowRequestContext.messageContext.getMessagesBySource('account')}">
							<c:if test="${message.severity eq 'ERROR'}">
								<br>
								<span class="info"><font color="red">${message.text}</font></span>
								<br>
							</c:if>
						</c:forEach>              
              
              <h3>Personal Information</h3>
              <hr />
              <form class="form-horizontal" method="post" action="${flowExecutionUrl}">
              <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
              <div class="form-group">
                  <label class="col-sm-3 control-label">User Login</label>
                  <div class="col-sm-9">                  	                  	                  
                  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).userLogin}" id=userLogin name=userLogin disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${userLoginValue}" placeholder="User Login" id=userLogin name=userLogin>
                  	</c:otherwise>    
                  </c:choose>                  	                 
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">First Name</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).firstName}" id=firstName name=firstName disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${firstNameValue}" placeholder="First Name" id=firstName name=firstName>
                  	</c:otherwise>    
                  </c:choose>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Last Name</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).lastName}" id=lastName name=lastName disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${lastNameValue}" placeholder="Last Name" id=lastName name=lastName>
                  	</c:otherwise>    
                  </c:choose>                  
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Email</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="email" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).email}" class="form-control" id=email name=email disabled>
                  	</c:when>  
                  	<c:otherwise>
                    <input type="email" value="${emailValue}" class="form-control" placeholder="Email" id=email name=email>
                  	</c:otherwise>    
                  </c:choose>                  
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Password</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="password" class="form-control" value="password hidden" id=password name=password disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="password" class="form-control" value="${passwordValue}" placeholder="" id=password name=password>
                  	</c:otherwise>    
                  </c:choose>                                    
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Date of birth</label>
                  <div class="col-sm-9">
                    <div class="form-inline">
                      <select class="form-control">
                        <option>-</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                      </select>&nbsp;
                      <select class="form-control">
                        <option>January</option>
                        <option>February</option>
                        <option>March</option>
                        <option>April</option>
                      </select>&nbsp;
                      <select class="form-control">
                        <option>2012</option>
                        <option>1991</option>
                        <option>1990</option>
                        <option>1989</option>
                      </select>
                    </div>
                  </div>
                </div>
                
                <h3>Billing Address Information</h3>
                <hr />
                
                <div class="form-group">
                  <label class="col-sm-3 control-label">Address</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                    	<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).address}" id=address name=address disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${addressValue}" placeholder="Address" id=address name=address>
                    	<span class="help-block">Street address, P.O. box, company name, c/o</span>
                  	</c:otherwise>    
                  </c:choose>                                    
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Address (Line 2)</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                    	<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).address2}" id=address2 name=address2 disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${address2Value}" placeholder="Address (Line 2)" id=address2 name=address2>
                    	<span class="help-block">Apartment, suite, unit, building, floor, etc.</span>
                  	</c:otherwise>    
                  </c:choose>                                                      
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">City</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).city}" id=city name=city disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${cityValue}" placeholder="City" id=city name=city>
                  	</c:otherwise>    
                  </c:choose>                                          
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Country</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).country}" id=country name=country disabled>
                  	</c:when>  
                  	<c:otherwise>
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
                  	</c:otherwise>    
                  </c:choose>                                                                              
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">State</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).state}" id=state name=state disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${stateValue}" placeholder="State" id=state name=state>
                  	</c:otherwise>    
                  </c:choose>                                                            
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Zip Code</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).zip}" id=zip name=zip disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${zipValue}" placeholder="#####" id=zip name=zip>
                  	</c:otherwise>    
                  </c:choose>                  
                  </div>
                </div>                
                <div class="form-group">
                  <label class="col-sm-3 control-label">Phone</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).phoneNumber}" id=phoneNumber name=phoneNumber disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${phoneNumberValue}" placeholder="(###)###-####" id=phoneNumber name=phoneNumber>
                  	</c:otherwise>    
                  </c:choose>                                    
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
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                    	<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).shipName}" id=shipName name=shipName disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${firstNameValue}" placeholder="Ship Name" id=shipName name=shipName>
                    	<span class="help-block">The name you would like to have packages shipped to.</span>
                  	</c:otherwise>    
                  </c:choose>                                    
                  </div>
                </div>                    
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping Address</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                    	<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).shipAddress}" id=shipAddress name=shipAddress disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${addressValue}" placeholder="Ship Address" id=shipAddress name=shipAddress>
                    	<span class="help-block">Street address, P.O. box, company name, c/o</span>
                  	</c:otherwise>    
                  </c:choose>                                    
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping Address (Line 2)</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                    	<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).address2}" id=shipAddress2 name=shipAddress2 disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${address2Value}" placeholder="Ship Address (Line 2)" id=shipAddress2 name=shipAddress2>
                    	<span class="help-block">Apartment, suite, unit, building, floor, etc.</span>
                  	</c:otherwise>    
                  </c:choose>                                                      
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping City</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).shipCity}" id=shipCity name=shipCity disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${cityValue}" placeholder="Ship City" id=shipCity name=shipCity>
                  	</c:otherwise>    
                  </c:choose>                                          
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping Country</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).shipCountry}" id=shipCountry name=shipCountry disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<select class="form-control" id=shipCountry name=shipCountry>
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
                  	</c:otherwise>    
                  </c:choose>                                                                              
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping State</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).shipState}" id=shipState name=shipState disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${stateValue}" placeholder="Ship State" id=shipState name=shipState>
                  	</c:otherwise>    
                  </c:choose>                                                            
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping Zip Code</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).zip}" id=zip name=zip disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${zipValue}" placeholder="#####" id=shipZip name=shipZip>
                  	</c:otherwise>    
                  </c:choose>                  
                  </div>
                </div>                
                <div class="form-group">
                  <label class="col-sm-3 control-label">Shipping Phone</label>
                  <div class="col-sm-9">
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="text" class="form-control" value="${accountService.getAccountByName(pageContext.request.userPrincipal.name).shipPhone}" id=shipPhone name=shipPhone disabled>
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="text" class="form-control" value="${phoneNumberValue}" placeholder="(###)###-####" id=shipPhone name=shipPhone>
                  	</c:otherwise>    
                  </c:choose>                                    
                  </div>
                </div>          
                </div>        
                    
                <div class="form-group">
                  <div class="col-sm-offset-3 col-sm-9">					
				  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">
                  		<input type="submit" class="btn btn-primary" name="_eventId_ok" value="OK" />
                  		<input type="submit" class="btn btn-default" name="_eventId_editAccount" value="Edit" />
                  	</c:when>  
                  	<c:otherwise>
                    	<input type="submit" class="btn btn-primary" name="_eventId_login2register" value="Register" />
                    	<input type="submit" class="btn btn-default" name="_eventId_cancel" value="Cancel" />
                  	</c:otherwise>    
                  </c:choose>  
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
