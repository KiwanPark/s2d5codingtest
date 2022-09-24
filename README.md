# dantalk_codingtest
1. 본 앱은 단톡의 2021년 안드로이드 개발자 신규채용을 위한 과제테스트 목적으로 개발되었습니다. 그 외의 목적으로 사용/배포하실 수 없습니다.

2. 본 앱의 기능과 화면 구성은 다음과 같습니다
 - 회원가입
 - 로그인
 - 목록보기
 - 자세히 보기

3. 개발환경
 - Android Studio Arctic Fox | 2020.3.1 Patch 1
 - kotlin 1.5.30
 - gradle 7.0.3
 - compileSdk 31
 - minSdk 23
 - compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
 -  kotlinOptions {
        jvmTarget = '1.8'
    }

4. API
 - 통신방법 : REST API
 - domain : https://us-central1-findpcroom-f0e38.cloudfunctions.net/

 - [GET] signup
   request : Query("id"), Query("password")
	 > 아이디와 패스워드로 가입함
	 > 아이디는 이메일 형식 으로 하며, 오류 검출 로직, 아이디 중복 체크 등은 필요하지 않음
	 > response :
	 > { "code":200, "message":"success", "data":null }
 
 - [GET] signin
   Query("id"), Query("password")
   > 아이디와 패스워드로 로그인함
	 > 아이디는 이메일 형식
	 > response :
	 > 성공시 
	  ```json 
	  { "code":200, "message" : "success", "data":null }
	  ```
	 > 실패시 
	  ```json 
	  { "code":404, "message" : "mismatch id/password",  "data" : null }
	  ```
	 > 실패시 
	  ```json 
	  { "code":404, "message" : "id not found",  "data" : null }
	  ```
   
 - [GET] album
   	> 엘범 자켓 정보를 모두 가져온다
   	>response :
   	> 성공시
   	
 ```json
    {
      "code": 200,
      "message": "success",
      "data": [
        {
          "id": "0RumPYZIvCggqnABaJbU",
          "name": "As time does by",
          "imgUrl": "https://firebasestorage.googleapis.com/v0/b/findpcroom-f0e38.appspot.com/o/dantalkTestImage%2F28.jpg?alt=media",
          "artist": "임희영"
        },
        {
          "id": "2u6Zn1aKVXM0vB2S7T08",
          "artist": "임영웅",
          "name": "별빛 같은 나의 사랑",
          "imgUrl": "https://firebasestorage.googleapis.com/v0/b/findpcroom-f0e38.appspot.com/o/dantalkTestImage%2F23.jpg?alt=media"
        }
      ]
    }
  ```
  
  - [GET] albumItem
   Query("id"), id
   > 엘범의 id로 상세정보를 불러온다
   ```json
  {
  "code": 200,
  "message": "success",
  "data": {
    "artistInfo": "<b>MSG워너비</b> ( 영어: <b>MSG</b> Wannabe )는 대한민국의 남자 음악 그룹이다. 2021년 《놀면 뭐하니?》에서 결성된 프로젝트 그룹이다. 이전 구성원 김정수 (김정민) 정기석 (사이먼 도미닉) 이동휘 이상이 별루-지... ",
    "albumInfo": "나를 아는 사람 90년대를 휩쓸었던 전형적인 8분의 6박자 컨템포러리 R&B곡으로 남성 중창단의 매력을 잘 표현할 수 있는 스타일의 곡이다.  40~50년대 가스펠과 Doo Wop 시대를 지나 60년대 Deep Soul, 70년대 Philly Soul 중창단의 계보를 잇는 90년대 남성 중창단의 인기는 대단했다.  팝적이면서도 흑인음악 특유의 하모니와 감성이 살아있는 컨템포러리 R&B는 그 시절 수많은 보컬그룹들을 양산해냈고 중창 특유의 매력을 발산하며 많은 사랑을 받았다.  이런 배경 속에서 만들어진 '나를 아는 사람'은 우리나라 말이 잘 어울리는 남자 4중창 컨템포러리 R&B곡으로 피아노를 중심으로 호소력 짙은 색소폰 연주와 매혹적인 하몬드 오르간의 선율이 애절하면서도 로맨틱한 분위기를 더해준다.",
    "artist": "MSG워너비",
    "name": "나를 아는 사람",
    "id": "RVhFCpJ7jU5pPBqBXfXU",
    "imgUrl": "https://firebasestorage.googleapis.com/v0/b/findpcroom-f0e38.appspot.com/o/dantalkTestImage%2F18.jpg?alt=media"
  }
}
```

5. 본 앱의 활용 기술은 다음과 같습니다
 - kotlin
 - jetpack library
 - lambda function
 - InputLayoutText
 - glide or picasso
 - lottie
 - MVVM
 - retrofit
 - data binding
 - material design
 - shared elements transition
 - Android Architecture Components
 - Clean Architecture

6. 위에 제시되지 않은 라이브러리는 자유롭게 사용하되, 위의  라이브러리를 사용하여 문제를 해결하면 가점이 있습니다

7. 제출방법은 깃허브에 반드시 프라이빗으로 올리고, [setting] - [manage access] - [add people] 에 아래 계정을 초대해주시고, 메일 전체회신 형태로 레포지토리 URL 공유 부탁드립니다
 kiwanpark@dantalk.co.kr

8. 샘플앱 다운로드 주소
 https://drive.google.com/file/d/13Ed6RK-r4Jao1V3FQ8NJ3YE0XgitVRVm/view?usp=sharing

9. resource files
 https://lottiefiles.com/10008-music-note-character
 https://lottiefiles.com/9329-loading
 https://drive.google.com/file/d/1_MA_96w8mV_ZRJ8XsfAI2MJjT0QXlVmV/view?usp=sharing

10. 제출 규정
제출 기한은 2021년 11월 4일 목요일 오후 5시까지이며, 과제 제출은 본 메일의 전체 회신으로 주시면 됩니다. 

과제 진행 관련 문의 사항은 아래를 참고하시어 연락 주시기 바랍니다.

과제를 다 끝마치지 못하더라도 진행하실 수 있는 만큼 진행하신 후, 제출해주세요. 
추가로 과제에 대한 간단한 설명을 추가해 주시면 더욱 좋습니다.

과제 내용 관련 질문
- 메일주소 : kiwanpark@dantalk.co.kr

11. 과제 포인트 지급 관련 제출 자료

아울러 과제를 수행해 주시면 들이신 시간에 대한 작은 사례로,
소정의 과제비를 지급해드립니다.
* 과제 후 2주 이내로 지급됩니다.
 
궁금하신 점 있으시면 편히 연락 주세요!
즐겁고 행복한 하루 보내시기 바라겠습니다:)
