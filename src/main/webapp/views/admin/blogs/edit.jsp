<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-blogs"/>
<c:url var ="NewsURL" value="/admin-blogs"/>
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <c:url var="listURL" value="/admin-blogs">
                        <c:param name="page" value="1"/>
                        <c:param name="maxPageItem" value="5"/>
                        <c:param name="sortName" value="title"/>
                        <c:param name="sortBy" value="desc"/>
                        <c:param name="type" value="list"/>
                    </c:url>
                    <a href='${listURL}'>
                        Quản lý bài viết
                    </a>
                </li>
                <c:if test="${empty model.id}">
                    <li class="active">Thêm mới bài viết</li>
                </c:if>
                <c:if test="${not empty model.id}">
                    <li class="active">Chỉnh sửa bài viết</li>
                </c:if>

            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert} fixed-alert">
                            <strong>${messageResponse}</strong>
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                        </div>
                    </c:if>

                    <!-- Trong file JSP của bạn -->
                    <div id="loadingComponent" class="loading-component">
                        <!-- Thẻ chứa nội dung loading -->
                        <div class="loading-spinner"></div>
                        <p>Loading...</p>
                    </div>

                    <form id="formSubmit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Danh mục</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="categoryId" name="categoryId">
                                    <c:if test="${empty model.categoryId}">
                                        <option value="">Chọn loại bài viết</option>
                                        <c:forEach var="item" items="${categoriesMap}">
                                            <option value="${item.key}">${item.value}</option>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${not empty model.categoryId}">
                                        <option value="">Chọn loại bài viết</option>
                                        <c:forEach var="item" items="${categoriesMap}">
                                            <option value="${item.key}"
                                                    <c:if test="${item.key == model.categoryId}">selected="selected"</c:if>
                                            >
                                                    ${item.value}
                                            </option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="title" name="title" value="${model.title}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Slug bài viết</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="slugBlog" name="slugBlog" value="${model.slugBlog}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                            <div class="col-sm-9">
                                <textarea
                                        <%--rows="10"--%>
                                          <%--cols="1"--%>
                                        style="width:820px;height: 175px"
                                          id="content" name="content"
                                          <%--class="form-control"--%>
                                >${model.content}</textarea>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật bài viết" id="btnAddOrUpdateNew"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm bài viết" id="btnAddOrUpdateNew"/>
                                </c:if>
                            </div>
                        </div>
                        <input type="hidden" value="${model.id}" id="id" name="id"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var editor = '';
    $(document).ready(function(){
        editor = CKEDITOR.replace( 'content');
    });

    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        $("#loadingComponent").show();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
        data["content"] = editor.getData();
        var id = $('#id').val();
        if (id == "") {
            addNew(data);
        } else {
            updateNew(data);
        }
    });
    function addNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${NewsURL}?type=edit&id="+result.id+"&message=insert_success";
            },
            error: function (error) {
                window.location.href = "${NewsURL}?type=list&maxPageItem=5&page=1&sortName=category&sortBy=desc&message=error_system";
            }
        });
    }
    function updateNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${NewsURL}?type=edit&id="+result.id+"&message=update_success";
            },
            error: function (error) {
                window.location.href = "${NewsURL}?type=list&maxPageItem=5&page=1&sortName=category&sortBy=desc&message=error_system";
            }
        });
    }
</script>
</body>
</html>
