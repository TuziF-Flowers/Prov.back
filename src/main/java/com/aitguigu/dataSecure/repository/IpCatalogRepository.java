package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.IpCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LucyChen
 * @date 2024-5-27
 * @desc:
 */
public interface IpCatalogRepository extends JpaRepository<IpCatalog,String> {
    List<IpCatalog>findIpCatalogsByDatasource(String datasource);
}
