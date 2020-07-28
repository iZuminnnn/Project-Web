
<%@page import="entity.Account"%>
<%@page import="entity.Account"%>
<!--Nav-->
<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="index.html">Vegefoods</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>

        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">



                <li class="nav-item active"><a href="index" class="nav-link">Home</a></li>
                <li class="nav-item active"><a href="List" class="nav-link">Shop</a></li>
                <li class="nav-item"><a href="about.jsp" class="nav-link">About</a></li>
                <li class="nav-item cta cta-colored"><a href="cart" class="nav-link"><span class="icon-shopping_cart"></span>Cart</a></li>

                <%
                    Account a = (Account) session.getAttribute("account");
                %>
                <%if (a != null) {%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%=a.getAccountid()%></a>
                    <div class="dropdown-menu" aria-labelledby="dropdown04">                         
                        <a class="dropdown-item" href="logout">Log out</a> 
                    </div>
                    
                    <%if (a != null && a.getAccountid().equals("admin")) {%>
                    <div class="dropdown-menu" aria-labelledby="dropdown04">
                        <a class="dropdown-item" href="addproduct">Add Product</a>     
                        <a class="dropdown-item" href="logout">Log out</a> 
                    </div>
                       <%}%>
                </li>
                <%} else {%>
                <li class="nav-item active"><a href="login" class="nav-link">Login</a></li>
                    <%}%>


            </ul>
        </div>
    </div>
</nav>
<!-- END nav -->

<div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span>Products</span></p>
                <h1 class="mb-0 bread">Products</h1>
            </div>
        </div>
    </div>
</div>
