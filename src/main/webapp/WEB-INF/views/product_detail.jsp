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
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#Faux Shop-navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>

        <div class="collapse navbar-collapse" id="Faux Shop-navbar">
          <ul class="nav navbar-nav navbar-right">
            <li><a class="disabled">CURRENCY</a></li>
            <li class="active"><a href="#">USD</a></li>
            <li><a href="#">EUR</a></li>
            <li><a href="#">IDR</a></li>
            <li class="divider-vertical"></li>
            <li class="dropdown">
              <a data-toggle="dropdown" class="dropdown-toggle" href="#">LANGUAGE <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">EN</a></li>
                <li><a href="#">ID</a></li>
                <li><a href="#">FR</a></li>
                <li class="divider"></li>
                <li><a href="#">IT</a></li>
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div>
    </nav>
    <!-- end:navbar -->

    <!-- begin:content -->
    <div class="container">
      <!-- begin:logo -->
      <div class="row">
        <div class="col-md-6 col-sm-6 col-xs-6">
          <div class="logo">
            <h1><a href="../index">Faux<span>shop</span> </a></h1>
            <p>Durable, Fashionable, Animal Friendly</p>
          </div>
        </div>
        <div class="col-md-6 col-sm-6 col-xs-6">
          <div class="account">
            <ul>
              <li id="your-account">
                <div class="hidden-xs">
                  <h4><a href="../account">Your Account</a></h4>
                  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">                  	
					<p>Welcome, ${pageContext.request.userPrincipal.name}</p>
					</c:when>
					<c:otherwise>										
                  	<p><a href="../login">Log in</a></p>
                  </c:otherwise>
                  </c:choose>  
                </div>
                <div class="visible-xs">
                  <a href="../login" class="btn btn-primary"><i class="fa fa-user"></i></a>
                </div>
              </li>
              <li>
                <div class="hidden-xs">
                  <h4><a href="../cart">Cart</a></h4>
                  <p><strong>${cartService.getCartByUserLogin(pageContext.request.userPrincipal.name).size()} Product(s)</strong></p>
                </div>
                <div class="visible-xs">
                  <a href="../cart" class="btn btn-primary"><span class="cart-item">3</span> <i class="fa fa-shopping-cart"></i></a>
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
                  <li><a href="../categories">Pants</a></li>
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
              <li><a href="../about">About</a></li>
              <li><a href="../contact">Contact</a></li>
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
                  <li><a href="#">Girl</a></li>
                  <li><a href="#">Pants</a></li>
                  <li class="active">Blackbox</li>
                </ol>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="heading-title">
                <h2><span>${leatherJacket.inventoryTxt}</span> <span class="text-yellow"></span></h2>
              </div>
              <div class="row">
                <!-- begin:product-image-slider -->
                <div class="col-md-6 col-sm-6">
                  <div id="product-single" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                      <div class="item active">
                        <div class="product-single">
                          <img src="<c:url value="${leatherJacketDetail.get(0).thumbnail1}"/>" class="img-responsive">
                        </div>
                      </div>
                      <div class="item">
                        <div class="product-single">
                          <img src="<c:url value="${leatherJacketDetail.get(0).thumbnail2}"/>" class="img-responsive">
                        </div>
                      </div>
                      <div class="item">
                        <div class="product-single">
                          <img src="<c:url value="${leatherJacketDetail.get(0).thumbnail3}"/>" class="img-responsive">
                        </div>
                      </div>
                    </div>

                    <a class="left carousel-control" href="#product-single" data-slide="prev">
                      <i class="fa fa-angle-left"></i>
                    </a>
                    <a class="right carousel-control" href="#product-single" data-slide="next">
                      <i class="fa fa-angle-right"></i>
                    </a>
                  </div>
                </div>
                <!-- end:product-image-slider -->

                <!-- begin:product-spesification -->
                <div class="col-md-6 col-sm-6">
                  <div class="single-desc">
                  
                  <c:url var="addAction" value="add/${leatherJacket.inventoryId}" ></c:url>
				  <form:form action="${addAction}" commandName="InventoryDetail">
                    <%-- <form:form method="post" action="${flowExecutionUrl}" commandName="InventoryDetail"> --%>
                  
                      <span class="visible-xs">
                          <strong>${leatherJacket.inventoryTxt} / <c:choose><c:when test="$leatherJacket.inStock">In Stock</c:when><c:otherwise>Out of Stock</c:otherwise></c:choose></strong>                                                  
                      </span>

                      <table>
                        <tbody>
                          <tr class="hidden-xs">
                              <td><strong>Brand</strong></td>
                              <td>:</td>
                              <td>${leatherJacket.brand}</td>
                          </tr>
						<%--<tr class="hidden-xs">
                              <td><strong>Product Code</strong></td>
                              <td>:</td>
                              <td>${leatherJacketDetail.productCode}</td>
                          </tr> --%>
                          <tr class="hidden-xs">
                              <td><strong>Availability</strong></td>
                              <td>:</td>
                              <c:choose>
                              <c:when test="${leatherJacket.inStock}">
                              <td>In Stock</td>
                              </c:when>
                              <c:otherwise>
                              <td>Out of Stock</td>
                              </c:otherwise>
                              </c:choose>                                                            
                          </tr>
                          <tr>
                              <td colspan="3"><span class="price-old">$32.91</span> <span class="price">$21.42</span></td>
                          </tr>
                          <tr>
                              <td><strong>Color</strong></td>
                              <td>:</td>
                              <td>
                                <form:select path="color" class="form-control" name="color">
                                  <form:option value="Black">Black</form:option>
                                  <form:option value="Green">Green</form:option>
                                  <form:option value="Blue">Blue</form:option>
                                  <form:option value="Yellow">Yellow</form:option>                                  
                                </form:select>                            
                            </td>   
                          </tr>   
                          <tr>
                              <td><strong>Size</strong></td>
                              <td>:</td>
                              <td>
                                <form:select path="size" class="form-control" name="size">
                                  <form:option value="XS"></form:option>
                                  <form:option value="S"></form:option>
                                  <form:option value="M"></form:option>
                                  <form:option value="L"></form:option>
                                  <form:option value="XL"></form:option>                                
                                </form:select>
                              </td>
                          </tr>
                          <tr>
                              <td><strong>Quantity</strong></td>
                              <td>:</td>
                              <td>
                                <input type="number" class="form-control" value="1" name="quantity">
                                <%-- <form:hidden path="quantity"/> --%>
                              </td>
                          </tr>
                          <tr>
                              <td colspan="3">
                              <!-- This needs to be fixed...get(0): -->
                              <!-- It should retrieve the exact inventoryDetailId based on the inventoryId, size, and color. -->
							<input type="submit" class="btn btn-sm btn-primary" value="Add To Cart"/>
                                <%-- <a href="<c:url value='add/${leatherJacket.inventoryId}'/>" class="btn btn-sm btn-primary">Add to Cart</a> --%>
                              </td>  
                          </tr>
                        </tbody>
                      </table>
                      </form:form>
                  </div>
                </div>
                <!-- end:product-spesification -->
              </div>
              <!-- break -->
              <!-- begin:product-detail -->
              <div class="row">
                <div class="col-md-12 content-detail">
                    <ul id="myTab" class="nav nav-tabs">
                      <li class="active"><a href="#desc" data-toggle="tab">Description</a></li>
                      <li class=""><a href="#care" data-toggle="tab">Care</a></li>
                      <li class=""><a href="#size" data-toggle="tab">Sizing</a></li>
                    </ul>

                    <div id="myTabContent" class="tab-content">
                      <div class="tab-pane fade active in" id="desc">
                        <p>${leatherJacket.inventoryDescription}</p>
                      </div>
                      <div class="tab-pane fade" id="care">
                        <p>${leatherJacket.inventoryCare}</p>
                      </div>
                      <div class="tab-pane fade" id="size">
                        <p>${leatherJacket.inventorySizeDesc}</p>
                        <table class="table table-striped table-bordered">
                          <tbody>
                            <tr>
                              <th>#</th>
                              <th>Size</th>
                              <th>Bust</th>
                              <th>Waist</th>
                              <th>Hip</th>
                              <th>Soulder Width</th>
                              <th>Frong Body length</th>
                            </tr>
                            <tr>
                              <td>1</td>
                              <td>XXL</td>
                              <td>96</td>
                              <td>76</td>
                              <td>102</td>
                              <td>41</td>
                              <td>44</td>
                            </tr>
                            <tr>
                              <td>2</td>
                              <td>XL</td>
                              <td>92</td>
                              <td>72</td>
                              <td>98</td>
                              <td>40</td>
                              <td>43.5</td>
                            </tr>
                            <tr>
                              <td>3</td>
                              <td>L</td>
                              <td>88</td>
                              <td>68</td>
                              <td>94</td>
                              <td>39</td>
                              <td>42</td>
                            </tr>
                            <tr>
                              <td>4</td>
                              <td>M</td>
                              <td>84</td>
                              <td>64</td>
                              <td>92</td>
                              <td>38</td>
                              <td>40.5</td>
                            </tr>
                            <tr>
                              <td>5</td>
                              <td>S</td>
                              <td>80</td>
                              <td>60</td>
                              <td>88</td>
                              <td>37</td>
                              <td>39</td>
                            </tr>
                            <tr>
                              <td>6</td>
                              <td>XS</td>
                              <td>76</td>
                              <td>56</td>
                              <td>84</td>
                              <td>36</td>
                              <td>37.5</td>
                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                </div>
              </div>
              <!-- end:product-detail -->

              <!-- begin:related-product -->
              <div class="row">
                <div class="col-md-12">
                  <div class="heading-title">
                    <h2>Related <span>Product</span> <span class="text-yellow">.</span></h2>
                  </div>

                  <div class="row product-container" style="position: relative; height: 463.75px;">
                    <div class="col-md-3 col-sm-6 col-xs-6">
                      <div class="thumbnail product-item">
                        <a href="../product_detail"><img alt="" src="<c:url value="/resources/img/product1.jpg"/>"></a>
                        <div class="caption">
                          <h5>Pants</h5>
                          <p>$54.00</p>
                          <p>Available</p>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-6">
                      <div class="thumbnail product-item">
                        <a href="../product_detail"><img alt="" src="<c:url value="/resources/img/product2.jpg"/>"></a>
                        <div class="caption">
                          <h5>Pants</h5>
                          <p>$54.00</p>
                          <p>Available</p>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-6">
                      <div class="thumbnail product-item">
                        <a href="../product_detail"><img alt="" src="<c:url value="/resources/img/product3.jpg"/>"></a>
                        <div class="caption">
                          <h5>Pants</h5>
                          <p>$54.00</p>
                          <p>Available</p>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-6">
                      <div class="thumbnail product-item">
                        <a href="../product_detail"><img alt="" src="<c:url value="/resources/img/product4.jpg"/>"></a>
                        <div class="caption">
                          <h5>Pants</h5>
                          <p>$54.00</p>
                          <p>Available</p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- end:related-product -->

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

  </body>
</html>