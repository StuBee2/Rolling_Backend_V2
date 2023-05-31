package com.stubee.rollingapplication.domain.member.service;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.member.port.api.CommandMemberUseCase;
import com.stubee.rollingapplication.domain.member.port.spi.CommandMemberPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.member.dto.command.UpdateNickNameCommand;
import com.stubee.rollingcore.domain.member.model.Member;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class CommandMemberService implements CommandMemberUseCase {

    private final MemberSecurityPort securityPort;
    private final CommandMemberPort commandMemberPort;

    @Override
    public void updateNickName(final UpdateNickNameCommand command) {
        commandMemberPort.save(toDomain(securityPort.getCurrentMember(), command.nickName()));
    }

    private Member toDomain(final Member member, final String nickName) {
        return Member.builder()
                .id(member.id())
                .name(member.name())
                .nickName(nickName)
                .socialId(member.socialId())
                .email(member.email())
                .imageUrl(member.imageUrl())
                .loginType(member.loginType())
                .memberRole(member.memberRole())
                .createdAt(member.createdAt())
                .build();
    }

}