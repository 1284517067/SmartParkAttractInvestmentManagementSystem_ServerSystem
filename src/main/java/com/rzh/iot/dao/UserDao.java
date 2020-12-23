package com.rzh.iot.dao;

import com.rzh.iot.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    @Select("select count(1) from user where (username = #{username}) limit 1;")
    int isExistUsername(User user);

    @Select("select count(1) from user where (username = #{username} and password = #{password}) limit 1;")
    int verifyPassword(User user);

    @Select("select * from user where username = #{username}")
    User getUserByUsername(User user);

    @Select("select status from user where username = #{username}")
    String getStatusByUsername(User user);

    @Select("select position_id from user where username = #{username}")
    Long getPositionIdByUsername(String username);

    @Select("select * from user order by username desc limit #{currentPage} , #{limit}")
    List<User> getUserListByLimit(Integer currentPage , Integer limit);

    @Select("select count(username) from user")
    int getUserCount();

    @Insert("insert into user (username,password,account_level,position_id,status) values (#{username},#{password},#{accountLevel},#{positionId},#{status})")
    int createUser(User user);

    @Update("update user set account_level = #{accountLevel},status = #{status},position_id = #{positionId} where username = #{username}")
    int updateUser(User user);

    @Update("update user set status = #{status} where username = #{username}")
    int updateUserStatus(String username,String status);

    @Update("update user set password = #{password} where username = #{username}")
    int updateUserPassword(User user);

    @Select("select distinct a.username , a.position_id , a.status , a.account_level , b.position_name , c.department_name from user a inner join position b , department c " +
            " where ( a.position_id = b.position_id ) and ( b.department_id = c.department_id )" +
            " and ( (a.username like '%${value}%')" +
            " or ( a.account_level like '%${value}%')" +
            " or (a.status like '%${value}%')" +
            " or (a.position_id like '%${value}%')" +
            " or (b.position_name like '%${value}%')" +
            " or (c.department_name like '%${value}%')" +
            " or (c.department_id like '%${value}%'))")
    List<User> getUserListByKey(String key);

    @Select("select count(distinct a.username)  from user a inner join position b , department c " +
            " where ( a.position_id = b.position_id ) and ( b.department_id = c.department_id )" +
            " and ( (a.username like '%${value}%')" +
            " or ( a.account_level like '%${value}%')" +
            " or (a.status like '%${value}%')" +
            " or (a.position_id like '%${value}%')" +
            " or (b.position_name like '%${value}%')" +
            " or (c.department_name like '%${value}%')" +
            " or (c.department_id like '%${value}%'))")
    int getCountOfUserByKey(String key);
};
