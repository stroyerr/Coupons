package me.stroyer.coupons.coupons.Methods.Generate;

import me.stroyer.coupons.coupons.Methods.CouponObject;
import me.stroyer.coupons.coupons.Methods.FileManagement.LoadCoupons;
import me.stroyer.coupons.coupons.Methods.FileManagement.SaveCoupons;
import me.stroyer.coupons.coupons.Methods.Send;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class Coupons implements Serializable {

    public static List<CouponObject> coupons = new ArrayList<CouponObject>();

    /*
    On load, the build function is called to biuld the array from the hard save. After the first build, the list is saved as
    a safe guard using the save function.

    When an object is added to this array via the create method, the object is added to the array then executes
    the build save function, then the build function. This ensures that the object will be stored even if the server crashed.

    When an object is removed from the array using the remove function, the array is first rebuilt using
    the build function. The built array is then modified to remove the selected object, then is saved and rebuilt.
     */

    //Called to rebuild local temporary array from storage
    public static void build(){
        List<CouponObject> newList = LoadCoupons.load();
        coupons = newList;
        //getLogger().info( "Original obj1 name: " + coupons.get(0).name + ", new obj1 name: " + newList.get(0).name);
    }

    public static void save(){
        SaveCoupons.save();
    }

    public static void create(CouponObject c){
        getLogger().info("attempting add to list");
        coupons.add(c);
        save();
    }

    public static boolean delete(String name){
        build();
        for(int i = 0; i < coupons.size(); i ++){
            if(coupons.get(i).name.equals(name)){
                coupons.remove(i);
                save();
                build();
                return true;
            }
        }
        return false;
    }
}
