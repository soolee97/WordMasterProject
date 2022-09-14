package org.example;

public class Word {
    private int id ;
    private int level ;
    private String word ;
    private String meaning ;

    // 생성자 constructor
    Word(){} ;

    public Word(int id, int level, String word, String meaning) {
        this.id = id;
        this.level = level;
        this.word = word;
        this.meaning = meaning;
    }

    //private 한 값에 접근하기 위해서 getter 와 setter를 지정해준다.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    //Word 클래스에 있는 정보들을 하나의 문장으로 나열하는 구조로 만들어보겠음
    //우클릭 -> generate -> overriding -> toString()
    /* < 출력 구성 화면 >
    * 1 *           electric 전기의, 전기를 생산하는
    * 2 *               pole 기둥, 장대
    * */
    @Override
    public String toString() { //화면에 출력할 때 일정한 포맷으로 출력하기 위한 함수
        String slevel = "" ; //난이도 별표로 출력
        for(int i=0; i<level ; i++) slevel += "*" ;

        // 원하는 format 의 문자열 만들기.
        String str = String.format("%-3s", slevel)
                + String.format("%15s", word) + "  " + meaning ;

        return str ;
    }

    public String toFileString(){ //데이터들을 파일에 저장할 때의 포맷을 저장해놓는 함수
        //return 값을 문자로 ! 이렇게 지정할 수 있구나..
        return this.level + "|" + this.word + "|" + this.meaning ;
    }


}
