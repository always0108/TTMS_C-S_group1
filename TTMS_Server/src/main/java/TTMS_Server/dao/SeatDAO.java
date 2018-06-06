package TTMS_Server.dao;

import TTMS_Server.model.Seat;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SeatDAO {

    //根据id获取座位信息
    @Select("select * from seat where seat_id = #{id}")
    Seat selectSeatById(Integer id);

    //根据演出厅ID获取演出厅内所有座位信息
    @Select("select * from seat where studio_id = #{id}")
    List<Seat> selectStudioSeatsByStudioId(Integer id);

    //新增座位
    @Insert("insert into seat(studio_id,seat_row,seat_column,seat_status) values" +
            "(#{studio_id},#{seat_row},#{seat_column},#{seat_status})")
    void addSeat(Seat seat);

    //删除座位
    @Delete("delete from seat where seat_id = #{id}")
    void deleteSeatById(Integer id);

    //更新座位状态（传入多个参数的解决办法）
    @Update("update seat set seat_status = #{seat_status} where seat_id = #{seat_id}")
    void updateStatusById(@Param("seat_id")Integer seat_id, @Param("seat_status")Integer seat_status);
}
