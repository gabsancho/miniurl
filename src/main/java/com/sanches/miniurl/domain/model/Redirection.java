package com.sanches.miniurl.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Redirection {
    private String origin;
    private String target;

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Redirection that)) return false;

        return origin.equals(that.origin);
    }

    @Override
    public int hashCode() {
        return origin.hashCode();
    }
}
