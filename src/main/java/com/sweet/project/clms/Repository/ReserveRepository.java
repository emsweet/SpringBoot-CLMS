package com.sweet.project.clms.Repository;

import com.sweet.project.clms.Entity.Place;
import com.sweet.project.clms.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.JoinColumn;
import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reservation,Long> {
//根据预订id查询记录
    @Query(value="select * from reservation where rid=?1",nativeQuery = true)
    Reservation selectbyid(Long rid);
//查询地点预定记录
    @Query(value="select * from reservation inner join place ON place.pid = reservation.place_pid where place.pid=?1",nativeQuery=true)
    List<Reservation> selectbypid(Long pid);
//查询用户预定记录
    @Query(value="select * from reservation inner join user ON users.uid = reservation.user_uid where users.uid=?1",nativeQuery=true)
    List<Reservation>selectByUser(Long uid);
// 根据类型查询预定记录
@Query(value="select * from reservation where status=?1",nativeQuery=true)
    List <Reservation>selectByStatus(String status);
    @Query(value="Update reservation status SET status=?2 where rid=?1",nativeQuery=true)
    void  UpdateStatus(Long rid,String status);
}
