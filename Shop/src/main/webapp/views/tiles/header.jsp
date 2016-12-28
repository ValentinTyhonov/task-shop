<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
	.logout input[type="submit"] {
		background-color: #fbfbfb;
	    color: #6e6a6a;
	    font-size: 14px;
	    padding: 20px;
	}
	
	.logout input[type="submit"]:hover {
		background-color: #5a88ca;
		color: #FFF;
	}
</style>

<div class="site-branding-area">
	<div class="container">
    	<div class="row">
        	<div class="col-sm-6">
            	<div class="logo">
                	<h1><a href="./"></a></h1>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End site branding area -->
    
<div class="mainmenu-area">
	<div class="container">
    	<div class="row">
            <div class="navbar-collapse collapse">
            	<ul class="nav navbar-nav">
                	<li class="home-active"><a href="index">Home</a></li>
                    <sec:authorize access="isAuthenticated()">
                    	<li class="cart-active"><a href="opencart">Cart</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
							<li class="admin-active"><a href="adminpage">Admin</a></li>
					</sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                    	<li class="logout">
							<form:form action="logout" method="post">
								<input type="submit" value="Logout">
							</form:form>
						</li>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                    	<li class="login-active"><a href="login">Login</a></li>
                    </sec:authorize>
                </ul>
            </div>  
        </div>
    </div>
</div> <!-- End mainmenu area -->