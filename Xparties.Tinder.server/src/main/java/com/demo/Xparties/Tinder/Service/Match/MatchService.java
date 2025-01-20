package com.demo.Xparties.Tinder.Service.Match;

import com.demo.Xparties.Tinder.Converter.MatchConverter;
import com.demo.Xparties.Tinder.Dto.MatchDto.MatchRequestDto;
import com.demo.Xparties.Tinder.Dto.MatchDto.MatchResponseDto;
import com.demo.Xparties.Tinder.Exception.EventException.EventNotFound;
import com.demo.Xparties.Tinder.Exception.MatchException.MatchAlreadyExists;
import com.demo.Xparties.Tinder.Exception.MatchException.MatchNotCreated;
import com.demo.Xparties.Tinder.Exception.PersonException.PersonNotFound;
import com.demo.Xparties.Tinder.Model.Entity.Event;
import com.demo.Xparties.Tinder.Model.Entity.Match;
import com.demo.Xparties.Tinder.Model.Entity.Person;
import com.demo.Xparties.Tinder.Repository.EventRepository;
import com.demo.Xparties.Tinder.Repository.MatchRepository;
import com.demo.Xparties.Tinder.Repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MatchService implements IMatchService {

    private final MatchRepository matchRepository;
    private final MatchConverter matchConverter;
    private final PersonRepository personRepository;
    private final EventRepository eventRepository;

    @Override
    public MatchResponseDto createMatch(MatchRequestDto matchRequestDto) {
        try {

            Match match = matchConverter.fromRequestDtoToEntity(matchRequestDto);

            Person person = personRepository.findById(match.getPerson().getId())
                    .orElseThrow(() -> new PersonNotFound("person with id " + match.getPerson().getId() + " could not be found."));

            Person targetPerson = personRepository.findById(match.getTargetPerson().getId())
                    .orElseThrow(() -> new PersonNotFound("target person with id " + match.getTargetPerson().getId() + " could not be found."));

            Event event = eventRepository.findById(match.getEvent().getId())
                    .orElseThrow(() -> new EventNotFound("event with id " + match.getEvent().getId() + " could not be found."));

            Optional<Match> existingMatch = matchRepository.findByPersonAndTargetPersonAndEvent(person, targetPerson, event);

            if (existingMatch.isPresent()) {
                throw new MatchAlreadyExists("match already exists.");
            }

            Match newMatch = new Match();
            newMatch.setPerson(person);
            newMatch.setTargetPerson(targetPerson);
            newMatch.setEvent(event);
            newMatch.setSmash(match.getSmash());

            return matchConverter.fromEntityToResponseDto(matchRepository.save(newMatch));

        } catch (Exception e) {
            throw new MatchNotCreated("match could not be created.");
        }

        // TODO: Check for mutual smash between male and female, investigate below code.
    }
}


// TODO: Investigation below

//@Service
//public class MatchService {
//
//    @Autowired
//    private MatchDecisionRepository matchDecisionRepository;
//
//    /**
//     * Save a smash or pass decision.
//     */
//    public MatchDecision makeDecision(Person person, Person targetPerson, Event event, boolean smash) {
//        // Check if the decision already exists
//        Optional<MatchDecision> existingDecision = matchDecisionRepository
//                .findByPersonAndTargetPersonAndEvent(person, targetPerson, event);
//
//        if (existingDecision.isPresent()) {
//            throw new IllegalArgumentException("You already made a decision for this person.");
//        }
//
//        // Save the new decision
//        MatchDecision decision = new MatchDecision();
//        decision.setPerson(person);
//        decision.setTargetPerson(targetPerson);
//        decision.setEvent(event);
//        decision.setSmash(smash);
//
//        MatchDecision savedDecision = matchDecisionRepository.save(decision);
//
//        // Check for mutual smash
//        if (smash && checkMutualSmash(person, targetPerson, event)) {
//            // Logic to notify or connect both people can go here
//            System.out.println("It's a match between " + person.getName() + " and " + targetPerson.getName() + "!");
//        }
//
//        return savedDecision;
//    }
//
//    /**
//     * Check if there is a mutual smash.
//     */
//    private boolean checkMutualSmash(Person person, Person targetPerson, Event event) {
//        Optional<MatchDecision> targetDecision = matchDecisionRepository
//                .findByPersonAndTargetPersonAndEvent(targetPerson, person, event);
//
//        return targetDecision.isPresent() && targetDecision.get().isSmash();
//    }
//}