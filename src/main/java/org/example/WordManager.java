package org.example;

import java.util.Scanner;

public class WordManager {
    //CRUD 기능을 구현하는 전체적인 관리 역할
    Scanner s = new Scanner(System.in) ;
    WordCRUD wordCRUD ;
    /*
    *** 영단어 마스터 ***
    *******************
    1. 모든 단어 보기
    2. 수준별 단어 보기
    3. 단어 검색
    4. 단어 추가
    5. 단어 수정
    6. 단어 삭제
    7. 파일 저장
    0. 나가기
    *******************
    => 원하는 메뉴는 ?
    * */
    WordManager(){
        wordCRUD = new WordCRUD(s) ;
    }
    public int selectMenu() {
        System.out.print("*** 영단어 마스터 ***\n" +
                "*******************\n" +
                "1. 모든 단어 보기\n" +
                "2. 수준별 단어 보기\n" +
                "3. 단어 검색\n" +
                "4. 단어 추가\n" +
                "5. 단어 수정\n" +
                "6. 단어 삭제\n" +
                "7. 파일 저장\n" +
                "0. 나가기\n" +
                "*******************\n" +
                "=> 원하는 메뉴는 ? ");
        return s.nextInt() ; //정수형태로 입력 받기
    }
    public void start(){
        while(true) {
            int menu = selectMenu();
            if(menu == 0) break ;
            if(menu == 4){
                // 메뉴 추가
                wordCRUD.addItem() ; // addWord 호출
            }
            else if(menu ==1){
                // 조회
                wordCRUD.listAlL() ;
            }
            else if(menu == 5){
                // 데이터 업데이트 기능
                wordCRUD.updateItem() ;
            }
            else if(menu == 6){
                // 데이터 삭제 기능
                wordCRUD.deleteItem() ;
            }
        }
    }

}
