## ver 1.1.2 수정 내역
### 1.mapper.xml 수정
조건문을 이용하여 경우에 따라서 다른 쿼리가 실행될수 있도록 하였습니다.

### 2. DiaryActionBean, DiaryService, DiaryMapper 에서 여러 메소드들을 하나로 통합하였습니다.
searchCategoriedList, searchCategoriedCount 등을 하나의 메소드 getDiaryList, getDiaryCount로 합치고 변수에 따라서 1번의 수정사항과 같이 다른 쿼리가 작동되도록 하였습니다.

![img.png](img.png)
