<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.104.2">
        <title>Sign up - Employee Management</title>

        <link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

        <meta name="theme-color" content="#712cf9">


        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet">

    </head>
   
    
    

    <body class="text-center">
        <script>
            function submitForm(){
                
                signupForm.submit();
            }
        </script>
       


        <main class="form-signin w-100 m-auto">
            <form action="PreSignup" id="signupForm">
                <img class="mb-4" src="images/flower-logo.jpg" alt="" width="200" height="200">
                <h1 class="h3 mb-3 fw-normal">Please provide below information</h1>

                <div class="form-floating">
                    <input type="email" class="form-control" name="email"  id="floatingInput" placeholder="name@example.com" value="${User.getEmail()}" >
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" value="${User.getPassword()}" name="password">
                    <label for="floatingPassword">Password</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="firstName" placeholder="first name" value="${User.getFirstname()}" name="Firstname">
                    <label for="firstName">First Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="lastName" placeholder="last name" value="${User.getLastName()}" name="LastName">
                    <label for="firstName">Last Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="phoneNumber" placeholder="phone number" value="${User.getPhoneNumber()}" name="phoneNumber">
                    <label for="firstName">Phone Number</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="address" placeholder="address" value="${User.getAddress()}" name="address">
                    <label for="firstName">Address</label>
                </div>
                
                
            

                <div class="form-group mb-4">
                    <select name="countryCode" class="form-select" id="countryId" onchange="submitForm()">
                        <option value=""  >Select a Country</option>
                    <c:forEach items="${ContList}" var="cont">
                        <option  <c:if test="${cont.getCountryCode()==User.getCountryCode()}">selected</c:if> value="${cont.getCountryCode()}"  > ${cont.getCountryName()}  </option>

                    </c:forEach>
                </select>

            </div>
                
                 <div class="form-group mb-5">
                     <select name="stateCode" class="form-select" id="stateId" onchange="submitForm()">
                        <option value=""  >Select a State</option>
                    <c:forEach items="${StateList}" var="state">
                        <option  <c:if test="${state.getStateCode()==User.getStateCode()}">Selected</c:if> value="${state.getStateCode()}"  > ${state.getStateName()}  </option>
                    </c:forEach>
                </select>

            </div>
                
                  <div class="form-group mb-6">
                      <select name="provinceCode" class="form-select" id="districtId" >
                        <option value="" >Select a Province</option>
                    <c:forEach items="${DistrictList}" var="district">
                        <option value="${district.getProvinceCode()}"> ${district.getProvinceName()}  </option>
                    </c:forEach>
                </select>

            </div>

                
                

                
                

                <div class="checkbox mb-3">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit">Sign Up</button>
                <a href="landingPage.jsp">
                    <button type="button" class="w-100 btn btn-lg btn-warning">Cancel</button>
                </a>
                <p class="mt-5 mb-3 text-muted">&copy; 2017?2022</p>
            </form>
        </main>



    </body>
</html>
