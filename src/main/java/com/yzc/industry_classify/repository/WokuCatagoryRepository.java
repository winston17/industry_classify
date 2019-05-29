package com.yzc.industry_classify.repository;

import com.yzc.industry_classify.entity.node.Company;
import com.yzc.industry_classify.entity.node.WokuCatagory;
import com.yzc.industry_classify.entity.relationship.CatagoryContains;
import com.yzc.industry_classify.entity.relationship.CatagoryRelatesCompany;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.GraphRepositoryQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface WokuCatagoryRepository extends Neo4jRepository<WokuCatagory, Long> {

    //搜索10个Company
    @Query("MATCH (n:WokuCatagory) RETURN n LIMIT 10")
    List<WokuCatagory> getWokuCatagoryList();


    @Query("MATCH (n:WokuCatagory) WHERE n.name = {name} RETURN n LIMIT 1")
    Company getCompanyByCompanyName(@Param("name") String name);

    @Query("MATCH (m:WokuCatagory)-[r:CatagoryRelatesCompany]->(n:company) WHERE n.companyName = {companyName} RETURN m")
    List<WokuCatagory> getWokuCatagoryListByCompanyName(@Param("companyName") String companyName);


    @Query("CREATE (n:WokuCatagory{level: {level}, name: {name}}) RETURN n")
    WokuCatagory addWokuCatagoryNode(@Param("level") int level, @Param("name") String name);

    //新建WokuCatagory的包含关系,一级包含二级,二级包含三级
    @Query("MATCH (n:WokuCatagory), (m:WokuCatagory) WHERE id(n) = {startId} AND id(m) = {endId} CREATE (n)-[r:CatagoryContains]->(m) RETURN r")
    CatagoryContains addCatagoryContainsRelationship(@Param("startId") Long startId, @Param("endId") Long endId);

    //新建从WokuCatagory指向公司的关系
    @Query("MATCH (n:WokuCatagory), (m:Company) WHERE id(n) = {startId} AND id(m) = {endId} " +
            "CREATE (n)-[r:CatagoryRelatesCompany{relateGoods: {relateGoods}, score: {score}, similarity: {similarity}}]->(m) " +
            "RETURN r")
    CatagoryRelatesCompany addCatagoryRelatesCompanyRelationship(@Param("startId") Long startId,
                                                                 @Param("endId") Long endId,
                                                                 @Param("relateGoods") String relateGoods,
                                                                 @Param("score") Double score,
                                                                 @Param("similarity") Double similarity);

}
