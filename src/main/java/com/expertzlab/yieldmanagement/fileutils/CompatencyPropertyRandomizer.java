package com.expertzlab.yieldmanagement.fileutils;

import com.expertzlab.yieldmanagement.models.CompetantProperty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by expertzlab on 9/6/17.
 */
public class CompatencyPropertyRandomizer {
    int pos1;
    int pos2;

    int recordcount =100;
    long lastId = 0;

    public CompatencyPropertyRandomizer(Connection con ) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(id) from CompetantProperty");
        while (res.next()){
            lastId = res.getLong(1);
        }
    }
    public List getRandomizedList(List list) {
        List l1 = new ArrayList(recordcount);

        for (long i = lastId+1; i <= recordcount; i++) {


            Random r = new Random();
            pos1 = r.nextInt(list.size());
            CompetantProperty p1 = (CompetantProperty) list.get(pos1);
            pos2 = r.nextInt(list.size());
            CompetantProperty p2 = (CompetantProperty) list.get(pos2);
            CompetantProperty p3 = new CompetantProperty();
            p3.setId((int)i);
            p3.setName(p1.getName() + " " + p2.getName() + r.nextInt(((int)(recordcount+lastId))));
            p3.setRegion(pos1 > pos2 ? p1.getRegion() : p2.getRegion());
            int rndNumer = r.nextInt(9999);
            p3.setRegion(p3.getRegion()+ " HN#"+rndNumer );
            p3.setPrice(pos1 > pos2 ? p1.getPrice() : p2.getPrice());

            Float price = p3.getPrice();
            //if(p3.getPrice() != null) {
                //p3.setPrice(price.subString(0, price.length() - ("" + rndNumer).length()-1) + rndNumer);
           // }
            l1.add(p3);
        }

        return l1;
    }
}

