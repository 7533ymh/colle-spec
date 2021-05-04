package com.example.backend.service.certificate;

import com.example.backend.advice.exception.CNotFoundCareerException;
import com.example.backend.advice.exception.CNotFoundEducationException;
import com.example.backend.domain.Certificate;
import com.example.backend.domain.Language;
import com.example.backend.mapper.CertificateMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CertificateService {

    private final CertificateMapper certificateMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public Certificate save(Certificate certificate) {

        logger.info("자격증 등록");

        certificate.setScore(changetoNumber(certificate));

        certificateMapper.save(certificate);

        return certificate;

    }

    public List<Certificate> findByUserIdx(int user_idx) {

        logger.info("자격증 조회");

        checkCertificateUserIdx(user_idx);

        return certificateMapper.findByUserIdx(user_idx);

    }



    public Certificate modify(Certificate certificate) {

        logger.info("자격증 수정");

        checkCertificateUserIdx(certificate.getUser_idx());

        checkAll(certificate.getIdx(), certificate.getUser_idx());

        certificate.setScore(changetoNumber(certificate));

        certificateMapper.update(certificate);

        return certificate;

    }

    public void delete (int idx , int user_idx) {

        logger.info("자격증 삭제");

        checkCertificateUserIdx(user_idx);

        checkAll(idx,user_idx);

        certificateMapper.delete(idx, user_idx);

    }





    public int changetoNumber (Certificate certificate) {




        return 10;

    }



    public void checkCertificateUserIdx(int user_idx) {
        if ( certificateMapper.findByUserIdx(user_idx).isEmpty()){
            throw new CNotFoundEducationException("해당 회원의 자격증 정보가 없습니다.");
        }

    }


    public void checkAll(int idx , int user_idx) {

        if (certificateMapper.finduser_idxByIdx(idx).isPresent() ) {
            if (certificateMapper.finduser_idxByIdx(idx).get() != user_idx)
                throw new CNotFoundCareerException("해당 회원의 자격증 번호가 아닙니다.");
        }
        else {
            throw new CNotFoundCareerException("해당 자격증 번호의 정보가 없습니다.");
        }
    }


}
