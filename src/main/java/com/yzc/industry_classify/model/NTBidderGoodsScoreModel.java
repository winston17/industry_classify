package com.yzc.industry_classify.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Classname NTBidderGoodsScoreModel
 * @Author lizonghuan
 * @Description 查询结果，带相似度分数的model
 * @Date-Time 2019/5/21-18:52
 * @Version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NTBidderGoodsScoreModel extends NTBidderGoods implements Comparable<NTBidderGoodsScoreModel>{
    //相似度分数，搜索引擎根据内部机制打分，是搜索引擎返回顺序的依据，可能大于1
    protected Double score;
    //通过最大公共子串计算相似度，最大值为1
    protected Double similarity;

    @Override
    public String toString() {
        return "NTBidderGoodsInsertModel{" +
                "projectId='" + getProjectId() + '\'' +
                "bidderId='" + getBidderId() + '\'' +
                "goodsId='" + getGoodsId() + '\'' +
                "projectName='" + getProjectName() + '\'' +
                "bidderName='" + getBidderName() + '\'' +
                "goodsName='" + getGoodsName() + '\'' +
                "lastUpdateTime='" + getLastUpdateTime() + '\'' +
                "score='" + score + '\'' +
                "similarity='" + similarity + '\'' +
                '}';
    }

    @Override
    public int compareTo(NTBidderGoodsScoreModel nTBidderGoodsScoreModel){
        if(this.score < nTBidderGoodsScoreModel.score){
            return 1;
        }
        if(this.score > nTBidderGoodsScoreModel.score){
            return -1;
        }
        if(this.similarity < nTBidderGoodsScoreModel.similarity){
            return 1;
        }
        if(this.similarity > nTBidderGoodsScoreModel.similarity){
            return -1;
        }
        return this.score.compareTo(nTBidderGoodsScoreModel.score);
    }



}
