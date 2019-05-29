package com.yzc.industry_classify.service;

import com.yzc.industry_classify.model.NTBidderGoods;
import com.yzc.industry_classify.model.NTBidderGoodsScoreModel;

import java.util.List;

public interface IEsService {

    int insertNTBidderGoods(NTBidderGoods ntBidderGoods);

    List<NTBidderGoodsScoreModel> searchNTBidderGoodsScoreModel(String catagory);
}
