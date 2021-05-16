package com.example.backend.service.program;

import com.example.backend.domain.Program;
import com.example.backend.mapper.collspec.ProgramMapper;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class ProgramCrawling {

    private final ProgramMapper programMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    //매일 0시에 크롤링 데이터 업데이트
    @Scheduled(cron = "0 0 0 * * *")
    public void Crawling() throws IOException {

        logger.info("크롤링 시작");

        System.setProperty("jsse.enableSNIExtension", "false");

        Competition();

        Program();

        Volunteer();

        //결과값이 없어져 마감기간이 지나도 삭제가 안되는 데이터 삭제 ( 크롤링하는 날짜 기준 )
        programMapper.deleteOverDate();

        logger.info("크롤링 종료");


    }


    public void Program() throws IOException {

        String url = "https://jobs.gnu.ac.kr/user/Pg/PgPg010L.do?CURRENT_MENU_CODE=MENU2017&TOP_MENU_CODE=MENU2016&CURR_PAGE=";

        for (int i = 1; i < 6; i++) {

            String chapter = url + i;
            Document doc = Jsoup.connect(chapter).get();
            Elements elements = doc.select("div.tableList");
            Elements el = elements.select("table tbody tr");

            if (el.get(0).select("th.ellipsis").get(0).text().isEmpty())
                break;

            for (Element e : el) {


                Elements topElements = e.select("th.ellipsis");

                String agencyAll = topElements.get(0).text();

                if (agencyAll.isEmpty())
                    break;

                String agency = agencyAll.substring(0, agencyAll.indexOf("("));

                String title = topElements.get(1).text();

                Elements dataElements = e.select("td");
                String receive_date = dataElements.get(1).text();
                String receive_date1 = receive_date.substring(0, receive_date.indexOf("~"));
                String receive_date2 = receive_date.substring(receive_date.indexOf("~") + 2);


                String real_date = dataElements.get(2).text();
                String real_date1 = real_date.substring(0, real_date.indexOf("~"));
                String real_date2 = real_date.substring(real_date.indexOf("~") + 2);

                String status = dataElements.get(3).text();

                if (status.equals("마감")) {

                    programMapper.findByTitle(title).ifPresent(
                            programMapper::deleteByIdx
                    );

                    continue;

                }

                Program program = Program.builder()
                        .division("프로그램")
                        .title(title)
                        .agency(agency)
                        .receive_date_start(receive_date1)
                        .receive_date_end(receive_date2)
                        .program_date_start(real_date1)
                        .program_date_end(real_date2)
                        .url(chapter)
                        .build();


                programMapper.findByTitle(program.getTitle())
                        .orElseGet(
                                () -> programMapper.insert(program)
                        );


            }


        }


    }

    public void Competition() throws IOException {

        String initUrl = "http://www.contestkorea.com/sub/list.php?displayrow=12&int_gbn=1&Txt_sGn=1&Txt_key=all&Txt_word=&Txt_bcode=030210001&Txt_code1%5B0%5D=26&Txt_code1%5B1%5D=30&Txt_aarea=&Txt_area%5B0%5D=97&Txt_area%5B1%5D=75&Txt_area%5B2%5D=64&Txt_sortkey=a.int_sort&Txt_sortword=desc&Txt_host=&Txt_tipyn=&Txt_actcode=&page=";

        for (int i = 1; i < 6; i++) {

            Document doc = Jsoup.connect(initUrl + i).get();
            Elements elements = doc.select("div.list_style_2");
            Elements el = elements.select("ul li");


            for (Element e : el) {

                if (e.select("span.condition").text().equals("접수종료")) {
                    programMapper.findByTitle(e.select("span.txt").text()).ifPresent(
                            programMapper::deleteByIdx
                    );
                    continue;
                }

                String NextUrl = e.select("a").attr("abs:href");
                if (NextUrl.isEmpty())
                    continue;

                Document docNext = Jsoup.connect(NextUrl).get();
                Elements titleElement = docNext.select("div.view_top_area.clfx");
                String title = titleElement.select("h1").text();


                Elements tableElements = docNext.select("div.txt_area");
                Elements contentElements = tableElements.select("table tbody tr");

                String agency = contentElements.select("td").get(0).text();

                String receive_date = contentElements.select("td").get(3).text();
                String receive_date1;
                String receive_date2;
                if (!receive_date.contains("~")) {
                    receive_date1 = receive_date;
                    receive_date2 = "9999.01.01";

                } else {
                    receive_date1 = receive_date.substring(0, receive_date.indexOf("~") - 1);
                    receive_date2 = receive_date.substring(receive_date.indexOf("~") + 2);
                }

                String program_date = contentElements.select("td").get(4).text();
                String program_date1;
                String program_date2;
                if (program_date == null) {
                    program_date1 = "9999.01.01";
                    program_date2 = "9999.01.01";
                } else {
                    program_date1 = program_date.substring(0, program_date.indexOf("~") - 1);
                    program_date2 = program_date.substring(program_date.indexOf("~") + 2);
                }

                String url = contentElements.select("td").get(7).select("a").attr("abs:href");

                Program program = Program.builder()
                        .division("공모전&대회")
                        .title(title)
                        .agency(agency)
                        .receive_date_start(receive_date1)
                        .receive_date_end(receive_date2)
                        .program_date_start(program_date1)
                        .program_date_end(program_date2)
                        .url(url).build();

                programMapper.findByTitle(program.getTitle())
                        .orElseGet(
                                () -> programMapper.insert(program)
                        );

            }
        }


    }

    public void Volunteer() throws IOException {


        String initUrl = "http://www.1365.go.kr/vols/1572247904127/partcptn/timeCptn.do?jsonUrl=%2Fvols%2FP9210%2Fmber%2FvolsMberJson.do&searchFlag=search&requstSe=&reqConfirm=" +
                "&firstSearch=&hopea1=&hopea2=&flag=A01&listType=&titleNm=&searchHopeArea1=6480000&searchHopeArea2=5310000&searchHopeSrvc1=&searchHopeSrvc2=&searchSrvcTarget=" +
                "&searchSrvcStts=3&adultPosblAt=Y&searchKeyword=&searchNanmmbyNm=&searchRequstSe=on";
        //크롤링 하는 날짜 기준으로 3달 범위까지 확인
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String first = df.format(cal.getTime());
        cal.add(Calendar.MONTH, 3);
        String second = df.format(cal.getTime());


        for (int i = 1; i < 6; i++) {


            String nextUrl = initUrl + "&cPage=" + i + "&searchProgrmBgnde=" + first + "&searchProgrmEndde=" + second;

            Document doc = Jsoup.connect(nextUrl).get();
            Elements elements = doc.select("div.board_list.board_list2.non_sub");
            Elements el = elements.select("li");


            if (elements.select("ul li div.null").text().equals("입력하신 검색어에 대한 조회결과가 없습니다"))
                break;

            for (Element e : el) {


                String title = e.select("dt.tit_board_list").text();

                if (e.select("div.end").text().equals("모집완료")) {
                    programMapper.findByTitle(title).ifPresent(
                            programMapper::deleteByIdx
                    );
                    continue;
                }


                String agency = e.select("dd.board_data.normal").select("dl").get(0).select("dd").text();

                String receive_date = e.select("dd.board_data.normal").select("dl").get(1).select("dd").text();
                String receive_date1 = receive_date.substring(0, receive_date.indexOf("~") - 1);
                String receive_date2 = receive_date.substring(receive_date.indexOf("~") + 2);

                String program_date = e.select("dd.board_data.normal").select("dl").get(2).select("dd").text();
                String program_date1 = program_date.substring(0, program_date.indexOf("~") - 1);
                String program_date2 = program_date.substring(program_date.indexOf("~") + 2);


                String url = "https://www.1365.go.kr/vols/1572247904127/partcptn/timeCptn.do?type=show&progrmRegistNo=" + e.select("input[type=hidden]").attr("value");


                Program program = Program.builder()
                        .division("봉사활동")
                        .title(title)
                        .agency(agency)
                        .receive_date_start(receive_date1)
                        .receive_date_end(receive_date2)
                        .program_date_start(program_date1)
                        .program_date_end(program_date2)
                        .url(url).build();

                programMapper.findByTitle(program.getTitle())
                        .orElseGet(
                                () -> programMapper.insert(program)
                        );

            }


        }


    }
}






