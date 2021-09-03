package me.stroyer.coupons.coupons.Methods.FileManagement;

import me.stroyer.coupons.coupons.Methods.CouponObject;
import me.stroyer.coupons.coupons.Methods.Generate.Coupons;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveCoupons {

    public static List<CouponObject> coupons = new ArrayList<CouponObject>();

    static Path path = Paths.get("./plugins/Coupons");

    public static void save(){
        coupons = Coupons.coupons;
        try {
            FileOutputStream fOut = new FileOutputStream("./plugins/Coupons/data.txt");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(coupons);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
