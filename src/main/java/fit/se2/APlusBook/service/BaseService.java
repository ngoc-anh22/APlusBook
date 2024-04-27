package fit.se2.APlusBook.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class BaseService<E>{
    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<E> clazz();

    /**
     * Thực hiện lưu hoặc cập nhật bản ghi trong cơ sở dữ liệu.
     * @param entity
     * @return
     */
    @Transactional
    public E saveEntity(E entity) {
        entityManager.persist(entity); // thêm mới
        return entity;
    }

    @Transactional
    public E updateEntity(E entity) {
        return entityManager.merge(entity); // thêm mới
    }

    /**
     * xóa bản ghi trong cơ sở dữ liệu
     * @param entity
     */
    @Transactional
    public void delete(E entity) {
        entityManager.remove(entity);
    }


    /**
     * Lấy tất cả bản ghi trong cơ sở dữ liệu.
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<E> findAll() {
        Table tbl = clazz().getAnnotation(Table.class);
        return (List<E>) entityManager.createNativeQuery("SELECT * FROM " + tbl.name(), clazz()).getResultList();
    }

    /**
     * Lấy bản ghi trong cơ sở dữ liệu theo khóa chính id.
     * @param id
     * @return
     */
    public E getById(int primaryKey) {
        return entityManager.find(clazz(), primaryKey);
    }

    /**
     * thực thi câu lệnh truy vấn cơ sở dữ liệu có phân trang
     * @param sql
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<E> getEntitiesByNativeSQL(String sql) {
        List<E> result = new ArrayList<E>();

        try {
            Query query = entityManager.createNativeQuery(sql, clazz());
            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * thực thi câu lệnh truy vấn cơ sở dữ liệu có phân trang
     * @param sql
     * @param page
     * @return
     */
    public E getEntityByNativeSQL(String sql) {
        try {
            return getEntitiesByNativeSQL(sql).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
