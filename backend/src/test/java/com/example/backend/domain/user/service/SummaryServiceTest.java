package com.example.backend.domain.user.service;

import com.example.backend.domain.user.domain.Summary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SummaryServiceTest {

    @Autowired
    SummaryService summaryService;



    @Test
    void show() {

        //given
        int idx = 121;

        //when
        Summary summary = summaryService.show(idx);

        //then

    }
}