## StayHome

 ##### 개발환경 : Android Studio(java), openAPI(공공데이터), bootpay api .. 

1. 기능

   - OpenAPI 사용하여 코로나 관련 정보 표시

   - 코로나 바이러스에 대비하는 물품  웹 크롤링(옥션)

   - 자가진단 체크(https://github.com/SparkYuan/SubmitButton)

   - 결제시스템 구현(bootpay api 사용 )

     

2. View 구성

   #### homeView & checkView 

   - [x] 코로나 openapi 사용
   - [x] 일일확진자(어제 기준) 및 검색 기능 ++Animation
   - [x] 특정 질문 또는 개수에 따른 다른 결과
   - [x] 클릭 시, Animation, 전화 표시

![ezgif com-gif-maker](https://user-images.githubusercontent.com/39898938/100467089-4b293c80-3115-11eb-83ad-833d8c155687.gif)    &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;   &nbsp;  &nbsp;   &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;      ![ezgif com-gif-maker (2)](https://user-images.githubusercontent.com/39898938/100466908-069da100-3115-11eb-9c69-ca419c4138de.gif)  



#### 	marketView & paymentView

- [x] 옥션 웹 크롤링(마스크, 손소독제) url , 상품명, 가격
- [x] 상품명에 해당 링크 연결 및 아이콘 클릭 시, 결제 창 이동
- [x] 결제시스템 구현(bootpay api 사용)
- [x] 테스트(가상계좌, 페이코..) & 실결제 모드(휴대폰)

![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/39898938/100466859-f1287700-3114-11eb-8a48-8a2552738428.gif)  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  ![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/39898938/102724743-337c5700-4355-11eb-90b9-4b874cf6cb7d.gif)  &nbsp;  &nbsp;  &nbsp;  &nbsp;   ![ezgif com-gif-maker (2)](https://user-images.githubusercontent.com/39898938/102724748-370fde00-4355-11eb-9025-37111f421bd9.gif) 

