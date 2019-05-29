package com.yzc.industry_classify.entity.relationship;

import com.yzc.industry_classify.entity.base.AbstractEntity;
import com.yzc.industry_classify.entity.node.Company;
import com.yzc.industry_classify.entity.node.WokuCatagory;
import lombok.*;
import org.neo4j.ogm.annotation.*;

/**
 * @Classname CatagoryContains
 * @Author lizonghuan
 * @Description 分类包括
 * @Date-Time 2019/5/28-10:24
 * @Version 1.0
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RelationshipEntity(type = "CatagoryContains")
public class CatagoryContains extends AbstractEntity {
    @Property(name = "")
    @Index
    private String relateGoods;

    @StartNode
    @Index
    private WokuCatagory startWokuCatagory;

    @EndNode
    @Index
    private WokuCatagory endWokuCatagory;


}
