<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
<%--  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  --%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="Access-Control-Allow-Origin" content="*"><title>扫码领取</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
	
	<style type="text/css">
        .Bar {  width:  100%;text-align:center;
            /* 宽度 */ border: 1px solid #B1D632; padding: 1px; 
border-radius:5px;
-moz-border-radius:55px;}
        .Bar div{ display: block; 
            background:peachpuff;/* 进度条背景颜色 */ color: #333333;
            height: 20px; /* 高度 */ line-height: 20px;
            /* 必须和高度一致，文本才能垂直居中 */ }
        .Bar div span{  width: 100%; font-weight: bold; }

    </style>
	</head>
	<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js" type="text/javascript"></script>
	<body style="text-align: center;background: #EEE;margin-top: 50px;">
    <div class="Bar">
        <div style="width:70%;" id="divprocessId">
            <span><span id="processId">80</span>%</span>
        </div>
    </div>
	<div style="background: white;padding: 10px;margin-top:30px;height:600px;">
			<div style="margin-top:30px;font-size:16px;font-weight:bold;">长按下方二维码，领取股票代码</div>
			<div style="padding:20px;">
				<img style="padding:5px;width:290px;height:320px;" id="qrcodeId" src="#"  />
			</div>
			<div><label style="color: red;" id="addCountId">353</label>人通过审核 | 仅剩名额 <label style="color: red;" id="subCountId">158</label>
				个</div>

		</div>
	</body>
	<script>
		var timer;
		var timer1;
		var timer2;
		
		$(function() {
				//取Ajax返回结果
			<%-- 	var url = <%=request.getParameter("picUrl")%> --%>
			var msg = "${picUrl }";
			$("#qrcodeId").attr("src",msg);
			/* 	alert(url); */
				/* $.ajax({
                type:"get",  //请求方式
                url:"http://caikanghua.top/finance/getPicUrl.do",
                dataType:"json",  
                contentType:"application/json; charset=utf-8",
                success:function(msg){  //成功
					$("#qrcodeId").attr("src",msg);
                }               
            }); */

			timer = setInterval("setProcess()", 200);
			timer1 = setInterval("addCount()", 900);
			timer2 = setInterval("subCount()", 600);
			// http://caikanghua.top/finance/getPicUrl.do
			
		})

		function setProcess() {
			var curVal = parseInt($("#processId").text());
			$("#processId").text(curVal + 2);
			$("#divprocessId").css("width",curVal + 2+"%");
			if (curVal + 2 >= 100) {
				$("#loadTitleId").text("加载完成");
				clearInterval(timer);

			}
		}

		function addCount() {
			var curVal = parseInt($("#addCountId").text());
			$("#addCountId").text(curVal + 1);
			if (curVal + 1 > 500) {
				clearInterval(timer1);

			}
		}

		function subCount() {
			var curVal = parseInt($("#subCountId").text());
			$("#subCountId").text(curVal - 1);
			if (curVal - 1 < 1) {
				clearInterval(timer2);

			}
		}
	</script>
</html>
