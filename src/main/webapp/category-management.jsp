<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="templates/head.jsp"/>

<body>
<div class="site-wrap">
    <jsp:include page="templates/header.jsp"/>

    <div class="bg-light py-3">
        <div class="container">
            <div class="row">
                <div class="col-md-12 mb-0"><a href="home">Home</a> <span class="mx-2 mb-0">/</span> <strong
                        class="text-black">Category management</strong></div>
            </div>
        </div>
    </div>

    <div class="site-section">
        <div class="container">
            <div class="row mb-5">
                <div class="col-md-12">
                    <div class="site-blocks-table">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Image</th>
                                <th>ID</th>
                                <th style="max-width: 120px"> Category name</th>
                                <th style="min-width: 195px">Edit / Remove</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${category_list}" var="o">
                                <tr>
                                    <td class="product-thumbnail">
                                        <img src="data:image/jpg;base64,${o.base64Image}" alt="Image" class="img-fluid">
                                    </td>

                                    <td>${o.id}</td>
                                    <td>${o.name}</td>
                                    <td>
                                        <a href="edit-product?product-id=${o.id}" class="btn btn-primary btn-sm"
                                           style="background-color: green ; border-color: green">
                                            <span class="icon icon-pencil"></span>
                                        </a>


                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="row mb-5">
                        <div class="col-md-6">
                            <button class="btn btn-outline-primary btn-sm btn-block">Delete</button>
                        </div>

                        <!-- Button trigger add product modal -->
                        <div class="col-md-6 mb-3 mb-md-0">
                            <button class="btn btn-primary btn-sm btn-block" data-toggle="modal"
                                    data-target="#addProductModal">Add Category
                            </button>
                        </div>

                        <!-- Add product Modal -->
                        <div class="modal fade bd-example-modal-lg" id="addProductModal" tabindex="-1" role="dialog"
                             aria-labelledby="myLargeModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg modal-dialog-centered">
                                <form class="modal-content" action="add-category" method="post"
                                      enctype="multipart/form-data">
                                    <div class="modal-header">
                                        <h5 class="modal-title text-black" id="addProductModalLabel">
                                            category information
                                        </h5>

                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>

                                    <div class="modal-body" style="padding: 0">
                                        <div class="p-3">
                                            <div class="form-group row">
                                                <div class="col-md-12">
                                                    <label for="category-name" class="text-black">
                                                        Category Name <span class="text-danger">*</span>
                                                    </label>

                                                    <input name="category-name" type="text" class="form-control"
                                                           id="category-name">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-md-12">
                                                    <label for="category-image" class="text-black">
                                                        Image <span class="text-danger">*</span>
                                                    </label>

                                                    <input name="category-image" type="file" class="form-control"
                                                           id="category-image">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-outline-primary btn-sm btn-block"
                                                data-dismiss="modal" style="margin-top: 0">
                                            Cancel
                                        </button>

                                        <button type="submit" class="btn btn-primary btn-sm btn-block"
                                                style="margin-top: 0">
                                            Save
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="templates/footer.jsp"/>
</div>

<jsp:include page="templates/scripts.jsp"/>
</body>
</html>