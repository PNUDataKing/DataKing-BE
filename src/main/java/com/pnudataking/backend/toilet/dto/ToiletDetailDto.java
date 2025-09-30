package com.pnudataking.backend.toilet.dto;

import java.util.ArrayList;
import java.util.List;

import com.pnudataking.backend.toilet.entity.Toilet;

public record ToiletDetailDto(
        Long id,
        String name,
        String address,
        String location,
        String tel,
        Double lat,
        Double lng,

        Integer maleChildrenToiletCount,
        Integer femaleChildrenToiletCount,

        List<ToiletType> diaperTableLocationList
) {
    public static ToiletDetailDto from(Toilet toilet) {
        List<ToiletType> types = new ArrayList<ToiletType>();
        if (toilet.getDiaperLocationFemale()) {
            types.add(ToiletType.FEMALE);
        }
        if (toilet.getDiaperLocationMale()) {
            types.add(ToiletType.MALE);
        }
        if (toilet.getDiaperLocationAccessible()) {
            types.add(ToiletType.ACCESSIBLE);
        }

        return new ToiletDetailDto(
                toilet.getId(),
                toilet.getName(),
                toilet.getAddress(),
                toilet.getLocation(),
                toilet.getTel(),
                toilet.getLat(),
                toilet.getLng(),
                toilet.getMaleChildrenToiletCount(),
                toilet.getFemaleChildrenToiletCount(),
                types
        );
    }

    private enum ToiletType {
        MALE, FEMALE, ACCESSIBLE
    }
}
