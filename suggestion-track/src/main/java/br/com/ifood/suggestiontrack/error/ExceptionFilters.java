package br.com.ifood.suggestiontrack.error;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExceptionFilters {

    private String title;
    private int status;
    private String details;
    private Long timestamp;
    private String devMsg;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ExceptionFiltersBuilder {

        private String title;
        private int status;
        private String details;
        private Long timestamp;
        private String devMsg;

        public static ExceptionFiltersBuilder newBuilder() {
            return new ExceptionFiltersBuilder();
        }

        public ExceptionFiltersBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ExceptionFiltersBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ExceptionFiltersBuilder details(String details) {
            this.details = details;
            return this;
        }

        public ExceptionFiltersBuilder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ExceptionFiltersBuilder devMsg(String devMsg) {
            this.devMsg = devMsg;
            return this;
        }

        public ExceptionFilters build() {
            ExceptionFilters exceptionFilters = new ExceptionFilters();
            exceptionFilters.status = this.status;
            exceptionFilters.devMsg = this.devMsg;
            exceptionFilters.timestamp = this.timestamp;
            exceptionFilters.title = this.title;
            exceptionFilters.details = this.details;
            return exceptionFilters;
        }
    }
}
