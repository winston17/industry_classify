package com.yzc.industry_classify.service;

import com.yzc.industry_classify.model.NTBidderGoods;
import com.yzc.industry_classify.model.NTBidderGoodsInsertModel;
import com.yzc.industry_classify.model.NTBidderGoodsScoreModel;
import com.yzc.industry_classify.util.Similarity;
import org.attoparser.util.TextUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname EsService
 * @Author lizonghuan
 * @Description 搜索引擎服务
 * @Date-Time 2019/5/21-16:43
 * @Version 1.0
 */
@Service
public class EsService implements IEsService{

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private String searchIndex = "industry_classification";
    private String searchType = "NTBidderGoods";

    public String getSearchIndex(){
        return searchIndex;
    }

    public void setSearchIndex(String searchIndex){
        this.searchIndex = searchIndex;
    }

    public String getSearchType(){
        return searchType;
    }

    public void setSearchType(String searchType){
        this.searchType = searchType;
    }


    @Override
    public int insertNTBidderGoods(NTBidderGoods ntBidderGoods){
        NTBidderGoodsInsertModel ntBidderGoodsInsertModel = new NTBidderGoodsInsertModel();
        ntBidderGoodsInsertModel.setProjectId(ntBidderGoods.getProjectId());
        ntBidderGoodsInsertModel.setBidderId(ntBidderGoods.getBidderId());
        ntBidderGoodsInsertModel.setGoodsId(ntBidderGoods.getGoodsId());
        ntBidderGoodsInsertModel.setProjectName(ntBidderGoods.getProjectName());
        ntBidderGoodsInsertModel.setBidderName(ntBidderGoods.getBidderName());
        ntBidderGoodsInsertModel.setGoodsName(ntBidderGoods.getGoodsName());
        ntBidderGoodsInsertModel.setProjectNameAgg(ntBidderGoods.getProjectName());
        ntBidderGoodsInsertModel.setBidderNameAgg(ntBidderGoods.getBidderName());
        ntBidderGoodsInsertModel.setGoodsNameAgg(ntBidderGoods.getGoodsName());
        System.out.println(ntBidderGoodsInsertModel);
        System.out.println(555);
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withIndexName(this.searchIndex)
                .withType(this.searchType)
                .withId(ntBidderGoodsInsertModel.hashCode() + "")
                .withObject(ntBidderGoodsInsertModel)
                .build();
        System.out.println(indexQuery.getSource());

//        System.out.println(indexQuery.getId());
//        System.out.println(indexQuery.getIndexName());
//        System.out.println(indexQuery.getObject());
//        System.out.println(indexQuery.getType());
//        System.out.println(indexQuery.getSource());
        try{
            System.out.println(elasticsearchTemplate);
            System.out.println(indexQuery);
            elasticsearchTemplate.index(indexQuery);
            return 1;
        } catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public List<NTBidderGoodsScoreModel> searchNTBidderGoodsScoreModel(String catagory){
//        QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("goodsName", catagory).slop(2);
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("goodsName", catagory);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices(this.searchIndex)
                .withTypes(this.searchType)
                .withQuery(queryBuilder)
                .withMinScore((float) 0.0)
//                .withFilter(boolFilter().must(termFilter("id", documentId)))
                .build();
        Page<NTBidderGoodsScoreModel> sampleEntities = elasticsearchTemplate.queryForPage(searchQuery, NTBidderGoodsScoreModel.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                SearchHits searchHits = searchResponse.getHits();
                List<NTBidderGoodsScoreModel> ntBidderGoodsScoreModelList = new ArrayList<>();
                for(SearchHit searchHit : searchHits){
                    if (searchHits.getHits().length <= 0) {
                        return null;
                    }
                    NTBidderGoodsScoreModel ntBidderGoodsScoreModel = new NTBidderGoodsScoreModel();
                    ntBidderGoodsScoreModel.setProjectId(searchHit.getSourceAsMap().get("projectId") == null ? null : searchHit.getSourceAsMap().get("projectId").toString());
                    ntBidderGoodsScoreModel.setBidderId(searchHit.getSourceAsMap().get("bidderId") == null ? null : searchHit.getSourceAsMap().get("bidderId").toString());
                    ntBidderGoodsScoreModel.setGoodsId(searchHit.getSourceAsMap().get("goodsId") == null ? null : searchHit.getSourceAsMap().get("goodsId").toString());
                    ntBidderGoodsScoreModel.setProjectName(searchHit.getSourceAsMap().get("projectName") == null ? null : searchHit.getSourceAsMap().get("projectName").toString());
                    ntBidderGoodsScoreModel.setBidderName(searchHit.getSourceAsMap().get("bidderName") == null ? null : searchHit.getSourceAsMap().get("bidderName").toString());
                    ntBidderGoodsScoreModel.setGoodsName(searchHit.getSourceAsMap().get("goodsName") == null ? null : searchHit.getSourceAsMap().get("goodsName").toString());
                    ntBidderGoodsScoreModel.setScore((double) searchHit.getScore());
                    ntBidderGoodsScoreModelList.add(ntBidderGoodsScoreModel);
                }
                if (ntBidderGoodsScoreModelList.size() > 0) {
                    return new AggregatedPageImpl<>((List<T>) ntBidderGoodsScoreModelList);
                }
                return null;
            }
        });
        if (sampleEntities != null){
            List<NTBidderGoodsScoreModel> resultList = sampleEntities.getContent();
            for(NTBidderGoodsScoreModel ntBidderGoodsScoreModel : resultList){
                ntBidderGoodsScoreModel.setSimilarity(Similarity.getGoodsCatagorySimilarity(ntBidderGoodsScoreModel.getGoodsName(), catagory));
            }
            return resultList;
        }else{
            return null;
        }
    }

}
