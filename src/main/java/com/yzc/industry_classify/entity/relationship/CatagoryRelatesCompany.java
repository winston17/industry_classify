package com.yzc.industry_classify.entity.relationship;

import com.yzc.industry_classify.entity.base.AbstractEntity;
import com.yzc.industry_classify.entity.node.Company;
import com.yzc.industry_classify.entity.node.WokuCatagory;
import lombok.*;
import org.neo4j.ogm.annotation.*;

/**
 * @Classname CatagoryRelatesCompany
 * @Author lizonghuan
 * @Description 分类关联公司的关系
 * @Date-Time 2019/5/23-19:07
 * @Version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RelationshipEntity(type = "CatagoryRelatesCompany")
public class CatagoryRelatesCompany extends AbstractEntity {

    @Property(name = "relateGoods")
    @Index
    private String relateGoods;

    @Property(name = "relateGoodsSeg")
    @Index
    private String relateGoodsSeg;

    @Property(name = "score")
    private Double score;

    @Property(name = "similarity")
    private Double similarity;

//    @Property(name = "beginNodeId")
//    @Index
//    private Long beginNodeId;
//
//    @Property(name = "endNodeId")
//    @Index
//    private Long endNodeId;

    @StartNode
    @Index
    private WokuCatagory woKuCatagory;

    @EndNode
    @Index
    private Company company;


}
