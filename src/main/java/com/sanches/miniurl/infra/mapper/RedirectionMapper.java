package com.sanches.miniurl.infra.mapper;

import com.sanches.miniurl.domain.model.Redirection;
import com.sanches.miniurl.infra.entity.RedirectionEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RedirectionMapper {
    public static Redirection toRedirection(RedirectionEntity redirection) {
        return new Redirection(redirection.getOrigin(), redirection.getTarget());
    }

    public static RedirectionEntity toRedirectionEntity(Redirection redirection) {
        return new RedirectionEntity(redirection.getOrigin(), redirection.getTarget());
    }
}
