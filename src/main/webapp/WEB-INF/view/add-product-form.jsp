<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          charset="ISO-8859-1">
<%@include file="./base.jsp"%>
</head>
<body>

    <div class="container mt-3">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <h1 class="text-center mb-3"> Fill the product Detail</h1>

                    <form action="handle-product" method="post">
                        <div class="form-group">
                            <label for="name">Product Name</label>
                            <input class="form-control"
                                      id="name"
                                      name="name"
                                      placeholder="Enter product name here">


                        </div>

                        <div class="form-group">
                            <label for="description">Product Description</label>
                            <textarea class="form-control"
                                      id="description"
                                      name="description"
                                      placeholder="Enter product description">
                            </textarea>

                        </div>

                        <div class="form-group">
                            <label for="price">Product Price</label>
                            <input
                                    class="form-control"
                                    id="price"
                                    name="price"
                                    type="text"
                                    rows="3"
                                    placeholder="Enter product price">

                        </div>

                        <div class="container text-center">
<%--                            This href gives you the "/" path which is the project root./--%>
                            <a href="${pageContext.request.contextPath}/"
                               class="btn btn-outline-danger">Back</a>
                            <button type="submit" class="btn btn-primary">Add</button>
                        </div>
                    </form>

            </div>
        </div>
    </div>



</body>
</html>