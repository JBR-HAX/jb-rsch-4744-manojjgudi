package org.jetbrains.assignment.service;

import org.jetbrains.assignment.dto.LocationDto;
import org.jetbrains.assignment.dto.MoveDto;
import org.jetbrains.assignment.dto.MovementLog;
import org.jetbrains.assignment.repository.MovementLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovementService {
    @Autowired
    private MovementLogRepository movementLogRepository;

    public List<LocationDto> processMovements(List<MoveDto> movementList) {
        int x = 0, y = 0;
        List<LocationDto> locations = new ArrayList<>();
        locations.add(new LocationDto(x, y));

        for (MoveDto move : movementList) {
            switch (move.getDirection()) {
                case "NORTH":
                    y += move.getSteps();
                    break;
                case "SOUTH":
                    y -= move.getSteps();
                    break;
                case "EAST":
                    x += move.getSteps();
                    break;
                case "WEST":
                    x -= move.getSteps();
                    break;
            }
            locations.add(new LocationDto(x, y));
        }

        String inputData = movementList.toString();
        String outputData = locations.toString();
        MovementLog log = new MovementLog(null, inputData, outputData);
        movementLogRepository.save(log);

        return locations;
    }

    public List<MoveDto> generateMovements(List<LocationDto> locations) {
        List<MoveDto> movements = new ArrayList<>();
        int x = locations.get(0).getX();
        int y = locations.get(0).getY();

        for (int i = 1; i < locations.size(); i++) {
            int dx = locations.get(i).getX() - x;
            int dy = locations.get(i).getY() - y;

            if (dx > 0) {
                movements.add(new MoveDto("EAST", dx));
            } else if (dx < 0) {
                movements.add(new MoveDto("WEST", -dx));
            }

            if (dy > 0) {
                movements.add(new MoveDto("NORTH", dy));
            } else if (dy < 0) {
                movements.add(new MoveDto("SOUTH", -dy));
            }

            x = locations.get(i).getX();
            y = locations.get(i).getY();
        }

        String inputData = locations.toString();
        String outputData = movements.toString();
        MovementLog log = new MovementLog(null, inputData, outputData);
        movementLogRepository.save(log);

        return movements;
    }
}