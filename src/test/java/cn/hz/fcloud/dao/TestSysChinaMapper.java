package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.*;
import cn.hz.fcloud.utils.R;
import cn.hz.fcloud.utils.ShiroUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
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
    }

    @Test
    public void eqInfosTest(){
//        EqInfos infos = eqInfosMapper.findOne("8123456");
//        System.out.println(infos);
//        List<EqInfos> list = eqInfosMapper.findByComId(new Long(1));
//        for(EqInfos eq : list){
//           System.out.println(eq);
//        }
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
}
