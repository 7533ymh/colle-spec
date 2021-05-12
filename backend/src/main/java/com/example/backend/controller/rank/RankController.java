package com.example.backend.controller.rank;

import com.example.backend.response.CommonResult;
import com.example.backend.service.rank.RankService;
import com.example.backend.service.response.ResponseService;
import com.example.backend.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"5. Rank"})
@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RankController {

    private final RankService rankService;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "랭크 업데이트 ", notes = "랭크정보를 데이터베이스에 업데이트한다.")
    @GetMapping("/rank")
    public CommonResult rankUpdate() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int idx = userService.findIdxById(AuthId);

        rankService.changetoRank(idx);


        return responseService.getSuccessResultMsg("랭크정보가 업데이트되었습니다.");

    }






}
