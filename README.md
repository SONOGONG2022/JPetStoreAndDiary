## ver 1.2.0 수정 내역
1. 파일 삭제 로직을 구현했습니다.
2. insertDiary시에 mabatis 기능을 이용하여 방금 insert된 게시글을 가지고 올 수 있도록 하였습니다.
3. getDiaryListByUserid sql구문이 예전에 작동이 안되던 sql이어서 수정하였습니다.
4. insertDiary 이후에 page2 변수가 초기화가 잘못되어있어서 userid기준으로 작성한 게시글이 불러와지지 않는 오류가 있었습니다. 그래서 clear()메소드에서 paging2와 관련된 변수들도 초기화 할 수 있도록 하였습니다.