package com.yzc.industry_classify.service;

import com.yzc.industry_classify.entity.node.Company;
import com.yzc.industry_classify.entity.node.WokuCatagory;
import com.yzc.industry_classify.repository.CompanyRepository;
import com.yzc.industry_classify.repository.WokuCatagoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname NeoService
 * @Author lizonghuan
 * @Description neo4j service
 * @Date-Time 2019/5/23-17:10
 * @Version 1.0
 */
@Service
public class NeoService implements INeoService{

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    WokuCatagoryRepository wokuCatagoryRepository;


    @Override
    public int insertCompanyToNeo4j(Company company){
        companyRepository.addCompanyNode(company.getCompanyId(), company.getCompanyName());
        return 0;
    }

    @Override
    public int insertWokuCatagoryToNeo4j(WokuCatagory wokuCatagory){
        wokuCatagoryRepository.addWokuCatagoryNode(wokuCatagory.getLevel(), wokuCatagory.getName());
        return 0;
    }

//    @Override
//    public int insertWokuCatagoryToNeo4j(){
//        return 0;
//    }

}
