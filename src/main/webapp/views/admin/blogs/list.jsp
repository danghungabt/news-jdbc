<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<c:url var="APIurl" value="/api-admin-blogs"/>
<c:url var="BlogsURL" value="/admin-blogs"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách bài viết</title>
</head>

<body>
<div class="main-content">

    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <%--<a href="#">--%>
                    Quản lý bài viết
                    <%--</a>--%>
                </li>
            </ul>
            <!-- /.breadcrumb -->
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

                    <%--Search--%>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="widget-box table-filter">
                                <div class="widget-header">
                                    <h4 class="widget-title">
                                        Tìm kiếm
                                    </h4>
                                    <div class="widget-toolbar">
                                        <a href="#" data-action="collapse">
                                            <i class="ace-icon fa fa-chevron-up"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <form action="${BlogsURL}" id="formSubmit" method="get">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <div class="col-sm-6">
                                                        <label for="title">Tên danh mục</label>
                                                        <input type="text" id="title" name="title"
                                                               value="${modelSearch.title}" class="form-control"/>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <label for="categoriesId">Danh mục bài viết</label>
                                                        <select id="categoriesId" name="categoriesId"
                                                                class="form-control">
                                                            <option value="0" label="--Tất cả--"/>
                                                            <c:forEach var="category" items="${categoriesMap}">
                                                                <option value="${category.key}"
                                                                        <c:if test="${modelSearch.categoriesId eq category.key}">selected</c:if>
                                                                >
                                                                        ${category.value}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="col-sm-6">
                                                        <label for="createdBy">Người tạo</label>
                                                        <select id="createdBy" name="createdBy" class="form-control">
                                                            <option value="" label="--Tất cả--"/>
                                                            <c:forEach var="user" items="${usersMap}">
                                                                <option value="${user.key}"
                                                                        <c:if test="${modelSearch.createdBy eq user.key}">selected</c:if>
                                                                >
                                                                        ${user.value}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>

                                                    <div class="col-sm-6">
                                                        <label for="modifiedBy">Người cập nhận gần nhất</label>
                                                        <select id="modifiedBy" name="modifiedBy" class="form-control">
                                                            <option value="" label="--Tất cả--"/>
                                                            <c:forEach var="user" items="${usersMap}">
                                                                <option value="${user.key}"
                                                                        <c:if test="${modelSearch.modifiedBy eq user.key}">selected</c:if>
                                                                >
                                                                        ${user.value}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <button id="btnSearch" type="button"
                                                            class="btn btn-sm btn-success">
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                            <input type="hidden" value="1" id="page" name="page"/>
                                            <input type="hidden" value="5" id="maxPageItem" name="maxPageItem"/>
                                            <input type="hidden" value="title" id="sortName" name="sortName"/>
                                            <input type="hidden" value="desc" id="sortBy" name="sortBy"/>
                                            <input type="hidden" value="list" id="type" name="type"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%--Tool--%>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="pull-right tableTools-container">
                                <div class="dt-buttons btn-overlap btn-group">
                                    <c:url var="createURL" value="/admin-blogs">
                                        <c:param name="type" value="edit"/>
                                    </c:url>
                                    <a flag="info"
                                       class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                       data-toggle="tooltip" title='Thêm bài viết' href='${createURL}'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                                    </a>
                                    <button id="btnDelete" type="button"
                                            class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                            data-toggle="tooltip" title='Xóa bài viết'
                                            onclick="warningBeforeDelete()">
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%--Table--%>
                    <div class="row" id="tableData">
                        <div class="col-xs-12">
                            <div class="table-responsive">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th><input type="checkbox" id="checkAll"/></th>
                                        <th>Tên bài viết</th>
                                        <th>Slug bài viết</th>
                                        <th class="col-md-1">Ngày đăng</th>
                                        <th class="col-md-1">Người đăng</th>
                                        <th class="col-md-1">Ngày cập nhật gần nhất</th>
                                        <th class="col-md-1">Người cập nhật gần nhất</th>
                                        <th class="col-md-1">Thao tác</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${model.listResult}">
                                        <tr>
                                            <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"/>
                                            </td>
                                            <td>${item.title}</td>
                                            <td>${item.slugBlog}</td>
                                            <td>${item.createdDateStr}</td>
                                            <td>${item.createdBy}</td>
                                            <td>${item.modifiedDateStr}</td>
                                            <td>${item.modifiedBy}</td>
                                            <td>
                                                <c:url var="editURL" value="/admin-blogs">
                                                    <c:param name="type" value="edit"/>
                                                    <c:param name="id" value="${item.id}"/>
                                                </c:url>
                                                <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                   title="Cập nhật bài viết" href='${editURL}'>
                                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <ul class="pagination" id="pagination"></ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Modal -->
<div id="assignmentBuildingModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Thông tin danh mục bài viết</h4>
            </div>
            <div class="modal-body">
                <form id="formSubmitAPI">
                    <div class="form-horizontal">

                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="btnSaveCategories" data-dismiss="modal">
                    Lưu
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>

