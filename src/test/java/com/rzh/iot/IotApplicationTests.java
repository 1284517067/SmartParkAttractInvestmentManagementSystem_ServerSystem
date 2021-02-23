package com.rzh.iot;

import com.rzh.iot.dao.*;
import com.rzh.iot.model.MenuNode;
import com.rzh.iot.model.Space;
import com.rzh.iot.model.User;
import com.rzh.iot.service.ApprovalOpinionService;
import com.rzh.iot.service.ApprovalProcessNodeService;
import com.rzh.iot.service.IntentionRegistrationFormService;
import com.rzh.iot.service.MenuService;
import com.rzh.iot.service.impl.ApprovalOpinionServiceImpl;
import com.rzh.iot.utils.Common;
import com.rzh.iot.utils.WebSocketServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class IotApplicationTests {

    @Resource
    DataSource dataSource;
    @Resource
    UserDao userDao;
    @Resource
    MenuDao menuDao;
    @Resource
    MenuService menuService;
    @Resource
    IndustryTypeDao industryTypeDao;
    @Resource
    ApprovalProcessDao approvalProcessDao;
    @Resource
    ApprovalProcessNodeService approvalProcessNodeService;
    @Resource
    SpaceDao spaceDao;
    @Resource
    EnterpriseDao enterpriseDao;
    @Resource
    ProjectDao projectDao;
    @Resource
    ApprovalOpinionService approvalOpinionService;
    @Resource
    IntentionRegistrationFormService intentionRegistrationFormService;
    @Resource
    Common common;
    @Resource
    WebSocketServer webSocketServer;


    @Test
    void contextLoads() {
    }

    @Test
    void testUserDao() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        User user1 = new User();
        user1.setPassword("12322");
        user1.setUsername("admin");
        System.out.println(userDao.isExistUsername(user));
        System.out.println(userDao.isExistUsername(user1));
        System.out.println(userDao.verifyPassword(user));
        System.out.println(userDao.verifyPassword(user1));
        System.out.println(userDao.getPositionIdByUsername(user.getUsername()));

    }
    @Test
    void testMenuDao(){
        Long positionId = Long.parseLong("1");
        List<MenuNode> trees = menuDao.getMenuNodeByLevelAndPositionId(3,positionId);
        System.out.println(trees.toString());
    }

    @Test
    void testMenuService(){
        System.out.println("----------------------final result-----------------------");
        System.out.println(menuService.getUserMenuTree(Long.parseLong("1")).toString());
    }

    @Test
    void testIndustryTypeDao(){
        Integer currentPage = 1;
        Integer limit = 10;
        String key = "信息通信";
    }
    @Test
    void testApprovalProcessDao(){
        System.out.println(approvalProcessDao.getApprovalProcessesByLimit(0,10));
    }

    @Test
    void testApprovalProcessNode(){
        System.out.println(approvalProcessNodeService.getApprovalProcessNodeData(Long.parseLong("1")));
    }

    @Test
    void testSpaceDao(){

    }

    @Test
    void testEnterpriseDao(){
        String str = "scaoihc(1)";
        str = str.replaceAll("(1)","2");
        System.out.println(str);
    }

    @Test
    void testProject(){
        spaceDao.updateSpaceEnterpriseIdBySpaceId(new Long(4),null);
    }

    @Test
    void testWebsocket(){
        WebSocketServer webSocketServer = new WebSocketServer();
        webSocketServer.sendMessage("admin","1");
    }

    public class Thread implements Runnable{

        @Override
        public void run() {
            try {
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
