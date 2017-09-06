package com.wangchang.greendao_android.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wangchang on 2017/9/5.
 */
@Entity(nameInDb = "user")
public class User {

    @Id(autoincrement = true)
    private long id;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "age")
    private int age;

    @Property(nameInDb = "desc")
    private String desc;

    @Property(nameInDb = "sex")
    private String sex;

    @Property(nameInDb = "score")
    private float score;

    @Generated(hash = 1361895593)
    public User(long id, String name, int age, String desc, String sex,
            float score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.desc = desc;
        this.sex = sex;
        this.score = score;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public float getScore() {
        return this.score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
