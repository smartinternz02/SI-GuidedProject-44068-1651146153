package com.externship.expensetracker.util;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
public class Expenses {

    @Id
    String email;
    int amount;
    LocalDate date;
    Category category;

    public String getDateForDatabase() {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
