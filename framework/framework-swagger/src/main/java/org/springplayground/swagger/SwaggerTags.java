package org.springplayground.swagger;

import springfox.documentation.service.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SwaggerTags {

    private final List<Tag> tags;

    private SwaggerTags(final List<Tag> tags) {
        this.tags = tags;
    }

    Optional<Tag> first() {
        if (!tags.isEmpty()) {
            return Optional.of(tags.get(0));
        } else {
            return Optional.empty();
        }
    }

    Optional<Tag[]> remaining() {
        if (tags.size() > 1) {
            final List<Tag> remainingTags = this.tags.subList(1, this.tags.size());
            return Optional.of(remainingTags.toArray(new Tag[0]));
        } else {
            return Optional.empty();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private final List<Tag> tags = new ArrayList<>();

        private Builder() {
        }

        public Builder tag(final Tag tag) {
            this.tags.add(tag);
            return this;
        }

        public SwaggerTags build() {
            return new SwaggerTags(tags);
        }
    }
}
