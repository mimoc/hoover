package com.example.hoover.validation;

import com.example.hoover.request.HooverRequest;
import com.example.hoover.util.Const;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Qualifier("syntaxValidation")
@Component
public class SyntaxValidation implements Validate{

    Predicate<Integer[]> isPositiveCoordinates = p->p[0]>=0 && p[1]>=0;
    Predicate<Integer[][]> isPositiveCoordinatesMatrix = p -> !Arrays.asList(p).stream().anyMatch(f -> isPositiveCoordinates.negate().test(f));
    Predicate<String> isCoordinatesValid = p -> !p.chars().mapToObj(c->(char)c).collect(Collectors.toSet()).stream().anyMatch(c-> !"NSWE".contains(String.valueOf(c))
    );
    @Override
    public void validate(HooverRequest hooverRequest){
        emptyErrorList();
        if (isPositiveCoordinates.negate().test(hooverRequest.getRoomSize())) addError(Const.NEGATIVE_VALUE_FOR_ROOM_SIZE);
        if (isPositiveCoordinates.negate().test(hooverRequest.getVacuumCoordinates())) addError(Const.NEGATIVE_VALUE_FOR_VACUUM);
        if ( isPositiveCoordinatesMatrix.negate().test(hooverRequest.getSpotsCoordinates())) addError(Const.NEGATIVE_VALUE_FOR_SPOTS);
        if (isCoordinatesValid.negate().test(hooverRequest.getInstructions())) addError(Const.COORDINATES_NOT_VALID);
    }
}
