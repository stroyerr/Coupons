package me.stroyer.coupons.coupons.Methods.Generate;

import me.stroyer.coupons.coupons.Methods.CouponObject;

import java.util.ArrayList;
import java.util.List;

public class Coupons {

    public List<CouponObject> coupons = new ArrayList<CouponObject>();

    /*
    On load, the build function is called to biuld the array from the hard save. After the first build, the list is saved as
    a safe guard using the save function.

    When an object is added to this array via the create method, the object is added to the array then executes
    the build save function, then the build function. This ensures that the object will be stored even if the server crashed.

    When an object is removed from the array using the remove function, the array is first rebuilt using
    the build function. The built array is then modified to remove the selected object, then is saved and rebuilt.
     */

    public static void create(CouponObject c){

    }
}
