package com.bw.movie.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.bw.movie.bean.hot.ResultBean_hot;
import com.bw.movie.bean.reselea.ResultBean_reselea;
import com.bw.movie.bean.upcoming.ResultBean_upcoming;
import com.bw.movie.bean.movieinfo.ResultBean_movieinfo;

import com.bw.movie.dao.ResultBean_hotDao;
import com.bw.movie.dao.ResultBean_reseleaDao;
import com.bw.movie.dao.ResultBean_upcomingDao;
import com.bw.movie.dao.ResultBean_movieinfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig resultBean_hotDaoConfig;
    private final DaoConfig resultBean_reseleaDaoConfig;
    private final DaoConfig resultBean_upcomingDaoConfig;
    private final DaoConfig resultBean_movieinfoDaoConfig;

    private final ResultBean_hotDao resultBean_hotDao;
    private final ResultBean_reseleaDao resultBean_reseleaDao;
    private final ResultBean_upcomingDao resultBean_upcomingDao;
    private final ResultBean_movieinfoDao resultBean_movieinfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        resultBean_hotDaoConfig = daoConfigMap.get(ResultBean_hotDao.class).clone();
        resultBean_hotDaoConfig.initIdentityScope(type);

        resultBean_reseleaDaoConfig = daoConfigMap.get(ResultBean_reseleaDao.class).clone();
        resultBean_reseleaDaoConfig.initIdentityScope(type);

        resultBean_upcomingDaoConfig = daoConfigMap.get(ResultBean_upcomingDao.class).clone();
        resultBean_upcomingDaoConfig.initIdentityScope(type);

        resultBean_movieinfoDaoConfig = daoConfigMap.get(ResultBean_movieinfoDao.class).clone();
        resultBean_movieinfoDaoConfig.initIdentityScope(type);

        resultBean_hotDao = new ResultBean_hotDao(resultBean_hotDaoConfig, this);
        resultBean_reseleaDao = new ResultBean_reseleaDao(resultBean_reseleaDaoConfig, this);
        resultBean_upcomingDao = new ResultBean_upcomingDao(resultBean_upcomingDaoConfig, this);
        resultBean_movieinfoDao = new ResultBean_movieinfoDao(resultBean_movieinfoDaoConfig, this);

        registerDao(ResultBean_hot.class, resultBean_hotDao);
        registerDao(ResultBean_reselea.class, resultBean_reseleaDao);
        registerDao(ResultBean_upcoming.class, resultBean_upcomingDao);
        registerDao(ResultBean_movieinfo.class, resultBean_movieinfoDao);
    }
    
    public void clear() {
        resultBean_hotDaoConfig.clearIdentityScope();
        resultBean_reseleaDaoConfig.clearIdentityScope();
        resultBean_upcomingDaoConfig.clearIdentityScope();
        resultBean_movieinfoDaoConfig.clearIdentityScope();
    }

    public ResultBean_hotDao getResultBean_hotDao() {
        return resultBean_hotDao;
    }

    public ResultBean_reseleaDao getResultBean_reseleaDao() {
        return resultBean_reseleaDao;
    }

    public ResultBean_upcomingDao getResultBean_upcomingDao() {
        return resultBean_upcomingDao;
    }

    public ResultBean_movieinfoDao getResultBean_movieinfoDao() {
        return resultBean_movieinfoDao;
    }

}
