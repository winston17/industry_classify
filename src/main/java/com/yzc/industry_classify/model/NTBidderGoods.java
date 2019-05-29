package com.yzc.industry_classify.model;

import lombok.*;

import java.io.Serializable;

/**
 * @Classname NTBidderGoods
 * @Author lizonghuan
 * @Description 询价供应商报名对应的物资和项目
 * @Date-Time 2019/5/21-15:37
 * @Version 1.0
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NTBidderGoods implements Serializable {
//    项目id
    private String projectId;
//    供应商id
    private String bidderId;
//    物资id
    private String goodsId;
//    项目名称
    private String projectName;
//    报名供应商名称
    private String bidderName;
//    物资名称
    private String goodsName;
//    最后更新时间戳
    private String lastUpdateTime;

}
