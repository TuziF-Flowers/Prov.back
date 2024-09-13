package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LucyChen
 * @date 2024-2-29
 * @desc:
 */
public interface LinkRepository extends JpaRepository<Link,String> {
    public Link findLinkByNodeId(String NodeId);
}
