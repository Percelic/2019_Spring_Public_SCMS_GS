# GS 인증 시험용 SCMS 모니터링 시스템 프로젝트

![image](https://user-images.githubusercontent.com/43790820/131301677-61a81bae-43c7-4a76-bb0d-36a713df028a.png)

![image](https://user-images.githubusercontent.com/43790820/131301964-0bb9fe23-98e0-4efc-848b-f3c23f99b590.png)


* 2019년 1월 말 ~ 2019년 12월 초 진행
- BackEnd 담당하여 진행하다가 6월 중 FrontEnd 작업을 겸함.
- BackEnd, FrontEnd Refactoring 작업 필요..
   2020.01.10

* Frontend (Spring)
 - AuthenticationProvider를 통해 비밀번호 암호화하는 방법은 상용화 시 적용하면 좋겠음.

 - 등록/수정 화면에서 각 입력란의 입력 범위를 사전에 확실히 정의해야 할 필요성 느낌
 - 화면마다 중복되는 항목 (Drawer 메뉴, AppBar 표시 )은 Component화하여 재사용해야 할 필요성 느낌
 - Frontend framework로 ReactJS , VueJS 둘 중 하나 학습하여 Frontend와 Backend를 구분하여 관리했으면 좋겠음

* Backend (Java)
- 개발 전 요구사항을 정리하여 구현하였음에도 불구하고 코드가 복잡해져서 상용화 프로젝트에 적용 시 
다소 Refactoring 작업이 필요함.
--------------------------------------------------------------------------------

- Main :  
    프로그램 Entry Point이면서 각 Process Module 제어 및 Global Variable을 관리

- DataCollector : 
 1) Socket Server를 동작하여 트래커로부터 Socket Client Connection을 수용
 2) 각 트래커로부터 일정 주기마다 TCP Protocol로 전송하는 String 기반 Packet을 수신,
 3) SCMS Data Protocol과 일치하는지 Validation Check 작업을 처리한 다음 Packet Parsing을 작업함.
 4) Parsing 된 Data는 SCMS Data Protocol을 종류마다 정의한 Class 타입으로 Instance화
 5) Instance 가공된 Data은 MySQL DB 통신 단에서 적절한 Query를 통해 Load됨
    
 ! 각 구간 사이 데이터 통신은 구간 별 순환 큐 Instance를 통해 처리하였음.
   
- TrackerController : 
 1) Web Socket Server를 동작하여 FrontEnd 의 Socket Client Connection을 수용
 2) FrontEnd에서 SCMS Data Protocol을 통해 TCP Protocol로 전송하는 특정 트래커 조작 요청 Packet을 수신
 3) SCMS Data Protocol과 일치하는지 Validation Check 작업 처리 후 Packet Parsing 수행
 4) Parsing 된 Data를 SCMS Data Protocol Class 타입으로 Instance화
 5) Instance 가공된 Data를 Hash 기반으로 구현한 트래커 Connection Info에서 확인하여 해당 Socket Connection에 적절한 제어 요청을 전달
  
! 트래커 Connection Info는 DataCollector Module과 공유되고 있음.
    
