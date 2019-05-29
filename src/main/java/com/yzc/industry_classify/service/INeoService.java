package com.yzc.industry_classify.service;


import com.yzc.industry_classify.entity.node.Company;
import com.yzc.industry_classify.entity.node.WokuCatagory;
import com.yzc.industry_classify.repository.CompanyRepository;

public interface INeoService {

    int insertCompanyToNeo4j(Company company);

    int insertWokuCatagoryToNeo4j(WokuCatagory wokuCatagory);


}
