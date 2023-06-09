package com.stubee.rollingcore.domain.member.dto.response;

import com.stubee.rollingcore.domain.company.model.Company;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollingcore.domain.review.dto.response.ReviewQueryResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record MemberInfoResponse(
        Member member,
        List<Company> companyList,
        List<ReviewQueryResponse> reviewList) {}