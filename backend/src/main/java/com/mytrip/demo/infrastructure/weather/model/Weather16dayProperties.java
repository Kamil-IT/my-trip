package com.mytrip.demo.infrastructure.weather.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather16dayProperties {
    Integer moonrise_ts;
    String wind_cdir;
    Integer rh;
    Double pres;
    Double high_temp;
    Integer sunset_ts;
    Double ozone;
    Double moon_phase;
    Double wind_gust_spd;
    Integer snow_depth;
    Integer clouds;
    Integer ts;
    Integer sunrise_ts;
    Double app_min_temp;
    Double wind_spd;
    Integer pop;
    String wind_cdir_full;
    Double moon_phase_lunation;
    Double slp;
    Double app_max_temp;
    String valid_date;
    Double vis;
    Integer snow;
    Double dewpt;
    Double uv;
    Weather16dayDescription weather;
    Integer wind_dir;
    Object max_dhi;
    Integer clouds_hi;
    Double precip;
    Double low_temp;
    Double max_temp;
    Integer moonset_ts;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate datetime;
    Double temp;
    Double min_temp;
    Integer clouds_mid;
    Integer clouds_low;
}
