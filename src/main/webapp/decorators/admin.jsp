<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><dec:title default="Trang chủ"/></title>
    <link rel="stylesheet"
          href="<c:url value='/template/admin/assets/css/bootstrap.min.css' />"/>
    <link rel="stylesheet"
          href="<c:url value='/template/admin/font-awesome/4.5.0/css/font-awesome.min.css' />"/>
    <link rel="stylesheet"
          href="<c:url value='/template/admin/assets/css/ace.min.css' />"
          class="ace-main-stylesheet" id="main-ace-style"/>
    <script
            src="<c:url value='/template/admin/assets/js/ace-extra.min.js' />"></script>
    <link rel="stylesheet"
          href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet"
          href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type='text/javascript'
            src='<c:url value="/template/admin/js/jquery-2.2.3.min.js" />'></script>
    <script
            src="<c:url value='/template/admin/assets/js/jquery.2.1.1.min.js' />"></script>
    <link rel="stylesheet"
          href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script
            src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>
    <script
            src="<c:url value='/ckeditor/ckeditor.js' />"></script>

    <style type="text/css">
        .my-page-header{
            display: flex;
            margin: 0 0 12px;
            border-bottom: 1px solid #eee;
            padding-bottom: 16px;
            padding-top: 7px;
            align-items: center;
        }

        .my-page-header h1{
            padding: 0;
            margin: 0 8px;
            font-size: 24px;
            font-weight: lighter;
            color: #2679b5;
            display: inline;
        }

        .fixed-alert {
            position: fixed;
            top: 8%; /* Điều chỉnh vị trí từ đỉnh trang */
            right: 0px; /* Để căn giữa theo chiều ngang */
            transform: translateX(-10%);
            z-index: 9999; /* Đảm bảo nó hiển thị trên các phần tử khác */
            display: flex; /* Sử dụng flexbox */
            align-items: center; /* Căn nội dung theo chiều dọc */
            padding: 10px 15px; /* Padding cho alert */
            border: 1px solid #ccc; /* Viền */
            border-radius: 4px; /* Bo tròn các góc */
            max-width: 80%; /* Chiều rộng tối đa của alert */
            min-width: 256px;
            justify-content: space-between;
        }
        .fixed-alert button{
            margin-left: 12px;
        }

        /* Trong file CSS của bạn */
        .loading-component {
            display: none; /* Ban đầu ẩn đi */
            /* Thêm các thuộc tính để căn giữa loading */
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 5px;
            text-align: center;
            z-index: 10;
        }

        .loading-spinner {
            /* Thêm CSS để tạo hiệu ứng loading spinner, ví dụ sử dụng animation */
            border: 4px solid rgba(0, 0, 0, 0.1);
            border-left: 4px solid #3498db;
            border-radius: 50%;
            width: 50px;
            height: 50px;
            animation: spin 1s linear infinite;
        }

        @keyframes spin {
            0% {
                transform: rotate(0deg);
            }
            100% {
                transform: rotate(360deg);
            }
        }

    </style>
</head>
<body class="no-skin">
<!-- header -->
<%@ include file="/common/admin/header.jsp" %>
<!-- header -->

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
    <!-- menu -->
    <%@ include file="/common/admin/menu.jsp" %>
    <!-- menu -->

    <dec:body/>

    <!-- footer -->
    <%@ include file="/common/admin/footer.jsp" %>
    <!-- footer -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse display">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>

<script src="<c:url value='/template/admin/assets/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery-ui.custom.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.ui.touch-punch.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.easypiechart.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.sparkline.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.pie.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/jquery.flot.resize.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/ace-elements.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/ace.min.js' />"></script>
<script src="<c:url value='/template/admin/assets/js/bootstrap.min.js'/>"></script>

<!-- page specific plugin scripts -->
<script src="<c:url value='/template/admin/assets/js/jquery-ui.min.js'/>"></script>
<%--sweetalert--%>
<script type='text/javascript' src="/template/admin/assets/sweetalert2/sweetalert2.min.js"></script>
<link rel="stylesheet" href="/template/admin/assets/sweetalert2/sweetalert2.min.css">


<script type="text/javascript">
    function showAlertBeforeDelete(callback) {
        swal({
            title: "Xác nhận xóa",
            text: "Bạn có chắc chắn xóa những dòng đã chọn",
            type: "warning",
            showCancelButton: true,
            confirmButtonText: "Xác nhận",
            cancelButtonText: "Hủy bỏ",
            confirmButtonClass: "btn btn-success",
            cancelButtonClass: "btn btn-danger"
        }).then(function (res) {
            if (res.value) {
                callback();
            } else if (res.dismiss == 'cancel') {
                console.log('cancel');
            }
        });
    }

    // Hiển thị alert với hiệu ứng fade in
    $('.alert').fadeIn();

    // Tự động ẩn alert sau 3 giây
    setTimeout(function () {
        $('.alert').fadeOut();
    }, 3000);
</script>
</body>
</html>