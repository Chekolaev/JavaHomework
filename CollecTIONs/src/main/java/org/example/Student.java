package org.example;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Student  {

    private int _ID;
    private String _Name, _Surname, _Patronymic;
    private LocalDate _DateBirth;
    private Float _Mean;

    Student(int ID,String Name,String Surname,String Patronymic, LocalDate DateBirth, float Mean){
        _ID = ID; _Name = Name; _Surname = Surname; _Patronymic = Patronymic; _DateBirth = DateBirth; _Mean = Mean;
    }

    public String PrintInfo(){
        return this._Surname + " " + this._Name + " " + this._Patronymic + " Birthday: " + this._DateBirth;
    }

    public boolean _SetID(int newID){
        if(newID>0) {
            _ID = newID;
            return true;
        }
        return false;
    }

    public int _GetAge(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow_format = formatForDateNow.format(dateNow);

        SimpleDateFormat nowDate = new SimpleDateFormat("dd");
        SimpleDateFormat nowMonth = new SimpleDateFormat("MM");
        SimpleDateFormat nowYear = new SimpleDateFormat("yyyy");

        String now_date = nowDate.format(dateNow);
        String now_month = nowMonth.format(dateNow);
        String now_year = nowYear.format(dateNow);

        int actual = calculateAge(_DateBirth, LocalDate.of(Integer.parseInt(now_year), Integer.parseInt(now_month), Integer.parseInt(now_date)));
        return actual;
    }

    private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}

