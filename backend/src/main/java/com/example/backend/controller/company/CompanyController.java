package com.example.backend.controller.company;

import com.example.backend.domain.CompanyList;
import com.example.backend.domain.CompareCompany;
import com.example.backend.response.ListResult;
import com.example.backend.response.SingleResult;
import com.example.backend.service.company.CompanyService;
import com.example.backend.service.response.ResponseService;
import com.example.backend.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"7. Company"})
@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CompanyController {

    private final CompanyService companyService;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "회사 조회", notes = "회사 idx와 이름 리스트를 조회한다. 만약 목표회사가 회사 리스트에 있다면 첫번째에 목표회사를 둔다.")
    @GetMapping(value = "/companyList")
    public ListResult<CompanyList> showCompanyList() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);


        return responseService.getListResultMsg(companyService.showCompanyList(user_idx), "회사 리스트를 조회하였습니다.");
    }

    @ApiOperation(value = "회사 조회", notes = "회사 idx와 이름 리스트를 조회한다. 만약 목표회사가 회사 리스트에 있다면 첫번째에 목표회사를 둔다.")
    @GetMapping(value = "/company")
    public SingleResult<CompareCompany> showCompany(@ApiParam(value = "회사 idx ", required = true) @RequestParam int company_idx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);


        return responseService.getSingleResult(companyService.showCompany(company_idx, user_idx));
    }




}
