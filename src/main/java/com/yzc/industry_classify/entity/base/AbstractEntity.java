package com.yzc.industry_classify.entity.base;

/**
 * @Classname AbstractEntity
 * @Author lizonghuan
 * @Description 还有id和hashcode equals, nodeEntity类可以从此类继承
 * @Date-Time 2019/5/27-14:48
 * @Version 1.0
 */
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

/**
 * The abstract entity which contains the basic fields such
 * as id, hashCode and equals function
 */
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        if (this.id == null) {
            // For newly created entity, id will be null
            return false;
        }

        AbstractEntity entity = (AbstractEntity) obj;
        return this.id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }
}
