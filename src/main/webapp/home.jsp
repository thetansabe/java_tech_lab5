<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lab5._52000643.entities.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: AN515-43
  Date: 10/7/2022
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  if(session.getAttribute("userSession") == null){
    response.sendRedirect("/login");
  }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Product management</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
        rel="stylesheet"
  />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="row p-3">
  <!-- left -->
  <div class="col-lg-4 p-3" id="left">
    <div class="mb-3">
      <h2>Product Management</h2>
    </div>

    <div class="border p-3">
      <h3 class="text-success fw-bold">Add new product</h3>

      <form action="/" method="post" id="form_add">
        <div class="form-group mb-3">
          <label for="product_name">Product name</label>
          <input type="text" class="form-control" name="product_name" id="product_name">
        </div>

        <div class="form-group mb-3">
          <label for="product_price">Product price</label>
          <input type="text" class="form-control" name="product_price" id="product_price">
        </div>

        <button type="submit" class="btn btn-success">Add product</button>

        <div id="form_add-msg" class="mt-3"></div>
      </form>
    </div>
  </div>

  <!-- right -->
  <div class="col-lg-8 py-3" id="right">
    <div class="mb-3 d-flex flex-row-reverse">
      <h4 >
        Hello <span class="text-danger">${sessionScope.userSession.getFullName()}</span>,
        <a href="/logout.jsp">Logout</a>
      </h4>
    </div>

    <div class="border p-3">
      <h3 class="text-success fw-bold">Product list</h3>

      <table class="table table-striped">
        <thead>
          <th scope="col">STT</th>
        <th scope="col">Product name</th>
      <th scope="col">Price</th>
      <th scope="col">Action</th>
      </thead>
        <c:forEach items="${products}" var="product" varStatus="loop">
          <tr>
            <th scope="row">${loop.count}</th>
            <td class="text-primary">${product.getProductName()}</td>
            <td>$ ${product.getPrice()}</td>
            <td>
              <button
                      type="button"
                      class="btn btn-link"
                      data-bs-toggle="modal"
                      data-bs-target="#exampleModal"
                      data-bs-productName="${product.getProductName()}"
                      data-bs-productId="${product.getProductId()}"
              >
                Delete
              </button>
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</div>

<!-- Modal -->
<div
        class="modal fade"
        id="exampleModal"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
>
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Deletion Confirm</h5>
        <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
        ></button>
      </div>
      <div class="modal-body">
        Are you sure to delete:
        <span class="modal-body_span text-danger fs-4 fw-bold"></span>
      </div>
      <div class="modal-footer">
        <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
        >
          Close
        </button>
        <button type="button" class="btn btn-danger">Save changes</button>
      </div>
    </div>
  </div>
</div>

<script>
  // handle modal
  var exampleModal = document.getElementById("exampleModal");

  exampleModal.addEventListener("show.bs.modal", function (event) {
    // Button that triggered the modal
    var button = event.relatedTarget;
    // Extract info from data-bs-* attributes
    var productId = button.getAttribute("data-bs-productId");
    var productName = button.getAttribute("data-bs-productName");

    var modalSpan = exampleModal.querySelector(".modal-body_span");
    modalSpan.textContent = productName;

    var delBtn = exampleModal.querySelector(".btn.btn-danger");
    delBtn.setAttribute("delId", parseInt(productId));
  });

  //delete handling
  exampleModal
          .querySelector(".btn.btn-danger")
          .addEventListener("click", async (e) => {
            const delId = parseInt(e.target.getAttribute('delId'))

            try{
              await fetch(`/?product_id=\${delId}`, {
                method: "DELETE"
              })

              window.location.reload()
            }catch (e){}
          });

  //add handling
  document.querySelector('#form_add').addEventListener("submit" , async e => {
    e.preventDefault();

    const name = e.target.querySelector("#product_name").value
    const price = e.target.querySelector("#product_price").value

    if(name == '' || price == ''){
      const content = `<div class="alert alert-danger alert-dismissible fade show " role="alert">
          <strong>Missing content!</strong> You should check in on some of those fields above.
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>`

      e.target.querySelector("#form_add-msg").innerHTML = content
      return
    }

    try{
      await fetch(`/?product_name=\${name}&product_price=\${price}`, {
        method: "POST"
      })

      window.location.reload()
    }catch (e){}
  })
</script>
</body>
</html>

