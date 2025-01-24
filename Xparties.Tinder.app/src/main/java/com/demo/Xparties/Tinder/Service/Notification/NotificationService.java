package com.demo.Xparties.Tinder.Service.Notification;

import com.demo.Xparties.Tinder.Model.Entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationService implements INotificationService {

    @Override
    public void notifyMatch(Person person, Person targetPerson) {
        // TODO: Implement this feature somehow :)
        System.out.println("It's a match between " + person.getFirstName() + " and " + targetPerson.getFirstName() + "!");
    }
}
