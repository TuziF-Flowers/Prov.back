package com.aitguigu.dataSecure.repository;


import com.aitguigu.dataSecure.entity.MyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WorklogCustomRepositoryImpl implements WorklogCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MyEntity> findWorklogStatistics(String col, String groupby) {
        // 构建动态 SQL
        String sql = String.format("SELECT DISTINCT %s, COUNT(DISTINCT B.XJOB) AS jobnum " +
                "FROM worklog_fact B " +
                " JOIN application ON B.XAPPLICATION = application.XAPPLICATION " +
                " JOIN user A ON A.XIDENTITY = B.XIDENTITY " +
                "GROUP BY %s " +
                "ORDER BY COUNT(DISTINCT B.XJOB) DESC", col, groupby);
        System.out.println(sql);
        // 创建查询
        Query query = entityManager.createNativeQuery(sql);
        System.out.println(query.getResultList());
        // 执行查询并返回结果

        List<Object[]> rows = query.getResultList();
        List<MyEntity> result = new ArrayList<>(rows.size());
        int flag=0;
        int rowlen=0;
        for (Object[] row : rows) {
            MyEntity myEntity=new MyEntity();
            if (flag==0){
                rowlen=row.length;
            }
            if(rowlen==3){
                myEntity.setXAPPLICATION((String) row[0]);
                myEntity.setXAPPLICATIONNAME((String) row[1]);
                myEntity.setJobnum((BigInteger) row[2]);
                result.add(myEntity);
            } else if (rowlen==4) {
                myEntity.setNAME((String) row[0]);
                myEntity.setPHONE((String) row[1]);
                myEntity.setXUNIT1((String) row[2]);
                myEntity.setJobnum((BigInteger) row[3]);
                result.add(myEntity);
            }else if(rowlen==5){

                myEntity.setXAPPLICATIONNAME((String) row[0]);
                myEntity.setNAME((String) row[1]);
                myEntity.setPHONE((String) row[2]);
                myEntity.setXUNIT1((String) row[3]);
                myEntity.setJobnum((BigInteger) row[4]);
                result.add(myEntity);
            }

        }
        return result;
    }
}
