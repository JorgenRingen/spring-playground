package org.springplayground.swagger;

import springfox.documentation.service.Tag;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SwaggerTagsTest {

    @Test
    void noTags() {
        final SwaggerTags swaggerTags = SwaggerTags.builder()
                .build();

        assertThat(swaggerTags.first()).isNotPresent();
        assertThat(swaggerTags.remaining()).isNotPresent();
    }

    @Test
    void oneTag() {
        final SwaggerTags swaggerTags = SwaggerTags.builder()
                .tag(new Tag("message", "bar"))
                .build();

        assertThat(swaggerTags.first()).isPresent();
        assertThat(swaggerTags.remaining()).isNotPresent();
    }

    @Test
    void multipleTags() {
        final SwaggerTags swaggerTags = SwaggerTags.builder()
                .tag(new Tag("1", "1"))
                .tag(new Tag("2", "2"))
                .tag(new Tag("3", "3"))
                .tag(new Tag("4", "4"))
                .build();

        assertThat(swaggerTags.first()).isPresent();
        assertThat(swaggerTags.remaining()).isPresent();
        assertThat(swaggerTags.remaining().get().length).isEqualTo(3);
    }
}