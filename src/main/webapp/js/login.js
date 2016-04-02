$(document).ready(
		function() {
			// 点击登录
			$('#but_login').click(function(e) {
				submit();
			});
			// 回车登录
			$(document).keydown(function(e) {
				if (e.keyCode == 13) {
					submit();
				}
			});

			$('#Kaptcha').click(
					function() {
						$(this).hide().attr(
								'src',
								'Kaptcha.jpg?'
										+ Math.floor(Math.random() * 100))
								.fadeIn();
					});
		});
// 表单提交
function submit() {
	var submit = true;
	$("input[nullmsg]").each(function() {
		if ($("#" + this.name).val() == "") {
			showError($("#" + this.name).attr("nullmsg"), 500);
			submit = false;
			return false;
		}
	});
	if (submit) {
		Login();
		return false;
	}

}
// 登录处理函数
function Login() {
	setCookie();
	var actionurl = $('form').attr('action');// 提交路径
	// var checkurl=$('form').attr('check');
	var formData = new Object();
	var data = $(":input").each(function() {
		formData[this.name] = $("#" + this.name).val();
	});
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : actionurl,// 请求的action路径
		data : formData,
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			// var d = $.parseJSON(data);
			var d = eval('(' + data + ')');
			if (d.status) {
				window.setTimeout(window.location.href="index.jsp",10);
				window.setTimeout(window.location.href="index.jsp",10);
				window.setTimeout(window.location.href="index.jsp",10);
			} else {
				showError(d.message);
			}
		}
	});
}
// 设置cookie
function setCookie() {
	if ($('#on_off').val() == '1') {
		$("input[iscookie='true']").each(function() {
			$.cookie(this.name, $("#" + this.name).val(), "/", 24);
			$.cookie("COOKIE_NAME", "true", "/", 24);
		});
	} else {
		$("input[iscookie='true']").each(function() {
			$.cookie(this.name, null);
			$.cookie("COOKIE_NAME", null);
		});
	}
}
// 读取cookie
function getCookie() {
	var COOKIE_NAME = $.cookie("COOKIE_NAME");
	if (COOKIE_NAME != null) {
		$("input[iscookie='true']").each(function() {
			$($("#" + this.name).val($.cookie(this.name)));
		});
		$("#on_off").attr("checked", true);
		$("#on_off").val("1");
	} else {
		$("#on_off").attr("checked", false);
		$("#on_off").val("0");
	}
}

// 显示错误提示
function showError(str) {
	alert(str);
}

function showSuccess(str) {
	alert(str);
}




