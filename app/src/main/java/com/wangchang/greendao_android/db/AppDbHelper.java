package com.wangchang.greendao_android.db;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class AppDbHelper implements DbHelper {

    private final DaoSession daoSession;


    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        daoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public void saveUser(User user) {
        daoSession.getUserDao().insert(user);
    }

    @Override
    public void deleteUser(User user) {
        daoSession.getUserDao().delete(user);
    }

    @Override
    public List<User> loadAll() {
        return  daoSession.getUserDao().loadAll();
    }

    @Override
    public void clear() {
        daoSession.getUserDao().deleteAll();
    }

    @Override
    public User query(long id) {
        return daoSession.getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(id)).build().unique();
    }

    @Override
    public void update(User user) {
        daoSession.getUserDao().update(user);
    }
}
