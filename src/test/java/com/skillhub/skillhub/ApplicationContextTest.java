package com.skillhub.skillhub;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationContextTest {

    @Test
    void contextLoads() {
    }
    @Test
    void mainMethodRuns() {
        SkillhubApplication.main(new String[]{});
    }
}