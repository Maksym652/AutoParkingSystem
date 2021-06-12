/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParkingSystem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Visit {
    private Date begin;
    private Date end;
    float price;
    public Date getBegin()
    {
        return begin;
    }
    public Date getEnd()
    {//якщо end==null, то цей інтервал часу ще триває - вертаємо поточну дату
        if(end==null) return new Date();
        return end;
    }
    public void setEnd(Date end, float price) {
        this.end = end;
        this.price = price;
    }
    public void setEnd(float price) {
        this.end = new Date();
        this.price = price;
    }
    public void setPrice(float price){
        this.price = price;
    }
    public float getPrice(){
        return this.price;
    }

    public Visit(Date begin, Date end, float price)
    {
        this.begin=begin;
        this.end=end;
        this.price = price;
    }

    public Visit(Date begin, Date end)
    {
        this.begin=begin;
        this.end=end;
    }
    
    public boolean includes(Visit interval)
    {
        return (this.getBegin().before(interval.getBegin()) || this.getBegin().equals(interval.getBegin())) && (this.getEnd().after(interval.getEnd()) || this.getEnd().equals(interval.getEnd()));
    }

    public long getDurationInHours()
    {
        if(this.end==null)
            return (new Date().getTime() - this.begin.getTime())/3600000;
        return (this.end.getTime() - this.begin.getTime())/3600000;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("[dd.MM.yy HH:mm]");
        if(end==null)
        {
            return  "\t З " + df.format(begin) +
                    " ДО " + df.format(new Date());
        }
        return  "\t З " + df.format(begin) +
                " ДО " + df.format(end);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Visit that = (Visit) o;
        return begin.equals(that.begin) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end);
    }
}
