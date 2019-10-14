SimpleLoginApp
==============
>+ 간단한 로그인 기능을 포함한 앱.  
>+ JSON형식의 로그인 데이터를 사용하였다.  
>+ Toast를 이용해 여러가지 정보를 표현해 보았다.  
---

  
### 페이지 구성
1. Login Page ( Relative Layout )
>+ 메인페이지로 로그인을 할 수 있는 Edit Text 두개
>+ 로그인 버튼 - 로그인 실패시 Toast로 알려주고, 다시 시도, 성공시 Home page로 이동
>+ 회원가입 버튼 - 회원가입 페이지로 이동

2. Signup Page ( Linear Layout ) 
>+ ID - 중복확인 기능 포함
>+ Password - 길이 제한 10자 이상
>+ Password Check - Password와 동일하지 않을 시 가입 제한
>+ 이름, 주소, 핸드폰 번호
>+ 약관 동의 - 동의하지 않을 시 가입 제한
>+ 로그인 화면으로 넘어갈 수 있는 버튼

3. Home Page ( Table Layout )
>+ 구글, 네이버, 야후 등 6개의 검색 엔진 페이지
>+ drawable 폴더에 있는 사진을 이용해 사진을 입한 Toggle 버튼
>+ 매 클릭시마다 두개의 사진을 번갈아 표시하도록

---
### 개발환경
>+ Android Studio 3.3.2
>+ JAVA "10.0.2" 2018-07-17
>+ Windows 10 Pro 64bit
>+ Pixel XL API 28
