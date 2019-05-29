package com.yzc.industry_classify.entity.node;

import com.yzc.industry_classify.entity.base.AbstractEntity;
import lombok.*;
import org.neo4j.ogm.annotation.*;

import java.util.List;

/**
 * @Classname WokuCatagory
 * @Author lizonghuan
 * @Description 沃库工业网分类
 * @Date-Time 2019/5/24-17:06
 * @Version 1.0
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity(label = "WOkuCatagory")
public class WokuCatagory extends AbstractEntity {

    @Property(name = "level")
    private int level;

    @Property(name = "name")
    @Index
    private String name;

//    @Relationship(type = "CatagoryRelatesCompany", direction = Relationship.OUTGOING)
//    private List<Company> relatesCompanyList;

//    @Relationship(type = "")
}
