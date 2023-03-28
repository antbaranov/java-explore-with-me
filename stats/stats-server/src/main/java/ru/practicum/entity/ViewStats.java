package ru.practicum.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewStats {

    private String app;
    private String uri;
    private Long hits;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewStats)) return false;
        return uri != null && (uri.equals(((ViewStats) o).getUri()));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uri);
    }
}
