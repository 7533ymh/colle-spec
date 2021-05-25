package com.example.backend.domain.company.service;

import com.example.backend.domain.company.domain.Company;
import com.example.backend.domain.company.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class CompanyCrawling {

    private final CompanyMapper companyMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public void Crawling() throws IOException, InterruptedException {

        logger.info("회사 크롤링 시작");

        String initUrl = "https://www.jobkorea.co.kr/starter/Company?G_ID=0&ChkDispStat=1&PageSize=30&Search_Order=1&Search_Type=1&Page=";

        for (int i = 1; i < 4; i++) {

            Document base = Jsoup.connect(initUrl + i).get();
            Elements elements = base.select("ul.starCdItem.clear h3.itemHd");


            for (Element e : elements) {

                String url = e.select("a").attr("abs:href");
                Document doc = Jsoup.connect(url).get();
                Elements el = doc.select("div.company-nav-container div.name");
                if (el.get(0).text().equals("홈")) {
                    doc = Jsoup.connect(url + "?tabType=I").get();
                }


                String name = doc.select("div.company-header-branding-body div.name").text();

                Company company = Company.builder()
                        .name(name)
                        .build();

                Elements element = doc.select("div.company-infomation-container.basic-infomation-container");
                Elements es = element.select("tbody tr.field");

                for (Element e1 : es) {

                    Elements e2 = e1.select("th.field-label");


                    for (int j = 0; j < e2.size(); j++) {
                        switch (e2.get(j).text()) {
                            case "산업":
                                company.setIndustry(e1.select("td").get(j).select("div.value").text());
                                break;
                            case "사원수":
                                company.setPeople(e1.select("td").get(j).select("div.value").text());
                                break;
                            case "기업구분":
                                company.setDivision(e1.select("td").get(j).select("div.value").text());
                                break;
                            case "설립일":
                                company.setEstablishment(e1.select("td").get(j).select("div.value").text());
                                break;
                            case "자본금":
                                company.setCapital(e1.select("td").get(j).select("div.value").text());
                                break;
                            case "매출액":
                                company.setTake(e1.select("td").get(j).select("div.value").text());
                                break;
                            case "대표자":
                                company.setRepresentative(e1.select("td").get(j).select("div.value").text());
                                break;
                            case "대졸초임":
                                company.setSalary(e1.select("td div.salary-average-item").text());
                                break;
                            case "주요사업":
                                company.setBussiness(e1.select("td").get(j).select("div.value").text());
                                break;
                            case "4대보험":
                                company.setInsurance(e1.select("td").get(j).select("div.value").text());
                                break;
                            case "홈페이지":
                                company.setUrl(e1.select("td").get(j).select("div.value").text());
                                break;
                            case "주소":
                                company.setAddress(e1.select("td").get(j).select("div.value").text());
                                break;
                        }
                    }


                }


                String next = url + "/PassAvgSpec";
                Document doc2 = Jsoup.connect(next).get();
                Elements el2 = doc2.select("div.totalList ul li");

                int language = 0;
                Double grade = Double.parseDouble(el2.get(0).select("em").text());

                if (grade >= 4.5)
                    company.setPass_grade(100);
                else if (grade >= 4.0)
                    company.setPass_grade(90);
                else if (grade >= 3.5)
                    company.setPass_grade(80);
                else if (grade >= 3.0)
                    company.setPass_grade(70);
                else if (grade >= 2.5)
                    company.setPass_grade(60);
                else if (grade >= 2.0)
                    company.setPass_grade(50);
                else if (grade >= 1.5)
                    company.setPass_grade(40);
                else if (grade > 0.0)
                    company.setPass_grade(30);
                else
                    company.setPass_grade(0);

                int toeic = Integer.parseInt(el2.get(1).select("em").text());

                if (toeic >= 860)
                    language += 100;
                else if (toeic >= 730)
                    language += 80;
                else if (toeic >= 470)
                    language += 60;
                else if (toeic >= 220)
                    language += 40;
                else
                    language += 30;

                String opic = el2.get(3).select("em").text();
                switch (opic) {
                    case "AL":
                        language += 100;
                    case "IH":
                    case "IM3":
                        language += 80;
                    case "IM1":
                    case "IM2":
                        language += 60;
                    case "IL":
                        language += 40;
                    default:
                        language += 30;
                }

                company.setPass_language(language);

                int certificate = (int) Math.round(Double.parseDouble(el2.get(5).select("em").text()));
                company.setPass_certificate(certificate * 100);

                int experience = (int) Math.round(Double.parseDouble(el2.get(6).select("em").text()));
                company.setPass_experience(experience * 70);

                int career = (int) Math.round(Double.parseDouble(el2.get(7).select("em").text()));
                company.setPass_career(career * 70);

                int award = (int) Math.round(Double.parseDouble(el2.get(8).select("em").text()));
                company.setPass_award(award * 60);

                int education = (int) Math.round(Double.parseDouble(el2.get(9).select("em").text()));
                company.setPass_education(education * 100);

                companyMapper.insert(company);

                Thread.sleep(5000);


            }

        }
    }





}
