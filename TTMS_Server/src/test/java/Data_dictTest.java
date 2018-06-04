import TTMS_Server.config.database.DbConfig;
import TTMS_Server.config.database.MyBatis;
import TTMS_Server.config.spring.RootConfig;
import TTMS_Server.config.spring.WebConfig;
import TTMS_Server.config.spring.WebInit;
import TTMS_Server.model.Data_dict;
import TTMS_Server.service.Data_dictService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {RootConfig.class,WebConfig.class,WebInit.class,MyBatis.class,DbConfig.class})
@WebAppConfiguration("src/main/resources") //1 此注解指定web资源的位置，默认为src/main/webapp
public class Data_dictTest {

    @Before
    public void before() throws Exception{}

    @After
    public void after() throws Exception{}

    @Autowired
    private Data_dictService data_dictService;

    @Test
    public void Text(){
        //增加
//        Data_dict data_dict = new Data_dict(1,1,"语言类型","0");
//        if(data_dictService.addDataDictionary(data_dict)){
//            System.out.println("添加成功");
//        }
//        else{
//            System.out.println("该数据字典已存在");
//        }

//       //删除
        if(data_dictService.deleteDataDictionaryById(3)){
            System.out.println("删除成功");
        }
        else{
            System.out.println("该数据字典不存在");
        }


        //更新
//        Data_dict data_dict_new = new Data_dict(1,1,"根","0");
//        data_dict_new.setDict_id(1);
//        if(data_dictService.updateDataDictionaryById(data_dict_new)){
//            System.out.println("更新成功");
//        }
//        else{
//            System.out.println("该数据字典已存在");
//        }
    }
}
