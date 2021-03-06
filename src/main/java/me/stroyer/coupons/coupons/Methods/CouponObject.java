package me.stroyer.coupons.coupons.Methods;

import java.io.Serializable;

public class CouponObject implements Serializable {

    public String command;
    public String type;
    public String name;

    public CouponObject(String command, String name, int type){
        this.command = command;
        this.name = name;
        int typeInt = type;
        if(typeInt == 0){
            this.type = "CONSOLE";
        } else if (typeInt == 1) {
            this.type = "PLAYER";
        }
    }
}
