package TTMS_Server.service;

import TTMS_Server.model.Seat;

import java.util.List;
import java.util.Map;

public interface SeatService {

    //根据id获取座位信息
    Seat selectSeatById(Integer id);

    //根据演出厅ID获取演出厅内所有座位信息
    List<Seat> selectStudioSeatsByStudioId(Integer id);

    //新增座位
    boolean addSeat(Seat seat);

    //删除座位
    boolean deleteSeatById(Integer id);

    //根据演出厅ID批量初始化座位
    boolean initSeatByStudioId(Integer id);

    //根据演出厅ID更新座位状态
    boolean updateStatusByStudioId(Integer studio_id, Map<Integer,Integer> seats);
}
