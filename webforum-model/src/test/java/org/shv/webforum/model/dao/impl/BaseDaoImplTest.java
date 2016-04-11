/**
 * This project is a simple web forum. I created it just to
 * demonstrate my programming skills to potential employers.
 *
 * Here is short description: ( for more detailed description please reade README.md or
 * go to https://github.com/VladimirSharapov/SpringWebForum )
 *
 * Front-end: jsp, bootstrap, jquery
 * Back-end: Spring, Hibernate
 * DB: MySQL and H2(for testing) were used while developing, but the project is database independent.
 *     Though it must be a relational DB.
 * Tools: git,maven,jenkins,nexus,liquibase.
 *
 * My LinkedIn profile: https://ru.linkedin.com/in/vladimir-sharapov-6075207
 */
package org.shv.webforum.model.dao.impl;

import org.junit.*;
import org.shv.webforum.common.BaseEntity;
import org.shv.webforum.common.Crud;
import org.shv.webforum.model.util.JpaEntityState;
import org.shv.webforum.model.util.PersistedObjectsFactory;

import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Base class which provides testing methods for crud operations and for constraint violations,
 * to avoid writing the same code in all tests for DAO classes.
 * Extend this class and override necessary abstract methods, which are:
 *
 * {@link org.shv.webforum.model.dao.impl.BaseDaoImplTest#getDao}
 * {@link org.shv.webforum.model.dao.impl.BaseDaoImplTest#changeEntity}
 * {@link org.shv.webforum.model.dao.impl.BaseDaoImplTest#fillParameters}
 *
 * @author Vladimir Sharapov
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration("classpath:/applicationContext-dao.xml")
@Transactional
public abstract class BaseDaoImplTest<T extends BaseEntity> {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Rule
    public TestName name = new TestName();

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private Class<T> entityClass;

    List<BaseEntity> entityParamList = new ArrayList<>();

    @Before
    public void setUp(){
        System.out.println(name.getMethodName());
        session = sessionFactory.getCurrentSession();
        entityClass = getDao().getType();
        PersistedObjectsFactory.setSession(session);
    }

    protected Session session() {
        return session;
    }

    public void flushAndClear() {
        session().flush();
        session().clear();
    }

    @Test
    public void testSave() {
        BaseEntity expectedEntity = createEntityWithState(JpaEntityState.TRANSIENT);
        getDao().saveOrUpdate((T)expectedEntity);
        flushAndClear();

        BaseEntity actualEntity = session().get(entityClass, expectedEntity.getId());
        assertReflectionEquals(expectedEntity,actualEntity);
    }

    @Test
    public void testGet() {
        BaseEntity expectedEntity = createEntityWithState(JpaEntityState.DETACHED);

        BaseEntity actualEntity = getDao().get(expectedEntity.getId());

        assertReflectionEquals(expectedEntity,actualEntity);
    }

    @Test
    public void testUpdate() {
        BaseEntity expectedEntity = createEntityWithState(JpaEntityState.DETACHED);

        changeEntity((T)expectedEntity);
        getDao().saveOrUpdate((T) expectedEntity);
        flushAndClear();

        BaseEntity actualEntity = session().get(entityClass, expectedEntity.getId());

        assertReflectionEquals(expectedEntity, actualEntity);
    }

    @Test
    public void testDeleteById() {
        BaseEntity expectedEntity = createEntityWithState(JpaEntityState.DETACHED);

        getDao().delete(expectedEntity.getId());
        flushAndClear();

        BaseEntity actualEntity = session().get(entityClass, expectedEntity.getId());

        assertNull(actualEntity);
    }

    @Test
    public void testDelete() {
        BaseEntity expectedEntity = createEntityWithState(JpaEntityState.DETACHED);

        getDao().delete((T)expectedEntity);
        flushAndClear();

        BaseEntity actualEntity = session().get(entityClass, expectedEntity.getId());

        assertNull(actualEntity);
    }

    @Test
    public void testConstraintViolation() {
        boolean isThrown = false;
        fillParameters();
        for(int i=0; i < entityParamList.size(); i++) {
            isThrown = false;
            BaseEntity paramEntity = entityParamList.get(i);

            try {
                getDao().saveOrUpdate((T) paramEntity);
                session().flush();
            } catch (ConstraintViolationException ex) {
                isThrown = true;
            }
            assertTrue(String.format("Constraint violation exception must be thrown for entity [%d], but it is not",i),isThrown);
        }
    }

    /**
     * @return constructed entity added to test parameters list
     */
    protected T entity() {
        BaseEntity entity = createEntityWithState(JpaEntityState.TRANSIENT);
        entityParamList.add(entity);
        return (T) entity;
    }

    /**
     * Returns domain object that was created, in certain state {@link org.shv.webforum.model.util.JpaEntityState}
     *
     * @param  jpaEntityState  returned domain object has this entityState
     * @return domain object in one of the states: {@link org.shv.webforum.model.util.JpaEntityState}
     */
    protected  BaseEntity createEntityWithState(JpaEntityState jpaEntityState)  {
        try {
            Method method = PersistedObjectsFactory.class.getMethod("create" + entityClass.getSimpleName(), JpaEntityState.class);
            return (BaseEntity) method.invoke(null, jpaEntityState);
        } catch (Exception ex) {
            throw new RuntimeException("Error while creating domain object",ex);
        }
    }

    /**
     * Access dao object created from Spring application context
     *
     * @return dao object for tested domain object
     */
    protected abstract Crud<T> getDao();

    /**
     * Change passed domain object. We need this for test update method.
     *
     * @param entity domain object that is going to be changed
     */
    protected abstract void changeEntity(T entity);

    /**
     *  Override this method to provide entities with invalid fields to test them for ConstraintViolationException.
     *  While overriding you must use {@link org.shv.webforum.model.dao.impl.BaseDaoImplTest#entity} method
     *  to construct entity.
     */
    protected void fillParameters(){
    }


}
