package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list ; //데이터 관리 타입
    Scanner s = new Scanner(System.in) ;
    /*
    * => 난이도 (1,2,3) & 새 단어 입력 : 1 drive way
    * 뜻 입력 : 차고 진입로
    * 새 단어가 단어장에 추가되었습니다.
    * */

    //WordCRUD라는 생성자를 만들 때 객체화 하기 위한.
    WordCRUD(Scanner s){
        list = new ArrayList<>() ;
        this.s = s ;
    }
    @Override
    public Object add() {
        // 사용자에게 입력 받는 부분 구현
        System.out.print("=> 난이도 (1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt() ; //난이도 숫자 입력
        String word = s.nextLine() ; //영어 단어 입력 //입력 버퍼 엔터

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine() ;

        //(입력 받은 데이터 기준) word 라는 클래스 생성해거 객체로 return
        return new Word(0, level, word, meaning) ;
    }

    public void addWord(){
        // 리스트에 추가하는 부분 구현
        Word one = (Word)add() ; //객체 생성 return type이 object 이기 때문에 (Word)
        list.add(one) ; //리스트에 추가
        System.out.println("새 단어가 단어장에 추가되었습니다.");
    }

    @Override
    public int upgrade(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    /*<list All 하면 출력되는 화면>
    => 원하는 메뉴는?
    ----------------------------
    1 *** superintendent 관리자, 감독관
    2 *         electric 전기의, 전기를 생산하는
    3.**       equipment 장비, 용품
    * */
    public void listAlL(){
        System.out.println("----------------------------");
        for(int i=0 ; i<list.size() ; i++){
            // 수치는 i 의 숫자가 하나씩 증가
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("----------------------------");
    }
}
