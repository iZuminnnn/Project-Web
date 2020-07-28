<%-- 
    Document   : List
    Created on : May 4, 2020, 2:22:19 PM
    Author     : cauch
--%>

<%@page import="Page.Paging"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
    <head>

        <title>Vegefoods - Free Bootstrap 4 Template by Colorlib</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
        <script src="js/jquery.min.js"></script>
        <%
            Account a = (Account) session.getAttribute("account");
            Integer totalpage = (Integer) request.getAttribute("totalpage");
            Integer pageindex = (Integer) request.getAttribute("pageindex");
        %>
    </head>
    <body class="goto-here">
        <div>
            <jsp:include page="header.jsp"/>
        </div>
        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <form class="search-form" action="List" method="get">
                        <input type="text" name="search" placeholder="Search" required>
                        <button type="submit"  class="btn btn-black">Search</button>
                    </form>
                    <div class="col-md-10 mb-5 text-center ">

                        <ul class="product-category ">
                            <li><a href="List" class="active" >All</a></li>
                            <li ><a href="List?type=1">Vegetables</a></li>
                            <li><a href="List?type=2" >Fruits</a></li>
                            <li><a href="List?type=3" >Juice</a></li>
                            <li><a href="List?type=4" >Dried</a></li>

                        </ul>
                    </div>
                </div>
                <div class="row">

                    <c:forEach items="${requestScope.Product}" var ="p">
                        <div class="col-md-6 col-lg-3 ftco-animate">
                            <div class="product">

                                <a href="product-single?id=${p.ID}" class="img-prod">
                                    <img class="img-fluid" src="${p.image}" alt="Colorlib Template">                         
                                    <div class="overlay"></div>
                                </a>
                                <div class="text py-3 pb-4 px-3 text-center">
                                    <h3><a href="#">${p.name}</a></h3>
                                    <div class="d-flex">
                                        <div class="pricing">
                                            <p class="price"><span>$${p.price}</span></p>
                                        </div>
                                    </div>
                                    <div class="bottom-area d-flex px-3">
                                        <div class="m-auto d-flex">
                                            <a href="cart?id=${p.ID}" class="add-to-cart d-flex justify-content-center align-items-center text-center">
                                                <span><i class="ion-ios-cart"></i></span>
                                            </a>

                                            <%if (a != null && a.getAccountid().equals("admin")) {%>
                                            <a href="updateproduct?id=${p.ID}" class="buy-now d-flex justify-content-center align-items-center mx-1">
                                                <span><i class="ion-ios-cut"></i></span>
                                            </a>
                                            <a href="delete?id=${p.ID}" class="heart d-flex justify-content-center align-items-center ">
                                                <span><i class="ion-ios-trash"></i></span>
                                            </a>
                                            <%} else {%>
                                            <%}%>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 
                    </c:forEach>

                </div>



                <div class="row mt-5">
                    <div class="col text-center">
                        <div class="block-27 ">
                            <ul>
                                <%=Paging.pager(2, pageindex, totalpage)%>
                            </ul>
                        </div>
                    </div>
                </div>


            </div>
        </section>

        <div>
            <jsp:include page="footer.jsp"/>
        </div>
        <!-- loader -->
        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
        <script src="js/jquery-migrate-3.0.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.easing.1.3.js"></script>
        <script src="js/jquery.waypoints.min.js"></script>
        <script src="js/jquery.stellar.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/aos.js"></script>
        <script src="js/jquery.animateNumber.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/scrollax.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>
