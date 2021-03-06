package com.expertzlab.yieldmanagement.fileutils.propertymanager;

import com.expertzlab.yieldmanagement.fileutils.CountConfig;
import com.expertzlab.yieldmanagement.models.PropertyManager;

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
public class PropertyManagerRandomizer {
    int pos1;
    int pos2;
    String pmIdGen;

    int recordcount = CountConfig.PROPERTY_MANAGER_COUNT;
    long lastId = 0;

    public PropertyManagerRandomizer(Connection con ) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("Select max(id) from property_manager");
        while (res.next()){
            lastId = res.getLong(1);
        }
    }
    public List getRandomizedList(List list) {
        List l1 = new ArrayList(recordcount);

        for (long i = lastId+1; i <= recordcount; i++) {

            Random r = new Random();
            pos1 = r.nextInt(list.size());
            if(pos1 == 0) pos1++;
            PropertyManager p1 = (PropertyManager) list.get(pos1);
            pos2 = r.nextInt(list.size());
            if(pos2 == 0) pos2 ++;
            PropertyManager p2 = (PropertyManager) list.get(pos2);
            if(p1 == null || p2 == null){
                throw new RuntimeException("Not enough value in the list");
            }
            PropertyManager p3 = new PropertyManager();
            //pmIdGen = "PM" + i;
            p3.setManagerId((int)i);
            p3.setName(p1.getName() + " " + p2.getName() + r.nextInt(((int)(recordcount+lastId))));
            p3.setRegion(pos1 > pos2 ? p1.getRegion() : p2.getRegion());
            int rndNumer = r.nextInt(99999);
            p3.setRegion(p3.getRegion()+rndNumer );
            p3.setContact(pos1 > pos2 ? p1.getContact() : p2.getContact());

            String contact = p3.getContact();
            if(p3.getContact() != null) {
                p3.setContact(contact.substring(0, contact.length() - ("" + rndNumer).length()-1) + rndNumer);
            }
            l1.add(p3);
        }

        return l1;
    }
}