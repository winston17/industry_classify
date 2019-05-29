package com.yzc.industry_classify.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Objects;

/**
 * @Classname NTBidderGoodsInsertModel
 * @Author lizonghuan
 * @Description NTBidderGoods插入模型
 * @Date-Time 2019/5/21-16:49
 * @Version 1.0
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "industry_classification", type = "NTBidderGoods", shards = 4, replicas = 1)
public class NTBidderGoodsInsertModel extends NTBidderGoods{
    //    项目名称不分词
    private String projectNameAgg;
    //    报名供应商名称不分词
    private String bidderNameAgg;
    //    物资名称不分词
    private String goodsNameAgg;

    @Override
    public String toString() {
        return "NTBidderGoodsInsertModel{" +
                "projectId='" + getProjectId() + '\'' +
                "bidderId='" + getBidderId() + '\'' +
                "goodsId='" + getGoodsId() +  '\''+
                "projectName='" + getProjectName() + '\'' +
                "bidderName='" + getBidderName() + '\'' +
                "goods='" + getGoodsName() + '\'' +
                "lastUpdateTime='" + getLastUpdateTime() + '\'' +
                "projectNameAgg='" + projectNameAgg + '\'' +
                ", bidderNameAgg='" + bidderNameAgg + '\'' +
                ", goodsNameAgg='" + goodsNameAgg + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NTBidderGoodsInsertModel that = (NTBidderGoodsInsertModel) o;
        return projectNameAgg.equals(that.projectNameAgg) &&
                bidderNameAgg.equals(that.bidderNameAgg) &&
                goodsNameAgg.equals(that.goodsNameAgg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectNameAgg, bidderNameAgg, goodsNameAgg);
    }
}
