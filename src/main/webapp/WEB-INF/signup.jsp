<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
<%@include file="includes/navigation.jsp" %>
<div class="container">
<br/>
<h2 class="h2">Sign Up</h2>
<hr class="mb-4">
<form class="form-horizontal" role="form" action="<c:url value="/signup"/>" method="post">
<div class="form-group">
<label for="username">UserName</label>
<input type="text" class="form-control" id="username" value="${userDTO.username}" name="username" required placeholder="type username"/>
<c:if test="${errors.username !=null}">
<small class="text-danger">${errors.username}</small></c:if>
</div>
<div class="form-group">
<label for="email">email</label>
<input type="email" class="form-control" id="email" value="${userDTO.email }" name="email" required placeholder="you@example.com"/>
<c:if test="${errors.email !=null}">
<small class="text-danger">${errors.email}</small></c:if>
</div>
<div class="form-group">
<label for="password">Password</label>
<input type="password" class="form-control" id="password" name="password" required/>
<c:if test="${errors.password !=null}">
<small class="text-danger">${errors.password}</small></c:if>
</div>
<div class="form-group">
<label for="passwordConfirmed">Password confirmed</label>
<input type="password" class="form-control" id="passwordConfirmed" name="passwordConfirmed" required/>
<c:if test="${errors.passwordConfirmed !=null}">
<small class="text-danger">${errors.passwordConfirmed}</small></c:if>
</div>
<div class="form-group">
<label for="email">First name</label>
<input type="text" class="form-control" id="firstName" name="firstName" required/>
<c:if test="${errors.firstName !=null}">
<small class="text-danger">${errors.firstName}</small></c:if>
</div>
<div class="form-group">
<label for="email">Last name</label>
<input type="text" class="form-control" id="lastName" name="lastName" required>
<c:if test="${errors.lastName !=null}">
<small class="text-danger">${errors.lastName}</small></c:if>
</div>
<hr class="mb-4">
<div class="form-group">
<button class="btn btn-primary btn-lg" type="submit">Sign Up</button>
</div></form>
</div>
<script type="text/javascript">
    function validatePassword() {
        var password = document.getElementsByName("password").value;
        var confirmPassword = document.getElementById("passwordConfirmed").value;
        if (password !== confirmPassword) {
            alert("Passwords do not match.");
            return false;
        }
        return true;
    }
</script>

<%@include file="includes/footer.jsp" %>