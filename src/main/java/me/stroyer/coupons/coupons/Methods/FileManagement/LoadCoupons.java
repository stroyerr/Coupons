package me.stroyer.coupons.coupons.Methods.FileManagement;

import me.stroyer.coupons.coupons.Methods.CouponObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LoadCoupons {
    static List<CouponObject> coupons = new ArrayList<CouponObject>();
    public static List<CouponObject> load(){
        try {
            FileInputStream fIn = new FileInputStream("./plugins/Coupons/data.txt");
            ObjectInputStream oIn = new ObjectInputStream(fIn);

            coupons = (List<CouponObject>) oIn.readObject();
            return coupons;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return coupons;
    }
}
