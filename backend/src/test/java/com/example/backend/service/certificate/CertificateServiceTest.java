package com.example.backend.service.certificate;

import com.example.backend.advice.exception.CNotFoundInfoByIdxException;
import com.example.backend.advice.exception.CNotFoundInfoByUserException;
import com.example.backend.advice.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.Certificate;
import com.example.backend.domain.Score;
import com.example.backend.mapper.collspec.CertificateMapper;
import com.example.backend.mapper.collspec.RankMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class CertificateServiceTest {

    @Autowired
    CertificateService certificateService;

    @Autowired
    CertificateMapper certificateMapper;

    @Autowired
    RankMapper rankMapper;



    @Test
    void save() {

        //given
        Certificate certificate = new Certificate();
        certificate.setUser_idx(37);
        certificate.setTitle("abc");
        certificate.setPublisher("abc");
        certificate.setDate(Date.valueOf("2017-02-01"));
        certificate.setContent("abc");

        Score finduser = rankMapper.FindScore(certificate.getUser_idx());

        //when
        certificateService.save(certificate);

        //then
        assertThat(certificate.getContent()).isEqualTo(certificateMapper.findByIdx(certificate.getIdx()).get().getContent());

        Score checkuser = rankMapper.FindScore(certificate.getUser_idx());
        assertThat(finduser.getCertificate_score() + certificateMapper.findByIdx(certificate.getIdx()).get().getScore()).isEqualTo(checkuser.getCertificate_score());
        assertThat(finduser.getScore() + certificateMapper.findByIdx(certificate.getIdx()).get().getScore()).isEqualTo(checkuser.getScore());

    }

    @Test
    void findByUserIdx() {
        //given
        Certificate certificate = new Certificate();
        certificate.setUser_idx(37);
        certificate.setTitle("abc");
        certificate.setPublisher("abc");
        certificate.setDate(Date.valueOf("2017-02-01"));
        certificate.setContent("abc");

        //when
        certificateService.save(certificate);

        //then
        List<Certificate> list = certificateService.findByUserIdx(certificate.getUser_idx());
        assertThat(1).isEqualTo(list.size());

    }

    @Test
    void modify() {

        //given
        Certificate test = new Certificate();
        test.setUser_idx(37);
        test.setTitle("abc");
        test.setPublisher("abc");
        test.setDate(Date.valueOf("2017-02-01"));
        test.setContent("abc");

        Certificate change = new Certificate();
        change.setUser_idx(37);
        change.setTitle("abcde");
        change.setPublisher("abcde");
        change.setDate(Date.valueOf("2017-05-01"));
        change.setContent("abcde");

        Score finduser = rankMapper.FindScore(test.getUser_idx());

        //when
        certificateService.save(test);
        change.setIdx(test.getIdx());
        certificateService.modify(change);

        //then
        assertThat("abcde").isEqualTo(certificateMapper.findByIdx(test.getIdx()).get().getContent());

        Score checkuser = rankMapper.FindScore(test.getUser_idx());
        assertThat(finduser.getCertificate_score() + certificateMapper.findByIdx(test.getIdx()).get().getScore()).isEqualTo(checkuser.getCertificate_score());
        assertThat(finduser.getScore() + certificateMapper.findByIdx(test.getIdx()).get().getScore()).isEqualTo(checkuser.getScore());

    }

    @Test
    void delete() {
        //given
        Certificate test = new Certificate();
        test.setUser_idx(37);
        test.setTitle("abc");
        test.setPublisher("abc");
        test.setDate(Date.valueOf("2017-02-01"));
        test.setContent("abc");

        Certificate change = new Certificate();
        change.setUser_idx(37);
        change.setTitle("abcde");
        change.setPublisher("abcde");
        change.setDate(Date.valueOf("2017-05-01"));
        change.setContent("abcde");

        //when
        certificateService.save(test);
        Score finduser = rankMapper.FindScore(test.getUser_idx());
        certificateService.save(change);
        certificateService.delete(change.getIdx(), change.getUser_idx());

        //then
        //then
        List<Certificate> list = certificateMapper.findByUserIdx(test.getUser_idx());
        assertThat(1).isEqualTo(list.size());

        Score checkuser = rankMapper.FindScore(test.getUser_idx());
        assertThat(finduser.getCertificate_score()).isEqualTo(checkuser.getCertificate_score());
        assertThat(finduser.getScore()).isEqualTo(checkuser.getScore());


    }

    @Test
    void changetoNumber() {
        //given
        Certificate test = new Certificate();
        test.setUser_idx(37);
        test.setTitle("abc");
        test.setPublisher("abc");
        test.setDate(Date.valueOf("2017-02-01"));
        test.setContent("abc");

        //when
        certificateService.save(test);

        //then
        assertThat(100).isEqualTo(certificateMapper.findByIdx(test.getIdx()).get().getScore());


    }

    @Test
    void checkCertificateUserIdx() {

        //given
        int user_idx = 37;


        //when
        CNotFoundInfoByUserException e = assertThrows(CNotFoundInfoByUserException.class, () -> certificateService.findByUserIdx(user_idx));


        //then
        assertThat("해당 회원의 자격증 정보가 없습니다.").isEqualTo(e.getMessage());

    }

    @Test
    void checkAll() {

        //given
        Certificate test = new Certificate();
        test.setUser_idx(37);
        test.setTitle("abc");
        test.setPublisher("abc");
        test.setDate(Date.valueOf("2017-02-01"));
        test.setContent("abc");

        //when
        certificateService.save(test);
        CNotHaveAccessInfoException e = assertThrows(CNotHaveAccessInfoException.class , () -> certificateService.delete(test.getIdx(),14));
        CNotFoundInfoByIdxException ex = assertThrows(CNotFoundInfoByIdxException.class , () -> certificateService.delete(222,37));


        //then
        assertThat("해당 회원의 자격증 번호가 아닙니다.").isEqualTo(e.getMessage());
        assertThat("해당 자격증 번호의 정보가 없습니다.").isEqualTo(ex.getMessage());
    }
}