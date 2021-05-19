package com.example.backend.service.certificate;

import com.example.backend.global.exception.exception.CNotFoundInfoByIdxException;
import com.example.backend.global.exception.exception.CNotFoundInfoByUserException;
import com.example.backend.global.exception.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.certificate.domain.Certificate;
import com.example.backend.domain.certificate.service.CertificateService;
import com.example.backend.domain.rank.domain.Score;
import com.example.backend.domain.certificate.mapper.CertificateMapper;
import com.example.backend.domain.rank.mapper.RankMapper;
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
        assertThat(checkuser.getCertificate_score()).isEqualTo(finduser.getCertificate_score() + certificateMapper.findByIdx(certificate.getIdx()).get().getScore());
        assertThat(checkuser.getScore()).isEqualTo(finduser.getScore() + certificateMapper.findByIdx(certificate.getIdx()).get().getScore());

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
        assertThat(list.size()).isEqualTo(1);

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
        assertThat(certificateMapper.findByIdx(test.getIdx()).get().getContent()).isEqualTo(change.getContent());

        Score checkuser = rankMapper.FindScore(test.getUser_idx());
        assertThat(checkuser.getCertificate_score()).isEqualTo(finduser.getCertificate_score() + certificateMapper.findByIdx(test.getIdx()).get().getScore());
        assertThat(checkuser.getScore()).isEqualTo(finduser.getScore() + certificateMapper.findByIdx(test.getIdx()).get().getScore());

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
        assertThat(checkuser.getCertificate_score()).isEqualTo(finduser.getCertificate_score());
        assertThat(checkuser.getScore()).isEqualTo(finduser.getScore());


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
        assertThat(certificateMapper.findByIdx(test.getIdx()).get().getScore()).isEqualTo(100);


    }

    @Test
    void checkCertificateUserIdx() {

        //given
        int user_idx = 37;


        //when
        CNotFoundInfoByUserException e = assertThrows(CNotFoundInfoByUserException.class, () -> certificateService.findByUserIdx(user_idx));


        //then
        assertThat(e.getMessage()).isEqualTo("해당 회원의 자격증 정보가 없습니다.");

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
        assertThat(e.getMessage()).isEqualTo("해당 회원의 자격증 번호가 아닙니다.");
        assertThat(ex.getMessage()).isEqualTo("해당 자격증 번호의 정보가 없습니다.");
    }
}