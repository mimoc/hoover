package com.example.hoover.validation;

import com.example.hoover.request.HooverRequest;
import com.example.hoover.util.Const;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Predicate;
@Qualifier("semanticValidation")
@Component
public class SemanticValidation implements Validate{

    Predicate<Integer[]> isCoordinatesWithinBoundaries(Integer[] coordinates){
        return p->coordinates[0]>=p[0] && coordinates[1]>=p[1];
    }
    Predicate<Integer[][]> isPatchesCoordinatesWithinBoundaries(Integer[] coordinates){
        return p-> !Arrays.asList(p).stream().filter( f->isCoordinatesWithinBoundaries(coordinates).negate().test(f)).findFirst().isPresent();
    }

    @Override
    public void validate(HooverRequest hooverRequest) {
        // do not perform semantic validation if there are syntax errors
        if(!validationErrorList.isEmpty()) return;
        if (isCoordinatesWithinBoundaries(hooverRequest.getRoomSize()).negate().test(hooverRequest.getVacuumCoordinates())) addError(Const.VACUUM_COORDINATES_NOT_VALID);
        if (isPatchesCoordinatesWithinBoundaries(hooverRequest.getRoomSize()).negate().test(hooverRequest.getSpotsCoordinates())) addError(Const.SPOTS_COORDINATES_NOT_VALID);
    }
}
