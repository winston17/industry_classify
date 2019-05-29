package com.yzc.industry_classify.entity;

import com.yzc.industry_classify.entity.base.AbstractEntity;
import com.yzc.industry_classify.entity.node.WokuCatagory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;

/**
 * @Classname WoKuContains
 * @Author lizonghuan
 * @Description 沃库工业网分类包含关系
 * @Date-Time 2019/5/23-18:38
 * @Version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@RelationshipEntity(type = "WokuContains")
public class WoKuContains extends AbstractEntity {

    @Property(name = "form")
    private String form;

    @StartNode
    private WokuCatagory beginWokuCatagory;

    @EndNode
    private WokuCatagory endWokuCatagory;



}
