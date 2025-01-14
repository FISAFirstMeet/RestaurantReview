## 🍴 프로젝트 소개


맛집 리뷰를 저장하고, 다양한 기준으로 정렬하여 한눈에 리뷰를 확인할 수 있는 **맛집 리뷰 관리 프로그램**


CLI 인터페이스를 통해 맛집 정보를 입력하고, 리뷰를 체계적으로 관리하여 사용자들에게 보다 풍부한 경험을 제공


## 👥 조원
|<img src="https://avatars.githubusercontent.com/u/80048007?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/60309978?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/129049084?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/193316939?v=4" width="150" height="150"/>|
|:-:|:-:|:-:|:-:|
|박영진<br/>[@DoomchitYJ](https://github.com/DoomchitYJ)|박정호<br/>[@Jeongho427](https://github.com/Jeongho427)|민정인<br/>[@min-jp](https://github.com/min-jp)|박재희<br/>[@JaeHee-devSpace](https://github.com/JaeHee-devSpace)|


## 📝 기획


### 🎯 목표


- **맛집 리뷰 관리 간소화**


    - 사용자들이 손쉽게 맛집 리뷰를 입력하고 저장할 수 있는 간단한 입력 시스템 제공


    - 주요 맛집 정보를 통합적으로 관리하여 데이터 접근성을 높임


- **다양한 정렬 기준 제공**


    - 리뷰를 별점, 나이대, 가격 등 다양한 기준으로 정렬하여 고객 맞춤형 정보를 제공


    - 직관적인 UI를 통해 사용자가 원하는 정보를 빠르게 찾을 수 있도록 지원


- **확장 가능한 아키텍처**


    - JDBC를 기반으로 하여 데이터베이스와의 연동성을 높이고, 다양한 데이터베이스에서도 활용 가능한 확장성 제공


    - 추후 웹 기반 프로젝트의 확장 가능성을 고려한 설계


## 🛠️ 설계


- 사람들이 리뷰를 보고 원하는 정보를 얻을 수 있도록 필요한 데이터를 컬럼으로 채택


    - 레스토랑 정보


    - 음식 정보


    - 평가


- 최대한 MVC 패턴을 지키며 프로젝트를 수행, 각 계층에 대한 확실한 역할을 부여하고 SRP를 준수


    - Model: DB에 직접적으로 접근하는 역할


    - View: 사용자와 직접적으로 소통하는 역할, 사용자에게 필요한 정보를 제공하고 사용자가 필요한 정보 입력


    - Controller: Model과 View 사이에서 데이터를 전달하는 역할


- 사용자의 정보를 보관한 객체를 전역적으로 선언하여 모든 클래스에서 사용 가능하게 함


    - Singleton Pattern


### 📊 데이터 모델링


![image](https://github.com/user-attachments/assets/16a3964f-fd88-43ed-a4e7-04129480d662)


맛집 리뷰를 담는 테이블


- review_id : 각 리뷰별 PK


- user_id : 유저의 개인 ID


- gender : 성별. Enum type으로 여자, 남자 존재


- restaurant_name : 음식점 이름


- category : 음식의 카테고리. Enum type으로 한식, 중식, 양식, 일식, 기타 존재


- menu : 메뉴 이름


- price : 메뉴 가격


- content : 리뷰 한줄평


- score : 별점 (0-5.0)


- date : 리뷰 입력한 날짜


## 🖥️ 개발


### ⚙️ 기술 스택


- **언어**: Java 17


- **라이브러리**: JDBC, Lombok


- **빌드 도구**: Maven


- **DB**: MySQL


- **DB 운영 환경**: VirtualBox - Ubuntu


- **버전 관리**: Git, GitHub


- **협업 도구**: Slack


- **테스트 프레임워크**: JUnit


### 🌟 주요 기능


- 개인 정보 입력


    - User ID, 나이, 성별 입력


- 리뷰 작성


    - 이름, 음식 카테고리, 메뉴, 가격, 내용, 별점 입력


- 리뷰 조회


    - 전체 리뷰


    - 평점 높은 순


    - 가격 낮은 순


    - 내가 쓴 리뷰


    - 연령대 별


    - 성별 별


    - 연령대 / 성별 별


    - 음식 카테고리 별


- 리뷰 수정


- 리뷰 삭제


### 📦 패키지 구조


![image](https://github.com/user-attachments/assets/c74c5413-2d8f-4555-8fe3-57a458d2e59f)


### 🎨 MVC 패턴 구조


![mvc2](https://github.com/user-attachments/assets/bba4c68c-3bde-405b-b4f6-5e7093a0a817)


## 🐞 트러블 슈팅


### 🔗 DB 연결


- MySQL 연결과정에서 권한 설정 문제


    - GRANT ALL PRIVILEGES ON TO 문법으로 권한 부여하여 해결


### 🚀 프로젝트 실행 과정


- Enum Type 사용으로 인한 문제 발생


    - Category, Gender를 위해 Enum Type 사용


    - name()을 통해 Enum의 값을 String으로 변환해서 사용


    - DB에 대소문자가 혼용되어 데이터가 삽입되어 있어, READ 시 문제 발생 -> 대문자로 통일하여 해결


- Scanner 클래스의 nextLine() 사용 시 문제 발생


    - 입력 시 개행 문자가 버퍼에 남아 다음 명령어 수행에 영향을 줌 -> nextLine()을 next()로 변경하여 해결


## 💡 고찰


### 🎯 기대효과


- **사용자 경험 향상**: 리뷰 관리 및 검색 과정을 간소화하여 효율성 제공


- **맛집 산업 지원**: 사용자 리뷰 데이터 활용으로 맛집 트렌드 파악 가능


- **데이터 활용 확대**: 리뷰 데이터의 체계적인 관리를 통해 다양한 서비스로 확장 가능


### ✍️ 느낀 점 및 배운 점


- 그동안 DB 연결 시 IDE와 같은 편리한 Tool을 사용해왔었는데 권한 부여부터 차근차근 단계를 밟아가는 경험을 했다. 인프라를 다루는 것이 쉽지 않다고 느꼈다.


- MVC 패턴의 역할에 맞춰 설계를 진행하고, 개발을 하며 유지보수와 코드의 재사용성을 높이는 경험을 했다.


## 📝 깃 커밋 규칙


- **feat**: 새로운 기능 추가


- **fix**: 버그 수정


- **docs**: 문서 수정


- **style**: 코드 스타일 수정 (공백, 세미콜론 등)


- **refactor**: 코드 리팩토링


- **test**: 테스트 추가 또는 수정


- **chore**: 기타 변경 (빌드 작업, 패키지 업데이트 등)


- **perf**: 성능 향상

