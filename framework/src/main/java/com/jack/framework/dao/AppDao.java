package com.jack.framework.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/11 16:36
 * @E-Mail: 528489389@qq.com
 * <p>
 * 测试的数据库dao
 */
@Entity
public class AppDao {
    @Id(autoincrement = true)
    private Long id;

    @Generated(hash = 2084329568)
    public AppDao(Long id) {
        this.id = id;
    }

    @Generated(hash = 936141168)
    public AppDao() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
