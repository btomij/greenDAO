package com.example.joelin.greendao001.Model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by joelin on 2017/9/16.
 */

//greenDAO step 5 create new model and "Make project"
//Entity is a class persisted in the database. An entity contains properties, which are mapped to database columns.
@Entity(indexes = {
        @Index(value = "text, date DESC", unique = true)
})
public class Note {

    @Id
    private Long id;

    @NotNull
    private String text;
    private Date date;
    private String user;
@Generated(hash = 401779376)
public Note(Long id, @NotNull String text, Date date, String user) {
    this.id = id;
    this.text = text;
    this.date = date;
    this.user = user;
}
@Generated(hash = 1272611929)
public Note() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public String getText() {
    return this.text;
}
public void setText(String text) {
    this.text = text;
}
public Date getDate() {
    return this.date;
}
public void setDate(Date date) {
    this.date = date;
}
public String getUser() {
    return this.user;
}
public void setUser(String user) {
    this.user = user;
}
}
