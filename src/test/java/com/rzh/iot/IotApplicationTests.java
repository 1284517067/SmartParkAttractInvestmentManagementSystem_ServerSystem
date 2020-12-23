package com.rzh.iot;

import com.rzh.iot.dao.*;
import com.rzh.iot.model.MenuNode;
import com.rzh.iot.model.Space;
import com.rzh.iot.model.User;
import com.rzh.iot.service.ApprovalProcessNodeService;
import com.rzh.iot.service.MenuService;
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
        Space space = new Space();
        space.setSpaceName("B1栋");
        Long i = new Long("1");
        space.setParentNodeId(i);
        System.out.println(space.toString());
        System.out.println(spaceDao.isSpaceExist(space));
    }

    @Test
    void testEnterpriseDao(){
/*
        System.out.println(enterpriseDao.getEnterpriseList().toString());
*/
    }

    @Test
    void testProject(){
        System.out.println(projectDao.getProjectByProjectId(new Long(1)));
    }

}
