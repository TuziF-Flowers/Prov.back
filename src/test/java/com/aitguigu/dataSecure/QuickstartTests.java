package com.aitguigu.dataSecure;

import com.aitguigu.dataSecure.entity.Event;
import com.aitguigu.dataSecure.repository.GraphBigRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuickstartTests {

    @Autowired
    private GraphBigRepository graphBigRepository;

    @Test
    void contextLoads() {
    }
}
