package com.stubee.rollingadapter.out.persistence.review.adapter;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.rollingcore.domain.review.dto.response.ReviewInfoResponse;
import com.stubee.rollingapplication.domain.review.port.spi.QueryReviewPort;
import com.stubee.rollingcore.domain.review.dto.response.ReviewQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.stubee.rollingadapter.out.persistence.company.entity.QCompanyEntity.*;
import static com.stubee.rollingadapter.out.persistence.member.entity.QMemberEntity.*;
import static com.stubee.rollingadapter.out.persistence.review.entity.QReviewEntity.*;

@Repository
@RequiredArgsConstructor
public class QueryReviewAdapter implements QueryReviewPort {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<ReviewInfoResponse> findById(final UUID reviewId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(infoResponseProjection())
                .from(reviewEntity)
                .innerJoin(memberEntity)
                .on(reviewEntity.memberId.eq(memberEntity.id))
                .where(reviewEntity.id.eq(reviewId))
                .fetchOne());
    }

    @Override
    public List<ReviewInfoResponse> findByCompanyId(final UUID companyId) {
        return jpaQueryFactory
                .select(infoResponseProjection())
                .from(reviewEntity)
                .innerJoin(memberEntity)
                .on(reviewEntity.memberId.eq(memberEntity.id))
                .where(reviewEntity.companyId.eq(companyId))
                .fetch();
    }

    @Override
    public List<ReviewQueryResponse> findByMemberId(final UUID memberId) {
        return jpaQueryFactory
                .select(queryResponseProjection())
                .from(reviewEntity)
                .innerJoin(companyEntity)
                .on(reviewEntity.companyId.eq(companyEntity.id))
                .where(companyEntity.registrantId.eq(memberId))
                .fetch();
    }

    private ConstructorExpression<ReviewInfoResponse> infoResponseProjection() {
        return Projections.constructor(
                ReviewInfoResponse.class,
                reviewEntity.id,
                reviewEntity.content,
                reviewEntity.position,
                reviewEntity.careerPath,
                reviewEntity.totalGrade,
                reviewEntity.balanceGrade,
                reviewEntity.salaryGrade,
                reviewEntity.welfareGrade,
                reviewEntity.createdAt,
                reviewEntity.modifiedAt,

                memberEntity.id,
                memberEntity.nickName,
                memberEntity.socialId,
                memberEntity.imageUrl);
    }

    private ConstructorExpression<ReviewQueryResponse> queryResponseProjection() {
        return Projections.constructor(
                ReviewQueryResponse.class,
                reviewEntity.id,
                reviewEntity.content,
                reviewEntity.position,
                reviewEntity.careerPath,
                reviewEntity.totalGrade,
                reviewEntity.balanceGrade,
                reviewEntity.salaryGrade,
                reviewEntity.welfareGrade,
                reviewEntity.createdAt,
                reviewEntity.modifiedAt,

                companyEntity.id,
                companyEntity.name,
                companyEntity.imgUrl);
    }

}