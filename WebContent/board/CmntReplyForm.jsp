<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title> 댓글 답변 </title>
    
    <style type="text/css">
        #wrap {
            width: 550px;
            margin: 0 auto 0 auto;
            text-align :center;
        }
    
        #commentReplyForm{
            text-align :center;
        }
    </style>
    
    <script type="text/javascript">
    
        var httpRequest = null;
        
        // httpRequest 객체 생성
        function getXMLHttpRequest(){
            var httpRequest = null;
        
            if(window.ActiveXObject){
                try{
                    httpRequest = new ActiveXObject("Msxml2.XMLHTTP");    
                } catch(e) {
                    try{
                        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e2) { httpRequest = null; }
                }
            }
            else if(window.XMLHttpRequest){
                httpRequest = new window.XMLHttpRequest();
            }
            return httpRequest;    
        }
    
    
        function checkValue()
        {
            var form = document.forms[0];
            // 전송할 값을 변수에 담는다.    
            var comment_num = "${cmnt.cmnt_num}";
            var comment_board = "${cmnt.cmnt_bd}";
            var comment_id = "${sessionScope.userID}";
            var comment_content = form.cmnt_content.value
            
            var pageNum = "${sessionScope.pageNum}";
            
            if(!comment_content)
            {
                alert("내용을 입력하세요");
                return false;
            }
            else{
                var param="cmnt_num="+comment_num+"&cmnt_bd="+comment_board
                                +"&cmnt_id="+comment_id+"&cmnt_content="+comment_content;
 
                httpRequest = getXMLHttpRequest();
                httpRequest.onreadystatechange = checkFunc;
                httpRequest.open("POST", "CmntReplyAction.cmnt", true);    
                httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8'); 
                httpRequest.send(param);
            }
        }
        
        function checkFunc(){
            if(httpRequest.readyState == 4){
                // 결과값을 가져온다.
                var resultText = httpRequest.responseText;
                if(resultText == 0){
                    if (opener != null) {
                        // 부모창 새로고침
                        //window.opener.document.location.reload(); 메소드 제대로 안 먹길래 직접 url 작성해서 href 방식으로 넘김
                        var url = "http://localhost:7972/MVC_Board/board/BoardDetail.board?num="+${cmnt.cmnt_bd}+"&pageNum="+${sessionScope.pageNum}
                      	opener.location.href = url;
                        opener.focus();
                        opener.replyForm = null;
                        self.close();
                    }
                }
            }
        }
        
    </script>
    
</head>
<body>

	<!-- top 레이아웃 삽입 -->
	<jsp:include page="../layout/topLayout.jsp" flush="false" />
	<br />
	
<!-- 
<div id="wrap">
    <br>
    <b><font size="5" color="gray">댓글 답변</font></b>
        <hr size="1" width="550">
    <br>
    <div id="commentReplyForm">
        <form name="replyInfo" target="parentForm">        
            <textarea rows="7" cols="70" name="cmnt_content"></textarea>
            <br><br>
            <input type="button" value="등록" onclick="checkValue()">
            <input type="button" value="창닫기" onclick="window.close()">
        </form>
    </div>
</div>  -->
    
    	<h1 class="text-center"><span class="badge badge-secondary">대댓글 작성</span></h1>
	
<form name="replyInfo" target="parentForm">

<table class="container col-lg-7">

	<tr><td>
		<textarea class="form-control text-center" rows="5" name="cmnt_content"
			placeholder="대댓글 내용을 입력해주세요" required 
		></textarea>
	</td></tr>

	<tr class="text-center"><td>
	<input type="button" class="btn btn-primary mb-3" value="등록" onclick="checkValue()">
	<input type="button" class="btn btn-secondary mb-3" value="취소" onclick="window.close()">
	</td></tr>
	</table>
</form>
  
  	<br />
	<!-- bottom 레이아웃 삽입 -->
	<jsp:include page="../layout/bottomLayout.jsp" flush="false" />
</body>
</html>