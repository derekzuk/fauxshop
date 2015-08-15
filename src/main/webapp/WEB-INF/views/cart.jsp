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
            <h1><a href="index">Faux<span>shop</span> </a></h1>
            <p>Durable, Fashionable, Animal Friendly</p>
          </div>
        </div>
        <div class="col-md-6 col-sm-6 col-xs-6">
          <div class="account">
            <ul>
              <li id="your-account">
                <div class="hidden-xs">
                  <h4><a href="#">Your Account</a></h4>
                  <c:choose>
                  	<c:when test="${pageContext.request.userPrincipal.name != null}">                  	
					<p>Welcome, ${currentUser.getPrincipal().getUsername()}</p>
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
                  <p><strong>3 Product(s)</strong></p>
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
                <ul class="cart list-unstyled">
                  <li>
                    <div class="row">
                      <div class="col-sm-7 col-xs-7">1 <a href="product_detail">Blackbox</a> <span>[ 26 ]</span></div>
                      <div class="col-sm-5 col-xs-5 text-right"><strong>$54.00</strong> <a href="#"><i class="fa fa-trash-o"></i></a></div>
                    </div>
                  </li>
                  <li>
                    <div class="row">
                      <div class="col-sm-7 col-xs-7">1 <a href="product_detail">JunkShirt</a> <span>[ M ]</span></div>
                      <div class="col-sm-5 col-sm-5 text-right"><strong>$26.00</strong> <a href="#"><i class="fa fa-trash-o"></i></a></div>
                    </div>
                  </li>
                </ul>
                <ul class="list-unstyled total-price">
                  <li>
                    <div class="row">
                      <div class="col-sm-8 col-xs-8">Shipping</div>
                      <div class="col-sm-4 col-xs-4 text-right">$1.00</div>
                    </div>
                  </li>
                  <li>
                    <div class="row">
                      <div class="col-sm-8 col-xs-8">Total</div>
                      <div class="col-sm-4 col-xs-4 text-right">$71.00</div>
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
                  <li class="active">Cart</li>
                </ol>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <ul class="nav nav-tabs">
                  <li class="active"><a href="cart">Cart</a></li>
                  <li><a href="#">Login</a></li>
                  <li><a href="#">Account</a></li>
                  <li><a href="#">Shipping</a></li>
                  <li><a href="#">Payment</a></li>
              </ul>

              <h3>Your Cart</h3>
              <hr />
                
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
                <tr>
                  <td><img src="<c:url value="/resources/img/product1.jpg"/>" class="img-cart" /></td>                  
                  <td><strong>BlackBox</strong><p>Size : 26</p></td>
                  <td>
                    <form class="form-inline">
                      <input class="form-control" type="text" value="1" />
                      <button rel="tooltip" title="Update" class="btn btn-default"><i class="fa fa-pencil"></i></button>
                      <a href="#" class="btn btn-primary" rel="tooltip" title="Delete"><i class="fa fa-trash-o"></i></a>
                    </form>
                  </td>
                  <td>$54.00</td>
                  <td>$54.00</td>
                </tr>
                <tr>
                  <td><img src="<c:url value="/resources/img/product2.jpg"/>" class="img-cart" /></td>
                  <td><strong>JunkShirt</strong><p>Size : M</p></td>
                  <td>
                    <form class="form-inline">
                      <input class="form-control" type="text" value="2" />
                      <button rel="tooltip" title="Update" class="btn btn-default"><i class="fa fa-pencil"></i></button>
                      <a href="#" class="btn btn-primary" rel="tooltip" title="Delete"><i class="fa fa-trash-o"></i></a>
                    </form>
                  </td>
                  <td>$16.00</td>
                  <td>$32.00</td>
                </tr>
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
              <form role="form" method="post" action="${flowExecutionUrl}">
              <input type="submit" class="btn btn-default" name="_eventId_continueShopping" value="Continue Shopping" />
              <input type="submit" class="btn btn-primary pull-right" name="_eventId_next" value="Next" />
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

  </body>
</html>
