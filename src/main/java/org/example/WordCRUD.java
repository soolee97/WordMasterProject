package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list ; //데이터 관리 타입
    Scanner s = new Scanner(System.in) ;
    final String fname = "Dictionary.txt" ; //수정하지 않을 것이기 때문에 'final'로 지정

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

    public void addItem(){
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

    //오버로딩으로 키워드를 입력받는 listAll 함수 생성
    public ArrayList<Integer> listAll(String keyword){ // 어레이 리스트로 반환
        ArrayList<Integer> idlist = new ArrayList<>() ;
        int j = 0 ;
        System.out.println("----------------------------");
        for(int i=0 ; i<list.size() ; i++){
            String word = list.get(i).getWord() ;
            if(!word.contains(keyword)) {
                continue ; // 입력받은 키워드가 word 리스트 안에 없으면 아래 실행 x
            }
            // 수치는 i 의 숫자가 하나씩 증가
            System.out.print((j+1) + " "); //i 가 아니라 j (키워드가 포함 되어 있을 때에만 이 코드가 실행되기 때문에)
            System.out.println(list.get(i).toString());
            idlist.add(i) ; //해당하는 키워드가 포함 되어 있으면 idlist에 추가
            j ++ ;
        }
        System.out.println("----------------------------");
        return idlist ;
    }

    public void updateItem() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next() ; //공백을 입력하지 않게 하기 위함.

        ArrayList<Integer> idlist = this.listAll(keyword) ; //현재 idlist 는 입력받은 키워드가 포함된 단어들의 값만 있음

        System.out.print("=> 수정할 번호 검색 : ");
        int id = s.nextInt() ;
        s.nextLine() ; //id 를 입력하고 나서 엔터 버퍼 입력받는 공간

        System.out.print("=> 뜻 입력 : ") ;
        String meaning = s.nextLine() ;

        Word word = list.get(idlist.get(id-1)) ; //사용자가 입력한 아이디
        //객체의 뜻 바꿔주기
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다.");
    }


    public void deleteItem() {
        /*update item 과 코드가 유사하다*/
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = s.next() ; //공백을 입력하지 않게 하기 위함.

        ArrayList<Integer> idlist = this.listAll(keyword) ; //현재 idlist 는 입력받은 키워드가 포함된 단어들의 값만 있음

        System.out.print("=> 삭제할 번호 검색 : ");
        int id = s.nextInt() ;
        s.nextLine() ; //id 를 입력하고 나서 엔터 버퍼 입력받는 공간

        System.out.print("=> 정말로 삭제하실래요? (Y/n) ") ;
        String ans = s.next() ;
        if(ans.equalsIgnoreCase("Y")){//equalsIgnoreCase -> 대소문자 구분 안함
            list.remove((int)idlist.get(id-1)) ; //데이터 삭제(번호-1)
            System.out.println("단어가 삭제 되었습니다.");
        } else{
            System.out.println("취소 되었습니다.");
        }
    }

    public void loadFile()  { // Dictionary.txt를 불러오는 함수 -> 호출 -> 프로그램이 구동되기 전에 호출 -> select menu 에서 메뉴를 보여주기 전에 호출
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname)) ;
            String line ;
            int count = 0 ;  // 데이터에 있는 단어의 갯수를 세는 변수

            while(true){ //반복문이 종료 되는 시점 잘 보기 ! 중요함.
                /*한줄 씩 읽어오기*/
                line = br.readLine() ; //한줄씩 읽어온다 readLine()
                if(line == null) break ;

                /*'바' 를 기준으로 쪼개기*/
                String data[] = line.split("\\|") ;

                //1. '|'는 원래 문자로 인식이 되질 않는다. 그래서 앞에 '\\'를 붙여 줘야 문자로 인식을 해서 구분한다.
                //2. dictionary.txt 내에 데이터가 많기 때문에,

                /* 쪼갠 값들을 임의의 변수에 type에 맞게 저장 */
                int level = Integer.parseInt(data[0]) ; //저장된 데이터의 가장 첫번째 숫자는 '난이도'임.
                                                        // 근데 저장된 데이터는 문자열이기 때문에 데이터를 숫자로 바꿔줘야함.
                String word = data[1] ; //단어
                String meaning = data[2] ; //뜻

                /*임의의 변수에 저장된 데이터들을 원래 '리스트'에 저장해야한다. */
                //Word에 생성자 활용 !! -> project 메뉴에 Word 에서 constructon 보기
                list.add(new Word(0, level, word, meaning)) ;
                count ++ ;

            }
            br.close() ;
            /*데이터를 로딩을 잘 하고 파일이 닫혔으면 데이터 로딩 완료라는 문구가 뜨게 하면된다. */
            System.out.println("==> "+ count + "개 단어 로딩 완료!!!") ;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile() { //수정한 파일을 저장하는 함수

    }
}
