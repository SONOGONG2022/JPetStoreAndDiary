## ver 1.0.0 수정 내역

1. 페이징 처리
    + DiaryActionBean에 변수 및 메소드가 추가되었습니다.
      ```
        class DiaryActionBean {
          //paging 처리
          private int totalCount;
          private int beginPage;
          private int endPage;
          private boolean next;
          private boolean prev;    
          ...
      
          public void paging() {
            //Diary 총 갯수를 가져와서 몇개의 페이지를 표시할지 나타낸다
            totalCount = diaryService.getDiaryCount();
            endPage = ((int)Math.ceil(page / (double)10)) * 10;

            beginPage = endPage - (10 - 1);

            int totalPage = (int)Math.ceil(totalCount / (double)6);

            if (totalPage < endPage) {
              endPage = totalPage;
            } else {
              next = true;  
            }
            prev = beginPage!=1;
        }
        ```
    + DiaryActionBean에서 viewDiary() 메소드가 실행될 때 자동으로 paging() 메소드가 실행됩니다.
      ```
      public ForwardResolution viewDiaryBoard(){
        paging();
        diaryList=diaryService.getDiaryList(page);
        return new ForwardResolution(VIEW_PET_DIARY_BOARD);
      }
      ```
    + DiaryService와 DiaryMapper에 getDiaryCount()메소드가 추가되었습니다. 해당 메소드는 작성된 Diary의 갯수를 반환합니다.


2. 파일 업로드
   + pom.xml에 Dependency가 추가되었습니다.
      ```
     <!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
      <dependency>
         <groupId>commons-fileupload</groupId>
         <artifactId>commons-fileupload</artifactId>
         <version>1.4</version>
      </dependency>
     ```
   + application.properties 에 static 경로(이미지 저장 경로)설정 부분이 추가됩니다.
      ```
      spring.mvc.static-path-pattern=/static/**
      spring.resources.static-locations=classpath:/static/
      spring.resources.add-mappings=true
     ```
   + DiaryActionBean 에 업로드된 이미지를 받을 FileBean 변수가 추가됩니다.
   + insertDiary() 메소드에서 로그인을 확인합니다
   + DiaryActionBean에 isAuthenticated() 로그인을 확인하는 메소드가 추가되었습니다.
   + src/main/webapp/static 폴더에 업로드 된 이미지가 저장됩니다. 현재는 주석처리 되어있으며 테스트시에는 해당 폴더내의 default.png파일을 불러오도록 설정되어있습니다.
   
    
3.Diary 도메인 클래스의 수정
   + setUserid(), setCategoryid(), setTitle(), setContent() 메소드는 insertDiary()할 때 꼭 필요한 항목들이므로 @Validate를 이용하여 꼭 세팅될 수 있도록 하였습니다.
   + 추후 해당 내용과 관련하여 update할 때의 메소드 역시 on = {} 내부에 추가하도록 합니다.

4.한글 깨짐 해결
+ web.xml에 인코딩 필터를 추가하였습니다.


..그 외 자잘한 로직들이 조금씩 수정되었습니다.
현재 가능한 기능은 insert와 getDiaryList입니다! 상세 페이지를 만들고 나머지 기능 붙이는거 진행하겠습니다.