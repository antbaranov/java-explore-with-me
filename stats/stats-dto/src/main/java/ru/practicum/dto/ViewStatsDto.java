package ru.practicum.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewStatsDto {

    private String app;
    private String uri;
    private Long hits;

    @JsonIgnore
    public Long getIdFromUri() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.uri);
        int lastIndex = sb.lastIndexOf("/");
        return Long.parseLong(sb.substring(lastIndex + 1));
    }
}