<!-- /.main-content -->
<script type="text/javascript">
    $("#tableData").show();
    var totalPage = ${model.totalPage};
    var currentPage = ${model.page};
    var limit = ${model.maxPageItem};
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPage,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                // $("#tableData").hidden();
                // $("#loadingComponent").show();
                if (currentPage != page) {
                    $('#maxPageItem').val(limit);
                    $('#page').val(page);
                    $('#sortName').val('title');
                    $('#sortBy').val('desc');
                    $('#type').val('list');
                    $('#formSubmit').submit();
                }
            }
        });
    });

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#formSubmit').submit();
    });

    function warningBeforeDelete() {
        var data = {};
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = ids;
        showAlertBeforeDelete(function () {
            event.preventDefault();
            deleteBlogs(data);
        });
    };

    function deleteBlogs(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${BlogsURL}?type=list&maxPageItem=2&page=1&sortName=title&sortBy=desc&message=delete_success";
            },
            error: function (error) {
                window.location.href = "${BlogsURL}?type=list&maxPageItem=2&page=1&sortName=title&sortBy=desc&message=error_system";
            }
        });
    }

    function assignmentBuilding(categoriesId) {
        openModalAssignmentBuilding(categoriesId);
    }

    function openModalAssignmentBuilding(categoriesId) {
        if (categoriesId !== 0) {
            $("#loadingComponent").show();
            $.ajax({
                type: "GET",
                url: "${APIurl}/serverSide/findOneById/" + categoriesId,
                //data: JSON.stringify(data),
                dataType: "json",
                // contentType: "application/json",
                success: function (response) {
                    console.log(response);

                    var row = '';
                    row += '<div class="form-group">';
                    row += '<div class="col-sm-12">';
                    row += '<label for="categoryAPI">Tên danh mục</label>';
                    row += '<input type="text" id="categoryAPI" name="category" value="' + response.category + '" class="form-control"/>';
                    row += '</div>';
                    row += '<div class="col-sm-12">';
                    row += '<label for="slugAPI">Slug danh mục</label>';
                    row += '<input type="text" id="slugAPI" name="slugCategory" value="' + response.slugCategory + '" class="form-control"/>';
                    row += '</div>';
                    row += '<input type="hidden" id="categoriesIdAPI" name="id" value="' + response.id + '" />';
                    row += '</div>';

                    $('#formSubmitAPI .form-horizontal').html(row);
                    $('#categoriesIdAPI').val(categoriesId);
                    setTimeout(function () {
                        $("#loadingComponent").hide();
                    }, 50);
                    setTimeout(function () {
                        $('#assignmentBuildingModal').modal();
                    }, 10);
                    listenEventInput();
                },
                error: function (response) {
                    console.log("failed");
                    console.log(response);
                    $("#loadingComponent").hide();
                }
            });
        } else {
            var row = '';
            row += '<div class="form-group">';
            row += '<div class="col-sm-12">';
            row += '<label for="categoryAPI">Tên danh mục</label>';
            row += '<input type="text" id="categoryAPI" name="category" value="" class="form-control"/>';
            row += '</div>';
            row += '<div class="col-sm-12">';
            row += '<label for="slugAPI">Slug danh mục</label>';
            row += '<input type="text" id="slugAPI" name="slugCategory" value="" class="form-control"/>';
            row += '</div>';
            row += '<input type="hidden" id="categoriesIdAPI" name="id" value=""/>';
            row += '</div>';

            $('#formSubmitAPI .form-horizontal').html(row);
            $('#categoriesIdAPI').val(categoriesId);
            setTimeout(function () {
                $('#assignmentBuildingModal').modal();
            }, 100);
            listenEventInput();
        }
    }

    $('#btnSaveCategories').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmitAPI').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });
        var id = $('#categoriesIdAPI').val();
        if (id == 0) {
            // addNew(data);
            console.log("add new", data);
            addCategories(data);
        } else {
            // updateNew(data);
            console.log("update", data);
            updateCategories(data);
        }
    });

    function addCategories(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${BlogsURL}?type=list&maxPageItem=2&page=1&sortName=category&sortBy=desc&message=insert_success";
            },
            error: function (error) {
                window.location.href = "${BlogsURL}?type=list&maxPageItem=2&page=1&sortName=category&sortBy=desc&message=error_system";
            }
        });
    }

    function updateCategories(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${BlogsURL}?type=list&maxPageItem=2&page=1&sortName=category&sortBy=desc&message=update_success";
            },
            error: function (error) {
                window.location.href = "${BlogsURL}?type=list&maxPageItem=2&page=1&sortName=category&sortBy=desc&message=error_system";
            }
        });
    }

    function listenEventInput() {
        // Lắng nghe sự kiện input trên các trường nhập liệu
        $('#categoryAPI, #slugAPI').on('input', function () {
            // Kiểm tra nếu cả hai trường đã được điền
            if ($('#categoryAPI').val().trim() !== '' && $('#slugAPI').val().trim() !== '') {
                $('#btnSaveCategories').prop('disabled', false); // Kích hoạt nút "Lưu"
            } else {
                $('#btnSaveCategories').prop('disabled', true); // Vô hiệu hóa nút "Lưu"
            }
        });

        // Ban đầu vô hiệu hóa nút "Lưu"
        $('#btnSaveCategories').prop('disabled', true);
    }

    // Sự kiện khi checkbox "Check All" thay đổi trạng thái
    $('#checkAll').change(function () {
        $('input[type="checkbox"][id^="checkbox_"]').prop('checked', $(this).prop('checked'));
        updateDeleteButtonStatus();
    });

    // Sự kiện khi bất kỳ checkbox riêng lẻ nào thay đổi trạng thái
    $('input[type="checkbox"][id^="checkbox_"]').change(function () {
        if (!$(this).prop('checked')) {
            $('#checkAll').prop('checked', false);
        } else {
            var allChecked = $('input[type="checkbox"][id^="checkbox_"]').length === $('input[type="checkbox"][id^="checkbox_"]:checked').length;
            $('#checkAll').prop('checked', allChecked);
        }
        updateDeleteButtonStatus();
    });

    function updateDeleteButtonStatus() {
        var anyChecked = $('input[type="checkbox"][id^="checkbox_"]:checked').length > 0;
        $('#btnDelete').prop('disabled', !anyChecked);
    }

    // Ban đầu vô hiệu hóa nút "Xóa"
    updateDeleteButtonStatus();

</script>
</body>

</html>