package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.Provider;
import cn.hz.fcloud.entity.SysChina;
import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.utils.ShiroUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-jdbc.xml"})
public class TestSysChinaMapper {

    @Autowired
    private SysChinaMapper chinaMapper;
    @Autowired
    private EquipmentMapper eqMapper;
    @Autowired
    private CompanyMapper comMapper;
    @Autowired
    private EqInfosMapper eqInfosMapper;
    @Autowired
    private ProviderMapper providerMapper;
    @Test
    public void sysChinaTest(){
        List<SysChina> list = chinaMapper.selectByParentid(100000);
        for(SysChina china : list){
            System.out.println(china.getMerger_short_name());
        }
    }
    @Test
    public void equipmentTest(){
//        List<Equipment> eqs = eqMapper.findAll();
//        for(Equipment eq:eqs){
//            System.out.println(eq);
//        }
        eqMapper.modifyState("0074577",1);
    }

    @Test
    public void companyTest(){
       /* List<Company> coms = comMapper.findAllCompanys();
        for(Company com:coms){
            System.out.println(com);
        }*/
       System.out.println(comMapper.findCompanyCode());
        String s = String.format("%05d", 25);
    }

    @Test
    public void eqInfosTest(){

    }

    @Test
    public void testShiro(){
        SysUser user = ShiroUtil.getUserEntity();
        System.out.println(user);
    }

    @Test
    public void testProvider(){
//        System.out.println(ShiroUtil.getUserEntity());
//        List<Provider> list =  providerMapper.findAllProvider();
//        for(Provider pro : list){
//            System.out.println(pro);
//        }
        Provider provider = providerMapper.selectByPrimaryKey(new Long(1));
        provider.setAddress("浙江");
        providerMapper.updateByPrimaryKeySelective(provider);
        System.out.println(provider);
    }
//    public void syso(List<Enum> list){
//        for(Object obj:list){
//            System.out.println(obj);
//        }
//    }
    @Test
    public void testString(){
//        System.out.println("img.jpg".split("\\.").length);
        String str = "A00001";
        System.out.println(Integer.valueOf(str.split("A")[1])+1);
        String s = String.format("%05d", Integer.valueOf(str.split("A")[1])+1);
        System.out.println(s);
//        System.out.println(new );
    }
    @Test
    public void testProperties() throws Exception{
        Properties prop = new Properties();
//        InputStream input = new FileInputStream(new File("file.properties"));
        BufferedReader  input = new BufferedReader(new FileReader("/file.properties"));
        prop.load(input);
        String realPath = prop.getProperty("dir");
        System.out.println(realPath);
    }
}
