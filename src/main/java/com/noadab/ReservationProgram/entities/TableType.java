package com.noadab.ReservationProgram.entities;

public enum TableType {

    COUPLE (2),
    TRIPLE(3),
    QUARTET(4);

    private final int dinerNum;
    TableType(int dinersNum){
        this.dinerNum=dinersNum;
    }

    public static TableType valueOfDinerNum (int dinerNum){
        for (TableType tableType: values()){
            if (tableType.dinerNum == dinerNum){
                return tableType;
            }
        }
        return null;
    }

    public int getDinersNum(){
        return dinerNum;
    }


}
