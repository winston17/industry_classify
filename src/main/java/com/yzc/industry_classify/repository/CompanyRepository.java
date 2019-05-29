package com.yzc.industry_classify.repository;

import com.yzc.industry_classify.entity.node.Company;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CompanyRepository extends Neo4jRepository<Company, Long> {

    //搜索10个Company, 不要使用
    @Query("MATCH (n:Company) RETURN n LIMIT 10")
    List<Company> getCompanyList();


    @Query("MATCH (n:Company) WHERE n.companyName = {companyName} RETURN n LIMIT 1")
    Company getCompanyByCompanyName(@Param("companyName") String companyName);


    @Query("MATCH (m:WokuCatagory)-[r:CatagoryRelatesCompany]->(n:company) WHERE m.name = {name} RETURN n")
    List<Company> getCompanyListByCatagory(@Param("name") String name);


    @Query("CREATE (n:Company{companyId: {companyId}, companyName: {companyName}}) RETURN n")
    Company addCompanyNode(@Param("companyId") String companyId, @Param("companyName") String companyName);


//    //以下为demo代码
//    @Query("create (n:User{age:{age},name:{name}}) RETURN n ")
//    List<Company> addUserNodeList(@Param("name") String name, @Param("age")int age);



}
