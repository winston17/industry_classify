package com.yzc.industry_classify.entity.node;

import com.yzc.industry_classify.entity.base.AbstractEntity;
import lombok.*;
import org.neo4j.ogm.annotation.*;

/**
 * @Classname Company
 * @Author lizonghuan
 * @Description neo4j公司实体
 * @Date-Time 2019/5/23-18:38
 * @Version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity(label = "Company")
public class Company extends AbstractEntity {

    @Property(name = "companyId")
    private String companyId;

    @Property(name = "companyName")
    @Index
    private String companyName;

}
