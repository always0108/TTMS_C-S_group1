
import TTMS_Server.config.database.DbConfig;
import TTMS_Server.config.database.MyBatis;
import TTMS_Server.config.spring.RootConfig;
import TTMS_Server.config.spring.WebConfig;
import TTMS_Server.config.spring.WebInit;
import TTMS_Server.model.Employee;
import TTMS_Server.model.Play;
import TTMS_Server.model.Studio;
import TTMS_Server.service.DataDictionaryService;
import TTMS_Server.service.EmployeeService;
import TTMS_Server.service.PlayService;
import TTMS_Server.service.StudioService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {RootConfig.class,WebConfig.class,WebInit.class,MyBatis.class,DbConfig.class})
@WebAppConfiguration("src/main/resources") //1 此注解指定web资源的位置，默认为src/main/webapp
public class PlayTest {


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Autowired
    private PlayService playService;

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Test
    public void test() {
        //增加
//        Play play = new Play(dataDictionaryService.selectDataDictionaryByName("动作").getDict_id(),
//                dataDictionaryService.selectDataDictionaryByName("英文").getDict_id(),
//                "复仇者联盟3", "《复仇者联盟3：无限战争》是由漫威电影制作的的科幻片。" +
//                "该片由安东尼·罗素、乔·罗素执导，小罗伯特·唐尼、乔什·布洛林、克里斯·埃文斯、克里斯·海姆斯沃斯、斯嘉丽·" +
//                "约翰逊、马克·鲁法洛等主演。是《复仇者联盟》系列电影的第三部，是漫威电影宇宙的第十九部电影，该片与《雷神3：诸神黄昏》" +
//                "剧情连接，讲述了复仇者联盟和他们的超级英雄盟友们牺牲一切，阻止灭霸毁灭一半宇宙的故事",120,
//                new BigDecimal("60"),Short.parseShort("1"));
//
//        if (playService.addPlay(play))
//           System.out.println("添加成功");
//        else
//           System.out.println("该剧目已存在");

        //删除
//        if (playService.deletePlayById(3))
//            System.out.println("删除成功");
//        else
//            System.out.println("该演出厅不存在");

//        //修改
//        Play play_new = new Play(1,1,"变形金刚","测试2",120,new BigDecimal("60"),Short.parseShort("1"));
//        play_new.setPlay_id(1);
//        if (playService.updatePlayById(play_new))
//            System.out.println("更新成功");
//        else
//            System.out.println("该剧目已存在");
    }
}