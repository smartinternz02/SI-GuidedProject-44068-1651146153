package com.externship.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "expense_id")
    int id;
    int amount;
    long user;
    @Column(columnDefinition = "Date")
    LocalDate date;
    int category;

    public String getDateForDatabase() {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
