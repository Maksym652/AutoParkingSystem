/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParkingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author 1
 */
public class Car_Controller {
    public DAO_Car dao = new DAO_Car();
    public boolean addVisit(String number, Date begin){
        Boolean result = false;
        try {
            if(dao.connection==null){
                dao.connection=dao.getConnection();
            }
            if(!dao.findById(number).isEmpty()) {
                PreparedStatement preparedStatement = dao.connection.prepareStatement("INSERT INTO Visits (`carNumber`, `beginTime`, `endTime`, `price`) VALUES (?, ?, ?, ?)");
                preparedStatement.setString(1, number);
                preparedStatement.setTime(2, new Time(begin.getTime()));
                preparedStatement.setTime(3, null);
                preparedStatement.setFloat(4,0);
                result = !preparedStatement.execute();
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean setEndVisit(String number, Date end, float price) {
        boolean result = false;
        try {
            if(dao.connection==null){
                dao.connection=dao.getConnection();
            }
            if(!dao.findById(number).isEmpty()) {
                PreparedStatement ps = dao.connection.prepareStatement("SELECT beginTime FROM Visits WHERE carNumber = ? AND endTime is null");
                ps.setString(1, number);
                ResultSet rs = ps.executeQuery();
                rs.next();
                if(new Date(rs.getTime(1).getTime()).after(end)){
                    return false;
                }
                PreparedStatement preparedStatement = dao.connection.prepareStatement("UPDATE Visits SET endTime = ?, price = ? WHERE carNumber = ? AND endTime is null");
                preparedStatement.setTime(1, new Time(end.getTime()));
                preparedStatement.setFloat(2, price);
                preparedStatement.setString(3,number);
                result = !preparedStatement.execute();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}