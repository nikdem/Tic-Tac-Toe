package com.hexlet;

import java.util.Scanner;

public class Field {

    private static final int FIELD_SIZE=3;
    private static final char DEFAULT_CELL_VALUE=' ';
    private static final int MAX_CELL_VALUE=3;
    private static final int MIN_CELL_VALUE=1;
    private static final int EXIT_CONDITION=MAX_CELL_VALUE*MAX_CELL_VALUE;

    private char[][] items = new char[FIELD_SIZE][FIELD_SIZE];

    public void fieldCreate(){
        for (int i = 0; i < FIELD_SIZE; i++){
            lineCreate (i);
        }
    }

    private void lineCreate(int lineNumber){
        for (int i=0; i< FIELD_SIZE; i++){
            items[i][lineNumber]=DEFAULT_CELL_VALUE;
        }
    }

    public void showField(){
        for (int i = 0; i < FIELD_SIZE; i++){
            showLine(i);
            System.out.println();
        }
    }

    private void showLine(int lineNumber){
        for (int i = 0; i < FIELD_SIZE; i++){
            System.out.print("[" + items[i][lineNumber] + "]");
        }
    }

    /*
    * игра просит вводить 2 значения по X и по Y
    * x=[1..3]
    * y=[1..3]
    * по полю значения распределены так:
    *      1   2   3  = x
    *  1  [ ] [ ] [ ]
    *  2  [ ] [ ] [ ]
    *  3  [ ] [ ] [ ]
    *  =
    *  y
    *  !!!х и у обязательно вводятся от 1 до 3, при остольных значениях!!!
    *  !!!или повторных значениях будет выдана ошибка программой!!!
    * */

    public void getCells(){

        Scanner cellInput = new Scanner(System.in);
        Scanner lineInput = new Scanner(System.in);

        boolean exit = false;

        for (int i=0;!exit; i++){
            System.out.print("x=");
            int cellNumber = cellInput.nextInt();
            System.out.print("y=");
            int lineNumber = lineInput.nextInt();

            if (!valuesCheck(cellNumber, lineNumber)){
                System.out.println("wrong cell or line value");
                i--;
                continue;
            }


            if (i % 2 == 0) items[cellNumber-1][lineNumber-1]='x';
            else  items[cellNumber-1][lineNumber-1]='o';

            showField();

            exit = winCheck();
            if (i==EXIT_CONDITION-1) exit = true;
        }

    }

    private boolean valuesCheck(int cellValue, int lineValue){
        return (cellValueCheck(cellValue) && lineValueCheck(lineValue) && isFreeCell(cellValue, lineValue));
    }

    private boolean isFreeCell(int cell, int line){
        return items[cell-1][line-1] == ' ';
    }

    private boolean cellValueCheck(int cellValue){
        return (cellValue <= MAX_CELL_VALUE && cellValue >= MIN_CELL_VALUE);
    }

    private boolean lineValueCheck(int lineValue){
        return (lineValue <= MAX_CELL_VALUE && lineValue >= MIN_CELL_VALUE);
    }

    /*
    * пока что не придумал как создать "универсальную" победу
    * для полей >3*3, и значения в ручную вводятся, аля items[i][j]==items[i+1][j]) && (items[i][j]==items[i+2][j]
    * может кто подкинет идею с решением этого
    * */

    private boolean winCheck(){

    if (horizontalWinCheck()){
    return true;
    }else if (verticalWinCheck()){
    return true;
    }else if (diagonalWinCheck()){
    return true;
    }else if (reversDiagonalWinCheck()){
    return true;
    }else return false;
    }

    private boolean horizontalWinCheck(){
        int i=MIN_CELL_VALUE-1;
        for (int j=0; j<FIELD_SIZE; j++){
            if ( (items[i][j] != ' ') && (items[i][j]==items[i+1][j]) && (items[i][j]==items[i+2][j]) ){
                if (items[i][j]=='x') {
                    System.out.println("Win 1st player!");
                }else{
                    System.out.println("Win 2nd player!");
                }
                return true;
            }
        }
        return false;
    }

    private boolean verticalWinCheck(){
        int j=MIN_CELL_VALUE-1;
        for (int i=0; i<FIELD_SIZE; i++){
            if ( (items[i][j] != ' ') && (items[i][j]==items[i][j+1]) && (items[i][j]==items[i][j+2]) ){
                if (items[i][j]=='x') {
                    System.out.println("Win 1st player!");
                }else{
                    System.out.println("Win 2nd player!");
                }
                return true;
            }
        }
        return false;
    }

    private boolean diagonalWinCheck(){
        int i=MIN_CELL_VALUE-1, j=MIN_CELL_VALUE-1;
        if ((items[i][j] != ' ') && (items[i][j]==items[i+1][j+1]) && (items[i][j]==items[i+2][j+2]) ){
            if (items[i][j]=='x'){
                System.out.println("Win 1st player!");
            }else{
                System.out.println("Win 2nd player!");
            }
            return true;
        }
        return false;
    }

    private boolean reversDiagonalWinCheck(){
        int i=MAX_CELL_VALUE-1, j=MIN_CELL_VALUE-1;
        if ((items[i][j] != ' ') && (items[i][j]==items[i-1][j+1]) && (items[i][j]==items[i-2][j+2]) ){
            if (items[i][j]=='x'){
                System.out.println("Win 1st player!");
            }else{
                System.out.println("Win 2nd player!");
            }
            return true;
        }
        return false;
    }

}
