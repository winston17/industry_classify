package com.yzc.industry_classify;

import com.yzc.industry_classify.entity.node.Company;
import com.yzc.industry_classify.entity.node.WokuCatagory;
import com.yzc.industry_classify.entity.relationship.CatagoryContains;
import com.yzc.industry_classify.entity.relationship.CatagoryRelatesCompany;
import com.yzc.industry_classify.model.NTBidderGoods;
import com.yzc.industry_classify.model.NTBidderGoodsScoreModel;
import com.yzc.industry_classify.repository.CompanyRepository;
import com.yzc.industry_classify.repository.WokuCatagoryRepository;
import com.yzc.industry_classify.service.EsService;
//import com.yzc.industry_classify.util.JdbcUtil;
import com.yzc.industry_classify.service.NeoService;
import com.yzc.industry_classify.util.JdbcUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndustryClassifyApplicationTests {
	@Resource
	EsService esService;

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	WokuCatagoryRepository wokuCatagoryRepository;

	@Autowired
	NeoService neoService;

	@Test
	public void contextLoads() {
		System.out.println(123);
	}


	@Test
	public void testInsertNTBidderGoodsToEs(){
		NTBidderGoods nTBidderGoods = new NTBidderGoods();
		nTBidderGoods.setProjectId("123");
		nTBidderGoods.setBidderId("111");
		nTBidderGoods.setGoodsId("222");
		nTBidderGoods.setProjectName("考验公司公司");
		nTBidderGoods.setBidderName("德国公司");
		nTBidderGoods.setGoodsName("足球");
		int a = esService.insertNTBidderGoods(nTBidderGoods);
		System.out.println(a);
	}

	@Test
	public void testImportNTBidderGoodsToEs(){
//		EsService esService = new EsService();
		System.out.println(1123);
		try{
			Connection conn = JdbcUtil.getConectionYouZC_DW();
			String selectSQL = "select p.projectId, g.goodsId, b.bidderId, p.ProjectName, g.GoodsName, b.biddername, p.LastUpdateTime from Pro_NT_Goods g\n" +
					"left join Pro_NT_Project p on g.ProjectId = p.ProjectId\n" +
					"left join Pro_NT_BidderRegistRecord b on g.ProjectId = b.ProjectId\n" +
					"where b.bidderName not like '%优质采%' and b.biddername not like '%测试%'";
			PreparedStatement stmt = conn.prepareStatement(selectSQL);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				NTBidderGoods nTBidderGoods = new NTBidderGoods();
				nTBidderGoods.setProjectId(rs.getString("projectId"));
				nTBidderGoods.setBidderId(rs.getString("bidderId"));
				nTBidderGoods.setGoodsId(rs.getString("goodsId"));
				nTBidderGoods.setProjectName(rs.getString("projectName"));
				nTBidderGoods.setBidderName(rs.getString("bidderName"));
				nTBidderGoods.setGoodsName(rs.getString("goodsName"));
				if (esService.insertNTBidderGoods(nTBidderGoods) < 1){
					System.out.println(" 插入失败");
					System.out.println(nTBidderGoods);
					System.out.println(esService.insertNTBidderGoods(nTBidderGoods));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(123);
	}

	@Test
	public void testCatagorySearch(){
//		EsService esService = new EsService();
		System.out.println(2222);
		String id, pid, name;
		try{
			Connection conn = JdbcUtil.getConectionSpiderTask();
			String selectSQL = "select id, pid, name from T_Category";
			PreparedStatement stmt = conn.prepareStatement(selectSQL);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				id = rs.getString("id").trim();
				pid = rs.getString("pid").trim();
				name = rs.getString("name").replaceAll("\\d+\\-", "");
				List<NTBidderGoodsScoreModel> ntBidderGoodsScoreModelList = esService.searchNTBidderGoodsScoreModel(name);

				System.out.println(ntBidderGoodsScoreModelList);


//				NTBidderGoods nTBidderGoods = new NTBidderGoods();
//				nTBidderGoods.setProjectId(rs.getString("id"));
//				nTBidderGoods.setBidderId(rs.getString("bidderId"));
//				nTBidderGoods.setGoodsId(rs.getString("goodsId"));
//				nTBidderGoods.setProjectName(rs.getString("projectName"));
//				nTBidderGoods.setBidderName(rs.getString("bidderName"));
//				nTBidderGoods.setGoodsName(rs.getString("goodsName"));
//				if (esService.insertNTBidderGoods(nTBidderGoods) < 1){
//					System.out.println(" 插入失败");
//					System.out.println(nTBidderGoods);
//					System.out.println(esService.insertNTBidderGoods(nTBidderGoods));
//				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(123);
	}


	@Test
	public void testInsertWokuCatagoryToNeo4j(){
		WokuCatagory wokuCatagory1 = new WokuCatagory(2, "土建材料");
		neoService.insertWokuCatagoryToNeo4j(wokuCatagory1);
		System.out.println(555);
	}

	@Test
	public void testInsertCompanyToNeo4j(){
		Company company1 = new Company("ccc", "浙江省省政府");
		System.out.println(company1);
		neoService.insertCompanyToNeo4j(company1);
		System.out.println(123);
	}

	@Test
	public void testGetWokuCatagory(){
		List<WokuCatagory> wokuCatagoryList = wokuCatagoryRepository.getWokuCatagoryList();
		System.out.println(wokuCatagoryList);
	}

	@Test
	public void testFind(){
		List<Long> idList = new ArrayList<>();
		idList.add(146521L);
		Iterable<WokuCatagory> resultIter = wokuCatagoryRepository.findAllById(idList);
		for(WokuCatagory wokuCatagory : resultIter){
			System.out.println(666);
			System.out.println(wokuCatagory);
		}
		System.out.println(123);
	}

	@Test
	public void catagoryCompanySearchToNeo4j(){
//		EsService esService = new EsService();
		System.out.println(2222);
		String id, pid, name;
		try{
			Connection conn = JdbcUtil.getConectionSpiderTask();
			String selectSQL = "select id, pid, name from T_Category";
			PreparedStatement stmt = conn.prepareStatement(selectSQL);
			ResultSet rs = stmt.executeQuery();
			WokuCatagory wokuCatagory = null, firstCatagory = null, secondCatagory = null, thirdCatagory = null;
			Company company = null;
			CatagoryContains catagoryContains = null;
			CatagoryRelatesCompany catagoryRelatesCompany = null;
			int level = 3;
			while(rs.next()){
				id = rs.getString("id").trim();
				pid = rs.getString("pid").trim();
				name = rs.getString("name").replaceAll("\\d+\\-", "");
				switch(id.length()){
					case(2):
						level = 1;
						break;
					case(4):
						level = 2;
						break;
					case(6):
						level = 3;
						break;
					default:
						level = 3;
						break;
				}
				wokuCatagory = wokuCatagoryRepository.addWokuCatagoryNode(level, name);
				switch(level){
					case(1):
						firstCatagory = wokuCatagory;
						break;
					case(2):
						secondCatagory = wokuCatagory;
						if (firstCatagory != null && secondCatagory != null){
							catagoryContains = wokuCatagoryRepository.
									addCatagoryContainsRelationship(firstCatagory.getId(), secondCatagory.getId());
						}
						break;
					case(3):
						thirdCatagory = wokuCatagory;
						if (secondCatagory != null && thirdCatagory != null){
							catagoryContains = wokuCatagoryRepository.
									addCatagoryContainsRelationship(secondCatagory.getId(), thirdCatagory.getId());
						}
						break;
					default:
						break;
				}
				List<NTBidderGoodsScoreModel> ntBidderGoodsScoreModelList = esService.searchNTBidderGoodsScoreModel(name);
				System.out.println(ntBidderGoodsScoreModelList);
				if (ntBidderGoodsScoreModelList != null){
					for(NTBidderGoodsScoreModel ntBidderGoodsScoreModel : ntBidderGoodsScoreModelList){
						company = companyRepository.addCompanyNode(ntBidderGoodsScoreModel.getBidderId(), ntBidderGoodsScoreModel.getBidderName());
						catagoryRelatesCompany = wokuCatagoryRepository.addCatagoryRelatesCompanyRelationship(
								wokuCatagory.getId(), company.getId(), ntBidderGoodsScoreModel.getGoodsName(),
								ntBidderGoodsScoreModel.getScore(), ntBidderGoodsScoreModel.getSimilarity());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(888);
	}




}
