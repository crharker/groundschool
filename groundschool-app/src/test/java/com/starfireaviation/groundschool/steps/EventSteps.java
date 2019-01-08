/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.steps;

// TODO import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

/**
 * EventSteps
 *
 * @author brianmichael
 */
public class EventSteps extends BaseSteps {

    /**
     * Given an event exists
     *
     * @throws Exception when things go wrong
     */
    @Given("^an event exists$")
    public void anEventExists() throws Exception {
        // Do something
        //throw new PendingException();
    }

    /**
     * Given an event does not exist
     *
     * @throws Exception when things go wrong
     */
    @Given("^an event does not exist$")
    public void anEventDoesNotExists() throws Exception {
        // Do something
    }

    /**
     * Given a future event exists
     *
     * @throws Exception when things go wrong
     */
    @Given("^a future event exists$")
    public void aFutureEventExists() throws Exception {
        // Do something
    }

    /**
     * When I list all events
     *
     * @throws Exception when things go wrong
     */
    @When("^I list all events$")
    public void iListAllEvents() throws Exception {
        // TODO events = eventController.list();
    }

    /**
     * When I list all future events
     *
     * @throws Exception when things go wrong
     */
    @When("^I list all future events$")
    public void iListAllFutureEvents() throws Exception {
        // TODO events = eventController.listFuture();
    }

    /**
     * When I retrieve an event
     *
     * @throws Exception when things go wrong
     */
    @When("^I retrieve an event$")
    public void iRetrieveAnEvent() throws Exception {
        // TODO event = eventController.get(id);
    }

    /**
     * When I update an event
     *
     * @throws Exception when things go wrong
     */
    @When("^I update an event$")
    public void iUpdateAnEvent() throws Exception {
        // TODO event = eventController.put(event);
    }

    /**
     * When I cancel an event
     *
     * @throws Exception when things go wrong
     */
    @When("^I cancel an event$")
    public void iCancelAnEvent() throws Exception {
        // TODO event = eventController.delete(id);
    }

    /**
     * Then I should receive a list of events
     *
     * @throws Exception when things go wrong
     */
    @Then("^I should receive a list of events$")
    public void receiveAListOfEvents() throws Exception {
        // TODO Assert.assertNotNull("Events list is null", events);
        // TODO Assert.assertTrue("No events found in list", events.size() > 0);
    }

    /**
     * Then I should receive a list of event summaries
     *
     * @throws Exception when things go wrong
     */
    @Then("^I should receive a list of event summaries$")
    public void receiveAListOfEventSummaries() throws Exception {
        // Do something
        //throw new PendingException();
    }

    /**
     * Then I should receive the event
     *
     * @throws Exception when things go wrong
     */
    @Then("^I should receive the event$")
    public void receiveTheEvent() throws Exception {
        // TODO Assert.assertNotNull("Event is null", event);
    }

    /**
     * Then I should receive the event summary
     *
     * @throws Exception when things go wrong
     */
    @Then("^I should receive the event summary$")
    public void receiveTheEventSummary() throws Exception {
        // Do something
        //throw new PendingException();
    }

    /**
     * Then The event should be updated
     *
     * @throws Exception when things go wrong
     */
    @Then("^The event should be updated$")
    public void theEventShouldBeUpdated() throws Exception {
        // Do something
        //throw new PendingException();
    }

    /**
     * Then I should receive an event not found error
     *
     * @throws Exception when things go wrong
     */
    @Then("^I should receive an event not found error$")
    public void iReceiveAnEventNotFoundError() throws Exception {
        // TODO Assert.assertNotNull("Exception is null", exception);
    }

    /**
     * Then The event should be cancelled
     *
     * @throws Exception when things go wrong
     */
    @Then("^The event should be cancelled$")
    public void theEventShouldBeCancelled() throws Exception {
        // Do something
        //throw new PendingException();
    }

}
