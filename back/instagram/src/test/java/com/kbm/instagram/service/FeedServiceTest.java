package com.kbm.instagram.service;

import com.kbm.instagram.domain.Feed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
class FeedServiceTest {
    @Autowired FeedService feedService;

    @Test
    @DisplayName("새로운 피드 생성")
    void saveSomethingNew() {
    }

    @Test
    @DisplayName("id로 피드 찾기")
    void findById() {
    }

    @Test
    @DisplayName("피드 수정")
    void update() {
    }

    @Test
    @DisplayName("피드 수정 - 삭제된 피드")
    void updateDeleted() {
    }

    @Test
    @DisplayName("피드 수정 - id가 할당된 적 없는 피드")
    void updateFeedIdIsNull() {
    }

    @Test
    @DisplayName("피드 삭제")
    void delete() {
    }
}