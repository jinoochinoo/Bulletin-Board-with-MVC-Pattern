# 자바 기반 JSP 게시판 만들기
- MVC 방식으로 Model, View, Controller 분리해서 코딩
- CRUD 기능 충실히 구현. 기본에 초점을 맞춘 게시판이니까
- 답변글, 댓글, 대댓글까지 다 한땀한땀 직접 구현

# 기술 스택
- Oracle DB 연동. conn, pstmt, rs 일일이 쓰면서 직접 코딩
- 부트스트랩. frontend 제작할 때 사용
- JQuery, Javascript. 답변글, 댓글 기능에 비동기 통신 추가
---
![MainVisitor](https://user-images.githubusercontent.com/73393147/104298240-13c8f080-5507-11eb-8ca8-736ade8e36cc.PNG)
## Main & Visitor
- 홈페이지 가장 첫 화면. 대략적인 소개와 함께
- 회심의 기능. 방문자 수 데이터가 왼쪽 하단에 표시된다
---
![intro](https://user-images.githubusercontent.com/73393147/104298236-1297c380-5507-11eb-8115-6b79ba2f1433.PNG)
## Introduction
- 본격적인 게시판 포트폴리오 소개
- 개발하면서 겪었던 우여곡절, 그리고 극복 방법까지 기재
---
![write](https://user-images.githubusercontent.com/73393147/104298247-14fa1d80-5507-11eb-8f2c-836c4f3c2992.PNG)
## Write
- 기본적인 게시글 작성 화면
- multipart/form-data 이용해 첨부파일 기능 구현
---
![SearchPaging](https://user-images.githubusercontent.com/73393147/104298243-14618700-5507-11eb-9509-f570039dade7.PNG)
## Search & Paging
- 가장 많이 애를 먹었던 검색과 페이징
- 페이징을 해결하면 검색에서 오류가... 
- 검색을 해결하면 페이징에서 오류가... ㄷㄷ;;
---
![Comment](https://user-images.githubusercontent.com/73393147/104298074-eaa86000-5506-11eb-9ec8-67d9feb07dee.PNG)
## Comment
- 댓글 + 대댓글까지 구현
- 여기에 또 + (사진에 없지만)답변글 구현
