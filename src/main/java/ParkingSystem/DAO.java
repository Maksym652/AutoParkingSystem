/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParkingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DAO <T> {
    default Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection result=DriverManager.getConnection("jdbc:sqlite:D://МАКСИМ//Навчання//2020-2021//ІІ семестр//ООП//ParkingDataBase.db");
        return result;
    }
    T create(T entity);
    boolean update(T entity);
    boolean delete(String id);
    List<T> find(T entity);
    default List<T> findById(String id){
        List<T> result=new ArrayList<>();
        return result;
    }
    List<T> findAll();
}