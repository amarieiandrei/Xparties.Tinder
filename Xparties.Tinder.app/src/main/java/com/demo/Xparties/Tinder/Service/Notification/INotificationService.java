package com.demo.Xparties.Tinder.Service.Notification;

import com.demo.Xparties.Tinder.Model.Entity.Person;

public interface INotificationService {

    public void notifyMatch(Person person, Person targetPerson);
}
