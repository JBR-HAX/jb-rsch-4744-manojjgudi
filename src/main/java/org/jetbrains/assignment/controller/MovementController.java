package org.jetbrains.assignment.controller;

import org.jetbrains.assignment.dto.*;
import org.jetbrains.assignment.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovementController {
    @Autowired
    private MovementService movementService;

    @PostMapping("/locations")
    public ResponseEntity<List<LocationDto>> processMovements(@RequestBody List<MoveDto> movementList) {
        List<LocationDto> locations = movementService.processMovements(movementList);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @PostMapping("/moves")
    public ResponseEntity<List<MoveDto>> generateMovements(@RequestBody List<LocationDto> locations) {
        List<MoveDto> movements = movementService.generateMovements(locations);
        return new ResponseEntity<>(movements, HttpStatus.OK);
    }
}