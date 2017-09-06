package com.wangchang.greendao_android.db;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public interface DbHelper {

    void saveUser(User user);

    void deleteUser(User user);

    List<User> loadAll();

    void clear();

    User query(long id);

    void update(User user);

}
