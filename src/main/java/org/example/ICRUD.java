package org.example;

public interface ICRUD {
    public Object add() ; //데이터 추가
    public int upgrade(Object obj) ; //데이터 수정
    public int delete(Object obj) ; //데이터 삭제
    public void selectOne(int id) ; //데이터 하나 조회
}
