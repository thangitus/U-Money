package com.itus.u_money.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity
public class Bill {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public long amount;

    public int typeId;

    public String note;

    public Date startDate;

    public String loopType; // DAY | WEEK | MONTH | YEAR

    public int interval;    // Default is 1

    /*
        ONLY CHECK THIS FIELD WHEN `loopType` equals 'WEEK'
        Ex: weekDaysList = {1,2,3,4,5,6,7}
        + 1 : sunday
        + 2..7 : monday..saturday
     */
    public List<Integer> weekDaysList;

    /*
        CHECKING `loopNumber` before `endDate`
        + loopNumber == 0 : this bill doesn't loop. No need to check `endDate` field
        + loopNumber == -1 : disable this field. Lets check `endDate` field
        + loopNumber > 0 : loopNumber's value is loop times before end
     */
    public int loopNumber;

    /*
        CHECKING THIS FIELD AFTER `loopNumber` (if necessary)
        + endDate == null: this bill loops forever
        + endDate != null: date this bill stops looping anymore
     */
    public Date endDate;
}
