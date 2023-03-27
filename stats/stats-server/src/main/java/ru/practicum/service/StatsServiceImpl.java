package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.EndpointHitRequestDto;
import ru.practicum.dto.EndpointHitResponseDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.entity.EndpointHit;
import ru.practicum.mapper.StatsMapper;
import ru.practicum.repository.StatsRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;
    private final StatsMapper statsMapper;

    @Override
    public EndpointHitResponseDto createHit(EndpointHitRequestDto endpointHitRequestDto) {
        EndpointHit endpointHit = statsMapper.toEndpointHit(endpointHitRequestDto);

        return statsMapper.toEndpointHitResponseDto(statsRepository.save(endpointHit));
    }

    @Override
    public List<ViewStatsDto> getStats(Timestamp start, Timestamp end, List<String> uris, boolean unique) {
        if (Boolean.TRUE.equals(unique)) {
            statsRepository.findStatsByDatesUniqueIp(start, end, uris);
        }
        return statsMapper.toListViewStatsResponseDto(statsRepository.findStatsByDates(start, end, uris));
    }
}