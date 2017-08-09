/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacodingtest_chris_romanyk;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Flight  {

    private String Destination;
    private String Origin;
    private Date DepartureTime;
    private Date DestinationTime;
    private Double Price;

    /**
     * @return the Destination
     */
    public String getDestination() {
        return Destination;
    }

    /**
     * @param Destination the Destination to set
     */
    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    /**
     * @return the Origin
     */
    public String getOrigin() {
        return Origin;
    }

    /**
     * @param Origin the Origin to set
     */
    public void setOrigin(String Origin) {
        this.Origin = Origin;
    }

    /**
     * @return the DepartureTime
     */
    public Date getDepartureTime() {
        return DepartureTime;
    }

    /**
     * @param DepartureTime the DepartureTime to set
     */
    public void setDepartureTime(Date DepartureTime) {
        this.DepartureTime = DepartureTime;
    }

    /**
     * @return the DestinationTime
     */
    public Date getDestinationTime() {
        return DestinationTime;
    }

    /**
     * @param DestinationTime the DestinationTime to set
     */
    public void setDestinationTime(Date DestinationTime) {
        this.DestinationTime = DestinationTime;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return Price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.Price = price;
    }
// overide allows set to remove dups 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.Destination);
        hash = 83 * hash + Objects.hashCode(this.Origin);
        hash = 83 * hash + Objects.hashCode(this.DepartureTime);
        hash = 83 * hash + Objects.hashCode(this.DestinationTime);
        hash = 83 * hash + Objects.hashCode(this.Price);
        return hash;
    } 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Flight other = (Flight) obj;
        if (!Objects.equals(this.Destination, other.Destination)) {
            return false;
        }
        if (!Objects.equals(this.Origin, other.Origin)) {
            return false;
        }
        if (!Objects.equals(this.DepartureTime, other.DepartureTime)) {
            return false;
        }
        if (!Objects.equals(this.DestinationTime, other.DestinationTime)) {
            return false;
        }
        if (!Objects.equals(this.Price, other.Price)) {
            return false;
        }
        return true;
    }
     @Override
    public String toString() {
        SimpleDateFormat slashDates = new SimpleDateFormat("MM/d/yyyy k:mm:ss");
         DecimalFormat df = new DecimalFormat("#0.00");
        return this.Origin + " --> " +this.Destination + " ("+slashDates.format(this.DepartureTime) +" --> "+slashDates.format(this.DestinationTime) +") - $"+df.format(this.Price);
    }

    
  
    
}
